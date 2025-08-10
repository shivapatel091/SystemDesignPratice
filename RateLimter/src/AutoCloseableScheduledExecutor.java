import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AutoCloseableScheduledExecutor implements AutoCloseable {

    private final ScheduledExecutorService scheduledExecutorService;

    public AutoCloseableScheduledExecutor(int threadPoolSize) {
        this.scheduledExecutorService = Executors.newScheduledThreadPool(threadPoolSize);
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    @Override
    public void close() {
        scheduledExecutorService.shutdown();
        try {
            if(!scheduledExecutorService.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduledExecutorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduledExecutorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

}
