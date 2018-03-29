package animation;
import java.awt.Color;
import base.Counter;
import biuoop.DrawSurface;

/**
 * This class represents an end screen object.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean win;

    /**
     * constructor.
     * @param score the score counter.

     * @param win the boolean variable.
     */
    public EndScreen(Counter score, boolean win) {
        this.score = score.getValue();
        this.win = win;

    }
    /**
     * draws each frame of the animation of the end screen.
     * @param d the DrawSurface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(0x145557));
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.BLACK);
        if (this.win) {
            d.drawText(130, 200, "Congratulations!", 80);
            d.setColor(new Color(0x8133F3));
            d.drawText(134, 196, "Congratulations!", 80);

            d.setColor(Color.BLACK);
            d.drawText(210, 280, "You Won :)", 60);
            d.setColor(new Color(0x944B85));
            d.drawText(214, 276, "You Won :)", 60);
        } else {
            d.drawText(150, 200, "Game Over", 100);
            d.setColor(new Color(0x42C7F3));
            d.drawText(154, 196, "Game Over ", 100);

            d.setColor(Color.BLACK);
            d.drawText(180, 280, "You Lost :(", 60);
            d.setColor(new Color(0x2B987F));
            d.drawText(184, 276, "You Lost :(", 60);
        }
        d.setColor(Color.BLACK);
        d.drawText(250, 370, "Press space to continue", 25);
        d.setColor(Color.WHITE);
        d.drawText(320, 430, "Final score: " + this.score, 20);

    }
    /**
     * return true if the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed.
     * false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }

}
