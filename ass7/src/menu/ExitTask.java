package menu;

import biuoop.GUI;

/**
 * this class represents a ExitTask object.
 */
public class ExitTask implements Task<Void> {
    private GUI gui;
    /**
     * constructor.
     * @param gui the given GUI.
     */
    public ExitTask(GUI gui) {
        this.gui = gui;
    }
    /**
     * run this task.
     * @return unimplemented option.
     */
    public Void run() {
        System.exit(0);
        return null;
    }
}
