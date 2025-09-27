public class RecursiveSelection {
    private static long swaps;
    public static void selectionSort(int[] arr) {
        selRec(arr, 0, arr.length);
    }
    public static void selRec(int[] arr, int start, int n) {
        if (start >= n - 1) return;
        int minIndex = findMinIndex(arr, start, n - 1);
        if (minIndex != start) {
            int temp = arr[start];
            arr[start] = arr[minIndex];
            arr[minIndex] = temp;
            swaps++;
        }
        selRec(arr, start + 1, n);  // Sort the tail
    }
    public static int findMinIndex(int[] arr, int start, int end) {
        if (start == end) {
            return start;
        }
        int minR = findMinIndex(arr, start + 1, end);
        if (arr[start] <= arr[minR]) {
            return start;
        } else {
            return minR;
        }
    }
    public static long sortAndCount(int[] a) {
        swaps = 0;
        selectionSort(a);   // your existing algorithm; see below to increment swaps
        return swaps;
    }
}
