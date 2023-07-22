package in.innoskrit.cache.policy;

public interface ReplacementPolicy<Key> {
    void keyAccessed(Key key);
     Key evictKey();
}
