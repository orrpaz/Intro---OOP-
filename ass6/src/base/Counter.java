package base;

/**
 *  represents a counter.
 */
public class Counter {
    private int counter;

    /**
     * constructor .
     * @param initialValue the initial Value
     */
    public Counter(int initialValue) {
        this.counter = initialValue;
    }

    /**
     *  add number to current count.
     * @param number the number
     */
    public void increase(int number) {
        this.counter += number;
    }

    /**
     * subtract number from current count.
     * @param number the number
     */
    public void decrease(int number) {
        this.counter -= number;
    }

    /**
     * get current count.
     * @return the current count.
     */
    public int getValue() {
        return this.counter;
    }
}