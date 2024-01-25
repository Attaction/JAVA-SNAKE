package Snake;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * The test class for Snake class.
 */
public class SnakeTest {
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
     * Test hit food.
     */
    @Test
    public void testHitFood() {
        snake.getBody().getHead().setX(20);
        snake.getBody().getHead().setY(10);
        snake.setDirection(Dir.RIGHT);
        food.setPosition(new Node(20, 10));

        assertTrue(Snake.hitFood(snake, food));
    }

    /**
     * Test snake update without food.
     */
    @Test
    public void testSnakeUpdateWithoutFood() {
        snake.getHead().setX(20);
        snake.getHead().setY(10);
        snake.setDirection(Dir.RIGHT);

        Snake.snakeUpdate(snake, food);

        assertEquals(1, snake.getLength());
        assertEquals(20 + GamePanel.UNIT_SIZE, snake.getHead().getX());
        assertEquals(10, snake.getHead().getY());
    }

    /**
     * Test snake update with food.
     */
    @Test
    public void testSnakeUpdateWithFood() {
        snake.getHead().setX(20);
        snake.getHead().setY(10);
        snake.setDirection(Dir.RIGHT);

        food.setPosition(new Node(20 + GamePanel.UNIT_SIZE, 10));

        Snake.snakeUpdate(snake, food);

        assertEquals(2, snake.getLength());
        assertEquals(20 + GamePanel.UNIT_SIZE, snake.getHead().getX());
        assertEquals(10, snake.getHead().getY());
    }
}
