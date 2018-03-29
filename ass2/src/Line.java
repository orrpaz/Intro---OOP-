/**
 * Created by Or on 31/03/2017.
 * this class crete line that build from 2 point
 */

public class Line {
    private Point start;
    private Point end;
    /**
     * cconstructor from point.
     * @param start - a start point.
     * @param end - a end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * constructor from x1,y1,x2,y2.
     * @param x1 - x of the first point
     * @param y1 - y of the first point
     * @param x2 -  x of the second point
     * @param y2 - x of the second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }
    /**
     * calculate the length of line.
     * @return - the length of the line
     */
    // Return the length of the line
    public double length() {
        return Math.abs(this.start().distance(this.end()));
    }
    /**
     * calaculate the middle point of line.
     * @return - the middle point of the line
     */
    // Returns t
    public Point middle() {
        double xMid = (this.start.getX() + this.end.getX()) / 2;
        double yMid = (this.start.getY() + this.end.getY()) / 2;
        return new Point(xMid, yMid);
    }
    /**
     * @return - the start point of the line
     */
    // Returns the start point of the line
    public Point start() {
        return this.start;
    }
    /**
     * @return -  the end point of the line
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }
    /**
     * @param other - the method get a other line and calculate the intesection point
     * @return - the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        if (m1 == Double.NEGATIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY  || m1 == m2) {
            return null;
        }
        double xInter = xInter(other);
        double yInter = yInter(xInter);
        Point point = new Point(xInter, yInter);
        if (isBetweenValue(point) && other.isBetweenValue(point)) {
            return point;
        }
        return null;
    }
    /**
     * check if 2 line is intersecting.
     * @param other - the method get a other line
     * @return return true if the line is intersection and false if not.
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        Point p = intersectionWith(other);
        if (p == null) {
            return false;
        }
        return true;
    }
    /**
     * calculate the slope of lines.
     * @return - the slope of line or negetive infinity if we cant calculate it.
     */
    public double getSlope() {
        if (this.end.getX() == this.start.getX()) {
            return Double.NEGATIVE_INFINITY;
        }
        return (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX());
    }
    /**
     * @param other - another line that we want to check with our object.
     * @return true if equal and false otherwise
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
     return (this.start == other.start) && (this.end == other.end);
     }
    /**
     * the method check if the x value and y value of the intersection point is between start and end of line.
     * she also check which point is bigger and smaller.
     * @param interPoint - get the intersection point.
     * @return - true if is between the point and false otherwise
     */
    private boolean isBetweenValue(Point interPoint) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        double minX = Math.min(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        return ((interPoint.getX() >= minX) && (interPoint.getX() <= maxX))
                && ((interPoint.getY() >= minY) && (interPoint.getY() <= maxY));
    }
    /**
     * calculate the x value of intersection point.
     * @param other - the method get a other line
     * @return the x value.
     */
    private double xInter(Line other) {
        double m1 = this.getSlope();
        double m2 = other.getSlope();
        return ((m1 * this.start.getX()) - this.start.getY() - m2 * other.start.getX() + other.start.getY())
                / (m1 - m2);
    }
    /**
     * calculate the y value of intersection point.
     * @param xInter - get the x value of intersection to calculate the y value
     * @return - the y value
     */
    private double yInter(double xInter) {
        double m1 = this.getSlope();
        return m1 * xInter - m1 * this.start.getX() + this.start.getY();
    }

}
