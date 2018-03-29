import java.util.Map;

/**
 * this class represents a logarithm function.
 */
public class Log extends BinaryExpression implements Expression {
    /**
     * constructor.
     * @param left a expression.
     * @param right a expression.
     */
    public Log(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a string
     */
    public Log(String left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a number
     */
    public Log(double left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a string
     */
    public Log(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a expression.
     * @param right a number
     */
    public Log(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left  a string
     * @param right a expression
     */
    public Log(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a expression.
     */
    public Log(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor.
     * @param left a string
     * @param right a double
     */
    public Log(String left, double right) {
        super(left, right);
    }

    /**
     *  constructor.
     * @param left a number
     * @param right a string
     */
    public Log(double left, String right) {
        super(left, right);
    }
    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (this.getLeftExpression().evaluate(assignment) <= 0
                || this.getLeftExpression().evaluate(assignment) == 1
                || this.getRightExpression().evaluate(assignment) <= 0) {
            throw new Exception("can't evaluate.");
        }
        return Math.log(this.getRightExpression().evaluate(assignment))
                / Math.log(this.getLeftExpression().evaluate(assignment));
    }
    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Log(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }
    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "Log(" + this.getLeftExpression().toString() + ", "
                + this.getRightExpression().toString() + ")";
        return string;
    }

    /**
     * differentiates logarithm functions that.
     * have only numbers as their base.
     * @param var the given variable.
     * @return the differentiated expression.
     */
    public Expression differentiateByNumInBase(String var) {
        return new Div(this.getRightExpression().differentiate(var),
                new Mult(new Log("e", this.getLeftExpression()),
                        this.getRightExpression()));
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        // we can introduce the expression by fraction of 2 log with standard base ('e').
        // it is fine if one of the expression will be log (e,e) - it is equal 1.
        Log nominator = new Log("e", this.getRightExpression());
        Log denominator = new Log("e", this.getLeftExpression());
        return new Div(
                new Minus(
                        new Mult(
                                nominator.differentiateByNumInBase(var), denominator),
                        new Mult(
                                nominator, denominator.differentiateByNumInBase(var))),
                new Pow(
                        denominator, 2));
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
            if (leftExpressionSimple.toString().equals(rightExpressionSimple.toString())) {
                return new Num(1);
            }
            if (rightExpressionSimple.toString().equals("1.0")) {
                return new Num(0);
            }
            // log(y,x^z) - for the Bonus.
            if (rightExpressionSimple instanceof Pow) {
                Expression exponent = ((Pow) rightExpressionSimple).getRightExpression().simplifyOneStep();
                Expression base = ((Pow) rightExpressionSimple).getLeftExpression().simplifyOneStep();
                return new Mult(exponent, new Log(leftExpressionSimple, base));
            }
            return new Log(leftExpressionSimple, rightExpressionSimple);
        }
    }
}
