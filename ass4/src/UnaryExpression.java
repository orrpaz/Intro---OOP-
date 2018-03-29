import java.util.List;
/**
 * this class represents an unary expression.
 */
public abstract class UnaryExpression extends BaseExpression {
    private Expression expression;
    /**
     * constructor.
     * @param expression a Expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }
    /**
     * constructor.
     * @param num a number double
     */
    public UnaryExpression(double num) {
        this.expression = new Num(num);
    }
    /**
     * constructor.
     * @param variable a string
     */
    public UnaryExpression(String variable) {
        this.expression = new Var(variable);
    }
    /**
     * @return a list of the variables in the expression
     */
    public List<String> getVariables() {
        return this.expression.getVariables();
    }

    /**
     * @return the Expression.
     */
    protected Expression getExpression() {
        return this.expression;
    }
}


