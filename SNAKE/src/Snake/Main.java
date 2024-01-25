package Snake;

import javax.swing.Timer;

import static Snake.GameOverPanel.getsavingScore;

/**
 * The Main class.
 */
public class Main {
    /**
     * The constant WIDTH.
     */
    public static final int WIDTH = 800;
    /**
     * The constant HEIGHT.
     */
    public static final int HEIGHT = 600;
    private static boolean leaderboard = false;
    private static final LeaderBoardFrame leaderBoardFrame = new LeaderBoardFrame();

    /**
     * Sets leaderboard boolean. This allows the leaderboard to be visible.
     *
     * @param leaderboard the leaderboard boolean
     */
    public static void setLeaderboard(boolean leaderboard) {
        Main.leaderboard = leaderboard;
    }
    private static boolean start = true;

    /**
     * Sets start boolean. This allows the main menu to be visible.
     *
     * @param start the start boolean
     */
    public static void setStart(boolean start) {
        Main.start = start;
    }
    private static final MainMenuFrame mainMenuFrame = new MainMenuFrame(WIDTH, HEIGHT);
    private static boolean game = false;

    /**
     * Sets game boolean. This allows the game to be visible.
     *
     * @param game the game
     */
    public static void setGame(boolean game) {
        Main.game = game;
    }
    private static final GameFrame gameFrame = new GameFrame(WIDTH, HEIGHT);
    private static boolean gameOver = false;

    /**
     * Sets gameOver boolean. This allows the game over screen to be visible.
     *
     * @param gameOver the game over
     */
    public static void setgameOver(boolean gameOver) {
        Main.gameOver = gameOver;
    }
    private static final GameOverFrame gameOverFrame = new GameOverFrame(WIDTH, HEIGHT);

    /**
     * Main method.
     *
     * @param args the args
     */
    public static void main(String[] args){
        mainMenuFrame.setVisible(true);
        Timer timer = new Timer(10, e -> updateFrame());

        timer.start();
    }

    /**
     * Update frame, that updates which frame is currently visible.
     */
    public static void updateFrame() {
        mainMenuFrame.setVisible(start && !leaderboard && !game && !gameOver);
        gameFrame.setVisible(game && !leaderboard && !gameOver && !start);
        if(!getsavingScore()) {
            gameOverFrame.setVisible(gameOver && !leaderboard && !game && !start);
            gameOverFrame.repaint();
        }
        leaderBoardFrame.setVisible(leaderboard);
    }
}