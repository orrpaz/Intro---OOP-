package animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * This class represents a pause screen object.
 */
public class PauseScreen implements Animation {

    /**
     * draw pause screen.
     * @param d the draw surface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x2C2338));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(new Color(0x040D07));

        d.setColor(Color.BLACK);
        d.drawText(210, 210, "paused", 100);
        d.setColor(new Color(0x94171F));
        d.drawText(214, 206, "paused", 100);
        d.setColor(Color.BLACK);
        d.drawText(150, 290, "press space to continue", 50);
        d.setColor(new Color(0x754424));
        d.drawText(154, 286, "press space to continue", 50);

        d.setColor(new Color(0x2D66DD));
        d.fillCircle(200, 450, 90);
        d.setColor(new Color(0x040D07));
        d.fillRectangle(170, 395, 20, 110);
        d.fillRectangle(210, 395, 20, 110);

    }

    /**
     * return true if the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}
