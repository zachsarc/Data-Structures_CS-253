import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 1, 3, 6};
        System.out.println("Original Array: ");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: ");
        System.out.println(Arrays.toString(arr));
    }

    private static void mergeSort(int[] arr, int start, int end) {
        if (start >= end) { // Base case: if the array has 1 or no elements, it's already sorted
            return;
        }
        int mid = (start + end) / 2;

        mergeSort(arr, start, mid); // Recursively sort the left half
        mergeSort(arr, mid + 1, end); // Recursively sort the right half
        merge(arr, start, mid, end); // Merge the two sorted halves
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int n1 = mid - start + 1; // Size of the left half
        int n2 = end - mid; // Size of the right half

        int[] lh = new int[n1]; // temp array for the left half with size n1
        int[] rh = new int[n2]; // temp array for the right half with size n2

        // Copy the left half of arr into lh[]
        for (int i = 0; i < n1; i++) {
            lh[i] = arr[start + i];
        }

        // Copy the right half of arr into rh[]
        for (int j = 0; j < n2; j++) {
            rh[j] = arr[mid + 1 + j];
        }
        // Initialize pointers for lh[], rh[], and the main arr[]
        int i = 0, j = 0, k = start;

        // Merge the lh[] and rh[] arrays into arr[]
        while (i < n1 && j < n2) {
            if (lh[i] <= rh[j]) { // Take from the left half if smaller
                arr[k] = lh[i]; // Assign the smaller left-half element to arr[k] element at kth position
                i++; // Increment i for next comparison
            } else { // Take from right if smaller
                arr[k] = rh[j]; // Assign the smaller right-half element to arr[k] element at kth position
                j++; // Increment j for next comparison
            }
            k++; // increment to move to next position in result array
        }
        // Copy any remaining elements from lh[]
        while (i < n1) {
            arr[k] = lh[i];
            i++;
            k++;
        }
        // Copy any remaining elements from rh[]
        while (j < n2) {
            arr[k] = rh[j];
            j++;
            k++;
        }
    }
}
