package Snake;

import javax.swing.*;
import java.awt.*;

/**
 * The Main menu panel class.
 */
public class MainMenuPanel extends JPanel {

    /**
     * The menuBar constant.
     */
    protected static final JMenuBar menuBar = new JMenuBar();

    /**
     * Instantiates a new Main menu panel.
     */
    public MainMenuPanel() {
        this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.setLayout(new BorderLayout());

        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        rulesMenuItem.addActionListener(e -> showRulesDialog());
        JMenuItem creatorMenuItem = new JMenuItem("Creator");
        creatorMenuItem.addActionListener(e -> createdByDialog());

        JMenu helpMenu = new JMenu("More");
        helpMenu.add(rulesMenuItem);
        helpMenu.add(creatorMenuItem);
        menuBar.add(helpMenu);

        String fontType = "Arial";

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel midPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel leaderBoardPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));

        JLabel titleLabel = new JLabel("SNAKE GAME");
        titleLabel.setFont(new Font(fontType, Font.BOLD, 24));

        JButton startButton = new JButton("START");
        startButton.addActionListener(e -> {
            Main.setGame(true);
            Main.setStart(false);
            Main.setgameOver(false);
            GamePanel.play();
        });
        startButton.setFont(new Font(fontType, Font.PLAIN, 20));

        JButton leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.addActionListener(e -> Main.setLeaderboard(true));


        topPanel.add(titleLabel);
        midPanel.add(startButton);
        leaderBoardPanel.add(leaderboardButton);
        bottomPanel.add(leaderBoardPanel);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(midPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    /**
     * Created by dialog.
     */
    private void createdByDialog() {
        String createdByText = """
        A Snake játékot készítette:
        Zalaváry Dániel
        """;
        JOptionPane.showMessageDialog(this, createdByText, "A játékot készítette.", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Show rules dialog.
     */
    private void showRulesDialog() {
        String rulesText = """
        A Snake játék lényege:

        1. Cél: A játék célja, hogy a kígyó minél tovább életben maradjon. Ehhez gyűjtsd össze a lehető legtöbb almát.
        2. Irányítás: Használd a nyilakat a kígyó irányításához. A játék megkezdése után a kígyó automatikusan mozogni fog.
        3. Ütközés: Ne ütközz a falba vagy a saját testedbe. Ha ez megtörténik, a játék véget ér.

        Jó játékot!
        """;

        JOptionPane.showMessageDialog(this, rulesText, "Snake Játék Szabályai", JOptionPane.INFORMATION_MESSAGE);
    }
}
