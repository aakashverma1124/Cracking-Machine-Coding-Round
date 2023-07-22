package in.innoskrit.cache.storage;

import in.innoskrit.cache.constant.Constant;
import in.innoskrit.cache.exception.KeyNotFoundException;
import in.innoskrit.cache.exception.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> map;
    private final Integer capacity;

    public HashMapStorage(Integer capacity) {
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public void add(Key key, Value value) throws StorageFullException {
        /*
         * The key might already be present, and if the existing key is being updated
         * then storage cannot be full. Hence, update and return directly.
         */
        if(map.containsKey(key)) {
            map.put(key, value);
            return;
        }
        if(isStorageFull()) throw new StorageFullException(Constant.STORAGE_FULL);
        map.put(key, value);
    }

    public void remove(Key key) throws KeyNotFoundException {
        if(!map.containsKey(key)) throw new KeyNotFoundException(Constant.KEY_NOT_FOUND);
        map.remove(key);
    }

    public Value get(Key key) throws KeyNotFoundException {
        if(!map.containsKey(key)) throw new KeyNotFoundException(Constant.KEY_NOT_FOUND);
        return map.get(key);
    }

    public boolean isStorageFull() {
        return this.capacity == map.size();
    }
}
