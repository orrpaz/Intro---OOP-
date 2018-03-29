/**
 *@author Or paz
 * the main class for the task
 */
public class DescribeNumbers {
    /**
     * print the minimum,maximum and average.
     * @param args - get args from user.
     */
    public static void main(String[] args) {
        int[] arr = stringsToInts(args);
        System.out.println("min: " + min(arr));
        System.out.println("max: " + max(arr));
        System.out.println("avg: " + avg(arr));

    }

    /**
     * search the min number in array.
     * @param numbers - array type int.
     * @return min - the minimum number.
     */
    public static int min(int[] numbers) {
        int min = numbers[0];
        int i;
        for (i = 1; i < numbers.length; ++i) {
            if (numbers[i] < min) {
                min = numbers[i];
            }
        }

        return min;
    }

    /**
     * search the max number in array.
     * @param numbers - array type int.
     * @return max - the maximum number
     */
    public static int max(int [] numbers) {
        int max = numbers[0];
        int i;
        for (i = 1; i < numbers.length; ++i) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    /**
     * calculate thr average.
     * @param numbers - array type int.
     * @return avg -average of number in array.
     */
    public static float avg(int [] numbers) {
        float sum = 0;
        float avg =  0;
        int i;
        for (i = 0; i < numbers.length; ++i) {
            // sum all numbers.
            sum += numbers[i];
        }
        avg = sum / numbers.length;
        return avg;
    }
    /**
     * create array of int and put them the number of array 'numbers'.
     * @param numbers -array of strings.
     * @return arr - array type int.
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            arr[i] = Integer.parseInt(numbers[i]);
        }
        return arr;
    }
}
