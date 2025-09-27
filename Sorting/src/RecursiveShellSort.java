import java.util.Arrays;

public class RecursiveShellSort {
    private static long moves;
    public static void shellSort (int[] arr) {
        shellByGap(arr, arr.length / 2);    // start with gap at n/2
    }
    public static void shellByGap(int [] arr, int gap) {
        if (gap < 1) {
            return;
        }
        gappedInsertionSweep(arr, gap, gap);    // walk i from gap to n -1
        shellByGap(arr, gap / 2);   // next gap
    }

    private static void gappedInsertionSweep(int[] arr, int gap, int i) {
        if (i >= arr.length) return;
        int key = arr[i];
        // key inserted into gapped prefix ending at i - gap
        insertGapped(arr, i, gap, key);
        gappedInsertionSweep(arr, gap, i + 1);  // move to next i
    }

    private static void insertGapped(int[] arr, int j, int gap, int key) {
        // found boundary or correct spot: place key
        if (j < gap || arr[j - gap] <= key) {
            arr[j] = key;
            moves++;
            return;
        }
        // shift and keep searching left
        arr[j] = arr[j - gap];
        moves++;
        insertGapped(arr, j - gap, gap, key);
    }

    public static long sortAndCount(int[] a) {
        moves = 0;
        // Call an instrumented version of your sort that increments `moves`
        shellSort(a); // see below notes on where to increment
        return moves;
    }
}
