import java.util.Arrays;

public class RecursiveInsertion{
    private static long moves;
    public static void insertionSort(int[] arr) {
        insertionSortRec(arr, arr.length);
    }

    public static void insertionSortRec(int[] arr, int n) {
        if (n <= 1) return;

        insertionSortRec(arr, n - 1);

        int key = arr[n - 1];
        insertKey(arr, n - 1, key);
    }

    public static void insertKey(int[] arr, int n, int key) {
        if (n == 0 || arr[n - 1] <= key) {
            arr[n] = key;
            moves++;
            return;
        }
        arr[n] = arr[n - 1];
        moves++;
        insertKey(arr, n - 1, key);
    }

    public static long sortAndCount(int[] a) {
        moves = 0;
        insertionSort(a);
        return moves;
    }
}
