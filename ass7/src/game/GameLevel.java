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
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import invaders.BlockEnemyCollection;
import base.Boundaries;
import levels.LevelInformation;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


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
    private LevelInformation level;
    private List<Shots> shoots;
    private BlockEnemyCollection enemyBlock;
    private List<Block> blocks;
    private Counter paddleHitCounter;
    private Paddle paddle;


    /**
     * constructor of gamelevel.
     *
     * @param level           the information about the level
     * @param keyboard        the keyboard
     * @param runner          the animation runner
     * @param score           the score counter
     * @param lives           the number of lives counter
     * @param boundaries      the boundaries of the game.
     * @param enemySpeed      the speed of enemy block
     */
    public GameLevel(LevelInformation level, KeyboardSensor keyboard, AnimationRunner runner, Counter score,
                     Counter lives, Boundaries boundaries, int enemySpeed) {
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
        this.horizontalBound = boundaries.horizontalBound();
        this.verticalBound = boundaries.verticalBound();
        this.sizeOfBlock = 1;
        this.paddleHitCounter = new Counter(1);
        this.paddle = new Paddle(horizontalBound / 2 - level.paddleWidth() / 2, verticalBound - 25,
                level.paddleWidth(), 15, this.sizeOfBlock, new Color(0x594575), keyboard);
        this.level = level;
//        this.sprite.addSprite(enemyBlock);
        this.shoots = new ArrayList<Shots>();
        this.enemyBlock = new BlockEnemyCollection(this, enemySpeed);
        this.blocks = new ArrayList<Block>();
        for (Block b : level.blocks()) {
            Block block = new Block(b);
            this.blocks.add(block);
            this.enemyBlock.addBlock(block);
        }
    }

    /**
     * this method adds a given collidable object to game.
     *
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
        Map<Integer, Filling> fillingList = new TreeMap<>();
        Filling newFilling = new Filling(Color.gray, null);
        Filling newFilling1 = new Filling(Color.cyan, null);
        fillingList.put(0, newFilling);
        fillingList.put(1, newFilling1);
        HitListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        HitListener blockRemover = new BlockRemover(this, this.remainingBlocks);
        HitListener ballRemover = new BallRemover(this, this.remainingBalls);
        HitListener blockRemover2 = new BlockRemover(this, new Counter(0));

        // create the boundaries of screen.
        Block topBorder = new Block(new Point(0, 15), this.horizontalBound,
                this.sizeOfBlock, Color.black, fillingList, 0, false);

        Block buttomBorder = new Block(new Point(this.sizeOfBlock, this.verticalBound),
                this.horizontalBound - 2 * this.sizeOfBlock, this.sizeOfBlock,
                Color.black, fillingList, 0, false);

        Block leftBorder = new Block(new Point(0, this.sizeOfBlock + 15) , this.sizeOfBlock,
                this.verticalBound - 15 - this.sizeOfBlock, Color.black, fillingList, 0, false);

        Block rightBorder = new Block(new Point(this.horizontalBound - this.sizeOfBlock, this.sizeOfBlock + 15),
                this.sizeOfBlock, this.verticalBound - 15 - this.sizeOfBlock,
                Color.black, fillingList, 0, false);

        this.level.getBackground().addToGame(this);
        topBorder.addToGame(this);
        buttomBorder.addToGame(this);
        buttomBorder.addHitListener(ballRemover);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        topBorder.addHitListener(ballRemover);
        buttomBorder.addHitListener(ballRemover);
        leftBorder.addHitListener(ballRemover);
        rightBorder.addHitListener(ballRemover);


        Block shield;
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 28; i++) {
                for (int k = 0; k < 3; k++) {
                    shield = new Block(new Point(60 + (i * 5) + (k * 270), 480 + (j * 5)),
                            5, 5, null, fillingList, 1, false);
                    shield.addHitListener(blockRemover2);
                    shield.addHitListener(ballRemover);
                    shield.addToGame(this);
                }
            }
        }
        this.enemyBlock.addToGame(this);
        for (Block block : this.blocks) {
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(ballRemover);
            block.addHitListener(scoreTrackingListener);
            block.addHitListener(this.enemyBlock);
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
        this.createBallAndPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprite));
        this.running = true;
        this.runner.run(this);
        this.paddle.removeFromGame(this);
//        this.removeSprite(this.paddle);
//        this.environment.getlist().remove(this.paddle);
        return;
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
        this.paddle.shoot(this, dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen()));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
        }
        if (this.paddleHitCounter.getValue() == 0 || this.enemyBlock.isFinish()) {
            this.running = false;
            this.enemyBlock.reset(dt);
            this.clearShots();
        }
        if ((this.keyboard.isPressed("b"))) { // *************** cheat << ***************************
            double x = this.paddle.getCollisionRectangle().getUp().middle().getX();
            double y = this.paddle.getCollisionRectangle().getUp().middle().getY() - 4;
           Shots shots = this.getPlayerShoot(new Point(x, y));
           shots.setVelocity(0, -300);
           shots.addToGame(this);
        }
        if ((this.keyboard.isPressed(KeyboardSensor.UP_KEY))) { // ****** cheat <<*************
            this.numberOfLives.increase(1);
        }
    }

    /**
     * create balls and paddle.
     */
    public void createBallAndPaddle() {
        // reset the counter.
        this.paddle = new Paddle(horizontalBound / 2 - level.paddleWidth() / 2, verticalBound - 25,
                level.paddleWidth(), 15, this.sizeOfBlock, new Color(0x594575), keyboard);
        this.paddleHitCounter = new Counter(1);
        HitListener pblr = new BallRemover(this, this.paddleHitCounter);
        this.paddle.setBounds(horizontalBound, verticalBound);
        this.paddle.addToGame(this);
        this.paddle.setVelocity(level.paddleSpeed());
        this.paddle.addHitListener(pblr);

    }

    /**
     * create new shoot to player.
     * @param point - center point of the shoot
     * @return new shoot (ball)
     */
    public Shots getPlayerShoot(Point point) {

        Shots shots = new Shots(point, 4, Color.WHITE, this.environment, false);
        this.shoots.add(shots);
        return shots;
    }

    /**
     * remove shoot from the game.
     */
    public void clearShots() {
        for (Shots shots : this.shoots) {
            shots.removeFromGame(this);
        }
    }

    /**
     * create shot of enemy(Alien).
     *
     * @param point center point of the shoot
     * @return new shoot (ball)
     */
    public Shots getEnemyShoot(Point point) {
        Shots shots = new Shots(point, 5, Color.RED, this.environment, true);
        this.shoots.add(shots);
        return shots;
    }
}