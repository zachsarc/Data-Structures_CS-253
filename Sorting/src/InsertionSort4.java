import java.util.Arrays;

public class InsertionSort4 {
    public static void main(String[] args) {
        int[] a = new int[]{1,26,3,2,1};
        System.out.println("array before sorting: " + Arrays.toString(a));
        insertionSort(a);
        System.out.println("array after sorting: " + Arrays.toString(a));
    }

    private static void insertionSort(int[] arr) {

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

}
