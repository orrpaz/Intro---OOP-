import java.util.Map;
import java.util.TreeMap;
/**
 * test of code.
 */
public class ExpressionsTest {
    /**
     * main method.
     * @param args input
     */
    public static void main(String[] args) {
        Sin sin = new Sin(new Mult(4, "y"));
        Expression e = new Plus(new Mult(2, "x"), new Plus(sin, new Pow("e",
                "x")));
        String s = e.toString();
        System.out.println(s);
        Map<String, Double> ass = new TreeMap<String, Double>();
        ass.put("x", 2.0);
        ass.put("y", 0.25);
        ass.put("e", 2.71);
        try {
            System.out.println(e.evaluate(ass));
        } catch (Exception exception) {
            System.out.println(exception);
        }
        System.out.println(e.differentiate("x"));
        try {
            System.out.println(e.differentiate("x").evaluate(ass));
        } catch (Exception e1) {
            System.out.println(e1);
        }
        System.out.println(e.differentiate("x").simplify());
    }
}