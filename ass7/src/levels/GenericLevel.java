package levels;
import base.Sprite;
import base.Velocity;
import game.Block;
import game.Filling;


import java.util.List;
import java.util.Map;

/**
 * this class represent a level.
 */
public class GenericLevel implements LevelInformation {
    /**
     * members.
     */
    private String levelName;
    private List<Velocity> ballVelocities;
    private Filling background;
    private Map<String, Integer> paddleDetail;
    private int numberOfBlocks;
    private int numberOfBalls;
    private List<Block> blocks;

    /**
     * constrctor.
     *
     * @param levelName      the level name
     * @param ballVelocities the ball velocities
     * @param background     the backround
     * @param paddleDetail   the paddle speed and the paddle width
     * @param numberOfBlocks the num of blocks
     * @param numberOfBalls  the num of balls
     * @param blocks         the blocks
     */
    public GenericLevel(String levelName, List<Velocity> ballVelocities,
                        Filling background, Map<String, Integer> paddleDetail,
                        int numberOfBlocks, int numberOfBalls, List<Block> blocks) {
        this.levelName = levelName;
        this.ballVelocities = ballVelocities;
        this.background = background;
        this.paddleDetail = paddleDetail;
        this.numberOfBlocks = numberOfBlocks;
        this.numberOfBalls = numberOfBalls;
        this.blocks = blocks;

    }

    /**
     * getting the number of balls.
     *
     * @return the num of balls
     */
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    /**
     * getting the balls velocities.
     *
     * @return the balls velocities
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocities;
    }

    /**
     * getting the paddle speed.
     *
     * @return paddle speed
     */
    public int paddleSpeed() {
        return this.paddleDetail.get("paddle speed");
    }

    /**
     * getting the paddle width.
     *
     * @return paddle width
     */
    public int paddleWidth() {
        return this.paddleDetail.get("paddle width");
    }

    /**
     * getting the level name.
     *
     * @return level name
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * getting the background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Background(this.background);
    }

    /**
     * getting the list o blocks.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * getting the num of blocks.
     *
     * @return the num of blocks
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocks;
    }
}
