package levels;

import base.Sprite;
import biuoop.DrawSurface;
import game.Filling;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

/**
 * represent background.
 */
public class Background implements Sprite {
    private Filling background;
    /**
     * constructor.
     * @param background a filling for the backround
     */
        public Background(Filling background) {
            this.background = background;
        }

    /**
     * draw the surface.
     * @param d a draw surface to draw on.
     */
    public void drawOn(DrawSurface d) {
            if (background.isItImage()) {
                d.drawImage(0, 0, background.getImage());
            } else {
                Rectangle rectangle = new Rectangle(new Point(20, 20), 780, 580);
                int x1 = (int) rectangle.getUpperLeft().getX();
                int x2 = (int) rectangle.getUpperLeft().getY();
                int x3 = (int) rectangle.getWidth();
                int x4 = (int) rectangle.getHeight();
                d.setColor(this.background.getColor());
                d.fillRectangle(x1, x2, x3, x4);
            }
        }

    /**
     * do nothing.
     * @param dt the dt
     */
        public void timePassed(double dt) {
        }

    /**
     * add to game.
     * @param g thr game
     */
        public void addToGame(GameLevel g) {
            g.addSprite(this);
        }
    }

