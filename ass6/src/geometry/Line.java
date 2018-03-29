package geometry;

import java.util.List;

 /**
 * this class crete line that build from 2 point.
 */

public class Line {
    private Point start;
    private Point end;
    static final double EPSILON = 0.00001;

    /**
     * cconstructor from point.
     *
     * @param start - a start point.
     * @param end   - a end point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor from x1,y1,x2,y2.
     *
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
     *
     * @return - the length of the line
     */
    // Return the length of the line
    public double length() {
        return Math.abs(this.start().distance(this.end()));
    }

    /**
     * calaculate the middle point of line.
     *
     * @return - the middle point of the line
     */
    // Returns t
    public Point middle() {
        double xMid = Math.round((this.start.getX() + this.end.getX())) / 2;
        double yMid = Math.round((this.start.getY() + this.end.getY())) / 2;
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
        double xInter;
        double yInter;
        Point point;



        // m1 == Double.NEGATIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY  ||
        if (m1 == m2) {
            return null;
        }
        if (m1 == Double.NEGATIVE_INFINITY) {
            xInter = this.end.getX();
            // yInter is m2 * xInter - m2 * x of other line + y of other line.
            yInter = calcYIntersection(xInter, other);

        } else {
            if (m2 == Double.NEGATIVE_INFINITY) {
                xInter = other.end.getX();
            } else {
                calcXIntersection(other);
                xInter = ((m1 * this.end.getX()) - this.end.getY() - m2 * other.end.getX() + other.end.getY())
                        / (m1 - m2);
            }
            // yInter is m1 * xInter - m1 * x of end line + y of end line;
           yInter = calcYIntersection(xInter, other);
        }
        point = new Point(xInter , yInter);
        // check if the point is between value of lines.
        if (this.isBetweenValue(point) && other.isBetweenValue(point)) {
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
    public boolean equals(Line other) {
     return (this.start == other.start) && (this.end == other.end);
     }
    /**
     * this method return the closest intersection point to the start of the line.
     * @param rect - rectangle
     * @return - return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> arrayListOfPoint;
        arrayListOfPoint = rect.intersectionPoints(this);
        if (arrayListOfPoint.size() == 0) {
            return null;
        } else if (arrayListOfPoint.size() == 1) {
            return arrayListOfPoint.get(0);
        } else {
            Point firstPoint = arrayListOfPoint.get(0);
            Point secondPoint = arrayListOfPoint.get(1);
            Point closest = firstPoint;
            if (this.start.distance(closest)
                    > this.start.distance(secondPoint)) {
                closest = secondPoint;
            }
            return closest;
        }
    }
    /**
     * this method return true if point is on line and false otherwise.
     * @param point - a point
     * @return true if point is on line and false otherwise.
     */
    public boolean isContainPoint(Point point) {
        if (point == null) {
            return false;
        }
        Line tempLine = new Line(this.start, point);
        Point tempPoint = tempLine.intersectionWith(this);
        if (tempPoint == null && isBetweenValue(point)) {
            return true;
        }
        return false;
    }


    /**
     * the method check if the x value and y value of the intersection point is between start and end of line.
     * she also check which point is bigger and smaller.
     * @param interPoint - get the intersection point.
     * @return - true if is between the point and false otherwise
     */
    private boolean isBetweenValue(Point interPoint) {
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double maxY =  Math.max(this.start.getY(), this.end.getY());
        double minX = Math.floor(Math.min(this.start.getX(), this.end.getX()));
        double minY =  Math.floor(Math.min(this.start.getY(), this.end.getY()));
        return ((interPoint.getX() >= minX - EPSILON) && (interPoint.getX() <= maxX + EPSILON))
                && ((interPoint.getY() >= minY - EPSILON) && (interPoint.getY() <= maxY + EPSILON));
    }
    /**
     * calculate the x value of intersection point.
     * @param other - the method get a other line
     * @return the x value.
     */
    private double calcXIntersection(Line other) {
        double m1 = this.getSlope();
        double m2 = other.getSlope();
//        if (m1 == Double.NEGATIVE_INFINITY) {
//            return this.start.getX();
//        }
//        if (m2 == Double.NEGATIVE_INFINITY) {
//            return other.start.getX();
//        }
        return ((m1 * this.end.getX()) - this.end.getY() - m2 * other.end.getX() + other.end.getY())
                / (m1 - m2);
    }
    /**
     * calculate the y value of intersection point.
     * @param xInter - get the x value of intersection to calculate the y value
     * @param line -
     * @return - the y value
     */
    private double calcYIntersection(double xInter, Line line) {
        double m1 = this.getSlope();
        double m2 = line.getSlope();
        if (m1 == Double.NEGATIVE_INFINITY) {
            return m2 * xInter - m2 * line.end.getX() + line.end.getY();
        }
        return m1 * xInter - m1 * this.end.getX() + this.end.getY();
    }

}
