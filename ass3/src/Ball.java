import java.awt.Color;
import biuoop.DrawSurface;


/**
 * Created by Or on 06/04/2017.
 * this class create a ball.
 */
public class Ball implements Sprite {
    private Point centerPoint;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;


    /**
     * @param center          - The center point of the ball
     * @param r               - radius
     * @param color           - color of the ball
     * @param gameEnvironment -
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {

        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.centerPoint = center;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * @param x               - x coordinate of center ball
     * @param y               - y coordinate of center ball
     * @param r               - radius
     * @param color           - color of the ball
     * @param gameEnvironment -
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.centerPoint = new Point(x, y);
        this.gameEnvironment = gameEnvironment;


    }

    /**
     * @param x           - x  coordinate of center ball
     * @param y           - y coordinate of center ball
     * @param r           - radius
     * @param color       - color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);

        this.gameEnvironment = new GameEnvironment();
        this.centerPoint = new Point(x, y);
    }

    /**
     * @param center      - The center point of the ball
     * @param r           - radius
     * @param color       - color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
        this.centerPoint = center;
    }

    /**
     * @return x value of the center.
     */
    public int getX() {
        return (int) this.centerPoint.getX();
    }

    /**
     * @return y value of rhe center.
     */
    public int getY() {
        return (int) this.centerPoint.getY();
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
     * the method moves the ball's center according to it's velocity int the bound.
     */
    public void moveOneStep() {
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory());
        if (info == null) {
            this.centerPoint = this.getVelocity().applyToPoint(this.centerPoint);
        } else {
            if (isInRec(info.collisionObject().getCollisionRectangle())) {
                this.centerPoint = new Point(this.centerPoint.getX(), this.centerPoint.getY() - 20);
                this.setVelocity(info.collisionObject().hit(info.collisionPoint(), this.velocity));
            } else {
                if (this.velocity.getDx() < 0) {
                    this.centerPoint = new Point(this.centerPoint.getX() + 0.1 * radius, this.centerPoint.getY());
                }
                if (this.velocity.getDx() > 0) {
                    this.centerPoint = new Point(this.centerPoint.getX() - 0.1 * radius, this.centerPoint.getY());
                }
                if (this.velocity.getDy() < 0) {
                    this.centerPoint = new Point(this.centerPoint.getX(), this.centerPoint.getY() + 0.1 * radius);
                }
                if (this.velocity.getDy() > 0) {
                    this.centerPoint = new Point(this.centerPoint.getX(), this.centerPoint.getY() - 0.1 * radius);
                }
                this.setVelocity(info.collisionObject().hit(info.collisionPoint(), this.getVelocity()));
            }
        }
    }



    /**
     *  check if point is in rectangle.
     * @param rec - a rectangle
     * @return - true if yes and false otherwise.
     */
    private boolean isInRec(Rectangle rec) {
        double x = this.centerPoint.getX();
        double y = this.centerPoint.getY();
        double upperLeftX = rec.getUpperLeft().getX();
        double upperLeftY = rec.getUpperLeft().getY();
        return (x >= upperLeftX && x <= upperLeftX + rec.getWidth() && y >= upperLeftY
                && y <= upperLeftY + rec.getHeight());
    }

    /**
     * this method return the trajectory of ball.
     * @return the trajectory of ball.
     */
    public Line trajectory() {
        return new Line(new Point(this.centerPoint.getX(), this.centerPoint.getY()),
                new Point(Math.floor(this.centerPoint.getX() + this.velocity.getDx()),
                       Math.floor(this.centerPoint.getY() + this.velocity.getDy())));
    }

    /**
     *  this method return Environment.
     * @return the Environment.
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * this method notifies the ball that a time unit has passed.
     */
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * this method adds the ball to the game's sprite list.
     * @param game the game that is currently played.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }

}





