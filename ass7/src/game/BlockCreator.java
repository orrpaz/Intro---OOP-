package game;

/**
 * Created by Or on 13/06/2017.
 */
public interface BlockCreator {
    /**
     * Create a block at the specified location.
     * @param xpos the starting point x value
     * @param ypos the starting point y value
     * @return new block
     */
    Block create(int xpos, int ypos);
}