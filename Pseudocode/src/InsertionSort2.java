import java.util.Arrays;

public class InsertionSort2 {
    public static void main(String[] args) {
        int[] arr = {4, 5, 1, 100, 90, 76, 45};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i]; // Current element
            int j = i - 1; // Index of the element before current

            while (j >= 0 && arr[j] > key) {
                arr[i + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
