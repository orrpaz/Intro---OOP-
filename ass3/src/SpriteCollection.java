import biuoop.DrawSurface;
import java.util.List;
import java.util.ArrayList;
/**
 * this class is a collection of sprite object.
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * this method adds a given sprite object to the collection.
     * @param s - the sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * this method notifies all the sprite objects that a time unit has passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).timePassed();
        }
    }

    /**
     /**
     * this method draws all the sprite objects on a given draw surface.
     * @param d - the DrawSurface to draw on.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}

