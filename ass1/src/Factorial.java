/**
 * @author Or paz
 * main calss for task factorial.
 *
 */
public class Factorial {
    /**
     * print on screen the result of factorial.
     * @param args - get from user .
     */
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        System.out.println("recursive: " + factorialRecursive(n));
        System.out.println("iterative: " + factorialIter(n));
    }

    /**
     * calculate factorial by Iter.
     * @param n - number the we want to calculate his factorial by Iter.
     * @return - the result of factorial
     */
    public static long factorialIter(long n) {
        long res = 1, i;
        for (i = 1; i <= n; ++i) {
            res *= i;
        }
        return res;
    }

    /**
     * calculate factorial by recursion.
     * @param n - number the we want to calculate his factorial by recursion
     * @return - the result of factorial
     */
    public static long factorialRecursive(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * factorialRecursive(n - 1);
        }
    }
}




