import java.util.Map;
/**
 * this class represents a multiplication function.
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * constructor.
     * @param left a expression.
     * @param right a expression.
     */
    public Mult(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a string
     */
    public Mult(String left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a number
     */
    public Mult(double left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a string
     */
    public Mult(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a number
     */
    public Mult(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left  a string
     * @param right a expression
     */
    public Mult(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a expression.
     */
    public Mult(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a double
     */
    public Mult(String left, double right) {
        super(left, right);
    }

    /**
     *  constructor.
     * @param left a number
     * @param right a string
     */
    public Mult(double left, String right) {
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
                * this.getRightExpression().evaluate(assignment);
    }

    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Mult(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }

    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "(" + this.getLeftExpression().toString()
                + " * "
                + this.getRightExpression().toString() + ")";
        return string;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Plus(
                new Mult(
                        this.getLeftExpression().differentiate(var), this.getRightExpression()),
                new Mult(
                        this.getLeftExpression(), this.getRightExpression().differentiate(var)));
    }

    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        Expression leftExpressionSimple = this.getLeftExpression().simplifyOneStep();
        Expression rightExpressionSimple = this.getRightExpression().simplifyOneStep();
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            super.arrangeExpression();
            if (leftExpressionSimple.toString().equals("1.0")) {
                return this.getRightExpression().simplifyOneStep();
            }
            if (rightExpressionSimple.toString().equals("1.0")) {
                return this.getLeftExpression().simplifyOneStep();
            }
            if (leftExpressionSimple.toString().equals("0.0")
                    || rightExpressionSimple.toString().equals("0.0")) {
                return new Num(0);
            }
            if (leftExpressionSimple.toString().equals(
                    rightExpressionSimple.toString())) {
                return new Pow(getLeftExpression().simplifyOneStep(), 2);
            }
            //  for x * ( y / x )
            if (rightExpressionSimple instanceof Div) {
                Expression denominator = ((Div) rightExpressionSimple).getRightExpression().simplifyOneStep();
                Expression numerator = ((Div) rightExpressionSimple).getLeftExpression().simplifyOneStep();
                if (leftExpressionSimple.toString().equals(denominator.toString())) {
                    return numerator;
                }
                return new Mult(leftExpressionSimple, rightExpressionSimple);
            }
            // for ( y / x ) * x
            if (leftExpressionSimple instanceof Div) {
                Expression denominator = ((Div) leftExpressionSimple).getRightExpression().simplifyOneStep();
                Expression numerator = ((Div) leftExpressionSimple).getLeftExpression().simplifyOneStep();
                if (rightExpressionSimple.toString().equals(denominator.toString())) {
                    return numerator;
                }
                return new Mult(leftExpressionSimple, rightExpressionSimple);
            }
            return new Mult(leftExpressionSimple,
                    rightExpressionSimple);
        }
    }
}
