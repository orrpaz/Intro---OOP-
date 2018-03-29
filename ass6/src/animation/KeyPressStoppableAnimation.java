package animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Created by Or on 08/06/2017.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String key;
    private Animation animation;
    private boolean close;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param keyboardSensor the keyboard
     * @param key the key to stop the animation
     * @param animation the animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboardSensor, String key, Animation animation) {
        this.keyboardSensor = keyboardSensor;
        this.key = key;
        this.animation = animation;
        this.close = false;
        this.isAlreadyPressed = true;
    }
    /**
     * draws each frame of the animation of the end screen.
     * @param d the DrawSurface
     * @param dt - the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.keyboardSensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.close = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }
    /**
     * return true if the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed.
     * false otherwise.
     */
    public boolean shouldStop() {
        return this.close;
    }
}
