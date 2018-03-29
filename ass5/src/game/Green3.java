package game;
import base.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 *  represents the background of the first level.
 */
public class Green3 implements Sprite {
    /**
     * draw background.
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0x17561D));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(145, 180, 10, 240);
        d.fillRectangle(135, 410, 30, 40);
        d.setColor(Color.ORANGE);
        d.fillCircle(150, 180, 10);
        d.setColor(Color.RED);
        d.fillCircle(150, 180, 7);
        d.setColor(Color.WHITE);
        d.fillCircle(150, 180, 3);
        d.setColor(Color.DARK_GRAY.darker());
        d.fillRectangle(100, 450, 100, 150);
        d.setColor(Color.WHITE);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(110 + 18 * i, 460 + 30 * j, 10, 25);
            }
        }
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
