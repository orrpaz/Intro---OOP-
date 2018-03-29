package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * this class represents an animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    private double dt;

    /**
     * constructor.
     * @param gui the gui
     * @param framesPerSecond the frames Per Second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.framesPerSecond = framesPerSecond;
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.dt = 1 / (double) framesPerSecond;

    }

    /**
     * the loop that run the game.
     * @param animation the animation that will run
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            if (animation.shouldStop()) {
                break;
            }
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = 1000 / this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}


