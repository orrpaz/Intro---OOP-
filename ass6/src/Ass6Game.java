import animation.AnimationRunner;
//import biuoop.DrawSurface;
import biuoop.GUI;
import game.HighScoresTable;
import io.LevelSetReader;
import menu.ExitTask;
import menu.MenuAnimation;
import menu.MenuSelection;
import menu.StartGame;
import menu.Task;
import menu.ShowHiScoresTask;
import menu.Menu;
//import java.awt.Color;
import java.io.File;
import java.util.List;

/**
 * this class is the main class.
 */
public class Ass6Game {
    /**
     *  main.
     * @param args get argument from user.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
//        DrawSurface d = gui.getDrawSurface();
//        d.setColor(new Color(0x3E65A9));
//        d.fillRectangle(0, 0, d.getWidth() , d.getHeight());
//        d.setColor(Color.black);
//        d.drawText(200, 300 , "Loading...", 50);
//        gui.show(d);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        final biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        File highScoresFile = new File("highscores");
        HighScoresTable table = HighScoresTable.loadFromFile(highScoresFile);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Level Sets", gui.getKeyboardSensor(), animationRunner);

        List<MenuSelection> levelsSet;
        levelsSet = LevelSetReader.fromReader(args);
        for (MenuSelection selection : levelsSet) {
            subMenu.addSelection(selection.getKey(),
                    selection.getMessage(),
                    new StartGame(gui, animationRunner, table,
                            selection.getLevelSet(), 7, highScoresFile));
        }

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Arkanoid", keyboard, animationRunner);
        menu.addSubMenu("s", "Start game", subMenu);
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                animationRunner, gui.getKeyboardSensor(), table));
        menu.addSelection("e", "Exit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.resrtStatus();
        }
    }
}

