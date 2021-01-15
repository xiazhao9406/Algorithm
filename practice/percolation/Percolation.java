import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[] grid;
    private final WeightedQuickUnionUF unionUF;
    private final WeightedQuickUnionUF unionFindPercolation;
    private final int n;
    private final int top;
    private final int bottom;
    private int count = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException();
        grid = new boolean[n * n + 1];
        top = n * n;
        bottom = top + 1;
        this.n = n;
        unionUF = new WeightedQuickUnionUF(n * n + 1);
        unionFindPercolation = new WeightedQuickUnionUF((n * n + 2));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i * n + j] = false;
            }
        }
        grid[top] = true;
    }

    public static void main(String[] args) {
        System.out.println();
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n)
            throw new IllegalArgumentException();
        if (isOpen(row, col)) {
            return;
        }
        grid[index(row, col)] = true;

        int newRow = row + 1;
        int newCol = col;
        if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
            unionUF.union(index(newRow, newCol), index(row, col));
            unionFindPercolation.union(index(newRow, newCol), index(row, col));
        }

        newRow = row;
        newCol = col - 1;
        if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
            unionUF.union(index(newRow, newCol), index(row, col));
            unionFindPercolation.union(index(newRow, newCol), index(row, col));
        }

        newRow = row;
        newCol = col + 1;
        if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
            unionUF.union(index(newRow, newCol), index(row, col));
            unionFindPercolation.union(index(newRow, newCol), index(row, col));
        }

        newRow = row - 1;
        newCol = col;
        if (isValid(newRow, newCol) && isOpen(newRow, newCol)) {
            unionUF.union(index(newRow, newCol), index(row, col));
            unionFindPercolation.union(index(newRow, newCol), index(row, col));
        }

        if (row == 1) {
            unionUF.union(top, index(row, col));
            unionFindPercolation.union(top, index(row, col));
        }

        if (row == n) {
            unionFindPercolation.union(bottom, index(row, col));
        }

        count++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n)
            throw new IllegalArgumentException();
        return grid[index(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n)
            throw new IllegalArgumentException();
        if (!isOpen(row, col)) {
            return false;
        }

        return unionUF.find(top) == unionUF.find(index(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFindPercolation.find(top) == unionFindPercolation.find(bottom);
    }

    private int index(int row, int col) {
        return (row - 1) * n + col - 1;
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= n && col >= 1 && col <= n;
    }

}