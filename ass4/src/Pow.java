import java.util.Map;
/**
 * this class represents a power function.
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a expression.
     * @param right a expression.
     */
    public Pow(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a string
     * @param right a string
     */
    public Pow(String left, String right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a number
     * @param right a number
     */
    public Pow(double left, double right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a expression.
     * @param right a string
     */
    public Pow(Expression left, String right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a expression.
     * @param right a number
     */
    public Pow(Expression left, double right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left  a string
     * @param right a expression
     */
    public Pow(String left, Expression right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a number
     * @param right a expression.
     */
    public Pow(double left, Expression right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a string
     * @param right a double
     */
    public Pow(String left, double right) {
        super(left, right);
    }

    /**
     * constructor - left is the base , right is the exponent.
     * @param left a number
     * @param right a string
     */
    public Pow(double left, String right) {
        super(left, right);
    }
    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(this.getLeftExpression().evaluate(assignment), this.getRightExpression().evaluate(assignment));
    }
    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Pow(this.getLeftExpression().assign(var, expression),
                this.getRightExpression().assign(var, expression));
    }
    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "(" + this.getLeftExpression().toString()
                + "^"
                + this.getRightExpression().toString() + ")";
        return string;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Mult(
                this, new Plus(
                new Mult(
                        this.getLeftExpression().differentiate(var),
                        new Div(
                                this.getRightExpression(), this.getLeftExpression())),
                new Mult(
                        this.getRightExpression().differentiate(var),
                        new Log(
                                new Var("e"), this.getLeftExpression()))));


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
            if (rightExpressionSimple.toString().equals("0.0")) {
                return new Num(1);
            }
            if (leftExpressionSimple.toString().equals("0.0")) {
                return new Num(0);
            }
            if (leftExpressionSimple.toString().equals("1.0")) {
                return new Num(1);
            }
            if (rightExpressionSimple.toString().equals("1.0")) {
                return leftExpressionSimple;
            }

            // ( x ^ y ) ^ z
            if (leftExpressionSimple instanceof Pow) {
                Expression base = ((Pow) this.getLeftExpression()).getLeftExpression().simplifyOneStep();
                Expression exponent = ((Pow) this.getLeftExpression())
                        .getRightExpression().simplifyOneStep();
//                Expression ex2 = new Pow(base, new Mult(exponent, this.getRightExpression().simplify()));
//                ex1 = exponent.simplify();
//                int length1 = ex1.toString().length();
//                int length2 = ex2.toString().length();
//                while (length2 != length1) {
//                    ex1 = ex2;
//                    ex2 = ex1.simplify();
//                    length1 = ex1.toString().length();
//                    length2 = ex2.toString().length();
//                }
//                return ex2;
                return new Pow(base, new Mult(exponent, rightExpressionSimple));
            }
            return new Pow(leftExpressionSimple,
                    rightExpressionSimple);
        }
    }
}