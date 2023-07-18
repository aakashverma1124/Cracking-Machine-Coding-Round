package in.innoskrit.cache.policy;

import in.innoskrit.algorithm.DLLNode;
import in.innoskrit.algorithm.DoublyLinkedList;
import in.innoskrit.cache.exception.KeyNotFoundException;
import in.innoskrit.cache.storage.Storage;

public class LRUReplacementPolicy<Key, Value> implements ReplacementPolicy<Key, Value> {

    private final Integer capacity;
    DoublyLinkedList<Key, Value> dll;
    private Storage<Key, Value> storage;

    public LRUReplacementPolicy(Integer capacity, Storage<Key, Value> storage) {
        this.capacity = capacity;
        this.dll = new DoublyLinkedList<Key, Value>();
        this.storage = storage;
    }

    public void put(Key key, Value value) {
        if(storage.containsKey(key)) {
            DLLNode<Key, Value> node = (DLLNode<Key, Value>) storage.get(key);
            dll.deleteNode(node);
            node.setValue(value);
            dll.addAtFirst(node);
            storage.add(key, (Value) node);
        } else {
            if(this.capacity == this.storage.size()) {
                System.out.println("Capacity Full");
                DLLNode node = dll.deleteFromLast();
                storage.remove((Key) node.getKey());
            }
            DLLNode<Key, Value> node = new DLLNode<Key, Value>(key, value);
            dll.addAtFirst(node);
            storage.add(key, (Value) node);
        }
    }

    public Value get(Key key) {
        if(!storage.containsKey(key)) {
            throw new KeyNotFoundException("Requested Key " + key + " Not Found.");
        }
        DLLNode<Key, Value> node = (DLLNode<Key, Value>) storage.get(key);
        dll.deleteNode(node);
        dll.addAtFirst(node);
        return node.getValue();
    }
}
