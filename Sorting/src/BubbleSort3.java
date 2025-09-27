import java.util.Arrays;

public class BubbleSort3 {
    public static void main(String[] arr) {
        int[] a = new int[]{1,26,3,2};
        System.out.println("array before sorting: " + Arrays.toString(a));
        sort(a);
        System.out.println("array after sorting: " + Arrays.toString(a));
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            boolean isSwapped = false;
            for (int j = 1; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                return;
            }
        }
    }
}
