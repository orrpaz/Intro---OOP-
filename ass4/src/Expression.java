import java.util.List;
import java.util.Map;
/**
 * this interface represents an expression.
 */
public interface Expression  {
    /**
     * this method evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * this method uses an empty assignment to evaluate an expression.
     * @return the result.
     * @throws Exception if the expression contains variables.
     */
    double evaluate() throws Exception;

    /**
     * this method returns a list of the variables in the expression.
     * @return a list with all the variables in the expression.
     */
    List<String> getVariables();

    /**
     * this method returns a string representation of the expression.
     * @return a string representation of the expression.
     */
    String toString();

    /**
     * this method returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    Expression assign(String var, Expression expression);

    /**
     * this method differentiates an expression according to a given variable.
     * @param var the given variable.
     * @return the differentiated expression.
     */
    Expression differentiate(String var);

    /**
     * this method returns the most simplified version of Expression.
     * @return the most simplified version of Expression.
     */
    Expression simplify();

    /**
     * /**
     * this method returns a simplified version of the current expression by one step.
     * @return the simplified expression.
     */
    Expression simplifyOneStep();
}