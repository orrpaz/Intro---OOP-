package game;
import base.Sprite;
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
     * @param dt dhe dt.
     */
    public void notifyAllTimePassed(double dt) {
        List<Sprite> newSpriteList = new ArrayList<>(this.spriteList);
        for (int i = 0; i < newSpriteList.size(); ++i) {
           newSpriteList.get(i).timePassed(dt);
        }
    }

    /**
     /**
     * this method draws all the sprite objects on a given draw surface.
     * @param d - the DrawSurface to draw on.
     */
    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        if (this.spriteList.size() > 0) {
            for (int i = 0; i < spriteList.size(); ++i) {
                this.spriteList.get(i).drawOn(d);
            }
        }
    }
    /**
     * return the list of the sprites.
     * @return the list of the sprites
     */
    public java.util.List getSpriteList() {
        return this.spriteList;
    }
}

