package Snake;

import java.util.Random;

/**
 * The Food class
 */
public class Food {

    /**
     * The Random instance to create a new position for the food on pickup or on startup.
     */
    public static final Random randomNumber = new Random();
    private Node position;

    /**
     * Instantiates a new Food.
     */
    public Food() {
        this.position = new Node(Main.WIDTH / 2, Main.HEIGHT / 2);
    }

    /**
     * Updates the food position. Called when the food's position needs to be updated. This method also checks if the food is on the snake.
     *
     * @param snake the snake
     * @param food  the food
     */
    public static void foodUpdate(Snake snake, Food food) {
        while (foodOnSnake(snake, food)) {
            food.setPosition(new Node(randomNumber.nextInt((Main.WIDTH - GamePanel.UNIT_SIZE) / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE, randomNumber.nextInt((Main.HEIGHT - GamePanel.UNIT_SIZE) / GamePanel.UNIT_SIZE) * GamePanel.UNIT_SIZE));
        }
    }

    /**
     * Food on snake boolean. Checks if the food is on the snake.
     *
     * @param snake the snake
     * @param food  the food
     * @return the boolean
     */
    public static boolean foodOnSnake(Snake snake, Food food) {
        Node node = snake.getHead();
        while (node != null) {
            if (node.getX() == food.getPosition().getX() && node.getY() == food.getPosition().getY()) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    /**
     * Gets the food's position.
     *
     * @return the position
     */
    public Node getPosition() {
        return position;
    }

    /**
     * Sets food's position.
     *
     * @param position the position
     */
    public void setPosition(Node position) {
        this.position = position;
    }
}