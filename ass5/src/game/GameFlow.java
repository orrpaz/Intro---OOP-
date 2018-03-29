package game;

import java.util.List;

import animation.AnimationRunner;
import base.Counter;
import biuoop.KeyboardSensor;


/**
 * this class manages the game handling.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int horizontalBound;
    private int verticalBound;
    private Counter lives;
    private Counter score;
    private boolean win;
    /**
     * constructor.
     * @param ar an animation runner connected to a gui object.
     * @param keybord the keybord.
     * @param numberOfLives the number of lives.
     * @param horizontalBound the width of the gui.
     * @param verticalBound the height of the gui.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor keybord, int numberOfLives,
                    int horizontalBound, int verticalBound) {
        this.animationRunner = ar;
        this.keyboardSensor = keybord;
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
        this.lives = new Counter(numberOfLives);
        this.score = new Counter(0);
    }
    /**
     *  gets a list of levelInformation objects
     * and runs the appropriate levels.
     * @param levelsList the given list.
     */
    public void runLevels(List<LevelInformation> levelsList) {
        for (LevelInformation levelInfo : levelsList) {
        GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.score,
                    this.lives, this.horizontalBound,
                    this.verticalBound);
            level.initialize();

            ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
            LivesIndicator livesIndicator = new LivesIndicator(this.lives);
            NameIndicator nameIndicator = new NameIndicator(levelInfo.levelName());
            level.addSprite(livesIndicator);
            level.addSprite(scoreIndicator);
            level.addSprite(nameIndicator);
            while (this.lives.getValue() > 0) {
                level.playOneTurn();
                if (level.isWin()) {
                    this.score.increase(100);
                    this.win = true;
                    break;
                } else {
                    this.lives.decrease(1);
                }
            }
            if (this.lives.getValue() == 0) {
                this.win = false;
                break;
            }
        }
        this.animationRunner.run(new EndScreen(this.score, this.keyboardSensor, this.win));
    }
}
