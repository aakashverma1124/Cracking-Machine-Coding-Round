package in.innoskrit.cache.storage;

import in.innoskrit.cache.exception.KeyNotFoundException;
import in.innoskrit.cache.exception.StorageFullException;

/**
 *
 * @param <Key> the key that you want to store into the storage
 * @param <Value> the value corresponding to the key you want to store
 */
public interface Storage<Key, Value> {
    void add(Key key, Value value) throws StorageFullException;
    void remove(Key key) throws KeyNotFoundException;
    Value get(Key key) throws KeyNotFoundException;
    public boolean isStorageFull();
}
