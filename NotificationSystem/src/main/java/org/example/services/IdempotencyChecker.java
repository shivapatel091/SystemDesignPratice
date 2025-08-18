package org.example.services;

import org.example.beans.Notification;
import org.example.services.repository.NotificationRepository;

import java.util.Optional;


public class IdempotencyChecker {

    private final NotificationRepository repo;

    public IdempotencyChecker(NotificationRepository notificationRepository) {
        this.repo = notificationRepository;
    }

    public Optional<Notification> lookup(String requestId) {
        if (requestId == null) {
            return Optional.empty();
        }
        return repo.findByRequestId(requestId);
    }
}
