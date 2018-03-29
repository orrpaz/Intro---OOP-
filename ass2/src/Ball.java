import java.awt.Color;
import biuoop.DrawSurface;


/**
 * Created by Or on 06/04/2017.
 * this class create a ball.
 */
public class Ball {
    private Point centerPoint;
    private int radius;
    private Color color;
    private Velocity velocity;
    private Point startScreen;
    private Point endScreen;

    /**
     * @param center - The center point of the ball
     * @param r      - radius
     * @param color  - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {

        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        // default screen
        this.startScreen = new Point(0, 0);
        this.startScreen = new Point(600, 600);
        this.centerPoint = this.checkAndSetCenterPoint(center.getX(), center.getY());
    }

    /**
     * @param x     - x coordinate of center ball
     * @param y     - y coordinate of center ball
     * @param r     - radius
     * @param color - color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        // default screen
        this.startScreen = new Point(0, 0);
        this.startScreen = new Point(600, 600);
        this.centerPoint = this.checkAndSetCenterPoint(x, y);


    }

    /**
     * @param x           - x  coordinate of center ball
     * @param y           - y coordinate of center ball
     * @param r           - radius
     * @param color       - color of the ball
     * @param startScreen - point of start screen ( Upper left edge )
     * @param endScreen   - point of end screen ( Right bottom edge )
     */
    public Ball(double x, double y, int r, java.awt.Color color, Point startScreen, Point endScreen) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.startScreen = startScreen;
        this.endScreen = endScreen;
        this.centerPoint = this.checkAndSetCenterPoint(x, y);
    }

    /**
     * @param center      - The center point of the ball
     * @param r           - radius
     * @param color       - color of the ball
     * @param startScreen - point of start screen ( Upper left edge )
     * @param endScreen   - point of end screen ( Right bottom edge )
     */
    public Ball(Point center, int r, java.awt.Color color, Point startScreen, Point endScreen) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.startScreen = startScreen;
        this.endScreen = endScreen;
        this.centerPoint = this.checkAndSetCenterPoint(center.getX(), center.getY());
    }

    /**
     * @return x value of the center.
     */
    public int getX() {
        return (int) Math.round(this.centerPoint.getX());
    }

    /**
     * @return y value of rhe center.
     */
    public int getY() {
        return (int) Math.round(this.centerPoint.getY());
    }

    /**
     * @return the radius of ball
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * @return - the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * @param v - Velocity object to apply on the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * @param dx - the change in position on the x value
     * @param dy - the change in position on the y value
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * @return the velocity of ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * @return true if the ball is smaller than thw size of boundaries, false otherwise.
     */
    public boolean isValid() {
        return (this.radius <= (endScreen.getX() - startScreen.getX()) / 2)
                && (this.radius <= (endScreen.getY() - startScreen.getY()) / 2);
    }

    /**
     * the method moves the ball's center according to it's velocity int the bound.
     */
    public void moveOneStep() {
        if ((this.centerPoint.getX() + this.radius + this.velocity.getDx() >= endScreen.getX())) {
            this.setVelocity(-1 * this.velocity.getDx(), this.velocity.getDy());
        }
        if (this.centerPoint.getX() - this.radius + this.velocity.getDx() <= startScreen.getX()) {
            this.setVelocity(-1 * this.velocity.getDx(), this.velocity.getDy());
        }
        if ((this.centerPoint.getY() + this.radius + this.velocity.getDy() >= endScreen.getY())) {
            this.setVelocity(this.velocity.getDx(), -1 * this.velocity.getDy());
        }
        if ((this.centerPoint.getY() - this.radius + this.velocity.getDy() <= startScreen.getY())) {
            this.setVelocity(this.velocity.getDx(), -1 * this.velocity.getDy());
        }
        this.centerPoint = this.getVelocity().applyToPoint(this.centerPoint);
    }

    /**
     * the method check if the center point of ball is not in our boundaries and fix it accordingly by set new point.
     * @param x - x value of point
     * @param y - y value of point
     * @return new center point
     */
    private Point checkAndSetCenterPoint(double x, double y) {

        double newX = x;
        double newY = y;
        // outside the left or right boundaries.
        if (x - this.radius < startScreen.getX()) {
           newX = this.startScreen.getX() + this.radius;
        } else if (x + this.radius >= endScreen.getX()) {
            newX = endScreen.getX() - this.radius;
        }
        // outside the up or down boundaries.
        if ((y - this.radius < startScreen.getY())) {
            newY = startScreen.getY() + this.radius;
        } else if ((y + this.radius >= endScreen.getY())) {
            newY = endScreen.getY() - this.radius;
        }
        return new Point(newX, newY);
    }
}





