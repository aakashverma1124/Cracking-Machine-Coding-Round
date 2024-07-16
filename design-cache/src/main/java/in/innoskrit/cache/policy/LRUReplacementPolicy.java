package in.innoskrit.cache.policy;

import in.innoskrit.algorithm.DLLNode;
import in.innoskrit.algorithm.DoublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUReplacementPolicy<Key> implements ReplacementPolicy<Key> {
    DoublyLinkedList<Key> dll;
    private final Map<Key, DLLNode<Key>> map;

    public LRUReplacementPolicy() {
        this.dll = new DoublyLinkedList<>();
        this.map = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if(map.containsKey(key)) {
            dll.deleteNode(map.get(key));
            dll.addAtFirst(map.get(key));
        } else {
            DLLNode<Key> node = new DLLNode<>(key);
            dll.addAtFirst(node);
            map.put(key, node);
        }
    }

    @Override
    public Key evictKey() {
        DLLNode<Key> node = dll.deleteFromLast();
        return node.getKey();
    }
}
