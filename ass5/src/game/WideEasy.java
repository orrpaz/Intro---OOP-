package game;
import base.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class represents the background of the second level.
 */
public class WideEasy implements Sprite {
    /**
     * draw background.
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
            d.setColor(new Color(0x83B4EE));
            d.fillRectangle(0, 0, d.getWidth() , d.getHeight());

            d.setColor(new Color(210, 202, 149));
            d.fillCircle(350, 130, 80);

            for (int i = 1; i <= 100; i++) {
                d.drawLine(350, 130, 8 * i, 280);
            }

            d.setColor(new Color(197, 194, 70));
            d.fillCircle(350, 130, 70);

            d.setColor(new Color(255, 218, 44));
            d.fillCircle(350, 130, 60);
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
