// Simple Radix Sort for non-negative integers
public class RadixSort {
    // Main method to sort an array
    public static void radixSort(int[] arr) {
        int max = getMax(arr); // Find the largest number

        // Do counting sort for every digit (1, 10, 100, etc.)
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    // Find the maximum number to know digit count
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
        }
        return max;
    }

    // Stable Counting Sort algorithm, sorts arr[] according to digit at exp place
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 0-9 for each digit

        // Count number of occurrences of each digit in the exp position
        for (int i = 0; i < n; i++) {
            int digit = (arr[i] / exp) % 10;
            count[digit]++;
        }

        // Change count[i] so that it contains actual position of this digit
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array (right to left for stability)
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy output array to arr[]
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Test code
    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        radixSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
