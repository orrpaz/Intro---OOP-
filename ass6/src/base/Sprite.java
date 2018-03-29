package base;

import biuoop.DrawSurface;
import game.GameLevel;

/**
 * this interface represents a drawable object.
 */
public interface Sprite {
    /**
     * this method draws the sprite object to the screen.
     * @param d a draw surface to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * this method notifies the sprite object that a time unit has passed.
     * @param dt the dt.
     */
    void timePassed(double dt);

    /**
     * add the object to the gameLevel.
     * @param gameLevel - the gameLevel.
     */
    void addToGame(GameLevel gameLevel);
}