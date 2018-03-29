package base;

/**
 * intruduce the Boundaries of game.
 */
public class Boundaries {
    private int horizontalBound;
    private int verticalBound;
    /**
     * constructor.
     * @param horizontalBound the given horizontalBound.
     * @param verticalBound the given verticalBound.
     */
    public Boundaries(int horizontalBound, int verticalBound) {
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
    }
    /**
     * a getter for the horizontalBound.
     * @return the horizontalBound.
     */
    public int horizontalBound() {
        return this.horizontalBound;
    }
    /**
     * a getter for the verticalBound.
     * @return the verticalBound.
     */
    public int verticalBound() {
        return this.verticalBound;
    }
}
