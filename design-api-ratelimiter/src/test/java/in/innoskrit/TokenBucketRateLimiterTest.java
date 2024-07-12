package in.innoskrit;

import in.innoskrit.algorithm.tokenbucket.TokenBucketRateLimiter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TokenBucketRateLimiterTest {

    @Test
    void testTokenBucketRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = new TokenBucketRateLimiter(5, 1000);
        for(int i = 0; i < 2; i++) {
            Assertions.assertTrue(rateLimiter.rateLimit("user1"));
        }

        Assertions.assertTrue(rateLimiter.rateLimit("user1"));

        Thread.sleep(7000);

        for(int i = 1; i <= 5; i++) {
            Assertions.assertTrue(rateLimiter.rateLimit("user1"));
        }

        Assertions.assertFalse(rateLimiter.rateLimit("user1"));

    }
}
