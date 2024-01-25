package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Iterator;

/**
 * The Game panel class.
 */
public class GamePanel extends JPanel implements ActionListener {
    /**
     * The Unit size.
     */
    static final int UNIT_SIZE = 10;
    /**
     * The Food instance.
     */
    static final Food food = new Food();
    /**
     * The constant score string. This is used to display the score on the screen.
     */
    protected static String score;
    /**
     * The Snake instance.
     */
    static Snake snake = new Snake();
    /**
     * The Timer instance.
     */
    protected static Timer timer;
    /**
     * The Running boolean.
     */
    protected static boolean running;

    /**
     * Instantiates a new Game panel.
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        timer = new Timer(100, this);
        running = false;
        pause();
    }

    /**
     * Controlling the timer. If this is called the game will start.
     */
    public static void play() {
        running = true;
        timer.start();
    }

    /**
     * Controlling the timer. If this is called the game will pause.
     */
    public static void pause() {
        running = false;
        timer.stop();
    }

    /**
     * Used to make everything become start state.
     */
    public static void startup() {
        snake = new Snake();
        Food.foodUpdate(snake, food);
    }

    /**
     * Restarting the game.
     */
    public static void restart() {
        startup();
        running = true;
    }

    /**
     * Game over.
     */
    public static void gameOver() {
        Main.setgameOver(true);
        Main.setGame(false);
    }

    /**
     * Painting all the components.
     *
     * @param graphics the graphics
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    /**
     * Drawing all the components to the panel.
     *
     * @param graphics the graphics
     */
    public static void draw(Graphics graphics) {
        Font oldfont = graphics.getFont();
        graphics.setFont(new Font("Arial", Font.BOLD, 26));
        graphics.setColor(Color.RED);
        score = "Score: " + (snake.getLength() - 1);
        GameOverPanel.setScoreLabel(snake.getLength() - 1);
        graphics.drawString(score, 0, 26);
        graphics.setFont(oldfont);
        if (running) {
            Iterator<Node> iterator = snake.getBody().iterator();
            while (iterator.hasNext()) {
                Node currentNode = iterator.next();
                graphics.setColor(Color.GREEN);
                graphics.fillRect(currentNode.getX(), currentNode.getY(), UNIT_SIZE, UNIT_SIZE);
                graphics.setColor(Color.BLACK);
                if (snake.getDirection() == Dir.UP || snake.getDirection() == Dir.DOWN) {
                    graphics.drawLine(currentNode.getX(), currentNode.getY(), currentNode.getX() + UNIT_SIZE, currentNode.getY());
                    graphics.drawLine(currentNode.getX(), currentNode.getY(), currentNode.getX(), currentNode.getY() + UNIT_SIZE);
                } else {
                    graphics.drawLine(currentNode.getX(), currentNode.getY(), currentNode.getX(), currentNode.getY() + UNIT_SIZE);
                    graphics.drawLine(currentNode.getX(), currentNode.getY(), currentNode.getX() + UNIT_SIZE, currentNode.getY());
                }
            }
            graphics.setColor(Color.RED);
            graphics.fillOval(food.getPosition().getX(), food.getPosition().getY(), UNIT_SIZE, UNIT_SIZE);
        } else {
            gameOver();
        }
    }

    /**
     * This method is called every time the timer is triggered. It is used to update the game.
     *
     * @param a the a
     */
    @Override
    public void actionPerformed(ActionEvent a) {
        if (running) {
            Snake.snakeUpdate(snake, food);
            if (Snake.hitWall(snake) || Snake.hitSelf(snake)) {
                gameOver();
            }
            Food.foodUpdate(snake, food);
        }
        repaint();
    }

    /**
     * The class MyKeyAdapter. This class is used to control the snake.
     */
    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    snake.setDirection(Dir.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.setDirection(Dir.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    snake.setDirection(Dir.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    snake.setDirection(Dir.DOWN);
                    break;
                case KeyEvent.VK_P:
                    if (running) {
                        pause();
                    } else {
                        play();
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
