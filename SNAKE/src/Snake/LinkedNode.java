package Snake;

import java.util.Collection;
import java.util.Iterator;

/**
 * The Linked node class.
 */
public class LinkedNode implements Iterable<Node> {
    private Node head;
    private Node tail;

    /**
     * Instantiates a new Linked node. The head is the first node in the linked list.
     */
    public LinkedNode() {
        head = null;
        tail = null;
    }

    /**
     * Gets head. The head is the first node in the linked list.
     *
     * @return the head
     */
    public Node getHead() {
        return head;
    }

    /**
     * Sets head. The head is the first node in the linked list.
     *
     * @param head the head
     */
    public void setHead(Node head) {
        this.head = head;
    }

    /**
     * Gets tail. The tail is the last node in the linked list.
     *
     * @return the tail
     */
    public Node getTail() {
        return tail;
    }

    /**
     * Sets tail. The tail is the last node in the linked list.
     *
     * @param tail the tail
     */
    public void setTail(Node tail) {
        this.tail = tail;
    }

    /**
     * Instantiats a new iterator for the linked list.
     */
    @Override
    public Iterator<Node> iterator() {
        return new LinkedListIterator();
    }

    /**
     * The iterator class for the linked list.
     */
    private class LinkedListIterator implements Iterator<Node> {
        private Node current = head;

        /**
         * Checks if current is the last element of the Linked list.
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Gets the next element of the Linked list.
         */
        @Override
        public Node next() {
            if (!hasNext()) {
                return null;
            }
            Node temp = current;
            current = current.getNext();
            return temp;
        }
    }

}
