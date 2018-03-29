package menu;

import animation.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import game.HighScoresTable;
import levels.LevelInformation;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * this class represents a StartGameTask object.
 */
public class StartGame implements Task<Void> {
    private GUI gui;
    private AnimationRunner animationRunner;
    private HighScoresTable table;
    private List<LevelInformation> levels;
    private int lives;
    private File highScoresFile;


    /**
     * constructor.
     * @param gui the GUI.
     * @param animationRunner the animationRunner.
     * @param table the highScoresTable.
     * @param levels the levelInformation list.
     * @param lives the number of lives.
     * @param highScoresFile the highScores file.
     */
    public StartGame(GUI gui, AnimationRunner animationRunner,
                     HighScoresTable table, List<LevelInformation> levels,
                     int lives, File highScoresFile) {
        this.gui = gui;
        this.animationRunner = animationRunner;
        this.table = table;
        this.levels = levels;
        this.lives = lives;
        this.highScoresFile = highScoresFile;

    }

    /**
     *  runs this task.
     * @return unimplemented option.
     */
    public Void run() {
        GameFlow game = new GameFlow(this.animationRunner,
                this.gui.getKeyboardSensor(), this.lives, this.table, this.gui.getDialogManager(),
                this.gui.getDrawSurface().getWidth(), this.gui.getDrawSurface().getHeight());
        game.runLevels(this.levels);
        try {
            table.save(this.highScoresFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}