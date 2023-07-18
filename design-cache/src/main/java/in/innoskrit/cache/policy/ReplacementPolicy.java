package in.innoskrit.cache.policy;

/**
 *
 * @param <Key> the key that you want to store into the storage
 * @param <Value> the value corresponding to the key you want to store
 */
public interface ReplacementPolicy<Key, Value> {
    void put(Key key, Value value);
    Value get(Key key);
}
