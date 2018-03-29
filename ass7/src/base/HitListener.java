package base;

import game.Shots;
import game.Block;
import game.Paddle;

/**
 * Created by Or on 22/05/2017.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the game.Shots that's doing the hitting.

    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit the block
     * @param hitter the ball hitter
     */
    void hitEvent(Block beingHit, Shots hitter);
    /**
     * called whenever the beingHit object is being hit.
     * @param beingHit the paddle that is being hit.
     * @param hitter the hitting ball.
     */
    void hitEvent(Paddle beingHit, Shots hitter);
}