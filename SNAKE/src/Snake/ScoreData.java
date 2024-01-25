package Snake;

import javax.swing.table.AbstractTableModel;
import java.io.Serializable;
import java.util.*;

/**
 * The Score class.
 */
class Score implements Serializable, Comparable<Score>{
    private final String playerName;
    private final int scorePoint;
    private final String date;

    /**
     * Instantiates a new Score.
     *
     * @param playerName the player name
     * @param score      the score
     * @param date       the date
     */
    public Score(String playerName, int score, String date) {
        this.playerName = playerName;
        this.scorePoint = score;
        this.date = date;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return scorePoint;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Compares this object with the specified object for order.
     * Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     *
     * @param other the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Score other) {
        return Integer.compare(other.scorePoint, this.scorePoint);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     * @return true if this object is the same as the obj argument; false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Score other)) return false;
        if(compareTo(other) != 0) return false;
        return this.playerName.equals(other.playerName) && this.date.equals(other.date);
    }
}

/**
 * The Score data class.
 * This class represents a collection of scores and implements the Collection interface.
 */
public class ScoreData extends AbstractTableModel implements Collection<Score> {

    /**
     * The constant scores.
     */
    protected static List<Score> scores = new ArrayList<>();

    /**
     * The names of the columns in the JTable.
     */
    private final String[] ColumnNames = {"Player Name", "Score", "Date"};

    /**
     * Add score.
     *
     * @param score the score
     */
    public static void addScore(Score score) {
        scores.add(score);
    }

    /**
     * Gets the number of rows.
     */
    @Override
    public int getRowCount() {
        return scores.size();
    }

    /**
     * Gets the number of columns.
     */
    @Override
    public int getColumnCount() {
        return ColumnNames.length;
    }

    /**
     * Gets the value at the specified row and column.
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Score score = scores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return score.getPlayerName();
            case 1:
                return score.getScore();
            case 2:
                return score.getDate();
            default:
                break;
        }
        return null;
    }

    /**
     * Gets the name of the column at the specified index.
     */
    @Override
    public String getColumnName(int columnIndex) {
        return ColumnNames[columnIndex];
    }

    /**
     * Get score.
     *
     * @param rowIndex the row index
     * @return the score
     */
    public Score get(int rowIndex) {
        return scores.get(rowIndex);
    }

    /**
     * Adds a score to the collection.
     *
     * @param score the score to add
     * @return true if the score was added successfully
     */
    @Override
    public boolean add(Score score) {
        return scores.add(score);
    }

    /**
     * Returns the number of scores in the collection.
     *
     * @return the number of scores
     */
    @Override
    public int size() {
        return scores.size();
    }

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return scores.isEmpty();
    }

    /**
     * Checks if the collection contains a specific score.
     *
     * @param o the score to check for
     * @return true if the score is in the collection, false otherwise
     */
    @Override
    public boolean contains(Object o) {
        return scores.contains(o);
    }

    /**
     * Returns an iterator over the scores in the collection.
     *
     * @return an iterator over the scores
     */
    @Override
    public Iterator<Score> iterator() {
        return scores.iterator();
    }

    /**
     * Returns an array containing all the scores in the collection.
     *
     * @return an array of scores
     */
    @Override
    public Object[] toArray() {
        return scores.toArray();
    }

    /**
     * Returns an array containing all the scores in the collection; the runtime type of the returned array is that of the specified array.
     *
     * @param a the array into which the elements of the collection are to be stored
     * @return an array of scores
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return scores.toArray(a);
    }

    /**
     * Adds all the scores in the specified collection to the collection.
     *
     * @param c the collection containing scores to be added
     * @return true if the collection is modified as a result of this operation
     */
    @Override
    public boolean addAll(Collection<? extends Score> c) {
        return scores.addAll(c);
    }

    /**
     * Removes a specific score from the collection.
     *
     * @param o the score to be removed
     * @return true if the score was removed successfully
     */
    @Override
    public boolean remove(Object o) {
        return scores.remove(o);
    }

    /**
     * Checks if the collection contains all the scores in the specified collection.
     *
     * @param c the collection to be checked for containment in this collection
     * @return true if this collection contains all the elements of the specified collection
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(scores).containsAll(c);
    }

    /**
     * Removes all the scores in the specified collection from the collection.
     *
     * @param c the collection containing scores to be removed
     * @return true if the collection is modified as a result of this operation
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return scores.removeAll(c);
    }

    /**
     * Retains only the scores in the collection that are contained in the specified collection.
     *
     * @param c the collection containing scores to be retained
     * @return true if the collection is modified as a result of this operation
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return scores.retainAll(c);
    }

    /**
     * Removes all the scores from the collection.
     */
    @Override
    public void clear() {
        scores.clear();
    }
}
