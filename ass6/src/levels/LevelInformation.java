package levels;
import base.Sprite;
import base.Velocity;
import game.Block;

import java.util.List;

/**
 * interface of levelInformation.
 */
public interface LevelInformation {
    /**
     * return the number of balls.
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * the initial velocity of each ball.
     * @return the list of the velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * return the paddle speed.
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * return the paddle width.
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * return the level name.
     * @return the level name
     */
    String levelName();

    /**
     * return the background.
     * @return the background
     */
    Sprite getBackground();

    /**
     * create the blocks.
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * return the number of blocks.
     * @return the the number of blocks
     */
    int numberOfBlocksToRemove();
}
