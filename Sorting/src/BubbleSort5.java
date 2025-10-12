import java.util.Arrays;

public class BubbleSort5 {
    public static void main(String[] args) {
        int[] a = new int[]{1,26,3,2,1};
        System.out.println("array before sorting: " + Arrays.toString(a));
        bubble(a);
        System.out.println("array after sorting: " + Arrays.toString(a));
    }

    public static void bubble(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean isSwapped = false;
            for (int j = 1; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                return;
            }
        }
    }
}
