import java.util.ArrayList;
import java.util.List;
/**
 * Created by Or on 12/05/2017.
 */
public abstract class BinaryExpression extends BaseExpression {
    private Expression leftExpression;
    private Expression rightExpression;

    /**
     * constructor.
     * @param leftExpression a expression.
     * @param rightExpression a expression.
     */
    public BinaryExpression(Expression leftExpression, Expression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    /**
     * constructor.
     * @param left a string
     * @param right a string
     */
    public BinaryExpression(String left, String right) {
        this.leftExpression = new Var(left);
        this.rightExpression = new Var(right);
    }

    /**
     * constructor.
     * @param left a number
     * @param right a number
     */
    public BinaryExpression(double left, double right) {
        this.leftExpression = new Num(left);
        this.rightExpression = new Num(right);
    }

    /**
     * constructor.
     * @param leftExpression a expression.
     * @param right a string
     */
    public BinaryExpression(Expression leftExpression, String right) {
        this.leftExpression = leftExpression;
        this.rightExpression = new Var(right);
    }

    /**
     * constructor.
     * @param leftExpression a expression.
     * @param right a number
     */
    public BinaryExpression(Expression leftExpression, double right) {
        this.leftExpression = leftExpression;
        this.rightExpression = new Num(right);
    }

    /**
     * constructor.
     * @param left  a string
     * @param rightExpression a expression
     */
    public BinaryExpression(String left, Expression rightExpression) {
        this.leftExpression = new Var(left);
        this.rightExpression = rightExpression;
    }

    /**
     * constructor.
     * @param left a number
     * @param rightExpression a expression.
     */
    public BinaryExpression(double left, Expression rightExpression) {
        this.leftExpression = new Num(left);
        this.rightExpression = rightExpression;
    }

    /**
     * constructor.
     * @param left a string
     * @param right a double
     */
    public BinaryExpression(String left, double right) {
        this.leftExpression = new Var(left);
        this.rightExpression = new Num(right);
    }

    /**
     *  constructor.
     * @param left a number
     * @param right a string
     */
    public BinaryExpression(double left, String right) {
        this.leftExpression = new Num(left);
        this.rightExpression = new Var(right);
    }

    /**
     * @return the left expression
     */
    protected Expression getLeftExpression() {
        return this.leftExpression;
    }

    /**
     * @return the left expression
     */
    protected Expression getRightExpression() {
        return this.rightExpression;
    }

    /**
     * set a new expression in LeftExpression.
     * @param expression - a expression
     */
    protected void setLeftExpression(Expression expression) {
        this.leftExpression = expression;
    }

    /**
     * set a new expression in RightExpression.
     * @param expression - a expression
     */
    protected void setRightExpression(Expression expression) {
        this.rightExpression = expression;
    }
    /**
     * returns a list of the variables in the expression.
     * @return a list of the variables in the expression
     */

    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        list.addAll(this.leftExpression.getVariables());
        list.addAll(this.rightExpression.getVariables());
        return list;
    }
    /**
     * rearrange the Expression in order to check if the left expression is equal to right expression.
     */
    public void arrangeExpression() {
        if (getLeftExpression().toString().compareTo(getRightExpression().toString()) > 0) {
            Expression temp = getRightExpression();
            setRightExpression(getLeftExpression());
            setLeftExpression(temp);
        }
    }
}
