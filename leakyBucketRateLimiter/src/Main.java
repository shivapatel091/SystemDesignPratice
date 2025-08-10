//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(5, 2);
        for(int i = 0; i < 7; i++) {
            leakyBucketRateLimiter.allowRequest();
            Thread.sleep(100);
        }

        leakyBucketRateLimiter.shutdown();
    }
}