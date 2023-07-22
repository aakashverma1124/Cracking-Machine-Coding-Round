package in.innoskrit;

import in.innoskrit.cache.Cache;
import in.innoskrit.cache.factory.CacheFactory;
import in.innoskrit.cache.policy.LRUReplacementPolicy;
import in.innoskrit.cache.storage.HashMapStorage;
import in.innoskrit.cache.storage.Storage;

public class Main {
    public static void main(String[] args) {
        Cache<Integer, Integer> cache = new CacheFactory<Integer, Integer>().getDefaultCache(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.get(1);
        cache.get(2);
        cache.put(4, 4);
        cache.get(3);
        cache.put(2, 20);
        cache.get(2);
    }
}