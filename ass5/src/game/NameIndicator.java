package game;

import java.awt.Color;
import base.Sprite;
import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * This class represents a level name indicator object.
 */
public class NameIndicator implements Sprite {
    private String levelName;
    private Rectangle rectangle;
    /**
     * construct a level name indicator from
     * a string of the level's name.
     * @param levelName the given string.
     */
    public NameIndicator(String levelName) {
        this.levelName = levelName;
        this.rectangle = new Rectangle(600, 0, 200, 15);
    }
    /**
     * this method draws the name indicator on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.WHITE);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(),
                (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                        + this.rectangle.getWidth() / 2 - 80),
                (int) (this.rectangle.getUpperLeft().getY()
                        + this.rectangle.getHeight() / 2 + 5),
                "Names Level: " + this.levelName, 15);
    }
    /**
     * this method notifies the name indicator that a time unit has passed.
     */
    public void timePassed() {
    }
    /**
     * this method adds the name indicator to a game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
