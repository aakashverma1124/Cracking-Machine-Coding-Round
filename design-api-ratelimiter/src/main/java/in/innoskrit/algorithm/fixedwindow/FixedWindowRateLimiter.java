package in.innoskrit.algorithm.fixedwindow;

import in.innoskrit.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int capacity;
    private final long windowSize;
    private final Map<String, FixedWindow> windows;

    public FixedWindowRateLimiter(int capacity, long windowSize) {
        this.capacity = capacity;
        this.windowSize = windowSize;
        windows = new HashMap<>();
    }

    private boolean allowRequest(FixedWindow fixedWindow) {
        long now = System.currentTimeMillis();
        long elapsed = now - fixedWindow.getWindowStartTimestamp();
        if(elapsed > fixedWindow.getWindowSize()) {
            fixedWindow.setWindowStartTimestamp(now);
            fixedWindow.setRequests(0);
        }
        if(fixedWindow.getRequests() < fixedWindow.getCapacity()) {
            fixedWindow.setRequests(fixedWindow.getRequests() + 1);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean rateLimit(String customerId) {
        FixedWindow fixedWindow = windows.computeIfAbsent(customerId, k -> new FixedWindow(capacity, windowSize));
        return allowRequest(fixedWindow);
    }
}
