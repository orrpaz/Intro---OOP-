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
    public void hitEvent(Block beingHit, Shots hitter) {
//        if (beingHit.getHits() == 0) {
        if (beingHit.isAlienBlock() && hitter.isAlienShot()) {
            return;
        }
            this.currentScore.increase(100);
//        } else {
//            this.currentScore.increase(5);
//        }
    }

    @Override
    public void hitEvent(Paddle beingHit, Shots hitter) {

    }
}
