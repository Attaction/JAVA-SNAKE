package Snake;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * The test class for ScoreData class.
 */
public class ScoreDataTest {
    private ScoreData scoreData;

    /**
     * Set up.
     */
    @Before
    public void setUp(){
        scoreData = new ScoreData();
        scoreData.scores = new ArrayList<>();
    }

    /**
     * Test add score.
     */
    @Test
    public void testAddScore() {
        Score score = new Score("test", 10, "2023-11-23");
        scoreData.addScore(score);

        assertEquals(1, scoreData.getRowCount());
        assertEquals(score, scoreData.get(0));
    }

    /**
     * Test get row count.
     */
    @Test
    public void testGetRowCount() {
        assertEquals(0, scoreData.getRowCount());

        Score score = new Score("test", 10, "2023-11-23");
        scoreData.addScore(score);

        assertEquals(1, scoreData.getRowCount());
    }

    /**
     * Test get value at.
     */
    @Test
    public void testGetValueAt() {
        Score score = new Score("test", 10, "2023-11-23");
        scoreData.addScore(score);

        assertEquals("test", scoreData.getValueAt(0, 0));
        assertEquals(10, scoreData.getValueAt(0, 1));
        assertEquals("2023-11-23", scoreData.getValueAt(0, 2));
    }

    /**
     * Test get column name.
     */
    @Test
    public void testGetColumnName() {
        assertEquals("Player Name", scoreData.getColumnName(0));
        assertEquals("Score", scoreData.getColumnName(1));
        assertEquals("Date", scoreData.getColumnName(2));
    }
}
