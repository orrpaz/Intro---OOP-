import java.util.Map;
/**
 * this class represents a subtraction function.
 */
public class Minus extends BinaryExpression implements Expression {
    /**
     * constructor.
     * @param left a expression.
     * @param right a expression.
     */
    public Minus(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a string
     */
    public Minus(String left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a number
     */
    public Minus(double left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a string
     */
    public Minus(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a number
     */
    public Minus(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left  a string
     * @param right a expression
     */
    public Minus(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a expression.
     */
    public Minus(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a double
     */
    public Minus(String left, double right) {
        super(left, right);
    }

    /**
     *  constructor.
     * @param left a number
     * @param right a string
     */
    public Minus(double left, String right) {
        super(left, right);
    }
    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.getLeftExpression().evaluate(assignment)
                - this.getRightExpression().evaluate(assignment);
    }

    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Minus(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "(" + this.getLeftExpression().toString()
                + " - "
                + this.getRightExpression().toString() + ")";
        return string;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Minus(this.getLeftExpression().differentiate(var),
                this.getRightExpression().differentiate(var));
    }
    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            if (this.getRightExpression().simplifyOneStep().toString().equals("0")) {
                return this.getLeftExpression().simplifyOneStep();
            }
            if (this.getLeftExpression().simplifyOneStep().toString().equals("0")) {
                return new Neg(this.getRightExpression().simplifyOneStep());
            }
            if (this.getLeftExpression().simplifyOneStep().toString().equals(
                    this.getRightExpression().simplifyOneStep().toString())) {
                return new Num(0);
            }
            // for x - ( - x)
            if (this.getRightExpression().simplifyOneStep() instanceof Neg) {
                return new Plus(this.getLeftExpression().simplifyOneStep(),
                        new Neg(this.getRightExpression().simplifyOneStep()).simplifyOneStep());
            }
            return new Minus(this.getLeftExpression().simplifyOneStep(), this.getRightExpression().simplifyOneStep());
        }
    }
}