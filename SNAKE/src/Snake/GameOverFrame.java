package Snake;

import javax.swing.*;

/**
 * The Game over frame class.
 */
public class GameOverFrame extends JFrame {
    /**
     * Instantiates a new Game over frame.
     *
     * @param WIDTH  the width
     * @param HEIGHT the height
     */
    public GameOverFrame(int WIDTH, int HEIGHT) {
        GameOverPanel gameOverPanel = new GameOverPanel();
        this.setSize(WIDTH, HEIGHT);
        this.add(gameOverPanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
