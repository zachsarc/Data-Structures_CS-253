public class MergeSort {
    public static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int midIndex = (startIndex + endIndex) / 2;
        mergeSort(arr, startIndex, midIndex);
        mergeSort(arr, midIndex + 1, endIndex);
        mergeHelper(arr, startIndex, midIndex, endIndex);
    }

    public static void mergeHelper(int[] arr, int startIndex, int midIndex, int endIndex) {
        // 1. calculate subarrays
        int lhSize = midIndex - startIndex + 1;
        int rhSize = endIndex - midIndex;
        int[] lh = new int[lhSize];
        int[] rh = new int[rhSize];

        // 2. copy elements from original array to temp left array
        for (int i = 0; i < lhSize; i++) {
            lh[i] = arr[startIndex + i];
        }
        // 2. copy elements from original array to temp right array
        for (int j = 0; j < rhSize; j++) {
            rh[j] = arr[midIndex + 1 + j];
        }

        // 3. create pointers
        int l = 0, r = 0, k = startIndex;

        // 4. start testing and placing sorted nums
        while (l < lhSize && r < rhSize) {
            if (lh[l] <= rh[r]) {
                arr[k] = lh[l];
                l++;
            } else {
                arr[k] = rh[r];
                r++;
            }
            k++;
        }

        // place any remaining nums into a sorted array
        while (l < lhSize) {
            arr[k] = lh[l];
            l++;
        }
        while (r < rhSize) {
            arr[k] = rh[r];
            r++;
        }
    }
}
