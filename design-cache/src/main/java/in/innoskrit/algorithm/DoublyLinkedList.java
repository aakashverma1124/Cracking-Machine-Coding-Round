package in.innoskrit.algorithm;

import in.innoskrit.algorithm.exception.UnexpectedElementFoundException;

public class DoublyLinkedList<Key> {
    DLLNode<Key> head;
    DLLNode<Key> tail;

    public DoublyLinkedList() {
        this.head = new DLLNode<>(null);
        this.tail = new DLLNode<>(null);
        this.head.next = tail;
        this.tail.prev = head;
    }

    public void addAtFirst(DLLNode<Key> node) {
        if(node == null) {
            throw new UnexpectedElementFoundException("Null node cannot be added.");
        }

        DLLNode<Key> headNext = head.next;
        node.next = headNext;
        headNext.prev = node;
        node.prev = head;
        head.next = node;
    }

    public DLLNode<Key> deleteFromLast() {
        DLLNode<Key> tailPrev = tail.prev;
        tailPrev.prev.next = tailPrev.next;
        tailPrev.next.prev = tailPrev.prev;
        return tailPrev;
    }

    public void deleteNode(DLLNode<Key> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
}
