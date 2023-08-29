/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;
    private int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        result = new double[trials];
        this.trials = trials;
        for (int i = 0; i < trials; i++) {
            Percolation test = new Percolation(n);
            while (!test.percolates()) {
                test.open(StdRandom.uniformInt(1, n + 1), StdRandom.uniformInt(1, n + 1));
            }
            result[i] = test.numberOfOpenSites() / (double) (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trials);
    }

    public static void main(String[] args) {

        PercolationStats test = new PercolationStats(Integer.parseInt(args[0]),
                                                     Integer.parseInt(args[1]));
        StdOut.println("mean                    =" + test.mean());
        StdOut.println("stddev                  =" + test.stddev());
        StdOut.println(
                "95% confidence interval =" + "[" + test.confidenceLo() + "," + test.confidenceHi()
                        + "]");
    }
}
