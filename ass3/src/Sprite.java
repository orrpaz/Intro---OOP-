import biuoop.DrawSurface;
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
     */
    void timePassed();

    /**
     * add the object to the game.
     * @param game - the game.
     */
    void addToGame(Game game);
}