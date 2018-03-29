package game;
import base.Sprite;
import base.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *  represents the first level.
 */
public class Level1 implements LevelInformation {
    /**
     * return the number of balls in the game.
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 1;
    }
    /**
     * initial the balls velocities.
     * @return the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelo = new ArrayList<Velocity>();
        Velocity v1 = Velocity.fromAngleAndSpeed(0, 5);
        listVelo.add(v1);
        return listVelo;
    }
    /**
     * return the paddle speed.
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 5;
    }
    /**
     * return the paddle width.
     * @return the paddle width
     */
    public int paddleWidth() {
        return 100;
    }
    /**
     * return the level name.
     * @return the level name
     */
    public String levelName() {
        return "Direct Hit";
    }
    /**
     * return the background.
     * @return the background
     */
    public Sprite getBackground() {
        return new DirectHit();
    }
    /**
     * create the blocks.
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();
        Block b1 = new Block(385, 150, 30, 30, Color.RED, 1);
        listBlocks.add(b1);
        return listBlocks;
    }
    /**
     * return the number of blocks.
     * @return the the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }

}