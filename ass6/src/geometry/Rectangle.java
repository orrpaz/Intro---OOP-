package geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * this class Create a new rectangle with location and width/height.
 */
public class Rectangle {
    private Line up;
    private Line down;
    private Line left;
    private Line right;
    /**
     * construct a rectangle from an upper left point, width and height.
     * @param upperLeft - the rectangle upper left point.
     * @param width - the rectangle's width.
     * @param height - the rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.up = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY());
        this.down = new Line(upperLeft.getX() , upperLeft.getY() + height,
                upperLeft.getX() + width, upperLeft.getY() + height);
        this.left = new Line(upperLeft.getX(), upperLeft.getY(),
                upperLeft.getX(), upperLeft.getY() + height);
        this.right = new Line(upperLeft.getX() + width, upperLeft.getY(),
                upperLeft.getX() + width, upperLeft.getY() + height);
    }
    /**
     * construct a rectangle from two coordinates, width and height.
     * @param x - the x value
     * of the rectangle's upper left corner.
     * @param y - the value
     * of the rectangle's upper left corner.
     * @param width - the rectangle's width.
     * @param height - the rectangle's height.
     */
    public Rectangle(double x, double y, double width, double height) {
        this.up = new Line(x, y, x + width, y);
        this.down = new Line(x, y + height, x + width, y + height);
        this.left = new Line(x, y, x, y + height);
        this.right = new Line(x + width, y, x + width, y + height);
    }
    /**
     * this method returns a List of this rectangle's
     * intersection points with a given line.
     * @param line - the given line.
     * @return List of intersection point with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> listPoint = new ArrayList<>();
        if (line.isIntersecting(this.up)) {
            listPoint.add(line.intersectionWith(this.up));
        }
        if (line.isIntersecting(this.down)) {
            listPoint.add(line.intersectionWith(this.down));
        }
        if (line.isIntersecting(this.left)) {
            listPoint.add(line.intersectionWith(this.left));
        }
        if (line.isIntersecting(this.right)) {
            listPoint.add(line.intersectionWith(this.right));
        }
        return listPoint;
    }

    /**
     * Return the width of the rectangle.
     * @return - the width  of the rectangle
     */
    // Return the width and height of the rectangle
    public double getWidth() {
        return this.up.length();
    }

    /**
     * the height of the rectangle.
     * @return - the height of the rectangle
     */
    public double getHeight() {
        return this.left.length();
    }
    /**
     * this method returns the rectangle's up line.
     * @return the rectangle's up line.
     */
    public Line getUp() {
        return this.up;
    }
    /**
     * this method returns the rectangle's buttom line.
     * @return the rectangle's buttom line.
     */
    public Line getDown() {
        return this.down;
    }
    /**
     * this method returns the rectangle's left line.
     * @return the rectangle's right line.
     */
    public Line getLeft() {
        return this.left;
    }
    /**
     * this method returns the rectangle's right line.
     * @return the rectangle's right line.
     */
    public Line getRight() {
        return this.right;
    }

    /**
     *  this method return the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.up.intersectionWith(this.left);
    }
}