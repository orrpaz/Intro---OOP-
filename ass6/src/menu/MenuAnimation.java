package menu;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Filling;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;


/**
 this class represents a menu animation object.
 * @param <T> the menu type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String menuTitle;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private List<String> messages;
    private List<T> options;
    private List<Menu<T>> subMenus;
    private List<String> keys;
    private T status;
    private boolean stop;
    private List<Boolean> isOption;
    private Image menuImage;

    /**
     * constructor.
     * @param menuTitle the given menuTitle.
     * @param keyboardSensor the given keyboardSensor.
     * @param animationRunner the given animationRunner.
     */
    public MenuAnimation(String menuTitle, KeyboardSensor keyboardSensor,
                         AnimationRunner animationRunner) {
        this.keyboardSensor = keyboardSensor;
        this.animationRunner = animationRunner;
        this.menuTitle = menuTitle;
        this.keys = new ArrayList<String>();
        this.messages = new ArrayList<String>();
        this.options = new ArrayList<T>();
        this.isOption = new ArrayList<Boolean>();
        this.stop = false;
        this.subMenus = new ArrayList<Menu<T>>();
        this.menuImage = Filling.imageFromString("Image/mainmenu.jpg");
    }
    /**
     * this method adds a selection to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keys.add(key);
        this.messages.add(message);
        this.options.add(returnVal);
        this.isOption.add(true);
        this.subMenus.add(null);

    }
    /**
     * returns the selected value after the occurrence
     * of a selection event, according to the menu type.
     * @return the selected value.
     */
    public T getStatus() {
        return this.status;
    }
    /**
     * adds a sub-menu to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param subMenu the given sub-menu.
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.keys.add(key);
        this.messages.add(message);
        this.subMenus.add(subMenu);
        this.options.add(null);
        this.isOption.add(false);
    }

    /**
     * draws the current state of the animation object to the screen.
     * @param d the draw surface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, this.menuImage);
        d.setColor(Color.black);
        for (int i = 0; i < this.keys.size(); i++) {
            d.drawText(70, 350 + i * 50, "(" + (this.keys.get(i)) + ")", 40);
            d.drawText(120, 350 + i * 50, this.messages.get(i), 40);
        }
        for (int i = 0; i < this.keys.size(); i++) {
            if (this.keyboardSensor.isPressed(this.keys.get(i))) {
                if (this.isOption.get(i)) {
                    this.status = this.options.get(i);
                    this.stop = true;
                } else {
                    this.animationRunner.run(this.subMenus.get(i));
                    this.status = this.subMenus.get(i).getStatus();
                    this.subMenus.get(i).resrtStatus();
                    this.stop = true;
                    break;
                }
            }
        }
    }
    /**
     * tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * this method resets this menu.
     */
    public void resrtStatus() {
        this.status = null;
        this.stop = false;
    }
}
