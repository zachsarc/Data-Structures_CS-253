import java.util.*;
import java.io.*;
import java.lang.reflect.*;

/**
 * It auto-discovers these classes if present in the same folder:
 *   - RecursiveBubble.bubbleSort(int[])
 *   - RecursiveInsertion.insertionSort(int[])
 *   - RecursiveMergeSort.mergeSort(int[])
 *   - RecursiveSelection.selectionSort(int[])
 *   - RecursiveShellSort.shellSort(int[])
 * For each algorithm and each input size, it:
 *  - Generates a random array (fixed seed for reproducibility)
 *  - Computes the input swaps count
 *  - Runs the sort and measures elapsed time (ms)
 *  - Checks correctness
 *
 * Output CSV: sort_all_results.csv with columns:
 *   algo,size,time_ms,swap_count,inversion_count,ran,sorted,note
 */
public class TestAllSorts {

    private static final int[] SIZES = new int[]{
        50, 100, 200, 300, 400, 500, 750, 1000,
        1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000
    };
    private static final long SEED = 42L;
    private static final int MAX_VALUE = 1_000_000;

    public static void main(String[] args) {
        List<Algo> algos = new ArrayList<>();

        // Built-ins (add if present)
        addIfPresent(algos, "RecursiveBubble",    "bubbleSort");
        addIfPresent(algos, "RecursiveInsertion", "insertionSort");
        addIfPresent(algos, "RecursiveMergeSort", "mergeSort");
        addIfPresent(algos, "RecursiveSelection", "selectionSort");
        addIfPresent(algos, "RecursiveShellSort", "shellSort");

        // CLI additions: pairs of ClassName MethodName
        if (args.length % 2 != 0) {
            System.err.println("Args must be pairs: <ClassName> <methodName>");
            System.err.println("Example: java TestAllSorts MyQuickSort sort MyHeapSort sort");
            return;
        }
        for (int i = 0; i + 1 < args.length; i += 2) {
            addIfPresent(algos, args[i], args[i + 1]);
        }

        if (algos.isEmpty()) {
            System.err.println("No algorithms found. Ensure your sorter classes are on the classpath.");
            return;
        }

        List<Result> results = new ArrayList<>();
        Random rng = new Random(SEED);

        for (Algo algo : algos) {
            for (int n : SIZES) {
                int[] arr = randomArray(n, rng);
                int[] copy = Arrays.copyOf(arr, arr.length);

                long inversionCount = countInversions(Arrays.copyOf(arr, arr.length));
                long swapCount = -1L;

                long start = System.nanoTime();
                boolean ran = true;
                String note = "";
                boolean sorted = false;
                try {
                    if (algo.sortAndCount != null) {
                        swapCount = (long) algo.sortAndCount.invoke(null, (Object) copy);
                        sorted = isSorted(copy);
                    } else {
                        algo.method.invoke(null, (Object) copy);
                        sorted = isSorted(copy);
                        // Fallbacks for swap count:
                        if (swapCount < 0 && algo.label.toLowerCase().contains("bubble")) {
                            swapCount = inversionCount; // exact for adjacent-swap bubble sort
                        }
                    }
                } catch (InvocationTargetException ite) {
                    ran = false;
                    Throwable cause = ite.getCause() != null ? ite.getCause() : ite;
                    note = cause.getClass().getSimpleName() + ": " + cause.getMessage();
                } catch (StackOverflowError e) {
                    ran = false;
                    note = "StackOverflowError";
                } catch (Throwable t) {
                    ran = false;
                    note = t.getClass().getSimpleName() + ": " + t.getMessage();
                }
                long elapsedNs = System.nanoTime() - start;

                results.add(new Result(algo.label, n, elapsedNs, swapCount, inversionCount, ran, sorted, note));
            }
        }

        printTable(results);
        writeCsv(results, "sort_all_results.csv");
        System.out.println("\nWrote CSV: sort_all_results.csv");
    }

    // === Reflection helpers ===

    private static void addIfPresent(List<Algo> algos, String className, String methodName) {
        try {
            Class<?> cls = Class.forName(className);
            Method m = cls.getDeclaredMethod(methodName, int[].class);
            m.setAccessible(true);
            Method sc = null;
            try {
                sc = cls.getDeclaredMethod("sortAndCount", int[].class);
                if (!sc.getReturnType().equals(long.class)) sc = null;
                if (sc != null) sc.setAccessible(true);
            } catch (NoSuchMethodException ignored) {}
            String label = className + "." + methodName;
            algos.add(new Algo(label, cls, m, sc));
            System.out.println("Added: " + label + (sc != null ? " (with sortAndCount)" : ""));
        } catch (Throwable ignored) {
            // Not present; skip
        }
    }

    // === Core helpers ===

    private static int[] randomArray(int n, Random rng) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = rng.nextInt(MAX_VALUE);
        return a;
    }

    private static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i] < a[i - 1]) return false;
        return true;
    }

    // Merge-sort-based inversion counter O(n log n).
    public static long countInversions(int[] a) {
        if (a.length < 2) return 0L;
        int[] tmp = new int[a.length];
        return sortCount(a, tmp, 0, a.length - 1);
    }
    private static long sortCount(int[] a, int[] tmp, int lo, int hi) {
        if (lo >= hi) return 0L;
        int mid = lo + (hi - lo) / 2;
        long inv = 0L;
        inv += sortCount(a, tmp, lo, mid);
        inv += sortCount(a, tmp, mid + 1, hi);
        inv += mergeCount(a, tmp, lo, mid, hi);
        return inv;
    }
    private static long mergeCount(int[] a, int[] tmp, int lo, int mid, int hi) {
        int i = lo, j = mid + 1, k = lo;
        long inv = 0L;
        while (i <= mid && j <= hi) {
            if (a[i] <= a[j]) tmp[k++] = a[i++];
            else {
                tmp[k++] = a[j++];
                inv += (mid - i + 1);
            }
        }
        while (i <= mid) tmp[k++] = a[i++];
        while (j <= hi) tmp[k++] = a[j++];
        for (int t = lo; t <= hi; t++) a[t] = tmp[t];
        return inv;
    }

    private static void printTable(List<Result> results) {
        System.out.printf("%-26s %-7s %-12s %-12s %-16s %-6s %-7s %s%n",
                "Algo", "Size", "Time(ms)", "SwapCount", "Inversions", "Ran", "Sorted", "Note");
        for (Result r : results) {
            double ms = r.elapsedNs / 1_000_000.0;
            System.out.printf("%-26s %-7d %-12.3f %-12d %-16d %-6s %-7s %s%n",
                    r.algo, r.n, ms, r.swapCount, r.inversionCount, r.ran, r.sorted, r.note);
        }
    }

    private static void writeCsv(List<Result> results, String filename) {
        try (PrintWriter pw = new PrintWriter(new File(filename))) {
            pw.println("algo,size,time_ms,swap_count,inversion_count,ran,sorted,note");
            for (Result r : results) {
                double ms = r.elapsedNs / 1_000_000.0;
                pw.printf(Locale.US, "%s,%d,%.3f,%d,%d,%s,%s,%s%n",
                        escapeCsv(r.algo), r.n, ms, r.swapCount, r.inversionCount,
                        r.ran, r.sorted, escapeCsv(r.note));
            }
        } catch (IOException e) {
            System.err.println("Failed to write CSV: " + e.getMessage());
        }
    }

    private static String escapeCsv(String s) {
        if (s == null) return "";
        if (s.contains(",") || s.contains("\"") || s.contains("\n")) {
            return "\"" + s.replace("\"", "\"\"") + "\"";
        }
        return s;
    }

    // === Data holders ===

    private static final class Algo {
        final String label;
        final Method method;
        final Method sortAndCount; // optional
        Algo(String label, Class<?> clazz, Method method, Method sortAndCount) {
            this.label = label;
            this.method = method;
            this.sortAndCount = sortAndCount;
        }
    }

    private static final class Result {
        final String algo;
        final int n;
        final long elapsedNs;
        final long swapCount;
        final long inversionCount;
        final boolean ran;
        final boolean sorted;
        final String note;
        Result(String algo, int n, long elapsedNs, long swapCount, long inversionCount,
               boolean ran, boolean sorted, String note) {
            this.algo = algo;
            this.n = n;
            this.elapsedNs = elapsedNs;
            this.swapCount = swapCount;
            this.inversionCount = inversionCount;
            this.ran = ran;
            this.sorted = sorted;
            this.note = note == null ? "" : note;
        }
    }
}
