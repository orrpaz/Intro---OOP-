package animation;

import biuoop.DrawSurface;
/**
 *  interface of animation object.
 */
public interface Animation {
    /**
     * the method that do each frame of the game.
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * check if the game should stop.
     * @return true if the game should stop and false if not
     */
    boolean shouldStop();
}