package menu;

import animation.Animation;

/**
 * this interface represents a menu.
 * @param <T> the menu type.
 */
public interface Menu<T> extends Animation {
    /**
     * adds a selection to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param returnVal the selection value according to the menu type.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * this method returns the selected value after the occurrence
     * of a selection event, according to the menu type.
     * @return the selected value.
     */
    T getStatus();
    /**
     * adds a sub-menu to this menu.
     * @param key the key of the selection.
     * @param message the name of the selection.
     * @param subMenu the given sub-menu.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
    /**
     * resets this status.
     */
    void resrtStatus();
}
