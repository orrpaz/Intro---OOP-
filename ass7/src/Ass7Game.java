import animation.AnimationRunner;
import biuoop.GUI;
import game.HighScoresTable;
import invaders.SpaceInvadersLevel;
import levels.LevelInformation;
import menu.ExitTask;
import menu.MenuAnimation;
import menu.StartGame;
import menu.Task;
import menu.ShowHiScoresTask;
import menu.Menu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * this class is the main class.
 */
public class Ass7Game {
    /**
     *  main.
     * @param args get argument from user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space Invaders", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        File highScoresFile = new File("highscores");
        HighScoresTable table = HighScoresTable.loadFromFile(highScoresFile);
        List<LevelInformation> level = new ArrayList<LevelInformation>();
        level.add(new SpaceInvadersLevel());
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Space Invaders", gui.getKeyboardSensor(), animationRunner);
        menu.addSelection("s", "Start Game", new StartGame(
                gui, animationRunner, table,
                level, 3, highScoresFile));
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                animationRunner, gui.getKeyboardSensor(), table));
        menu.addSelection("e", "Exit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
           menu.resetStatus();
        }
    }
}
