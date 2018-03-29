import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;



/**
 * this class manages the game handling.
 */
public class Game {
    static final int HORZINONTAL_BOUND = 800;
    static final int VERTICAL_BOUND = 600;
    static final int SIZE_OF_BLOCK = 20;
    private SpriteCollection sprite;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;


    /**
     * this method constructs a game object.
     */
    public Game() {
        this.environment = new GameEnvironment();
        this.sprite = new SpriteCollection();
        this.sleeper = new Sleeper();
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
     * this method initializes the game by creating Blocks, Balls and Paddle.
     */
    public void initialize() {

        this.gui = new GUI("Game", HORZINONTAL_BOUND, VERTICAL_BOUND);
        // create the boundaries of screen.
        Block topBorder = new Block(0, 0, HORZINONTAL_BOUND, SIZE_OF_BLOCK, Color.GRAY, 0);
        Block buttomBorder = new Block(0, VERTICAL_BOUND - SIZE_OF_BLOCK,
                HORZINONTAL_BOUND, SIZE_OF_BLOCK, Color.GRAY, 0);
        Block leftBorder = new Block(0, SIZE_OF_BLOCK, SIZE_OF_BLOCK,
                VERTICAL_BOUND - 2 * SIZE_OF_BLOCK, Color.GRAY, 0);
        Block rightBorder = new Block(HORZINONTAL_BOUND - SIZE_OF_BLOCK, SIZE_OF_BLOCK,
                SIZE_OF_BLOCK, VERTICAL_BOUND - 2 * SIZE_OF_BLOCK, Color.GRAY, 0);
        topBorder.addToGame(this);
        buttomBorder.addToGame(this);
        leftBorder.addToGame(this);
        rightBorder.addToGame(this);
        int width = 50;
        int height = 20;
        int upperPointY = 100;
        int counter = 12;
        int numOfHits = 2;
        for (int i = 0; i < 6; ++i) {

            for (int j = counter; j > 0; --j) {
                Block block = new Block(HORZINONTAL_BOUND - height - j * 50,
                        upperPointY + i * height, width, height, this.selectColor(i), numOfHits);
                block.addToGame(this);
            }
            counter--;
            numOfHits = 1;
        }
        // create paddle.
        Paddle paddle = new Paddle((double) HORZINONTAL_BOUND / 2 - width,
                (double) VERTICAL_BOUND - 2 * height, width * 3, height,
                20, this.gui, Color.magenta);
        paddle.addToGame(this);

        // create 2 balls and add to game.
        Ball ball = new Ball(HORZINONTAL_BOUND / 2 - width,
                VERTICAL_BOUND - 3 * height, 5, Color.black, this.environment);
        Ball ball2 = new Ball(HORZINONTAL_BOUND / 2 - width,
                VERTICAL_BOUND - 3 * height, 5, Color.black, this.environment);
        ball.addToGame(this);
        ball.setVelocity(Velocity.fromAngleAndSpeed(60, 4));
        ball2.addToGame(this);
        ball2.setVelocity(3, 5);
    }

    /**
     *  this method return differnt color.
     * @param i - index that that choose every iteration which color give back.
     * @return - color in array
     */
    public Color selectColor(int i) {
        Color[] colors;
        colors = new Color[6];
        colors[0] = new Color(243, 178, 70);
        colors[1] = new Color(129, 51, 243);
        colors[2] = new Color(81, 135, 17);
        colors[3] = new Color(97, 150, 243);
        colors[4] = new Color(115, 37, 70);
        colors[5] = new Color(185, 172, 38);
        return colors[i];

        }

    /**
     * this method starts the animation loop.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {

            long startTime = System.currentTimeMillis();
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, this.gui.getDrawSurface().getWidth(),
                    this.gui.getDrawSurface().getHeight());
            this.sprite.drawAllOn(d);
            this.gui.show(d);
            this.sprite.notifyAllTimePassed();
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
