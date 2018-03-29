package game;

import base.HitListener;


/**
 * This class represents an object that prints information about blocks
 * that were hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * this method prints information about blocks.
     * that were hit.
     * @param beingHit the block that was hit.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A game.Block with "
                + beingHit.getHits() + " points was hit.");
    }
}