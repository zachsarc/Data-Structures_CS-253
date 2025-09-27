public class RecursiveBubble {
    private static long swaps;
    public static void bubbleSort(int[] arr) {
        bubbleSortRec(arr, arr.length);
    }
    // Outer recursion: shrink problem size after each full pass
    public static void bubbleSortRec(int[] arr, int n) {
        if (n <= 1) {
            return;
        }
        bubblePass(arr, n, 0);  // inner pass from i = 0 to n - 2
        bubbleSortRec(arr, n - 1);  // largest is at n - 1, sort the rest
    }
    // Inner recursion: single pass that bubbles the max to the end of [0..n-1]
    public static void bubblePass(int[] arr, int n, int i) {
        if (i == n - 1) {   // no adjacent pair left
            return;
        }
        if (arr[i] > arr[i + 1]) {  // swap if out of order
            int temp = arr[i];
            arr[i] = arr[i + 1];
            arr[i + 1] = temp;
            swaps++;
        }
        bubblePass(arr, n, i + 1);  // move to next pair
    }

    public static long sortAndCount(int[] a) {
        swaps = 0;
        bubbleSort(a);
        return swaps;
    }
}
