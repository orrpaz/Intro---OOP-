package game;
import java.io.Serializable;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.util.List;


/**
 * this class represents a high score table .
 */
public class HighScoresTable implements Serializable {
    private List<ScoreInfo> highScoresList;
    private int size;

    /**
     * constructor.
     * @param size the size of the table
     */
    public HighScoresTable(int size) {
        this.highScoresList = new ArrayList<ScoreInfo>();
        this.size = size;
    }

    /**
     * Add a high-score.
     * @param score the user score
     */
    public void add(ScoreInfo score) {
        this.highScoresList.add(this.getRank(score.getScore()) - 1, score);
        while (this.highScoresList.size() > this.size) {
            this.highScoresList.remove(this.highScoresList.size() - 1);
        }
    }

    /**
     * Return table size.
     * @return the table size
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highScoresList;
    }

    /**
     * return the rank of the current score.
     * @param score the user score
     * @return the rank of the current score
     */
    public int getRank(int score) {
        int i;
        for (i = 0; i < this.highScoresList.size(); i++) {
            if (score > this.highScoresList.get(i).getScore()) {
                break;
            }
        }
        return i + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.highScoresList.clear();
    }

    /**
     * Load table data from file.
     * @param filename high score table
     * @throws IOException when there is problem with the loading
     */
    public void load(File filename) throws IOException {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(
                    new FileInputStream(filename));
            HighScoresTable highScoresTable;
            highScoresTable = (HighScoresTable) objectInputStream.readObject();
            this.size = highScoresTable.size;
            this.highScoresList = highScoresTable.highScoresList;
        } catch (FileNotFoundException e) {
            System.err.println("Unable to find file: " + filename);
            HighScoresTable emptyHighScoresTable = new HighScoresTable(5);
            emptyHighScoresTable.save(filename);
            this.size = emptyHighScoresTable.size;
            this.highScoresList = emptyHighScoresTable.highScoresList;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to find class for object in file: " + filename);
        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * Save table data to the specified file.
     * @param filename the file name
     * @throws IOException when there is problem with the saving
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(
                    new FileOutputStream(filename));
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            System.err.println("Failed saving object");
        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Failed closing file: " + filename);
            }
        }
    }

    /**
     * reads a table from file and return it.
     * @param filename the given filename.
     * @return a new table with the values according to the data in the file.
     * if the file does not exist, or there is a problem
     * with reading it, an empty table is returned.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable emptyTable = new HighScoresTable(5);
        try {
            if (!filename.exists()) {
                return emptyTable;
            }
            emptyTable.load(filename);
        } catch (IOException e) {
            System.err.println("Failed closing file: " + filename);
            return new HighScoresTable(5);
        }
        return emptyTable;
    }
}



