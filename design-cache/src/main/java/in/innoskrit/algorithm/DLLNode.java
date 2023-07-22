package in.innoskrit.algorithm;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @param <Key> type of element to be inserted into the node.
 */
@Getter
@Setter
public class DLLNode<Key> {
    DLLNode<Key> prev;
    DLLNode<Key> next;
    Key key;
    public DLLNode(Key key) {
        this.key = key;
        this.prev = null;
        this.next = null;
    }
}
