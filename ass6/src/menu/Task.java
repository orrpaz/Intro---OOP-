package menu;

/**
 * this interface represent a generic task.
 * @param <T> the task type.
 */
public interface Task<T> {
    /**
     *  runs this task.
     * @return unimplemented option.
     */
    T run();

}