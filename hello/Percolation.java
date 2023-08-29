/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    private boolean[][] trackopen; // input
    private int n;
    private WeightedQuickUnionUF map;
    private int numOfsite = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("bad argv");
        }
        this.n = n;
        trackopen = new boolean[n][n];
        map = new WeightedQuickUnionUF(n * n);

        for (int i = 0; i < n; i++) {
            map.union(0, i);
            for (int j = 0; j < n; j++) {
                trackopen[i][j] = false;
            }
        }

    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException("wrong row/col open");
        }
        if (!isOpen(row, col)) {
            trackopen[row - 1][col - 1] = true;
            numOfsite += 1;
            if (row > 1 && isOpen(row - 1, col)) {
                map.union((row - 2) * n + col - 1, (row - 1) * n + col - 1);
            }
            if (row < n && isOpen(row + 1, col)) {
                map.union(row * n + col - 1, (row - 1) * n + col - 1);
            }
            if (col > 1 && isOpen(row, col - 1)) {
                map.union((row - 1) * n + col - 2, (row - 1) * n + col - 1);
            }
            if (col < n && isOpen(row, col + 1)) {
                map.union((row - 1) * n + col, (row - 1) * n + col - 1);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException("wrong row/col open");
        }
        return (trackopen[row - 1][col - 1]);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row > n || col > n || row < 1 || col < 1) {
            throw new IllegalArgumentException("wrong row/col full");
        }

        if (!isOpen(row, col)) {
            return false;
        }
        int group = map.find((row - 1) * n + col - 1);
        int exitset = map.find(0);
        if (group == exitset) {
            return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numOfsite;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n; i++) {
            if (isFull(this.n, i + 1)) { // HERE!!!
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
