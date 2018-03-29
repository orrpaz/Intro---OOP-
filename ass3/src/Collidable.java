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
     * @return the new velocity expected after the hit.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}