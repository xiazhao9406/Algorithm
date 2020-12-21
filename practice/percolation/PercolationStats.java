import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private static final double DELTA = 1.96;
    private final double[] x;
    private final int t;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();
        x = new double[trials];
        this.t = trials;
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                int m = StdRandom.uniform(n * n);
                test.open(1 + m / n, 1 + m % n);
            }
            x[i] = 1.0 * test.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(x);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(x);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - DELTA / Math.sqrt(t);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + DELTA / Math.sqrt(t);
    }

    public static void main(String[] args) {
        System.out.println();
    }

}