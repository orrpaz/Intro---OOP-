import java.util.Map;
import java.util.TreeMap;
/**
 * this is an abstract class that represents a basic expression.
 */
public abstract class BaseExpression  {
    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @return the result
     * @throws Exception if the expression contains a variable which is not in the assignment
     */
    public abstract double evaluate(Map<String, Double> assignment) throws Exception;
    /**
     * this method evaluates a given expression without use map.
     * it assumes that the expression contains only numbers and no variables.
     * @return the evaluated expression.
     * @throws Exception if the expression contains variables.
     */
    public double evaluate() throws Exception  {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        return this.evaluate(assignment);

    }

    /**
     * this method simplifies a expression .
     * by checking the length of expression it checks if the expression is the most simplified expression.
     * the loop stop when the length of 2 expression is equal.
     * @return the most simplified version of Expression
     */
    public Expression simplify() {
        Expression expression = (Expression) this;
        int lengthOfExpression = expression.toString().length();
        Expression simplyExpression = expression.simplifyOneStep();
        int lengthOfSimplyExpression = simplyExpression.toString().length();


        while (lengthOfSimplyExpression != lengthOfExpression) {
            expression = simplyExpression;
            simplyExpression = expression.simplifyOneStep();
            lengthOfExpression = expression.toString().length();
            lengthOfSimplyExpression = simplyExpression.toString().length();
        }
        return simplyExpression;
    }
}