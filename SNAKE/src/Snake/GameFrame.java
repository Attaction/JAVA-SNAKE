package Snake;

import javax.swing.*;
import java.awt.*;

/**
 * The Game frame class.
 */
public class GameFrame extends JFrame {
    /**
     * Instantiates a new Game frame.
     *
     * @param WIDTH  the width
     * @param HEIGHT the height
     */
    public GameFrame(int WIDTH, int HEIGHT) {
        GamePanel gamePanel = new GamePanel();
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.add(gamePanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
