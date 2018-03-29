import java.util.Map;
/**
 * this class represents the trigonometric function sine.
 */
public class Sin extends UnaryExpression implements Expression {
    /**
     * constructor.
     * @param expression Expression
     */
    public Sin(Expression expression) {
        super(expression);
    }
    /**
     * constructor.
     * @param string a string
     */
    public Sin(String string) {
        super(string);
    }
    /**
     * constructor.
     * @param num a double num
     */
    public Sin(double num) {
        super(num);
    }
    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Sin(this.getExpression().assign(var, expression));
    }
    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(this.getExpression().evaluate(assignment)));
    }
    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        return "Sin(" + this.getExpression().toString() + ")";
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Mult(
                new Cos(
                        this.getExpression()), this.getExpression().differentiate(var));
    }
    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            if (this.getExpression().simplifyOneStep() instanceof Neg) { // if sin(-x)
                return new Neg(new Sin(((Neg) this.getExpression().simplifyOneStep()).
                        getExpression().simplifyOneStep()));
            }
            return new Sin(this.getExpression().simplifyOneStep());
        }
    }
}