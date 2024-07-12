package in.innoskrit.algorithm.tokenbucket;

public class TokenBucket {
    private final int capacity;
    private final int refillRate;
    private long lastRefillTimestamp;
    private int tokens;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.tokens = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public long getLastRefillTimestamp() {
        return lastRefillTimestamp;
    }

    public void setLastRefillTimestamp(long lastRefillTimestamp) {
        this.lastRefillTimestamp = lastRefillTimestamp;
    }

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public long getRefillRate() {
        return refillRate;
    }

    @Override
    public String toString() {
        return "TokenBucket{" +
                "capacity=" + capacity +
                ", lastRefillTimestamp=" + lastRefillTimestamp +
                ", tokens=" + tokens +
                '}';
    }
}
