package base;

/**
 * Created by Or on 22/05/2017.
 */
public interface HitNotifier {

    /**
     * add hl as a listener to hit events.
     * @param hl the listener
     */
    void addHitListener(HitListener hl);

    /**
     * remove hl from the list of listeners to hit events.
     * @param hl the listener
     */
    void removeHitListener(HitListener hl);
}