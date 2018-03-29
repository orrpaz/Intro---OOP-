package base;

import geometry.Point;

/**
 * this class holds information about a collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * constructor.
     * @param collisionPoint the collision point.
     * @param collisionObject the collision object.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }
    // the point at which the collision occurs.
    /**
     * this method returns the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * this method returns the collision object from this collisionInfo.
     * @return the collision object from this collisionInfo.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}