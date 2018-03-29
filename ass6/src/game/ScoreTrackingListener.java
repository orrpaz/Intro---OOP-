package game;
import base.Counter;
import base.HitListener;


/**
 *  represents a score tracking listener object.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * update the score.
     * @param beingHit the block that being hit
     * @param hitter  the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHits() == 0) {
            this.currentScore.increase(10);
        } else {
            this.currentScore.increase(5);
        }
    }
}
