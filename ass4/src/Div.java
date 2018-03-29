import java.util.Map;

/**
 * this class represents a division function.
 */
public class Div extends BinaryExpression implements Expression {
    /**
     * constructor.
     * @param left a expression.
     * @param right a expression.
     */
    public Div(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a string
     */
    public Div(String left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a number
     */
    public Div(double left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a string
     */
    public Div(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a number
     */
    public Div(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left  a string
     * @param right a expression
     */
    public Div(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a expression.
     */
    public Div(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a double
     */
    public Div(String left, double right) {
        super(left, right);
    }

    /**
     *  constructor.
     * @param left a number
     * @param right a string
     */
    public Div(double left, String right) {
        super(left, right);
    }

    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (this.getRightExpression().evaluate(assignment) == 0) {
            throw new Exception("Division By 0");
        } else {
            return this.getLeftExpression().evaluate(assignment) / this.getRightExpression().evaluate(assignment);
        }
    }
    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Div(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));

    }
    /**
     * @return string representation of the expression
     */
    public String toString() {
        String string;
        string = "(" + this.getLeftExpression().toString()
                + " / "
                + this.getRightExpression().toString() + ")";
        return string;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Div(
                new Minus(
                        new Mult(
                                this.getLeftExpression().differentiate(var), this.getRightExpression()),
                        new Mult(
                                this.getLeftExpression(), this.getRightExpression().differentiate(var))),
                new Pow(this.getRightExpression(), 2));
    }
    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            if (this.getRightExpression().simplifyOneStep().toString().equals("1.0")) {
                return this.getLeftExpression().simplifyOneStep();
            }
            if (this.getLeftExpression().simplifyOneStep().toString().equals("0.0")) {
                return new Num(0);
            }
            if (this.getLeftExpression().simplifyOneStep().toString().equals(
                    this.getRightExpression().simplifyOneStep().toString())) {
                return new Num(1);
            }
            // x / -x || -x / x
            if ((this.getLeftExpression().simplifyOneStep().toString().equals(
                    (new Neg(this.getRightExpression())).simplifyOneStep().toString())
                    || this.getRightExpression().toString().equals(
                            (new Neg(this.getLeftExpression().simplifyOneStep())).simplifyOneStep().toString()))) {
                return new Num(-1);
            }
            return new Div(this.getLeftExpression().simplifyOneStep(),
                    this.getRightExpression().simplifyOneStep());
        }
    }
}
