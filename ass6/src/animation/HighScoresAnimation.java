package animation;
import biuoop.DrawSurface;
import game.HighScoresTable;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * represent a high score animation object.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScore;

    /**
     * constructor.
     * @param scores high score table
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScore = scores;
    }

    /**
     * draws each frame of the animation of the end screen.
     * @param d the DrawSurface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {

        InputStream is =
                ClassLoader.getSystemClassLoader().getResourceAsStream("Image/highscores.jpg");
        BufferedImage img = null;
        try {
            img = ImageIO.read(is);
            d.drawImage(0, 0, img);
        } catch (IOException e) {
            System.out.println("Error: failed to load image");
        }

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
