import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import biuoop.Sleeper;



/**
 * created by Or on 08/04/2017.
 * This class create multi ball in board.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * converts an array from strings to integers.
     * @param numbers - the strings array
     * @return array of int
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            try {
                arr[i] = (int) Double.parseDouble(numbers[i]);
            } catch (Exception e) {
                System.out.println("Invalid argument");
                System.exit(1);
            }
        }
        return arr;
    }


    /**
     * the method generate ball according to velocity and put them in array of balls.
     * @param arrayOfRadius - array of radius.
     * @param startScreen - point of start screen ( Upper left edge )
     * @param endScreen - point of end screen ( Right bottom edge )
     * @param  maxRadius - the bigger radius.
     * @return array of balls
     */
    public Ball[] generateBalls(int[] arrayOfRadius, int maxRadius, Point startScreen, Point endScreen) {
        Random rand = new Random();
        int length = arrayOfRadius.length;
        Ball[] balls = new Ball[length];

        for (int i = 0; i < length; ++i) {
            if (arrayOfRadius[i] <= 0) {
                System.out.println("Invalid argument");
                System.exit(1);
            }
            balls[i] = new Ball(
                     rand.nextInt((int) endScreen.getX()), rand.nextInt((int) endScreen.getY()),
                    arrayOfRadius[i], java.awt.Color.BLACK, startScreen, endScreen);
            if (!balls[i].isValid()) {
                System.out.println("radius is bigger than screen");
                System.exit(1);
            }
                Velocity velocity = Velocity.fromAngleAndSpeed(rand.nextInt(360),
                        maxRadius / (double) balls[i].getSize());
                balls[i].setVelocity(velocity);
            }

        return balls;
    }

    /**
     * open a window and draws on it moving ball with suitable radius.
     * @param args - get from the user.
     * @param startScreen - point of start screen ( Upper left edge )
     * @param endScreen - point of end screen ( Right bottom edge )
     */
    public void drawBalls(String[] args, Point startScreen, Point endScreen) {
        int[] arrayOfRadius = stringsToInts(args);
        int maxRadius = arrayOfRadius[0];
        for (int i = 1; i < arrayOfRadius.length; ++i) {
            if (arrayOfRadius[i] > maxRadius) {
                maxRadius = arrayOfRadius[i];
            }
        }
        Ball[] balls = generateBalls(arrayOfRadius, maxRadius, startScreen, endScreen);
        GUI gui = new GUI("title", (int) endScreen.getX(), (int) endScreen.getY());
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; ++i) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(5);  // wait for 10 milliseconds.

        }
    }

    /**
     * main method.
     * @param args - get from the user.
     */
    public static void main(String[] args) {
        Point startScreen = new Point(0, 0);
        Point endScreen = new Point(300, 400);
        MultipleBouncingBallsAnimation d = new MultipleBouncingBallsAnimation();
        d.drawBalls(args, startScreen, endScreen);
    }
}
