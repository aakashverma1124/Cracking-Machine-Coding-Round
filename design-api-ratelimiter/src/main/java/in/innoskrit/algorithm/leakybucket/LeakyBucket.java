package in.innoskrit.algorithm.leakybucket;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
@Setter
public class LeakyBucket {
    private final int capacity;
    private final long leakInterval;
    private Queue<Long> requestQueue;
    private long lastLeakTimestamp;

    public LeakyBucket(int capacity, long leakInterval) {
        this.capacity = capacity;
        this.leakInterval = leakInterval;
        this.requestQueue = new LinkedList<>();
        this.lastLeakTimestamp = System.currentTimeMillis();
    }
}
