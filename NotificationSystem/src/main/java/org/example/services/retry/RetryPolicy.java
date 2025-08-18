package org.example.services.retry;

public class RetryPolicy {

    private int maxAttempts;
    private long baseDelayinMilliseconds;

    public RetryPolicy(int maxAttempts, long baseDelayinMilliseconds) {
        this.maxAttempts = maxAttempts;
        this.baseDelayinMilliseconds = baseDelayinMilliseconds;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public long nextDelayInMilliseconds(int attemptsSoFar) {
        long delay = baseDelayinMilliseconds * (1L << Math.max(0, attemptsSoFar - 1));
        long jitter = (long) (baseDelayinMilliseconds * Math.random());
        return delay + jitter;
    }
}
