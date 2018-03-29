package base;

import game.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * this interface represents a collidable object.
 */
public interface Collidable {
    /**
     * @return  the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * this method gets a collisionPoint and a velocity
     * and calculates a new velocity according to the specific object.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the velocity of the colliding object.
     * @param hitter the ball hitter.
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}