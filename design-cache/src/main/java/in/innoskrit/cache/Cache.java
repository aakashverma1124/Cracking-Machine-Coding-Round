package in.innoskrit.cache;

import in.innoskrit.cache.exception.KeyNotFoundException;
import in.innoskrit.cache.policy.ReplacementPolicy;

/**
 *
 * @param <Key>
 * @param <Value>
 */
public class Cache<Key, Value> {

    private final ReplacementPolicy<Key, Value> replacementPolicy;

    public Cache(ReplacementPolicy<Key, Value> replacementPolicy) {
        this.replacementPolicy = replacementPolicy;
    }

    public void put(Key key, Value value) {
        replacementPolicy.put(key, value);
    }

    public Value get(Key key) {
        Value val = null;
        try {
            val  = replacementPolicy.get(key);
        } catch (KeyNotFoundException e) {
            System.out.println("Key " + key + " Not Found");
        }
        return val;
    }
}
