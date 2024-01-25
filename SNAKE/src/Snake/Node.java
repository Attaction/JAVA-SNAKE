package Snake;

import java.util.Objects;

/**
 * The Node class.
 */
public class Node {
    /**
     * Reference to the next Node element of the Node linked list.
     */
    private Node next;
    /**
     * Reference to the previous Node element of the linked list.
     */
    private Node prev;
    /**
     * The X and Y coordinate of the Node.
     */
    private int x;
    private int y;

    /**
     * Instantiates a new Node.
     */
    public Node() {
        this.x = 0;
        this.y = 0;
        this.next = null;
        this.prev = null;
    }

    /**
     * Instantiates a new Node.
     *
     * @param x the x
     * @param y the y
     */
    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.next = null;
        this.prev = null;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets next.
     *
     * @return the next
     */
    public Node getNext() {
        return next;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Gets prev.
     *
     * @return the prev
     */
    public Node getPrev() {
        return prev;
    }

    /**
     * Sets prev.
     *
     * @param prev the prev
     */
    public void setPrev(Node prev) {
        this.prev = prev;
    }

    /**
     * Equals boolean.
     *
     * @param obj the obj
     * @return the boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return x == other.x && y == other.y;
    }

    /**
     * Overriding the hashCode method.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
