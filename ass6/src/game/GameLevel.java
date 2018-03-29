package game;

import animation.PauseScreen;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import base.HitListener;
import base.Collidable;
import base.Counter;
import base.Sprite;
import base.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * this class manages the game handling.
 */
public class GameLevel implements Animation {
    private int horizontalBound;
    private int verticalBound;
    private KeyboardSensor keyboard;
    private int sizeOfBlock = 25;
    private SpriteCollection sprite;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private Counter numberOfLives;
    private boolean running;
    private AnimationRunner runner;
    private List<Velocity> initialBallsVelocity;
    private LevelInformation level;


    /**
     * constructor of gamelevel.
     *
     * @param level           the information about the level
     * @param keyboard        the keyboard
     * @param runner          the animation runner
     * @param score           the score counter
     * @param lives           the number of lives counter
     * @param horizontalBound the horizontal Bound
     * @param verticalBound   the vertical Bound
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score,
                     Counter lives,
                     int horizontalBound,
                     int verticalBound) {
        this.environment = new GameEnvironment();
        this.score = score;
        this.sprite = new SpriteCollection();
        this.level = level;
        this.remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        this.score = score;
        this.numberOfLives = lives;
        this.running = true;
        this.runner = runner;
        this.keyboard = keyboard;
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
        this.initialBallsVelocity = level.initialBallVelocities();
        this.sizeOfBlock = 25;
        this.level = level;
    }

    /**
     * this method adds a given collidable object to game.
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * this method adds a given sprite object to the game.
     *
     * @param s the given sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprite.addSprite(s);
    }

    /**
     * this method initializes the game by creating Blocks, Balls and game.Paddle.
     */
    public void initialize() {
        Map<Integer, Filling> fillingList = new HashMap<Integer, Filling>();
        Filling newFilling = new Filling(Color.gray, null);
        fillingList.put(0, newFilling);
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        HitListener blockRemover = new BlockRemover(this, this.remainingBlocks);
        HitListener ballRemover = new BallRemover(this, this.remainingBalls);

        // create the boundaries of screen.
        Block topBorder = new Block(0, 15, this.horizontalBound,
                this.sizeOfBlock, Color.black, fillingList, 0);

        Block buttomBorder = new Block(0, this.verticalBound,
                this.horizontalBound - 2 * this.sizeOfBlock, this.sizeOfBlock, Color.black, fillingList, 0);

        Block leftBorder = new Block(0, this.sizeOfBlock + 15, this.sizeOfBlock,
                this.verticalBound - 15 - this.sizeOfBlock, Color.black, fillingList, 0);

        Block rightBorder = new Block(this.horizontalBound - this.sizeOfBlock, this.sizeOfBlock + 15,
                this.sizeOfBlock, this.verticalBound - 15 - this.sizeOfBlock, Color.black, fillingList, 0);

        this.level.getBackground().addToGame(this);
        topBorder.addToGame(this);
        buttomBorder.addToGame(this);
        buttomBorder.addHitListener(ballRemover);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        List<Block> blocks = level.blocks();
        List<Block> cpyBlocks = new ArrayList<>();
        for (int j = 0; j < blocks.size(); j++) {
            Block block = new Block(blocks.get(j));
            cpyBlocks.add(block);
        }
        for (Block block : cpyBlocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * check if the user win.
     *
     * @return true if remain of block is 0 and false otherwise.
     */
    public boolean isWin() {
        return this.remainingBlocks.getValue() == 0;
    }

    /**
     * this method starts the animation loop.
     */
    public void playOneTurn() {
        Paddle gamePaddle = this.createBallAndPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprite));
        this.running = true;
        this.runner.run(this);
        this.removeSprite(gamePaddle);
        this.environment.getlist().remove(gamePaddle);

    }

    /**
     * remove the collidable from the list.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.getlist().remove(c);
    }

    /**
     * remove the collidable from the list.
     *
     * @param s - the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprite.getSpriteList().remove(s);
    }

    /**
     * het the block counter.
     *
     * @return the counter.
     */
    public Counter getBlocksCounter() {
        return this.remainingBlocks;
    }

    /**
     * return the counter balls class.
     *
     * @return the counter balls class
     */
    public Counter getBallsCounter() {
        return this.remainingBalls;
    }

    /**
     * tells if the level should stop.
     *
     * @return true if the level should stop, and false otherwise
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * show each frame of the level.
     *
     * @param dt the dt.
     * @param d  the draw surface
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprite.drawAllOn(d);
        this.sprite.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen()));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * vcreate balls and paddle.
     *
     * @return the paddle
     */
    public Paddle createBallAndPaddle() {

        for (Velocity i : this.initialBallsVelocity) {
            Ball ball = new Ball(this.horizontalBound / 2,
                    this.verticalBound - this.sizeOfBlock - 30,
                    5, Color.white, this.environment);
            ball.setVelocity(i);
            this.remainingBalls.increase(1);
            ball.addToGame(this);
        }

        // create the paddle
        Paddle paddle = new Paddle((double) (horizontalBound - this.level.paddleWidth()) / 2,
                (double) verticalBound - 25, this.level.paddleWidth(), 20,
                this.sizeOfBlock, new Color(0x594575), keyboard);
        paddle.addToGame(this);
        paddle.setVelocity(level.paddleSpeed());
        paddle.setBounds(horizontalBound, verticalBound);
        return paddle;

    }
}