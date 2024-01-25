package Snake;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the Food class.
 */
public class FoodTest {
    private Snake snake;
    private Food food;

    /**
     * Set up.
     */
    @Before
    public void setUp(){
        snake = new Snake();
        food = new Food();
    }

    /**
     * Test food update.
     */
    @Test
    public void testFoodUpdate() {
        snake.getHead().setX(100);
        snake.getHead().setY(100);

        Food.foodUpdate(snake, food);

        assertNotEquals(100, food.getPosition().getX());
        assertNotEquals(100, food.getPosition().getY());

        assertFalse(Food.foodOnSnake(snake, food));
    }

    /**
     * Test food on snake.
     */
    @Test
    public void testFoodOnSnake() {
        snake.getHead().setX(100);
        snake.getHead().setY(100);

        food.setPosition(new Node(100, 100));

        assertTrue(Food.foodOnSnake(snake, food));
    }

    /**
     * Test food not on snake.
     */
    @Test
    public void testFoodNotOnSnake() {
        snake.getHead().setX(100);
        snake.getHead().setY(100);

        food.setPosition(new Node(200, 200));

        assertFalse(Food.foodOnSnake(snake, food));
    }
}
