package animation;

import biuoop.DrawSurface;
import game.SpriteCollection;

import java.awt.Color;

/**
 * This class represents a count down animation object.
 */
public class CountdownAnimation implements Animation {
    private double numOfMillis;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int numberCounting;
    private long initiationTime;
    private boolean running = true;

    /**
     * constructor.
     * @param numOfSeconds the number of seconds
     * @param countFrom  from which number to count
     * @param gameScreen  the sprites list
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.initiationTime = System.currentTimeMillis();
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numberCounting = countFrom;
    }


    /**
     * return the stop condition of the count down class.
     * @return the stop condition
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * draws each frame of the animation of the count down animation on a given DrawSurface.
     * @param d the draw surface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {


        if (countFrom == 1) {
            this.running = false;
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(new Color(117, 82, 46));
        d.drawText(380, 450, Integer.toString(countFrom), 80);
        if (System.currentTimeMillis() - this.initiationTime
                > this.numOfMillis / this.numberCounting) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
    }

}
