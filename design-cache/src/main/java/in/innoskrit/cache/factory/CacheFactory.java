package in.innoskrit.cache.factory;

import in.innoskrit.cache.Cache;
import in.innoskrit.cache.policy.LRUReplacementPolicy;
import in.innoskrit.cache.storage.HashMapStorage;

public class CacheFactory<Key, Value> {
    public Cache<Key, Value> getDefaultCache(final int capacity) {
        return new Cache<Key, Value>(new LRUReplacementPolicy<>(),
                new HashMapStorage<>(capacity));
    }
}
