package game;
import base.Collidable;
import base.HitListener;
import base.HitNotifier;
import base.Velocity;
import base.Sprite;
import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * this class represents a block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private int hits;
    private List<HitListener> hitListeners;
    private java.awt.Color stroke;
    private Map<Integer, Filling> filling;
    private Point originalPoint;
    private boolean isAlienBlock;


    /**
     * construct a block from a rectangle, color and initial number of hits.
     *
     * @param filling the filling of the block
     * @param rec     - the rectangle that defines the block.
     * @param stroke  - the stroke of block
     * @param hits    - the number of hits.
     * @param isAlienBlock boolean if the block is alien or not.
     */
    public Block(Rectangle rec, java.awt.Color stroke,
                 Map<Integer, Filling> filling, int hits, boolean isAlienBlock) {
        this.rectangle = rec;
        this.stroke = stroke;
        this.originalPoint = new Point(this.getCollisionRectangle().getUpperLeft().getX(),
                this.getCollisionRectangle().getUpperLeft().getY());
        this.filling = filling;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.isAlienBlock = isAlienBlock;
    }

    /**
     * construct a block from an upper left point, width, height,
     * color and initial number of hits.
     *
     * @param filling   the filling of the block
     * @param upperLeft - the block's upper left point.
     * @param width     - the block's width.
     * @param height    - the block's height.
     * @param stroke    - the stroke of block
     * @param hits      - the number of hits.
     * @param isAlienBlock boolean if the block is alien or not.
     */
    public Block(Point upperLeft, int width, int height, java.awt.Color stroke,
                 Map<Integer, Filling> filling, int hits, boolean isAlienBlock) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.stroke = stroke;
        this.originalPoint = upperLeft;
        this.hits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.filling = filling;
        this.isAlienBlock = isAlienBlock;
    }

    /**
     * construct a block from two coordinates,
     * width, height, color and initial number of hits.
     *
     * @param filling the filling of the block
     * @param x       - the x value
     *                of the block's upper left corner.
     * @param y       - the y value
     *                of the block's upper left corner.
     * @param width   - the block's width.
     * @param height  - the block's height.
     * @param hits    - the  number of hits.
     * @param isAlienBlock boolean if the block is alien or not.
     */
    public Block(double x, double y, double width, double height,
                 Map<Integer, Filling> filling, int hits, boolean isAlienBlock) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.hits = hits;
        this.originalPoint = new Point(x, y);
        this.filling = filling;
        this.stroke = null;
        this.hitListeners = new ArrayList<HitListener>();
        this.isAlienBlock = isAlienBlock;


    }

    /**
     * constructor.
     */
    public Block() {
        this.rectangle = new Rectangle(0, 0, 1, 1);
        this.filling = null;
        this.stroke = Color.black;
        this.hits = 0;
        this.hitListeners = new ArrayList<HitListener>();
        this.isAlienBlock = true;
    }

    /**
     * copy constructor.
     *
     * @param other other block.
     */
    public Block(Block other) {
        this.rectangle =
                new Rectangle(other.getCollisionRectangle().getUpperLeft().getX(),
                        other.getCollisionRectangle().getUpperLeft().getY(), other.getCollisionRectangle().getWidth(),
                        other.getCollisionRectangle().getHeight());
        this.filling = other.filling;
        this.stroke = other.stroke;
        this.hits = other.hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.isAlienBlock = other.isAlienBlock;
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
     * return if is it alien block.
     * @return true if yes; false otherwise.
     */
    public boolean isAlienBlock() {
        return this.isAlienBlock;
    }


    /**
     * set stroke.
     * @param strokes the stroke.
     */
    public void setStorke(java.awt.Color strokes) {
        this.stroke = stroke;
    }

    /**
     * this method gets collision point and velocity and returns
     * a new velocity based on the hit's properties.
     *
     * @param hitter          the ball hitter
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity.
     * @return a new velocity based on the hit's properties.
     */

    public Velocity hit(Shots hitter, Point collisionPoint, Velocity currentVelocity) {
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
     *
     * @return the hit number.
     */
    public int getHits() {
        return this.hits;
    }

    /**
     * set new upper left point.
     * @param x the x value
     * @param y the y value.
     */
    public void setUpperLeft(double x, double y) {
        Point upperLeft = new Point(x, y);
        this.setUpperLeft(upperLeft);
    }

    /**
     * set new upper left point.
     * @param upperLeft the new point
     */
    public void setUpperLeft(Point upperLeft) {
        this.rectangle = new Rectangle(upperLeft, this.rectangle.getWidth(),
                this.rectangle.getHeight());
    }
    /**
     * this method notifies the block that a time unit has passed.
     *
     * @param dt - the dt
     */
    public void timePassed(double dt) {

    }

    /**
     * this method draws the block on given DrawSurface.
     *
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        int x1 = (int) Math.round(this.rectangle.getUpperLeft().getX());
        int x2 = (int) Math.round(this.rectangle.getUpperLeft().getY());
        int x3 = (int) Math.round(this.rectangle.getWidth());
        int x4 = (int) Math.round(this.rectangle.getHeight());

        if (this.filling.containsKey(this.hits)) {
            if (this.filling.get(this.hits).isItColor()) {
                surface.setColor(this.filling.get(this.hits).getColor());
                surface.fillRectangle(x1, x2, x3, x4);
            } else if (this.filling.get(this.hits).isItImage()) {
                surface.drawImage(x1, x2, this.filling.get(this.hits).getImage());
            }
        } else if (this.filling.containsKey(1)) {
            if (this.filling.get(1).isItColor()) {
                surface.setColor(this.filling.get(1).getColor());
                surface.fillRectangle(
                        x1, x2, x3, x4);
            } else if (this.filling.get(1).isItImage()) {
                surface.drawImage(x1, x2, this.filling.get(1).getImage());
            }
        }
        if (this.stroke != null) {
            surface.setColor(this.stroke);
            surface.drawRectangle(x1, x2, x3, x4);
        }
    }


    /**
     * this method adds the block to a gameLevel.
     *
     * @param gameLevel the gameLevel.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * remove the block from the game.
     *
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * notify all listeners about a hit event.
     *
     * @param hitter the ball hitter
     */
    private void notifyHit(Shots hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * responsibble to move the block.
     * @param velocity the velocity of block
     * @param dt the dt
     */
    public void move(Velocity velocity, double dt) {
        Velocity v = velocity.setDt(dt);
        double dx = Math.round(v.getDx());
        double dy = Math.round(v.getDy());
        int newX = (int) (this.rectangle.getUpperLeft().getX() + dx);
        int newY = (int) (this.rectangle.getUpperLeft().getY() + dy);
        this.setUpperLeft(newX, newY);

    }
}

