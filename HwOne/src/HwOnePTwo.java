public class HwOnePTwo {
    public static void main(String[] args) {
        testFindPairsBruteForce();
        testFindPairsImproved();
    }
    // Brute Force
    public void findPairsBruteForce(int[] input, int target) {
        if (input == null || input.length < 2) {
            System.out.println("Not possible.");
            return;
        }
        boolean found = false;

        for (int i = 0; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] + input[j] == target) {
                    // Print the smaller value first to avoid order differences like "25 -3"
                    int a = Math.min(input[i], input[j]);
                    int b = Math.max(input[i], input[j]);
                    System.out.println(a + " " + b);
                    found = true;
                }
            }
        }

        if (!found) {
            System.out.println("Not possible.");
        }
    }

    // Test method
    public static void testFindPairsBruteForce() {
        HwOnePTwo hw = new HwOnePTwo();

        int[] input = {2, 7, 11, 15, 1, 8, 4};
        int target = 9;

        // Start the timer
        long startTime = System.nanoTime();

        System.out.println("Testing findPairsBruteForce with input: " + java.util.Arrays.toString(input) + " and target: " + target);
        hw.findPairsBruteForce(input, target);

        // End the timer
        long endTime = System.nanoTime();

        // Calculate elapsed time in milliseconds
        long elapsedTime = (endTime - startTime) / 1_000_000;
        System.out.println("Execution time: " + elapsedTime + " ms");
    }




    // Sort + Two Pairs
    public void findPairsImproved(int[] input, int target) {
        if (input == null || input.length < 2) {
            System.out.println("Not possible.");
            return;
        }

        // If you don't want to mutate the original, copy it first:
        // int[] a = Arrays.copyOf(input, input.length);
        int[] a = input; // use given array in-place per assignment hint

        mergeSort(a, 0, a.length - 1); // O(n log n) sort (given)

        int i = 0, j = a.length - 1;
        boolean found = false;

        while (i < j) {
            int sum = a[i] + a[j];
            if (sum == target) {
                System.out.println(a[i] + " " + a[j]);
                found = true;
                // Move both to avoid reusing elements and to skip other permutations
                i++;
                j--;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        if (!found) {
            System.out.println("Not possible.");
        }
    }

    // Test for finding pairs and merge sort
    // Test method for findPairsImproved
    // Test method for findPairsImproved
    public static void testFindPairsImproved() {
        HwOnePTwo hw = new HwOnePTwo();

        int[] input = {2, 7, 11, 15, 1, 8, 4}; // Example input, you can modify this
        int target = 9;

        // Start the timer
        long startTime = System.nanoTime();

        System.out.println("Testing findPairsImproved with input: " + java.util.Arrays.toString(input) + " and target: " + target);
        hw.findPairsImproved(input, target);

        // End the timer
        long endTime = System.nanoTime();

        // Time calculation for execution
        long elapsedTime = (endTime - startTime) / 1_000_000; // convert to milliseconds
        System.out.println("Execution time: " + elapsedTime + " ms");
    }


    // Merge Sort

    private static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        int midIndex = (startIndex + endIndex) / 2;
        mergeSort(arr, startIndex, midIndex);
        mergeSort(arr, midIndex + 1, endIndex);
        mergeHelper(arr, startIndex, midIndex, endIndex);
    }

    private static void mergeHelper(int[] arr, int startIndex, int midIndex, int endIndex) {
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
