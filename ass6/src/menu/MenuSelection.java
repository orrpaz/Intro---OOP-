package menu;

import levels.LevelInformation;

import java.util.List;

/**
 * this class represents a menu selection object.
 */
public class MenuSelection {
    private String key;
    private String message;
    private List<LevelInformation> levelSet;
    /**
     * constructor.
     * @param key the given key.
     * @param message the given message.
     * @param levelSet the given level set.
     */
    public MenuSelection(String key, String message,
                         List<LevelInformation> levelSet) {
        this.key = key;
        this.message = message;
        this.levelSet = levelSet;
    }
    /**
     *  return this menuSelection's key.
     * @return this menuSelection's key.
     */
    public String getKey() {
        return this.key;
    }
    /**
     *  return this menuSelection's message.
     * @return this menuSelection's message.
     */
    public String getMessage() {
        return this.message;
    }
    /**
     *  return this menuSelection's level set.
     * @return this menuSelection's level set.
     */
    public List<LevelInformation> getLevelSet() {
        return this.levelSet;
    }
}
