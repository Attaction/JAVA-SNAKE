package Snake;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Leaderboard frame class.
 */
public class LeaderBoardFrame extends JFrame {

    /**
     * The Logger to display log messages.
     */
    private static final Logger logger = Logger.getLogger(LeaderBoardFrame.class.getName());

    /**
     * The JTable instance.
     */
    private static JTable table;

    /**
     * The ScoreData instance.
     */
    private ScoreData data;

    /**
     * Instantiates a new Leader board frame.
     */
    @SuppressWarnings("unchecked")
    public LeaderBoardFrame() {
        super("Leaderboard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        String cd = System.getProperty("user.dir");

        String filePath = cd + File.separator + "scores.dat";
        try {
            data = new ScoreData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
            ScoreData.scores = (List<Score>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException fnfe) {
            logger.log(Level.SEVERE, "File not found", fnfe);
        } catch (EOFException eofe) {
            logger.log(Level.SEVERE, "EOF", eofe);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception", e);
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
                    oos.writeObject(ScoreData.scores);
                    oos.close();
                } catch (FileNotFoundException fnfe) {
                    logger.log(Level.SEVERE, "File not found", fnfe);
                } catch (EOFException eofe) {
                    logger.log(Level.SEVERE, "EOF", eofe);
                } catch (Exception ex) {
                    logger.log(Level.SEVERE, "Exception", ex);
                }

                Main.setLeaderboard(false);
            }
        });

        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /**
     * Reloading the table. Used when a new score is added.
     */
    public static void reload() {
        table.repaint();
    }

    /**
     * Initializing the components. Setting the table and the table cell renderer.
     */
    protected void initComponents() {
        this.setLayout(new BorderLayout());

        table = new JTable(data);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);

        TableRowSorter<ScoreData> sorter = new TableRowSorter<>(data);
        table.setRowSorter(sorter);
        sorter.setComparator(0, (o1, o2) -> {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.compareToIgnoreCase(s2);
        });
        sorter.setComparator(1, (o1, o2) -> {
            Integer i1 = (Integer) o1;
            Integer i2 = (Integer) o2;
            return i2.compareTo(i1);
        });
        sorter.setComparator(2, (o1, o2) -> {
            String s1 = (String) o1;
            String s2 = (String) o2;
            return s1.compareToIgnoreCase(s2);
        });

        ScoreTableCellRenderer scoreRenderer = new ScoreTableCellRenderer(table.getDefaultRenderer(Integer.class));
        ScoreTableCellRenderer nameRenderer = new ScoreTableCellRenderer(table.getDefaultRenderer(String.class));
        table.getColumnModel().getColumn(0).setCellRenderer(nameRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(scoreRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(nameRenderer);
    }

    /**
     * The Score table cell renderer class.
     */
    private static class ScoreTableCellRenderer implements TableCellRenderer {
        private final TableCellRenderer renderer;

        /**
         * Instantiates a new Score table cell renderer.
         *
         * @param renderer the renderer
         */
        public ScoreTableCellRenderer(TableCellRenderer renderer) {
            this.renderer = renderer;
        }

        /**
         * Gets the table cell renderer component.
         *
         * @param table      the table
         * @param value      the value
         * @param isSelected the is selected
         * @param hasFocus   the has focus
         * @param row        the row
         * @param column     the column
         * @return the table cell renderer component
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = renderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            int scoreColumnModelIndex = table.convertColumnIndexToModel(1);
            int score = (Integer) table.getModel().getValueAt(table.convertRowIndexToModel(row), scoreColumnModelIndex);

                if (score >= 0) {
                    if (score == getHighestScore(table)) {
                        component.setBackground(Color.YELLOW);
                    } else if (score == getSecontHighestScore(table)) {
                        component.setBackground(Color.LIGHT_GRAY);
                    } else if (score == getThirdHighestScore(table)) {
                        component.setBackground(new Color(205, 127, 50));
                    } else {
                        component.setBackground(Color.WHITE);
                    }
                }
            return component;
        }

        /**
         * Gets the highest score.
         *
         * @param table the table
         * @return the highest score
         */
        private int getHighestScore(JTable table) {
            int highest = Integer.MIN_VALUE;
            for (int i = 0; i < table.getRowCount(); i++) {
                int value = (Integer) table.getValueAt(i, 1);
                if (value > highest) {
                    highest = value;
                }
            }
            return highest;
        }

        /**
         * Gets the highest score after the parameter highest.
         *
         * @param table   the table
         * @param highest the highest
         * @return the highest score after the highest
         */
        private int highestAfterHighest(JTable table, int highest) {
            int secondHighest = Integer.MIN_VALUE;
            for (int i = 0; i < table.getRowCount(); i++) {
                int value = (Integer) table.getValueAt(i, 1);
                if (value < highest) {
                    secondHighest = Math.max(secondHighest, value);
                }
            }
            return secondHighest;
        }

        /**
         * Gets the second-highest score.
         *
         * @param table the table
         * @return the second-highest score
         */
        private int getSecontHighestScore(JTable table) {
            int highest = getHighestScore(table);
            return highestAfterHighest(table, highest);
        }

        /**
         * Gets the third-highest score.
         *
         * @param table the table
         * @return the third-highest score
         */
        private int getThirdHighestScore(JTable table) {
            int secondHighest = getSecontHighestScore(table);
            return highestAfterHighest(table, secondHighest);
        }
    }
}