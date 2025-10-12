import java.util.Arrays;

public class SelectionSort2 {
    public static void main(String[] args) {
        int[] a = new int[]{1,26,3,2,1};
        System.out.println("array before sorting: " + Arrays.toString(a));
        selectionSort(a);
        System.out.println("array after sorting: " + Arrays.toString(a));
    }

    private static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int bigIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[bigIndex]) {
                    bigIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[bigIndex];
            arr[bigIndex] = temp;
        }
    }

}
