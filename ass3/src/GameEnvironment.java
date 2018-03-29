import java.util.ArrayList;
/**
 * this class is a collection of collidable objects.
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidableList;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }
    /**
     * this method adds a given collidable object to the collection.
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * this method gets a trajectory line and returns the closest collision information.
     * if there is no collision, returns null.
     * @param trajectory a line.
     * @return collision information, null if there isn't any.
     */

    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collusion = null;
        CollisionInfo collisionInfo = null;
        int i = 0;
        int size = this.collidableList.size();
        for (; i < size; ++i) {
            collusion = trajectory.closestIntersectionToStartOfLine(
                    this.collidableList.get(i).getCollisionRectangle());
            if (collusion != null) {
                break;
            }
        }
        if (collusion == null) {
            return null;
        }
        size--;
        collisionInfo = new CollisionInfo(collusion, this.collidableList.get(i));
        double minDis = collusion.distance(trajectory.start());
        while (i < size) {
            collusion = trajectory.closestIntersectionToStartOfLine(
                    this.collidableList.get(i + 1).getCollisionRectangle());
            if (collusion != null) {
                double distance = collusion.distance(trajectory.start());
                if (distance < minDis) {
                    collisionInfo = new CollisionInfo(collusion, this.collidableList.get(i + 1));
                }
            }
            i++;
        }
        return collisionInfo;
    }
    /**
     * @return the list of colidables.
     */
    public ArrayList getlist() {
        return this.collidableList;
    }
}