package game;

import base.Counter;
import base.HitListener;

/**
 * this class represents a ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter removedBalls;

    /**
     * constructor.
     * @param gameLevel the class of the game
     * @param removedBalls  the counter of the balls
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.removedBalls = removedBalls;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     * @param beingHit the block
     * @param hitter the ball hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.removedBalls.decrease(1);
    }
}
