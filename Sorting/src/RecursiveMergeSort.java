import java.util.Arrays;

public class RecursiveMergeSort {
    private static long moves;
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int[] temp = new int[arr.length];
        mergeSortRec(arr, 0, arr.length - 1, temp);
    }
    private static void mergeSortRec(int[] arr, int lo, int hi, int[] temp) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSortRec(arr, lo, mid, temp);
        mergeSortRec(arr, mid + 1, hi, temp);

        mergePointers(arr, temp, lo, mid, hi, lo, mid + 1, lo);

        copyBack(arr, temp, lo, hi);
    }

    private static void copyBack(int[] arr, int[] temp, int lo, int hi) {
        if (lo > hi) return;
        arr[lo] = temp[lo];
        copyBack(arr, temp, lo + 1, hi);
    }

    /**
     * Recursively merge arr[lo..mid] and arr[mid+1..hi] into temp[lo..hi].
     * i walks left half, j walks right half, k writes into temp.
     */
    private static void mergePointers (int[] arr, int[] temp, int lo, int mid, int hi, int i, int j, int k) {
        if (k > hi) {
            int len = hi - lo + 1;
            System.arraycopy(temp, lo, arr, lo, len);
            moves += len;
            return;
        }

        // If left side done, take from right
        if (i > mid) {
            temp[k] = arr[j];
            moves++;
            mergePointers(arr, temp, lo, mid, hi, i, j + 1, k + 1);
            return;
        }

        // If right side done, take from left
        if (j > hi) {
            temp[k] = arr[i];
            moves++;
            mergePointers(arr, temp, lo, mid, hi, i + 1, j, k + 1);
            return;
        }

        // Both sides have elements: take smaller
        if (arr[i] <= arr[j]) {
            temp[k] = arr[i];
            moves++;
            mergePointers(arr, temp, lo, mid, hi, i + 1, j, k + 1);
        } else {
            temp[k] = arr[j];
            moves++;
            mergePointers(arr, temp, lo, mid, hi, i, j + 1, k + 1);
        }
    }

    public static long sortAndCount(int[] a) {
        moves = 0;
        mergeSort(a);   // instrument merge to increment moves
        return moves;
    }

}
