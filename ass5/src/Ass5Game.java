import java.util.ArrayList;
import java.util.List;
import animation.AnimationRunner;
import base.Counter;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.GameFlow;
import game.Level1;
import game.Level2;
import game.Level3;
import game.Level4;
import game.LevelInformation;


/**
 * this class is the main class.
 */
public class Ass5Game {
    /**
     * the main of the game.
     *
     * @param args the order of the levels
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        Counter counter = new Counter(0);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        GameFlow myGame = new GameFlow(ar, keyboard,
                7, 800, 600);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        // no args.
        if (args.length == 0) {
            addLevelsToList(levels);
        } else {
            for (int i = 0; i < args.length; i++) {
                try {
                    switch (Integer.parseInt(args[i])) {
                        case 1:
                            levels.add(new Level1());
                            break;
                        case 2:
                            levels.add(new Level2());
                            break;
                        case 3:
                            levels.add(new Level3());
                            break;
                        case 4:
                            levels.add(new Level4());
                            break;
                        default:
                       counter.increase(1);
                            break;
                    }
                } catch (NumberFormatException e) {
                    continue;
                }
            }

            if (counter.getValue() == args.length) {
                addLevelsToList(levels);
            }
        }
            myGame.runLevels(levels);
            gui.close();
        }

    /**
     * add all levels to the list.
     * @param levels lisf of levels
     */
    public static void addLevelsToList(List<LevelInformation> levels) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
    }
