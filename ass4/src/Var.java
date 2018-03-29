import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * this class represents a variable.
 */
public class Var implements Expression {
    private String variable;

    /**
     * construct a variable from a given string.
     *
     * @param var the given variable.
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(this.variable)) {
            return assignment.get(this.variable);
        } else {
            throw new Exception("Exception in evaluating variable: \""
                    + this.variable
                    + "\" - there is no assignment to compare with.");
        }
    }

    /**
     * this method throws exception if it's called.
     * because it can't evaluate a variable without being given a map.
     * @return the evaluated variable.
     * @throws Exception if there is no assignment to compare with.
     */
    public double evaluate() throws Exception {
        throw new Exception("Exception in evaluating variable: \""
                + this.variable
                + "\" - there is no assignment to compare with.");
    }

    /**
     * this method returns a 1-size list with the variable in it.
     * @return a list with the variable in it.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.add(this.variable);
        return list;
    }

    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        return this.variable;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }
    /**
     * simplify the var.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        return this;
    }
    /**
     * simplify the var.
     * @return the simplify expression
     */
    public Expression simplify() {
        return this;
    }

}