package in.innoskrit.algorithm.tokenbucket;

public class TokenBucket {
    private long lastRefillTimestamp;
    private int tokens;

    public TokenBucket(int capacity) {
        this.lastRefillTimestamp = System.currentTimeMillis();
        this.tokens = capacity;
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

    @Override
    public String toString() {
        return "TokenBucket{" +
                ", lastRefillTimestamp=" + lastRefillTimestamp +
                ", tokens=" + tokens +
                '}';
    }
}
