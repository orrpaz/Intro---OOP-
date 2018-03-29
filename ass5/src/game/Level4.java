package game;

import base.Sprite;
import base.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * This class represents the fourth level.
 */
public class Level4 implements LevelInformation {
    /**
     * return the number of balls in the game.
     * @return the number of balls in the game
     */
    public int numberOfBalls() {
        return 10;
    }

    /**
     * initial the balls velocities.
     * @return the list of the velocities
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> listVelo = new ArrayList<Velocity>();
        listVelo.add(Velocity.fromAngleAndSpeed(35, 5));
        listVelo.add(Velocity.fromAngleAndSpeed(325, 5));
        listVelo.add(Velocity.fromAngleAndSpeed(0, 5));
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
        return 140;
    }

    /**
     * return the level name.
     * @return the level name
     */
    public String levelName() {
        return "Final Four";
    }

    /**
     * return the background.
     * @return the background
     */
    public Sprite getBackground() {
        return new Final4();
    }

    /**
     * create the blocks.
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();
        int y = 120;
        Color[] colors = {Color.decode("#0A6649"), Color.decode("#236de5"), Color.decode("#8423e5"),
                Color.decode("#88966b"), Color.decode("#967e6b"), Color.decode("#8d3040"), Color.decode("#bedd0b")};
        int[] hitPoints = {2, 1, 1, 1, 1, 1, 1};
        for (int i = 0; i < colors.length; ++i) {
            for (int j = 0; j < 15; ++j) {
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