import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;


/**
 * Created by Or on 08/04/2017.
 */
public class BouncingBallAnimation {
    /**
     * @param args - didn't get anything.
     */
    public static void main(String[] args) {

        Point startScreen = new Point(0, 0);
        Point endScreen = new Point(200, 200);
        GUI gui = new GUI("title", (int) endScreen.getX(), (int) endScreen.getY());
        Sleeper sleeper = new Sleeper();
        java.util.Random rand = new java.util.Random();
        Ball ball = new Ball(0, 0, 30,
                java.awt.Color.BLACK, startScreen, endScreen);
       // ball.setVelocity(2, 2);
        ball.setVelocity(Velocity.fromAngleAndSpeed(60, 2));

        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(10);  // wait for 50 milliseconds.
        }
    }
}

// rand.nextInt(141) + 30, rand.nextInt(141) + 30