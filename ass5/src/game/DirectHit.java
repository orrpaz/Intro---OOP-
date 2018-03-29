package game;
import base.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class represents the background of the first level.
 */
public class DirectHit implements Sprite {
    /**
     * draw background.
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLUE);
        d.drawCircle(400, 165, 50);
        d.drawCircle(400, 165, 80);
        d.drawCircle(400, 165, 110);
        d.drawLine(400, 0, 400, 140);
        d.drawLine(420, 165, 575, 165);
        d.drawLine(400, 190, 400, 330);
        d.drawLine(220, 165, 375, 165);
    }
    /**
     * do nothing.
     */
    public void timePassed() {
    }

    /**
     * add the background to game.
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
