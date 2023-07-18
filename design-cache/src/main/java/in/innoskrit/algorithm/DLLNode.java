package in.innoskrit.algorithm;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @param <Key> type of element to be inserted into the node.
 */
@Getter
@Setter
public class DLLNode<Key, Value> {
    DLLNode<Key, Value> prev;
    DLLNode<Key, Value> next;
    Key key;
    Value value;

    public DLLNode(Key key, Value value) {
        this.key = key;
        this.value = value;
        this.prev = null;
        this.next = null;
    }
}
