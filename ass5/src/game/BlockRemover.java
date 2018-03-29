package game;
import base.Counter;
import base.HitListener;

/**
 * this class represents a block remover.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel the game
     * @param remainingBlocks  the counter of the blocks that remaining
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     * @param beingHit the block
     * @param hitter the ball hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
    }
}

