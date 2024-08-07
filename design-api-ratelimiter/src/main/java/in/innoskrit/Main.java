package in.innoskrit;

import in.innoskrit.algorithm.fixedwindow.FixedWindowRateLimiter;
import in.innoskrit.algorithm.leakybucket.LeakyBucketRateLimiter;
import in.innoskrit.algorithm.slidingwindowlog.SlidingWindowLogRateLimiter;
import in.innoskrit.algorithm.tokenbucket.TokenBucketRateLimiter;

import java.util.Map;

public class Main {

    /**
     * This method tests the code against main thread only.
     * Here, user1 will not be able to make more than 5 requests.
     */
    public static void testTokenBucketRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = new TokenBucketRateLimiter(5, 1000);
        String customerId = "user1";
        for(int i = 0; i < 5; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
        }

        System.out.println(Thread.currentThread().getName() + " Last Request: " + rateLimiter.rateLimit(customerId));

        Thread.sleep(4000);

        for(int i = 0; i < 5; i++) {
            boolean allowed = rateLimiter.rateLimit(customerId);
            System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
        }

        System.out.println(Thread.currentThread().getName() + " Last Request: " + rateLimiter.rateLimit(customerId));

    }

    /**
     * This method tests the code against multiple threads.
     */
    public static void testTokenBucketRateLimiterUsingMultipleThreads() {
        RateLimiter rateLimiter = new TokenBucketRateLimiter(5, 1000); // Capacity: 5, Refill Interval: 1 second
        String customerId = "user1";
        Runnable makeRequests = () -> {
            for (int i = 0; i < 5; i++) {
                boolean allowed = rateLimiter.rateLimit(customerId);
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
                try {
                    Thread.sleep(200); // Simulate time between requests
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1 = new Thread(makeRequests, "Thread 1");
        Thread thread2 = new Thread(makeRequests, "Thread 2");

        thread1.start();
        thread2.start();
    }

    public static void testLeakyBucketRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = new LeakyBucketRateLimiter(5, 200);
        for(int i = 0; i < 6; i++) {
            boolean allowed = rateLimiter.rateLimit("user1");
            System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
            Thread.sleep(10);
        }
    }

    public static void testLeakyBucketRateLimiterUsingTwoThreads() {
        RateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 200);
        Runnable makeRequests = () -> {
            for(int i = 0; i < 10; i++) {
                boolean allowed = rateLimiter.rateLimit("user1");
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1 = new Thread(makeRequests);
        Thread thread2 = new Thread(makeRequests);

        thread1.start();
        thread2.start();
    }

    public static void testFixedWindowRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = new FixedWindowRateLimiter(5, 1000);
        for(int i = 0; i < 6; i++) {
            boolean allowed = rateLimiter.rateLimit("user1");
            System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
            Thread.sleep(100);
        }
    }

    public static void testFixedWindowRateLimiterUsingTwoThreads() {
        RateLimiter rateLimiter = new FixedWindowRateLimiter(5, 1000);
        Runnable makeRequests = () -> {
            for(int i = 0; i < 6; i++) {
                boolean allowed = rateLimiter.rateLimit("user1");
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1 = new Thread(makeRequests);
        Thread thread2 = new Thread(makeRequests);

        thread1.start();
        thread2.start();
    }

    public static void testSlidingWindowLogRateLimiter() throws InterruptedException {
        RateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 1000);
        for(int i = 0; i < 6; i++) {
            boolean allowed = rateLimiter.rateLimit("user1");
            System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
            Thread.sleep(10);
        }
    }

    public static void testSlidingWindowLogRateLimiterUsingTwoThreads() {
        RateLimiter rateLimiter = new SlidingWindowLogRateLimiter(5, 1000);
        Runnable makeRequests = () -> {
            for(int i = 0; i < 6; i++) {
                boolean allowed = rateLimiter.rateLimit("user1");
                System.out.println(Thread.currentThread().getName() + " Request " + (i + 1) + ": " + allowed);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread thread1 = new Thread(makeRequests);
        Thread thread2 = new Thread(makeRequests);

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) throws InterruptedException {

//        System.out.println("Testing TokenBucketRateLimiter using main thread");
//        Main.testTokenBucketRateLimiter();
//
//        System.out.println("Testing TokenBucketRateLimiter using multiple threads");
//        Main.testTokenBucketRateLimiterUsingMultipleThreads();

//        System.out.println("Testing LeakyBucketRateLimiter using main thread");
//        testLeakyBucketRateLimiter();

        System.out.println("Testing LeakyBucketRateLimiter using multiple threads");
        testLeakyBucketRateLimiterUsingTwoThreads();
//        System.out.println("Testing FixedWindowRateLimiter using main thread");
//        Main.testFixedWindowRateLimiter();
//
//        System.out.println("Testing FixedWindowRateLimiter using two thread");
//        Main.testFixedWindowRateLimiterUsingTwoThreads();
//
//        System.out.println("Testing SlidingWindowLogRateLimiter using main thread");
//        Main.testSlidingWindowLogRateLimiter();
//
//        System.out.println("Testing SlidingWindowLogRateLimiter using two thread");
//        Main.testSlidingWindowLogRateLimiterUsingTwoThreads();

    }
}
