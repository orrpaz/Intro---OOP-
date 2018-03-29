package game;

import base.Counter;
import base.Sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;

/**
 * Created by Or on 24/05/2017.
 */
public class LivesIndicator implements Sprite {
    private Counter numberOfLives;
    private Rectangle livesRectangle;
    /**
     *
     * constructor.
     * @param numberOfLives the given lives counter.
     */
    public LivesIndicator(Counter numberOfLives) {
        this.numberOfLives = numberOfLives;
        this.livesRectangle = new Rectangle(0, 0, 200, 15);
    }
    /**
     * create the block and draw the text.
     * @param surface The draw surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) this.livesRectangle.getUpperLeft().getX(),
                (int) this.livesRectangle.getUpperLeft().getY(),
                (int) this.livesRectangle.getWidth(),
                (int) this.livesRectangle.getHeight());
        surface.setColor(Color.BLACK);
        String stringLives = "Lives: " + Integer.toString(this.numberOfLives.getValue());
        surface.drawText((int) (this.livesRectangle.getUpperLeft().getX()
                        + this.livesRectangle.getWidth() / 2 - 20),
                (int) (this.livesRectangle.getUpperLeft().getY()
                        + this.livesRectangle.getHeight() / 2 + 5), stringLives , 15);

    }
    /**
     * this method notifies the sprite object that a time unit has passed.
     *@param dt the dt.
     */
    public void timePassed(double dt) {

    }

    /**
     * add the object to the gameLevel.
     * @param gameLevel - the gameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
