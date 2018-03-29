package game;

import java.util.List;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import base.Counter;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import levels.LevelInformation;


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
    private HighScoresTable highScoresTable;
    private DialogManager dialog;

    /**
     * constructor.
     * @param ar an animation runner connected to a gui object.
     * @param numberOfLives the number of lives.
     * @param keybord the keybord.
     * @param horizontalBound the width of the gui.
     * @param verticalBound the height of the gui.
     * @param highScoresTable a table of high scores.
     * @param dialog a dialog manager
     * @param
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor keybord, int numberOfLives,
                    HighScoresTable highScoresTable, DialogManager dialog, int horizontalBound, int verticalBound) {
        this.animationRunner = ar;
        this.dialog = dialog;
        this.keyboardSensor = keybord;
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
        this.lives = new Counter(numberOfLives);
        this.score = new Counter(0);
        this.highScoresTable = highScoresTable;
        this.win = true;
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
        if (this.highScoresTable.getRank(this.score.getValue()) <= this.highScoresTable.size()) {
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
            this.highScoresTable.add(scoreInfo);
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new EndScreen(this.score, this.win)));
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, "space",
                new HighScoresAnimation(this.highScoresTable)));
       // this.animationRunner.run(new EndScreen(this.score, this.keyboardSensor, this.win));
    }
}
