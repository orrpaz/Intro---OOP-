package geometry;

/**
 * this class crete a point.
 */

public class Point {
    private double x;
    private double y;
    /**
     * constructor.
     * @param x - the x value of point
     * @param y - the y value of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * this method return the distance of this point to the other point.
     * @param other - another point.
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double dx = this.x - other.getX();
        double dy = this.y - other.getY();
        return Math.sqrt((dx * dx) + (dy * dy));
    }
    /**
     * check if 2 point is equal.
     * @param other - another point.
     * @return true if yes , false otherwise
     */
    public boolean equals(Point other) {

        return (this.x == other.getX()) && (this.y == other.getY());
    }
    /**
     * @return the x value of point
     */
    public double getX() {
        return this.x;
    }
    /**
     * @return the y value of point
     */
    public double getY() {
        return this.y;

    }
}
