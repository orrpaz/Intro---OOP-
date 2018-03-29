package game;
import base.Counter;
import base.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 *  represents a score indicator object.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private geometry.Rectangle scoreRectangle;

    /**
     *
     * constructor.
     * @param score the given score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.scoreRectangle = new geometry.Rectangle(200, 0, 400, 15);
    }
    /**
     * create the block and draw the text.
     * @param surface The draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) this.scoreRectangle.getUpperLeft().getX(),
                (int) this.scoreRectangle.getUpperLeft().getY(),
                (int) this.scoreRectangle.getWidth(),
                (int) this.scoreRectangle.getHeight());
        surface.setColor(Color.BLACK);
        String stringScore = "Score: " + Integer.toString(this.score.getValue());
        surface.drawText((int) (this.scoreRectangle.getUpperLeft().getX()
                        + this.scoreRectangle.getWidth() / 2 - 20),
                (int) (this.scoreRectangle.getUpperLeft().getY()
                        + this.scoreRectangle.getHeight() / 2 + 5), stringScore , 15);

    }
    /**
     * do nothing.
     * @param dt the dt
     */
    public void timePassed(double dt) {

    }

    /**
     * add the object to the gameLevel.
     * @param gameLevel - the game Level.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}