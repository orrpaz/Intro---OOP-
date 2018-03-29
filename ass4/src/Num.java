import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 * this class represents a number.
 */
public class Num implements Expression {
    private double num;
    /**
     * constructor.
     * @param num a number.
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * evaluate the expression using the variable values provided in the assignment, and return the result.
     * @param assignment the map
     * @throws Exception never
     * @return the result
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }
    /**
     * this method evaluate a number.
     * @return the number.
     * @throws Exception never.
     */
    public double evaluate() throws Exception {
        return this.num;
    }
    /**
     * returns an empty list if it's called because.
     * there are no variables in the number expression.
     * @return an empty list.
     */
    public List<String> getVariables() {
        List<String> list = new ArrayList<String>();
        return list;
    }
    /**
     * @return string representation of the expression
     */
    @Override
    public String toString() {
//        String string;
//        if (this.num % 1 == 0) {
//            string = String.format("%d", (int) this.num);
//        } else {
//            string = String.format("%s", this.num);
//        }
//        return string;
        return ((Double) (this.num)).toString();
    }
    /**
     * Returns a new expression in which all occurrences of the variable variable are replaced.
     * with the provided expression.
     * @param var the variable which is replaced
     * @param expression the provided expression
     * @return the new expression
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }
    /**
     * the differentiate.
     * @param var the variable that differentiate the expression
     * @return the differentiate
     */
    public Expression differentiate(String var) {
        return new Num(0);
    }
    /**
     * simplify the number.
     * @return the simplify expression
     */
    public Expression simplifyOneStep() {
        return this;
    }
    /**
     * simplify the number.
     * @return the simplify expression
     */
    public Expression simplify() {
        return this;
    }
}
