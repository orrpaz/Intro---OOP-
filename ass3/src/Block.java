import java.awt.Color;
import biuoop.DrawSurface;

/**
 * this class represents a block.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;
    private int hits;

/**
     * construct a block from a rectangle, color and initial number of hits.
     * @param rec - the rectangle that defines the block.
     * @param color - the rectangle color.
     * @param hits - the number of hits.
     */
    public Block(Rectangle rec, Color color, int hits) {
        this.rectangle = rec;
        this.color = color;
        this.hits = hits;
    }
    /**
     * construct a block from an upper left point, width, height,
     * color and initial number of hits.
     * @param upperLeft - the block's upper left point.
     * @param width - the block's width.
     * @param height - the block's height.
     * @param color - the block's color.
     * @param hits - the number of hits.
     */
    public Block(Point upperLeft, int width, int height, Color color, int hits) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hits = hits;
    }

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     * @param x - the x value
     * of the block's upper left corner.
     * @param y - the y value
     * of the block's upper left corner.
     * @param width - the block's width.
     * @param height - the block's height.
     * @param color - the block's color.
     * @param hits - the  number of hits.
     */
    public Block(double x, double y, double width, double height, Color color, int hits) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hits = hits;
    }
    /**
     * this method returns the rectangle that defines the block.
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this method gets collision point and velocity and returns
     * a new velocity based on the hit's properties.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return a new velocity based on the hit's properties.
     */
    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        if (this.hits > 0) {
            this.hits--;
        }
        Velocity newVelocicy = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
//        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()
//                || collisionPoint.getY() == this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight()){
//            newVelocicy.setDy(-newVelocicy.getDy());
//        }
//        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
//                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()){
//            newVelocicy.setDx(-newVelocicy.getDx());
//        }

        if (this.rectangle.getUp().isContainPoint(collisionPoint)
                || this.rectangle.getDown().isContainPoint(collisionPoint)) {
            newVelocicy.setDy(-newVelocicy.getDy());
        }
        if (this.rectangle.getLeft().isContainPoint(collisionPoint)
                || this.rectangle.getRight().isContainPoint(collisionPoint)) {
            newVelocicy.setDx(-newVelocicy.getDx());
        }

        return newVelocicy;
    }
    /**
     * this method notifies the block that a time unit has passed.
     */
    public void timePassed() {

    }
    /**
     * this method draws the block on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        Point upperLeft = this.rectangle.getUpperLeft();
        surface.fillRectangle(
                (int) Math.round(upperLeft.getX()),
                (int) Math.round(upperLeft.getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));
        surface.setColor(Color.BLACK);
        surface.drawRectangle(
                (int) Math.round(upperLeft.getX()),
                (int) Math.round(upperLeft.getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));

        if (this.hits > 0) {
            surface.setColor(Color.WHITE);
            surface.drawText(
                    (int) (this.rectangle.getUpperLeft().getX()
                            + this.rectangle.getWidth() / 2 - 3),
                    (int) (this.rectangle.getUpperLeft().getY()
                            + this.rectangle.getHeight() / 2 + 5),
                    Integer.toString(this.hits), 15);
        } else {
            surface.setColor(Color.WHITE);
            surface.drawText(
                    (int) (this.rectangle.getUpperLeft().getX()
                            + this.rectangle.getWidth() / 2 - 3),
                    (int) (this.rectangle.getUpperLeft().getY()
                            + this.rectangle.getHeight() / 2 + 5),
                    "x", 15);
        }
    }

    /**
     * this method adds the block to a game.
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}

