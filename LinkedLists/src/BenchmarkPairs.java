import java.io.*;
import java.util.*;

    public class BenchmarkPairs {

        // Adjust these if your method names or signatures differ
        private static void runBruteForce(int[] a, int k) { HwOnePTwo.findPairsBruteForce(a, k); }
        private static void runImproved(int[] a, int k)    { HwOnePTwo.findPairsImproved(a, k); }
        private static void runHash(int[] a, int k)        { HwOnePTwo.findPairsHash(a, k); }

        public static void main(String[] args) throws Exception {
            // Config - feel free to tweak
            int[] sizes = {100, 200, 400, 800, 1600, 3200};
            int trialsPerSize = 7;          // more trials - smoother averages
            int warmupTrials = 3;           // JIT warmup
            int minVal = -1_000_000;
            int maxVal =  1_000_000;
            int target = 50;                // any integer target is fine

            // Output CSV
            try (PrintWriter out = new PrintWriter(new FileWriter("pairs_bench_raw.csv"))) {
                out.println("n,method,trial,time_ns");

                // Use a fixed Random seed so arrays are reproducible across runs
                Random rng = new Random(42);

                for (int n : sizes) {
                    // Build a pool of unique integers, then shuffle and take first n
                    int[] base = makeUniqueArray(n, minVal, maxVal, rng);

                    // Prepare three independent shuffled copies so each method gets similar difficulty
                    int[][] arraysForMethod = new int[3][];
                    for (int i = 0; i < 3; i++) arraysForMethod[i] = base.clone();

                    // Warmup - run each once on a smaller n to trigger JIT
                    for (int i = 0; i < warmupTrials; i++) {
                        int[] warm = makeUniqueArray(Math.min(256, n), minVal, maxVal, rng);
                        quiet(() -> runBruteForce(warm, target));
                        quiet(() -> runImproved(warm, target));
                        quiet(() -> runHash(warm, target));
                    }

                    // Actual trials
                    for (int t = 1; t <= trialsPerSize; t++) {
                        shuffleInPlace(arraysForMethod[0], rng);
                        shuffleInPlace(arraysForMethod[1], rng);
                        shuffleInPlace(arraysForMethod[2], rng);

                        long ns;

                        ns = timeNs(() -> quiet(() -> runBruteForce(arraysForMethod[0].clone(), target)));
                        out.printf(Locale.US, "%d,%s,%d,%d%n", n, "bruteforce", t, ns);

                        ns = timeNs(() -> quiet(() -> runImproved(arraysForMethod[1].clone(), target)));
                        out.printf(Locale.US, "%d,%s,%d,%d%n", n, "improved", t, ns);

                        ns = timeNs(() -> quiet(() -> runHash(arraysForMethod[2].clone(), target)));
                        out.printf(Locale.US, "%d,%s,%d,%d%n", n, "hash", t, ns);
                    }
                }
            }

            // Also write a summary CSV with per-size averages
            summarize("pairs_bench_raw.csv", "pairs_bench_summary.csv");

            System.out.println("Wrote pairs_bench_raw.csv and pairs_bench_summary.csv");
            System.out.println("Open pairs_bench_summary.csv to make your line chart.");
        }

        /* ---------- Helpers ---------- */

        // Generate n unique ints in [minVal, maxVal], uniformly without collisions
        private static int[] makeUniqueArray(int n, int minVal, int maxVal, Random rng) {
            if ((long) maxVal - (long) minVal + 1L < n) {
                throw new IllegalArgumentException("Range too small for uniqueness.");
            }
            // Sample without replacement using HashSet, then copy to array
            HashSet<Integer> set = new HashSet<>(n * 2);
            while (set.size() < n) {
                int v = minVal + rng.nextInt(maxVal - minVal + 1);
                set.add(v);
            }
            int[] a = new int[n];
            int i = 0;
            for (int v : set) a[i++] = v;
            return a;
        }

        private static void shuffleInPlace(int[] a, Random rng) {
            for (int i = a.length - 1; i > 0; i--) {
                int j = rng.nextInt(i + 1);
                int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
            }
        }

        private static long timeNs(Runnable r) {
            long t0 = System.nanoTime();
            r.run();
            return System.nanoTime() - t0;
        }

        // Suppress System.out printing during the run to avoid I/O skew
        private static void quiet(Runnable r) {
            PrintStream original = System.out;
            try {
                System.setOut(new PrintStream(OutputStream.nullOutputStream()));
                r.run();
            } finally {
                System.setOut(original);
            }
        }

        // Compute averages per n and method
        private static void summarize(String inCsv, String outCsv) throws IOException {
            Map<String, List<Long>> buckets = new LinkedHashMap<>();
            try (BufferedReader br = new BufferedReader(new FileReader(inCsv))) {
                String line = br.readLine(); // header
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",", -1);
                    if (parts.length != 4) continue;
                    String n = parts[0];
                    String method = parts[1];
                    long ns = Long.parseLong(parts[3]);
                    String key = n + "|" + method;
                    buckets.computeIfAbsent(key, k -> new ArrayList<>()).add(ns);
                }
            }
            try (PrintWriter out = new PrintWriter(new FileWriter(outCsv))) {
                out.println("n,method,avg_time_ms");
                for (Map.Entry<String, List<Long>> e : buckets.entrySet()) {
                    String[] km = e.getKey().split("\\|");
                    String n = km[0], method = km[1];
                    List<Long> vals = e.getValue();
                    double avgNs = vals.stream().mapToLong(Long::longValue).average().orElse(Double.NaN);
                    double ms = avgNs / 1_000_000.0;
                    out.printf(Locale.US, "%s,%s,%.6f%n", n, method, ms);
                }
            }
        }

}
