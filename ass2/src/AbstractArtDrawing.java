import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;
/**
 * this class is a short program of simple abstract art drawing.
 */
public class AbstractArtDrawing {
    /**
     * create random lines in the area.
     * @param width - the width of the windows.
     * @param height - the height the windows.
     * @return a Line wirh random points.
     */
    public Line generateRandomLine(int width, int height) {
        Random rand = new Random();
        // create random point.
        Point start = new Point(rand.nextInt(width) + 1,
                rand.nextInt(height) + 1);
        Point end = new Point(rand.nextInt(width) + 1,
                rand.nextInt(height) + 1);
        // return new line with 2 random point in range.
        return new Line(start, end);
    }
    /**
     * draws a black line on a given DrawSurface.
     * @param line -  a line
     * @param d - a DrawSurface to draw on.
     */
    private void drawLine(Line line, DrawSurface d) {
        int x1 = (int) (line.start().getX());
        int y1 = (int) (line.start().getY());
        int x2 = (int) (line.end().getX());
        int y2 = (int) (line.end().getY());
        d.setColor(Color.BLACK);
        d.drawLine(x1, y1, x2, y2);
    }
    /**
     * draws a blue line's middle point.
     * @param line - a Line object we want to mark on it
     * @param d - a DrawSurface to draw on.
     */
   private void drawBlueMiddle(Line line, DrawSurface d) {
       // put x value of middle point on x.
        int xMid = (int) (line.middle().getX());
       // put y value of middle point on y.
        int yMid = (int) (line.middle().getY());
        d.setColor(Color.BLUE);
        d.fillCircle(xMid, yMid, 3);


    }
    /**
     * draws all the intersection points in red on a given DrawSurface.
     * @param arrayLines - an array of lines.
     * @param d - a DrawSurface to draw on.
     */
    private void drawRedIntersection(Line[] arrayLines, DrawSurface d) {
        for (int i = 0; i < arrayLines.length - 1; ++i) {
            for (int j = i + 1; j < arrayLines.length; ++j) {
                if (arrayLines[i].isIntersecting(arrayLines[j])) {
                    // put the xInter on x.
                    int x = (int) (arrayLines[i].intersectionWith(arrayLines[j]).getX());
                    // put the yInter on y.
                    int y = (int) (arrayLines[i].intersectionWith(arrayLines[j]).getY());
                    d.setColor(Color.RED);
                    d.fillCircle(x, y, 3);
                }
            }
        }
    }
    /**
     * shows a window with 10 random black lines with blue point that symbolize middle point.
     * and red point that symbolize intersection point
     */
    private void drawRandomLines() {
        GUI gui = new GUI("Random Lines", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        Line[] arr = new Line[10];
        for (int i = 0; i < 10; ++i) {
            // put line that created randomaly in array
            arr[i] = generateRandomLine(400, 300);
            drawLine(arr[i], d);
            drawBlueMiddle(arr[i], d);
        }
        drawRedIntersection(arr, d);
        gui.show(d);
    }
    /**
     * main method.
     * @param args - dont get from user
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}