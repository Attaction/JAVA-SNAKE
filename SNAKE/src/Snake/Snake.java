package Snake;

/**
 * The enum Dir.
 */
enum Dir {
    /**
     * Direction up.
     */
    UP,
    /**
     * Direction down.
     */
    DOWN,
    /**
     * Direction left.
     */
    LEFT,
    /**
     * Direction right.
     */
    RIGHT
}

/**
 * The Snake class.
 */
public class Snake {

    /**
     * The body of the snake.
     */
    private final LinkedNode body = new LinkedNode();

    /**
     * The length of the snake.
     */
    private int length = 1;

    /**
     * The direction of the snake.
     */
    private Dir direction = Dir.RIGHT;

    /**
     * Instantiates a new Snake.
     */
    public Snake() {
        Node head = new Node(Main.WIDTH / 2, Main.HEIGHT / 2);
        body.setHead(head);
        body.setTail(head);
    }

    /**
     * Hit food boolean. If the snake hits the food, return true.
     *
     * @param snake the snake
     * @param food  the food
     * @return the boolean
     */
    public static boolean hitFood(Snake snake, Food food) {
        return (snake.getHead().getX() == food.getPosition().getX() && snake.getHead().getY() == food.getPosition().getY());
    }

    /**
     * Hit wall boolean. If the snake hits the wall, return true.
     *
     * @param snake the snake
     * @return the boolean
     */
    public static boolean hitWall(Snake snake) {
        if (snake.getHead().getX() < 0) {
            return true;
        } else if (snake.getHead().getX() > (Main.WIDTH - GamePanel.UNIT_SIZE)) {
            return true;
        } else if (snake.getHead().getY() < 0) {
            return true;
        } else return snake.getHead().getY() > (Main.HEIGHT - GamePanel.UNIT_SIZE);
    }

    /**
     * Hit self boolean. If the snake hits itself, return true.
     *
     * @param snake the snake
     * @return the boolean
     */
    public static boolean hitSelf(Snake snake) {
        Node node = snake.getHead().getNext();
        while (node != null) {
            if (node.getX() == snake.getHead().getX() && node.getY() == snake.getHead().getY()) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Snake update. Updates the snake's position.
     *
     * @param snake the snake
     * @param food  the food
     */
    public static void snakeUpdate(Snake snake, Food food) {
        Node newStart = new Node();
        snake.getHead().setPrev(newStart);
        newStart.setNext(snake.getHead());
        newStart.setX(snake.getHead().getX());
        newStart.setY(snake.getHead().getY());

        switch (snake.getDirection()) {
            case UP -> newStart.setY(newStart.getY() - GamePanel.UNIT_SIZE);
            case DOWN -> newStart.setY(newStart.getY() + GamePanel.UNIT_SIZE);
            case LEFT -> newStart.setX(newStart.getX() - GamePanel.UNIT_SIZE);
            case RIGHT -> newStart.setX(newStart.getX() + GamePanel.UNIT_SIZE);
        }

        snake.getBody().setHead(newStart);
        snake.getBody().getHead().setPrev(null);

        if (!hitFood(snake, food)) {
            Node oldtail = snake.getTail();
            snake.getTail().getPrev().setNext(null);
            snake.getBody().setTail(oldtail.getPrev());
        } else {
            snake.setLength(snake.getLength() + 1);
        }
    }

    /**
     * Gets body.
     *
     * @return the body
     */
    public LinkedNode getBody() {
        return body;
    }

    /**
     * Gets head.
     *
     * @return the head
     */
    public Node getHead() {
        return body.getHead();
    }

    /**
     * Gets tail.
     *
     * @return the tail
     */
    public Node getTail() {
        return body.getTail();
    }

    /**
     * Gets length.
     *
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets length.
     *
     * @param length the length
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * Gets direction.
     *
     * @return the direction
     */
    public Dir getDirection() {
        return direction;
    }

    /**
     * Sets direction.
     *
     * @param direction the direction
     */
    public void setDirection(Dir direction) {
        this.direction = direction;
    }
}
