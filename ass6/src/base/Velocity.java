package base;

import geometry.Point;

/**
 * this class represents a base.Velocity by specifying
 * the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;


    /**
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
    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - a point with position (x,y).
     * @return a new point after add the dx and dy.
     */
    public Point applyToPoint(Point p) {
        double x = p.getX() + this.dx;
        double y = p.getY() + this.dy;
        return new Point(x,  y);
    }

    /**
     * construct base.Velocity object from Polar representation.
     * @param angle - the angle of rhe vector
     * @param speed - the speed of vector
     * @return base.Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleRad = Math.toRadians(angle);
        double dx = Math.sin(angleRad) * speed;
        double dy = -1 * Math.cos(angleRad) * speed;
        return new Velocity(dx, dy);
    }
    /**
     * this method update the dx of velocity.
     * @param x - the new x value of velocity
     */
    public void setDx(double x) {
        this.dx = (x);
    }
    /**
     * this method update the dy of velocity.
     * @param y - the new y value of velocity
     */
    public void setDy(double y) {
        this.dy = (y);
    }
    /**
     * this method set velocity with new 2 double.
     * @param x - x value of ne velocity
     * @param y - y value of ne velocity
     */
    public void setVelocity(double x, double y) {
        this.dx = Math.round(x);
        this.dy = Math.round(y);
    }

    /**
     * this method retunt the spee of velocity.
     * @return the speed of velocity
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * set dt to velocity.
     * @param dt the dt
     * @return new velocity.
     */
    public Velocity setDt(double dt) {
        return new Velocity(this.dx * dt, this.dy * dt);
    }

}
