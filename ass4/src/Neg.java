import java.util.Map;

/**
 * this class represents a an expression with a negative sign.
 */
public class Neg extends UnaryExpression implements Expression {
    /**
     * constructor.
     * @param expression Expression
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * constructor.
     * @param num a num
     */
    public Neg(double num) {
        super(num);
    }

    /**
     * constructor.
     * @param string a string
     */
    public Neg(String string) {
        super(string);
    }

    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the variable values
     * @return the result
     * @throws Exception If the expression contains a variable which is not in the assignment
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * (this.getExpression().evaluate(assignment));
    }

    /**
     * returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var        the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Neg(this.getExpression().assign(var, expression));
    }

    /**
     * @return a nice string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "(-" + this.getExpression().toString() + ")";
        return string;
    }

    /**
     * the differentiate.
     * @param var the variable we differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Neg(this.getExpression().differentiate(var));
    }

    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        try {
            return new Num(this.getExpression().evaluate());
        } catch (Exception e) {
            // - ( - x )
            if (this.getExpression().simplifyOneStep() instanceof Neg) {
                return ((Neg) this.getExpression().simplifyOneStep()).getExpression().simplifyOneStep();
            }
            return new Neg(this.getExpression().simplifyOneStep());
        }
    }
}
