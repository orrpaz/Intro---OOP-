import java.util.Map;
/**
 * this class represents the trigonometric function cosine.
 */
public class Cos extends UnaryExpression implements Expression {
    /**
     * constructor.
     * @param expression the given expression.
     */
    public Cos(Expression expression) {
        super(expression);
    }

    /**
     * constructor.
     * @param string the given string.
     */
    public Cos(String string) {
        super(string);
    }

    /**
     * constructor.
     * @param num the given string.
     */
    public Cos(double num) {
        super(num);
    }

    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception if the expression contains a variable which is not in the assignment
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(
                this.getExpression().evaluate(assignment)));
    }
    /**
     * returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return new Cos(this.getExpression().assign(var, expression));
    }

    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
        String string;
        string = "Cos(" + this.getExpression().toString() + ")";
        return string;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Mult(
                new Neg(
                        new Sin(
                                this.getExpression())), this.getExpression().differentiate(var));
    }

    /**
     * simplify the expression.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        try {
            double evaluation = this.evaluate();
            return new Num(evaluation);
        } catch (Exception e) {
            // for cos ( - x )
            if (this.getExpression().simplifyOneStep() instanceof Neg) {
                return new Cos(
                        (new Neg(
                                this.getExpression().simplifyOneStep()))).simplifyOneStep();
            }
            return new Cos(this.getExpression().simplifyOneStep());
        }
    }
}