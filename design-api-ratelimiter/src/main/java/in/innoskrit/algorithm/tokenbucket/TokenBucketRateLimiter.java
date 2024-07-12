package in.innoskrit.algorithm.tokenbucket;

import in.innoskrit.RateLimiter;

import java.util.HashMap;
import java.util.Map;

public class TokenBucketRateLimiter implements RateLimiter {

    private final int capacity;
    private final int refillRate;
    Map<String, TokenBucket> tokens;

    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        tokens = new HashMap<>();
    }

    private void refillBucket(TokenBucket tokenBucket) {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - tokenBucket.getLastRefillTimestamp();
        if(elapsed > refillRate) {
            int tokens = (int) Math.min(tokenBucket.getCapacity(), tokenBucket.getTokens() + (int) elapsed/tokenBucket.getRefillRate());
            tokenBucket.setTokens(tokens);
            tokenBucket.setLastRefillTimestamp(currentTime);
        }
    }

    private boolean allowRequest(TokenBucket tokenBucket) {
        refillBucket(tokenBucket);
        if(tokenBucket.getTokens() > 0) {
            tokenBucket.setTokens(tokenBucket.getTokens() - 1);
            return true;
        }
        return false;
    }

    @Override
    public synchronized boolean rateLimit(String userId) {
        TokenBucket tokenBucket = tokens.computeIfAbsent(userId, k -> new TokenBucket(this.capacity, refillRate));
        return allowRequest(tokenBucket);
    }
}
