package Snake;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * The Game over panel class.
 */
public class GameOverPanel extends JPanel {
    private static final JLabel scoreLabel = new JLabel();
    private static boolean savingScore = false;

    /**
     * Instantiates a new Game over panel.
     */
    public GameOverPanel() {
        this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setLayout(new GridLayout(3, 1));

        String fontType = "Arial";

        JPanel pageStartPanel = new JPanel(new GridLayout(2, 1));
        JPanel gameOverPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel scorePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel leaderboardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel pageEndPanel = new JPanel(new BorderLayout());

        JLabel gameOverLabel = new JLabel("GAME OVER");
        gameOverLabel.setFont(new Font(fontType, Font.BOLD, 36));

        scoreLabel.setFont(new Font(fontType, Font.PLAIN, 24));

        JButton restartButton = new JButton("RESTART");
        restartButton.addActionListener(e -> {
            Main.setGame(true);
            Main.setgameOver(false);
            Main.setStart(false);
            GamePanel.restart();
        });
        restartButton.setFont(new Font(fontType, Font.PLAIN, 20));

        JButton saveScoreButton = new JButton("SAVE SCORE");
        saveScoreButton.addActionListener(e -> {
            setsavingScore(true);
            saveScore();
        });
        saveScoreButton.setFont(new Font(fontType, Font.PLAIN, 20));

        JButton leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.addActionListener(e -> {
            LeaderBoardFrame.reload();
            Main.setLeaderboard(true);
        });

        gameOverPanel.add(gameOverLabel);
        scorePanel.add(scoreLabel);
        pageStartPanel.add(gameOverPanel);
        pageStartPanel.add(scorePanel);
        centerPanel.add(restartButton);
        centerPanel.add(saveScoreButton);
        leaderboardPanel.add(leaderboardButton);
        pageEndPanel.add(leaderboardPanel, BorderLayout.PAGE_END);
        this.add(pageStartPanel);
        this.add(centerPanel);
        this.add(pageEndPanel);
    }

    /**
     * Sets score label.
     *
     * @param score the score
     */
    public static void setScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Gets the saving score boolean.
     *
     * @return the score
     */
    public static boolean getsavingScore() {
        return savingScore;
    }

    /**
     * Sets the saving score boolean.
     *
     * @param savingScore the saving score boolean
     */
    public static void setsavingScore(boolean savingScore) {
        GameOverPanel.savingScore = savingScore;
    }

    /**
     * Saves the score into a new Score instance.
     */
    private void saveScore() {
        String playerName = JOptionPane.showInputDialog("Enter your name: ");
        if (playerName != null) {
            if (!playerName.isEmpty()) {
                int score = GamePanel.snake.getLength() - 1;

                String date = java.time.LocalDate.now().toString();

                Score newScore = new Score(playerName, score, date);

                ScoreData.addScore(newScore);
                saveScoreData();
                JOptionPane.showMessageDialog(this, "Score saved!");

                setsavingScore(false);
            } else {
                JOptionPane.showMessageDialog(this, "Wrong input!");
            }
        }
    }

    /**
     * Saves the score data into file.
     */
    private void saveScoreData() {
        String cd = System.getProperty("user.dir");
        String filePath = cd + File.separator + "scores.dat";

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
            oos.writeObject(ScoreData.scores);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving score!");
        }
    }
}