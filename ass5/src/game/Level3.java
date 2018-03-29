package game;
import base.Sprite;
import base.Velocity;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the third level.
 */
public class Level3 implements LevelInformation {
    /**
     * return the number of balls in the game.
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 2;
    }
    /**
     * initial the balls velocities.
     * @return the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelo = new ArrayList<Velocity>();
        listVelo.add(Velocity.fromAngleAndSpeed(35, 5));
        listVelo.add(Velocity.fromAngleAndSpeed(325, 5));
        return listVelo;
    }
    /**
     * return the paddle speed.
     * @return the paddle speed
     */
    public int paddleSpeed() {
        return 10;
    }
    /**
     * return the paddle width.
     * @return the paddle width
     */
    public int paddleWidth() {
        return 120;
    }
    /**
     * return the level name.
     * @return the level name
     */
    public String levelName() {
        return "Green 3";
    }
    /**
     * return the background.
     * @return the background
     */
    public Sprite getBackground() {
        return new Green3();
    }
    /**
     * create the blocks.
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();

        Color[] colors = {Color.decode("#F37A30"), Color.decode("#8133F3"),
                Color.decode("#B9AC26"),  Color.decode("#6196F3"), Color.decode("#732546")};
        int[] hitPoints = {2, 1, 1, 1, 1 };
        int y = 140;
        for (int i = 0; i < colors.length; i++) {
            for (int j = i + 5; j < 15; j++) {
                listBlocks.add(new Block(j * 50 + 25, y, 50, 20, colors[i], hitPoints[i]));
            }
            y += 20;
        }
        return listBlocks;
    }

    /**
     * return the number of blocks.
     * @return the the number of blocks
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

}