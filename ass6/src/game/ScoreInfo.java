package game;

import java.io.Serializable;

/**
 * this class represent ScoreInfo.
 */
public class ScoreInfo implements Serializable {
    /**
     * members.
     */
    private String name;
    private int score;

    /**
     * constructor.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * return the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * return the score.
     *
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }

}
