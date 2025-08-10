
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LeakyBucketRateLimiter {
    private final BlockingQueue<Long> bucket;
    private final int capacity;
    private final int leakyRate;
    private final ScheduledExecutorService scheduledExecutorService;

    public LeakyBucketRateLimiter(int capacity, int leakyRate) {
        this.capacity = capacity;
        this.leakyRate = leakyRate;
        this.bucket = new LinkedBlockingQueue<>(capacity);
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(this::leakTheBucket, 0, 1000 / leakyRate, TimeUnit.MILLISECONDS);
    }

    public void leakTheBucket() {
        if(!bucket.isEmpty()){
            bucket.poll();
            System.out.println("Processed a request ✅, Bucket size: " + bucket.size());
        }
    }
    public void allowRequest() {
        if(bucket.remainingCapacity() > 0) {
            boolean isOffered = bucket.offer(System.currentTimeMillis());
            System.out.println("Request added ✅, Bucket size: " + bucket.size());
        } else {
            System.out.println("Request dropped ❌, Bucket is full!");

        }
    }

    public void shutdown() {
        scheduledExecutorService.shutdownNow();
    }


}
