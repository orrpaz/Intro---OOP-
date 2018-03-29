package game;

import java.awt.Color;
import base.Collidable;
import base.HitListener;
import base.HitNotifier;
import base.Velocity;
import base.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import java.util.List;
import java.util.ArrayList;

/**
 * this class represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private int hits;
    private List<HitListener> hitListeners;

    /**
     * construct a block from a rectangle, color and initial number of hits.
     *
     * @param rec   - the rectangle that defines the block.
     * @param color - the rectangle color.
     * @param hits  - the number of hits.
     */
    public Block(Rectangle rec, Color color, int hits) {
        this.rectangle = rec;
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * construct a block from an upper left point, width, height,
     * color and initial number of hits.
     *
     * @param upperLeft - the block's upper left point.
     * @param width     - the block's width.
     * @param height    - the block's height.
     * @param color     - the block's color.
     * @param hits      - the number of hits.
     */
    public Block(Point upperLeft, int width, int height, Color color, int hits) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     *
     * @param x      - the x value
     *               of the block's upper left corner.
     * @param y      - the y value
     *               of the block's upper left corner.
     * @param width  - the block's width.
     * @param height - the block's height.
     * @param color  - the block's color.
     * @param hits   - the  number of hits.
     */
    public Block(double x, double y, double width, double height, Color color, int hits) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * this method returns the rectangle that defines the block.
     *
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * this method gets collision point and velocity and returns
     * a new velocity based on the hit's properties.
     * @param hitter the ball hitter
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return a new velocity based on the hit's properties.
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (this.hits > 0) {
            this.hits--;
        }
        Velocity newVelocicy = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        if (this.rectangle.getUp().isContainPoint(collisionPoint)
                || this.rectangle.getDown().isContainPoint(collisionPoint)) {
            newVelocicy.setDy(-newVelocicy.getDy());
        }
        if (this.rectangle.getLeft().isContainPoint(collisionPoint)
                || this.rectangle.getRight().isContainPoint(collisionPoint)) {
            newVelocicy.setDx(-newVelocicy.getDx());
        }
        this.notifyHit(hitter);
        return newVelocicy;
    }

    /**
     * return the hit number.
     * @return the hit number.
     */
    public int getHits() {
        return this.hits;
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
        Point upperLeft = this.rectangle.getUpperLeft();
        surface.setColor(this.color);
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
    }

    /**
     * this method adds the block to a gameLevel.
     * @param gameLevel the gameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * remove the block from the game.
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     * @param hl the listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all listeners about a hit event.
     * @param hitter the ball hitter
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}

