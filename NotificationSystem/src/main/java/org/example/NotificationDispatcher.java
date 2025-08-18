package org.example;

import org.example.beans.Notification;
import org.example.services.IdempotencyChecker;
import org.example.services.repository.NotificationRepository;
import org.example.services.retry.RetryPolicy;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotificationDispatcher {

    private final NotificationRepository repository;
    private final EmailSender sender;
    private final IdempotencyChecker  checker;
    private ExecutorService worker;
    private ScheduledExecutorService scheduler;
    private RetryPolicy retryPolicy;


    public NotificationDispatcher(NotificationRepository repository, EmailSender sender,
                                  IdempotencyChecker checker,
                                  RetryPolicy retryPolicy) {
        this.repository = repository;
        this.sender = sender;
        this.checker = checker;
        this.worker = Executors.newFixedThreadPool(8);
        this.scheduler = Executors.newScheduledThreadPool(2);
        this.retryPolicy = retryPolicy;
    }


    void handleFailure(Notification notification, Exception exception) {
        notification.markFailed(exception.getMessage()); repository.update(notification);
        if(notification.getNoOfAttempts() < retryPolicy.getMaxAttempts()) {
            long delay = retryPolicy.nextDelayInMilliseconds(notification.getNoOfAttempts());
            scheduler.schedule( () -> process(notification), delay, TimeUnit.MILLISECONDS);
        } else {
            notification.markDlq(exception.getMessage());
            repository.update(notification);
        }
    }
    void process(Notification notification) {
        try {
            notification.markInProgress(); repository.update(notification);
            sender.send(notification);
            notification.incrementNoOfAttempts();
            notification.markSent(); repository.update(notification);
        } catch (Exception e) {
            handleFailure(notification, e);
        }
    }

    void scheduleImmediate(Notification notification) {
        worker.submit(() -> {
           process(notification);
        });
    }

    public Notification sumbit(Notification notification) {
        checker.lookup(notification.getRequestId()).ifPresentOrElse(existing -> {
            throw new IllegalStateException(String.format("Notification already exists for requestId: %s", existing));
        }, () -> {
            repository.save(notification);
            scheduleImmediate(notification);
        });
        return notification;
    }


    public void after() {
        worker.shutdown();
        scheduler.shutdown();
    }


}
