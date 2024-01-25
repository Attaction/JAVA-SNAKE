package Snake;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * The test class for the GamePanel class.
 */
public class GamePanelTest {

    private GamePanel gamePanel;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        gamePanel = new GamePanel();
    }

    /**
     * Test game initialization.
     */
    @Test
    public void testGameInitialization() {
        assertFalse(gamePanel.running);
        assertNotNull(gamePanel.snake);
        assertNotNull(gamePanel.food);
        assertNotNull(gamePanel.timer);
    }

    /**
     * Test game start.
     */
    @Test
    public void testGameStart() {
        gamePanel.play();
        assertTrue(gamePanel.running);
        assertTrue(gamePanel.timer.isRunning());
    }

    /**
     * Test game pause.
     */
    @Test
    public void testGamePause() {
        gamePanel.play();
        gamePanel.pause();
        assertFalse(gamePanel.running);
        assertFalse(gamePanel.timer.isRunning());
    }

    /**
     * Test game restart.
     */
    @Test
    public void testGameRestart() {
        gamePanel.play();
        gamePanel.restart();
        assertTrue(gamePanel.running);
        assertNotNull(gamePanel.snake);
        assertNotNull(gamePanel.food);
    }

}
