package in.innoskrit.algorithm.slidingwindowlog;

import java.util.LinkedList;
import java.util.Queue;

public class SlidingWindowLog {

    private final int capacity;
    private final long windowSize;

    private Queue<Long> requestsLog;

    public SlidingWindowLog(int capacity, long windowSize) {
        this.capacity = capacity;
        this.windowSize = windowSize;
        this.requestsLog = new LinkedList<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public long getWindowSize() {
        return windowSize;
    }

    public Queue<Long> getRequestsLog() {
        return requestsLog;
    }

    public void setRequestsLog(Queue<Long> requestsLog) {
        this.requestsLog = requestsLog;
    }
}
