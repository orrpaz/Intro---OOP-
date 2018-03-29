package invaders;
import base.Sprite;
import base.Velocity;
import game.Block;
import game.Filling;
import geometry.Point;
import levels.Background;
import levels.LevelInformation;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * this class represent a level.
 */
public class SpaceInvadersLevel implements LevelInformation {
    /**
     * getting the number of balls.
     *
     * @return the num of balls
     */
    public int numberOfBalls() {
        return 0;
    }

    /**
     * getting the balls velocities.
     *
     * @return the balls velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> lst = new ArrayList<Velocity>();
        return lst;
    }

    /**
     * getting the paddle speed.
     *
     * @return paddle speed
     */
    public int paddleSpeed() {
        return 300;
    }

    /**
     * getting the paddle width.
     *
     * @return paddle width
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * getting the level name.
     *
     * @return level name
     */
    public String levelName() {
        return "Level no. ";
    }

    /**
     * getting the background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Background(new Filling(Color.black, null));
    }

    /**
     * create list of blocks.
     *
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Map<Integer, Filling> filling = new HashMap<Integer, Filling>();
        Image image = Filling.imageFromString("block_images/enemy.png");
        filling.put(1, new Filling(null, image));
        Block block = new Block(new Point(0, 1), 40, 30, null, filling, 1, true);
        int y = 20;
        for (int i = 0; i < 5; i++) {
            int x = 150;
            for (int j = 0; j < 10; j++) {
                Block b = new Block(block);
                b.setUpperLeft(x, y);
                blocks.add(b);
                x += 50;
            }
            y += 40;
        }
        return blocks;
    }

    /**
     * getting the num of blocks.
     *
     * @return the num of blocks
     */
    public int numberOfBlocksToRemove() {
        return 50;
    }

}