/**
 * advanced simplification.
 */
public class SimplificationDemo {
    /**
     * the test main.
     *
     * @param args no args
     */
    public static void main(String[] args) {
        Expression ex1 = new Cos(new Neg("x"));
        System.out.println(ex1.toString());
        System.out.println(ex1.simplify().toString());
        System.out.println();

        Expression ex2 = new Sin(new Neg("x"));
        System.out.println(ex2.toString());
        System.out.println(ex2.simplify().toString());
        System.out.println();

        Expression ex3 = new Div(0, "x");
        System.out.println(ex3.toString());
        System.out.println(ex3.simplify().toString());
        System.out.println();

        Expression ex4 = new Div("x", new Neg("x"));
        System.out.println(ex4.toString());
        System.out.println(ex4.simplify().toString());
        System.out.println();

        Expression ex5 = new Div(new Neg("x"), "x");
        System.out.println(ex5.toString());
        System.out.println(ex5.simplify().toString());
        System.out.println();

        Expression ex6 = new Log("y", new Pow("x", 2));
        System.out.println(ex6.toString());
        System.out.println(ex6.simplify().toString());
        System.out.println();

        Expression ex7 = new Minus("x", new Neg("x"));
        System.out.println(ex7.toString());
        System.out.println(ex7.simplify().toString());
        System.out.println();

        Expression ex8 = new Mult("x", "x");
        System.out.println(ex8.toString());
        System.out.println(ex8.simplify().toString());
        System.out.println();

        Expression ex9 = new Mult("x", new Div("y", "x"));
        System.out.println(ex9.toString());
        System.out.println(ex9.simplify().toString());
        System.out.println();

        Expression ex10 = new Neg(new Neg("x"));
        System.out.println(ex10.toString());
        System.out.println(ex10.simplify().toString());
        System.out.println();

        Expression ex11 = new Plus("x", new Neg("x"));
        System.out.println(ex11.toString());
        System.out.println(ex11.simplify().toString());
        System.out.println();

        Expression ex12 = new Plus(new Neg("x"), "x");
        System.out.println(ex12.toString());
        System.out.println(ex12.simplify().toString());
        System.out.println();

        Expression ex13 = new Plus("x", "x");
        System.out.println(ex13.toString());
        System.out.println(ex13.simplify().toString());
        System.out.println();

        Expression ex14 = new Pow(new Pow("x", "y"), "z");
        System.out.println(ex14.toString());
        System.out.println(ex14.simplify().toString());
        System.out.println();

        Expression ex15 = new Pow(new Pow(new Pow(new Pow("x", "y"), "y"), "y"), "y");
        System.out.println(ex15.toString());
        System.out.println(ex15.simplify().toString());
        System.out.println();

        Expression ex16 = new Div(new Cos(new Plus("x", "y")), new Cos(new Plus("y", "x")));
        System.out.println(ex16.toString());
        System.out.println(ex16.simplify().toString());
    }
}
