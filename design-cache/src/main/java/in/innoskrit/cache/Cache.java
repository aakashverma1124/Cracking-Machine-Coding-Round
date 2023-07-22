package in.innoskrit.cache;

import in.innoskrit.cache.exception.KeyNotFoundException;
import in.innoskrit.cache.exception.StorageFullException;
import in.innoskrit.cache.policy.ReplacementPolicy;
import in.innoskrit.cache.storage.Storage;

/**
 *
 * @param <Key>
 * @param <Value>
 */
public class Cache<Key, Value> {
    private final ReplacementPolicy<Key> replacementPolicy;
    private final Storage<Key, Value> storage;

    public Cache(ReplacementPolicy<Key> replacementPolicy, Storage<Key, Value> storage) {
        this.replacementPolicy = replacementPolicy;
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        System.out.println("Adding key => " + key);
        try {
            storage.add(key, value);
            replacementPolicy.keyAccessed(key);
        } catch(StorageFullException e) {
            System.out.println(e.getMessage());
            Key keyToRemove = replacementPolicy.evictKey();
            System.out.println("Evicted the key => " + keyToRemove.toString());
            storage.remove(keyToRemove);
            storage.add(key, value);
        }
        System.out.println("Successfully added => " + key);
        System.out.println("------------------------------");
    }

    public Value get(Key key) {
        Value value = null;
        try {
            value = storage.get(key);
            replacementPolicy.keyAccessed(key);
            System.out.println("Successfully got the key => " + key + " and value => " + value);
        } catch (KeyNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("------------------------------");
        return value;
    }
}
