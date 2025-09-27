import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int[] a = new int[]{1,26,3,2,1};
        System.out.println("array before sorting: " + Arrays.toString(a));
        sort(a);
        System.out.println("array after sorting: " + Arrays.toString(a));
    }
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}