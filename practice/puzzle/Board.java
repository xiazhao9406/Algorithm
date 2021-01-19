import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int[][] DIRS = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};
    private final int[][] tiles;
    private final int n;
    private final int hamming;
    private final int manhattan;
    private int blankR;
    private int blankC;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();
        this.n = tiles.length;
        if (n < 2 || n >= 128) throw new IllegalArgumentException();
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
                if (this.tiles[i][j] == 0) {
                    this.blankC = j;
                    this.blankR = i;
                }
            }
        }
        hamming = calculateHamming();
        manhattan = calculateManhattan();
    }

    // unit testing (not graded)

    private static void exchange(int[][] tiles, int r0, int c0, int r1, int c1) {
        final int temp = tiles[r0][c0];
        tiles[r0][c0] = tiles[r1][c1];
        tiles[r1][c1] = temp;
    }

    private static int[][] deepCloneTiles(int[][] tiles) {
        final int[][] copy = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                copy[i][j] = tiles[i][j];
            }
        }
        return copy;
    }

    private int calculateManhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    continue;
                } else {
                    sum += Math.abs((tiles[i][j] - 1) / n - i) + Math.abs((tiles[i][j] - 1) % n - j);
                }
            }
        }
        return sum;
    }

    private int calculateHamming() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != 0 && i * n + j + 1 != tiles[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // string representation of this board
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] < 10 && tiles[i][j] >= 0) {
                    stringBuilder.append("  " + tiles[i][j]);
                } else {
                    stringBuilder.append(" " + tiles[i][j]);
                }
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (n != that.n) return false;
        if (manhattan != that.manhattan) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != that.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> list = new ArrayList<>();
        for (int i = 0; i < DIRS.length; i++) {
            final int[][] copyTiles = deepCloneTiles(this.tiles);
            final int c = this.blankC + DIRS[i][1];
            final int r = this.blankR + DIRS[i][0];
            if (r >= 0 && r < n && c >= 0 && c < n) {
                exchange(copyTiles, r, c, this.blankR, this.blankC);
                list.add(new Board(copyTiles));
            }
        }
        return list;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        final int[][] tiles = deepCloneTiles(this.tiles);
        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            exchange(tiles, 0, 0, 0, 1);
        } else {
            exchange(tiles, 1, 0, 1, 1);
        }
        return new Board(tiles);
    }

//    public int tileAt(int row, int col) {
//        return tiles[row][col];
//    }
}
