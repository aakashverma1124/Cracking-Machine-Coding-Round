package in.innoskrit;

public interface RateLimiter {
    boolean rateLimit(String customerId);
}
