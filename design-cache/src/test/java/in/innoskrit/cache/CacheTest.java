package in.innoskrit.cache;

import in.innoskrit.cache.factory.CacheFactory;
import in.innoskrit.cache.policy.LRUReplacementPolicy;
import in.innoskrit.cache.storage.HashMapStorage;
import in.innoskrit.cache.storage.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheTest {
    Cache<Integer, Integer> cache;

    @BeforeEach
    public void setup() {
        cache = new CacheFactory<Integer, Integer>().getDefaultCache(3);
    }

    @Test
    public void testCachePutAndGetFunctionality() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        assertEquals(1, cache.get(1));
        assertEquals(2, cache.get(2));
        cache.put(4, 4);
        assertEquals(null, cache.get(3));
        cache.put(2, 20);
        assertEquals(20, cache.get(2));
    }
}
