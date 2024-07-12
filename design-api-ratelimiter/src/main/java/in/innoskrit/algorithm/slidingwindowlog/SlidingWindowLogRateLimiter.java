package in.innoskrit.algorithm.slidingwindowlog;

import in.innoskrit.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class SlidingWindowLogRateLimiter implements RateLimiter {

    private final int capacity;
    private final long windowSize;
    private final Map<String, SlidingWindowLog> customerLog;

    public SlidingWindowLogRateLimiter(int capacity, long windowSize) {
        this.capacity = capacity;
        this.windowSize = windowSize;
        this.customerLog = new HashMap<>();
    }

    private void cleanUp(long currTime, SlidingWindowLog log) {
        while(!log.getRequestsLog().isEmpty() && (currTime - log.getRequestsLog().peek()) > log.getWindowSize()) {
            log.getRequestsLog().poll();
        }
    }

    private boolean allowRequest(SlidingWindowLog slidingWindowLog) {
        long now = System.currentTimeMillis();
        cleanUp(now, slidingWindowLog);
        if(slidingWindowLog.getRequestsLog().size() < slidingWindowLog.getCapacity()) {
            slidingWindowLog.getRequestsLog().offer(now);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean rateLimit(String customerId) {
        SlidingWindowLog slidingWindowLog = customerLog.computeIfAbsent(customerId, k -> new SlidingWindowLog(capacity, windowSize));
        return allowRequest(slidingWindowLog);
    }
}
