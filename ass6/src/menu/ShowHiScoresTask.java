package menu;

import animation.AnimationRunner;
import animation.HighScoresAnimation;
import biuoop.KeyboardSensor;
import game.HighScoresTable;
import animation.KeyPressStoppableAnimation;

/**
 * this class represents a ShowHiScoresTask object.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private KeyboardSensor keyboardSensor;
    private HighScoresTable highScoresTable;
    /**
     * constructor.
     * @param runner the given AnimationRunner.
     * @param keyboardSensor the given KeyboardSensor.
     * @param highScoresTable the given HighScoresTable.
     */
    public ShowHiScoresTask(AnimationRunner runner, KeyboardSensor keyboardSensor, HighScoresTable highScoresTable) {
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.highScoresTable = highScoresTable;
    }
    /**
     * this method runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        this.runner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, "space", new HighScoresAnimation(highScoresTable)));
        return null;
    }
}
