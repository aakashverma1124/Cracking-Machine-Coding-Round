package in.innoskrit.algorithm.leakybucket;

import in.innoskrit.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class LeakyBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private final long leakInterval;
    Map<String, LeakyBucket> customerMap;

    public LeakyBucketRateLimiter(int capacity, long leakInterval) {
        this.capacity = capacity;
        this.leakInterval = leakInterval;
        this.customerMap = new HashMap<>();
    }

    private void leak(long currentTime, LeakyBucket leakyBucket) {
        long elapsed = currentTime - leakyBucket.getLastLeakTimestamp();
        if(elapsed > leakyBucket.getLeakInterval()) {
            int leaks = (int) (elapsed/leakyBucket.getLeakInterval());
            for(int i = 0; i < leaks; i++) {
                leakyBucket.getRequestQueue().poll();
            }
            leakyBucket.setLastLeakTimestamp(currentTime);
        }
    }
    private boolean allowRequest(LeakyBucket leakyBucket) {
        long now = System.currentTimeMillis();
        leak(now, leakyBucket);
        if(leakyBucket.getRequestQueue().size() < leakyBucket.getCapacity()) {
            leakyBucket.getRequestQueue().offer(now);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean rateLimit(String customerId) {
        LeakyBucket leakyBucket = customerMap.computeIfAbsent(customerId, k -> new LeakyBucket(capacity, leakInterval));
        return allowRequest(leakyBucket);
    }
}
