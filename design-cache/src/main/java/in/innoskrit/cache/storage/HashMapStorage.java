package in.innoskrit.cache.storage;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {

    Map<Key, Value> map;
    public HashMapStorage() {
        map = new HashMap<Key, Value>();
    }

    public void add(Key key, Value value) {
        map.put(key, value);
    }

    public void remove(Key key) {
        map.remove(key);
    }

    public Value get(Key key) {
        return map.get(key);
    }

    public boolean containsKey(Key key) {
        if(map.containsKey(key)) return true;
        else return false;
    }

    public int size() {
        return map.size();
    }
}
