package game;

import base.Sprite;
import base.Velocity;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the second level.
 */
public class Level2 implements LevelInformation {
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
        for (int j = -50; j <= 50; j += 10) {
            if (j == 0) {
                continue;
            }
            listVelo.add(Velocity.fromAngleAndSpeed(j, 4));
        }
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
        return 600;
    }
    /**
     * return the level name.
     * @return the level name
     */
    public String levelName() {
        return "Wide Easy";
    }
    /**
     * return the background.
     * @return the background
     */
    public Sprite getBackground() {
        return new WideEasy();
    }
    /**
     * create the blocks.
     * @return the list of blocks
     */
    public List<Block> blocks() {
        List<Block> listBlocks = new ArrayList<Block>();
        listBlocks.add(new Block(25, 280, 50 , 20, Color.RED, 1));
        listBlocks.add(new Block(75, 280, 50 , 20, Color.RED, 1));
        listBlocks.add(new Block(125, 280, 50 , 20, Color.ORANGE, 1));
        listBlocks.add(new Block(175, 280, 50 , 20, Color.ORANGE, 1));
        listBlocks.add(new Block(225, 280, 50 , 20, Color.YELLOW, 1));
        listBlocks.add(new Block(275, 280, 50 , 20, Color.YELLOW, 1));
        listBlocks.add(new Block(325, 280, 50 , 20, Color.GREEN, 1));
        listBlocks.add(new Block(375, 280, 50 , 20, Color.GREEN, 1));
        listBlocks.add(new Block(425, 280, 50 , 20, Color.GREEN, 1));
        listBlocks.add(new Block(475, 280, 50 , 20, Color.BLUE, 1));
        listBlocks.add(new Block(525, 280, 50 , 20, Color.BLUE, 1));
        listBlocks.add(new Block(575, 280, 50 , 20, Color.PINK, 1));
        listBlocks.add(new Block(625, 280, 50 , 20, Color.PINK, 1));
        listBlocks.add(new Block(675, 280, 50 , 20, Color.CYAN, 1));
        listBlocks.add(new Block(725, 280, 50 , 20, Color.CYAN, 1));
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