package animation;
import biuoop.DrawSurface;
import game.Filling;
import game.HighScoresTable;

import java.awt.Color;
import java.awt.Image;


/**
 * represent a high score animation object.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScore;
    private Image highImage;

    /**
     * constructor.
     * @param scores high score table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScore = scores;
        this.highImage = Filling.imageFromString("Image/highscores.jpg");
    }

    /**
     * draws each frame of the animation of the end screen.
     * @param d the DrawSurface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawImage(0, 0, this.highImage);
        d.setColor(Color.cyan);
        for (int i = 0; i < this.highScore.getHighScores().size(); i++) {
            d.drawText(250, 230 + i * 30, i + 1 + ". "
                    + this.highScore.getHighScores().get(i).getName(), 30);
            d.drawText(
                    470,
                    230 + i * 30,
                    Integer.toString(this.highScore.getHighScores().get(i)
                            .getScore()), 30);
        }
        d.drawText(250, 450, "Press space to continue", 30);
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
