package in.innoskrit;

import in.innoskrit.cache.Cache;
import in.innoskrit.cache.policy.LRUReplacementPolicy;
import in.innoskrit.cache.storage.HashMapStorage;
import in.innoskrit.cache.storage.Storage;

public class Main {
    public static void main(String[] args) {
        Storage<Integer, Integer> storage = new HashMapStorage<Integer, Integer>();
        LRUReplacementPolicy<Integer, Integer> lru = new LRUReplacementPolicy<Integer, Integer>(3, storage);
        Cache<Integer, Integer> cache = new Cache<Integer, Integer>(lru);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(3));
        cache.put(2, 20);
        System.out.println(cache.get(2));
    }
}