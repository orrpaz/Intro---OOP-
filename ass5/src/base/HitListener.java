package base;

import game.Ball;
import game.Block;

/**
 * Created by Or on 22/05/2017.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the game.Ball that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block
     * @param hitter the ball hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}