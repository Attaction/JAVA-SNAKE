package Snake;

import javax.swing.*;

/**
 * The Main menu frame class.
 */
public class MainMenuFrame extends JFrame {
    /**
     * Instantiates a new Main menu frame.
     *
     * @param WIDTH  the width
     * @param HEIGHT the height
     */
    public MainMenuFrame(int WIDTH, int HEIGHT) {
        MainMenuPanel mainMenuPanel = new MainMenuPanel();
        this.setSize(WIDTH, HEIGHT);
        this.add(mainMenuPanel);
        this.setTitle("Snake");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setJMenuBar(MainMenuPanel.menuBar);
    }
}
