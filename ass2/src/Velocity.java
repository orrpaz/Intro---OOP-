


/**
 * this class represents a Velocity by specifying
 * the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;


    /**
     *
     * @param dx - the change position on x.
     * @param dy - the change position on y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * returns the velocity's change in position on the x axis.
     * @return - the velocity's change in position on the x axis.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * returns the velocity's change in position on the y axis.
     * @return - the velocity's change in position on the y axis.
     */
    public double getDy() {
        return this.dy;
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)

    /**
     * @param p - a point with position (x,y).
     * @return a new point after add the dx and dy.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x,  y);
    }

    /**
     * construct Velocity object from Polar representation.
     * @param angle - the angle of rhe vector
     * @param speed - the speed of vector
     * @return Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle);
        double dx = Math.sin(angleRad) * speed;
        double dy = Math.cos(angleRad) * speed;
        return new Velocity(dx, dy);
    }


}
