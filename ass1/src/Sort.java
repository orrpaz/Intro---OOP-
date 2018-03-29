/**
 * @author Or paz
 * @since 23.3.17
 * main class for task Sort.
 */
public class Sort {
    /**
     * check the first args if it is "desc" or "asc" and then call to other method.
     * @param args - get from user
     */
    public static void main(String [] args) {
        int[] arr = stringsToInts(args);
        String word = args[0];
        if (word.equals("desc")) {
            descSort(arr);
        } else if (word.equals("asc")) {
            ascSort(arr);
        }
        printArray(arr);
    }

    /**
     * change the array from string to int by using parseInt.
     * @param numbers - array of numbers type string
     * @return - arr
     */
    public static int[] stringsToInts(String[] numbers) {
        int[] arr = new int[numbers.length - 1];
        for (int i = 0; i <  numbers.length - 1; ++i) {
            arr[i] = Integer.parseInt(numbers[i + 1]);
        }
        return arr;
    }

    /**
     * swap numbers in array.
     * @param arr - array of number type int
     * @param i - a specific index we want to swap with it
     * @param j - a specific index we want to swap with it
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * sort the array with bubble sort (descending).
     * @param arr - array of number type int.
     */
    public static void descSort(int[] arr) {
        // bubble sort.
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = 0; j < arr.length - i - 1; ++j) {
                // if we need swap.
                if (arr [j] < arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    /**
     * sort the array with bubble sort (ascending).
     * @param arr - array of number type int
     */
    public static void ascSort(int[] arr) {
        // bubble sort.
        for (int i = 0; i < arr.length - 1; ++i) {
            for (int j = 0; j < arr.length - i - 1; ++j) {
                //// if we we need swap.
                if (arr [j] > arr[j  + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * print the array with space after number.
     * @param arr - array of number type int
     */
    public static void printArray(int [] arr) {
        for (int i = 0; i < arr.length; ++i) {
            System.out.print(arr[i] + " ");
        }
       System.out.println("");
    }
}


