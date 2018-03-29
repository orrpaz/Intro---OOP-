import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.util.Random;
import java.awt.Color;


/**
 * created by Or on 08/04/2017.
 * This class create multi frames in board.
 */
public class MultipleFramesBouncingBallsAnimation {
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
     * check which radius is the biggest.
     * @param arrayOfRadius - radiuses that we get from user in array(int)
     * @return the maximum radius in array
     */
    public static int maxRadius(int[] arrayOfRadius) {
        int max = arrayOfRadius[0];
        for (int i = 1; i < arrayOfRadius.length; ++i) {
            if (arrayOfRadius[i] > max) {
                max = arrayOfRadius[i];
            }
        }
        return max;
    }


    /**
     * this method generates an array of balls according to their range with suitable speed.
     * @param arrayOfRadius - radiuses that we get from user in array(int)
     * @param start - number that symbolize from where we need to run
     * @param end - number that symbolize until where we need to run
     * @param max - the biggest radius in array
     * @param p1 - point that symbolize the start of rectangle
     * @param p2 - point that symbolize the end of rectangle
     * @return - array of balls
     */
    public Ball[] generateBalls(int[] arrayOfRadius, int start, int end, int max, Point p1, Point p2) {
        Random rand = new Random();
        Ball[] balls = new Ball[end - start];

        int j = 0;
        for (int i = start; i < end; ++i, ++j) {
            if (arrayOfRadius[i] < 0) {
                System.out.println("Invalid argument");
                System.exit(1);
            }
            // create ball in the range.
            balls[j] = new Ball(
                     rand.nextInt((int) p2.getX()), rand.nextInt((int) p2.getY()),
                    arrayOfRadius[i], java.awt.Color.BLACK, p1, p2);
            if (!balls[j].isValid()) {
                System.out.println("Radius is bigger than screen.");
                System.exit(1);
            }
            // velocity with regard to the biggest radius.
            Velocity velocity = Velocity.fromAngleAndSpeed(rand.nextInt(360),
                    max / (double) balls[j].getSize());
            balls[j].setVelocity(velocity);
        }

        return balls;
    }

    /**
     * the method create new windows with 2 rectangles and between them balls that moves.
     * @param args - get from user.
     * @param startGreyBoard - point that symbolize the start of grey board
     * @param endGreyBoard -  point that symbolize the end of grey board
     * @param startYellowBoard - point that symbolize the start of yellow board
     * @param endYellowBoard -  point that symbolize the end of yellow board
     */
    public void drawBalls(String[] args , Point startGreyBoard, Point endGreyBoard,
                          Point startYellowBoard, Point endYellowBoard) {
        int[] arrayOfRadius = stringsToInts(args);
        GUI gui = new GUI("Titles", 700, 700);
        int maxRadius = maxRadius(arrayOfRadius);
        int length = arrayOfRadius.length;
        Ball[] balls1 = generateBalls(arrayOfRadius, 0, length / 2,
                maxRadius, startGreyBoard, endGreyBoard);
        Ball[] balls2 = generateBalls(arrayOfRadius, length / 2 , length,
                maxRadius, startYellowBoard, endYellowBoard);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.yellow);
            d.fillRectangle(450, 450, 150, 150);
            for (int i = 0; i < balls1.length; ++i) {

                balls1[i].moveOneStep();
                balls1[i].drawOn(d);
                balls2[i].moveOneStep();
                balls2[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(10);  // wait for 10 milliseconds.
        }

    }
    /**
     * main method.
     * @param args - get numbers from user
     */
    public static void main(String[] args) {
        Point startGreyBoard = new Point(50, 50);
        Point endGreyBoard = new Point(500, 500);
        Point startYellowBoard = new Point(450, 450);
        Point endYellowBoard = new Point(600, 600);
        MultipleFramesBouncingBallsAnimation s = new MultipleFramesBouncingBallsAnimation();
        s.drawBalls(args, startGreyBoard, endGreyBoard,  startYellowBoard, endYellowBoard);
    }
}
