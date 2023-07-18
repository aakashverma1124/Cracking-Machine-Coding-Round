package in.innoskrit.cache.storage;

/**
 *
 * @param <Key> the key that you want to store into the storage
 * @param <Value> the value corresponding to the key you want to store
 */
public interface Storage<Key, Value> {
    void add(Key key, Value value);
    void remove(Key key);
    Value get(Key key);
    public boolean containsKey(Key key);
    public int size();
}
