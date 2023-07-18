package in.innoskrit.algorithm;

import in.innoskrit.algorithm.exception.UnexpectedElementFoundException;

public class DoublyLinkedList<Key, Value> {
    DLLNode<Key, Value> head;
    DLLNode<Key, Value> tail;

    public DoublyLinkedList() {
        this.head = new DLLNode<Key, Value>(null, null);
        this.tail = new DLLNode<Key, Value>(null, null);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public DLLNode<Key, Value> addAtFirst(DLLNode<Key, Value> node) {
        if(node == null) {
            throw new UnexpectedElementFoundException("Null Node cannot be added.");
        }

        DLLNode<Key, Value> headNext = head.next;
        node.next = headNext;
        headNext.prev = node;
        node.prev = head;
        head.next = node;

        return node;
    }

    public DLLNode<Key, Value> deleteFromLast() {
        DLLNode<Key, Value> tailPrev = tail.prev;
        tailPrev.prev.next = tailPrev.next;
        tailPrev.next.prev = tailPrev.prev;
        return tailPrev;
    }

    public void deleteNode(DLLNode<Key, Value> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

}
