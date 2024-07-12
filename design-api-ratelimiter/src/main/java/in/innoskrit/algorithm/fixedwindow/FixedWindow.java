package in.innoskrit.algorithm.fixedwindow;

public class FixedWindow {
    private final int capacity;
    private final long windowSize;
    private int requests;
    private long windowStartTimestamp;

    public FixedWindow(int capacity, long windowSize) {
        this.capacity = capacity;
        this.windowSize = windowSize;
        this.requests = 0;
        this.windowStartTimestamp = System.currentTimeMillis();
    }

    public int getCapacity() {
        return capacity;
    }
    public long getWindowSize() {
        return windowSize;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public long getWindowStartTimestamp() {
        return windowStartTimestamp;
    }

    public void setWindowStartTimestamp(long windowStartTimestamp) {
        this.windowStartTimestamp = windowStartTimestamp;
    }

    @Override
    public String toString() {
        return "FixedWindow{" +
                "windowSize=" + windowSize +
                ", requests=" + requests +
                ", windowStartTimestamp=" + windowStartTimestamp +
                '}';
    }
}
