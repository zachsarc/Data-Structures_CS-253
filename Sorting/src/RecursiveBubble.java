public class RecursiveBubble {
    public static void bubbleRec(int[] arr) {

        int n = arr.length;
        if (n == 1) {
            return;
        }

        for (int i = 0; i < n - 2; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i + 1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
