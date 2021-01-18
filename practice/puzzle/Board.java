import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;

public class Board {
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    private final int[][] tiles;
    private int count = 0;
    private final int n;
    public Board(int[][] tiles) {
        if (tiles == null) throw new IllegalArgumentException();
        this.n = tiles.length;
        if (n < 2 || n >= 128) throw new IllegalArgumentException();
        this.tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }


    }
    // string representation of this board
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                stringBuilder.append(tiles[i][j]);
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
    // board dimension n
    public int dimension() {
        if (tiles == null) return 0;
        else return n;
    }
    // number of tiles out of place
    public int hamming() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    count += 0;
                } else if (i * n + j + 1 != tiles[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }
    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = i * n + j + 1;

                int k = Math.abs((tiles[i][j] - 1) / n - i) + Math.abs((tiles[i][j] - 1) % n - j);
                if (tiles[i][j] == 0) {
                    k = 0;
                }

                if (x != tiles[i][j]) {
                    sum += k;
                }
            }
        }
        return sum;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    if (0 == tiles[i][j]);
                    else return false;
                } else if (i * n + j + 1 != tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if (n != ((Board) y).n ) return false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != ((Board) y).tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> list = new ArrayList<>();
        int c = 0, m = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (0 == tiles[i][j]) {
                    c = i;
                    m = j;
                }
            }
        }
        if (c == 0 && m == 0) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m + 1];
            neighbor1[c][m + 1] = 0;
            neighbor2[c][m] = tiles[c + 1][m];
            neighbor2[c + 1][m] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));
        } else if (c == 0 && m == n - 1) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m - 1];
            neighbor1[c][m - 1] = 0;
            neighbor2[c][m] = tiles[c + 1][m];
            neighbor2[c + 1][m] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));

        } else if (c == 0) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            int[][] neighbor3 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                    neighbor3[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m + 1];
            neighbor1[c][m + 1] = 0;
            neighbor2[c][m] = tiles[c + 1][m];
            neighbor2[c + 1][m] = 0;
            neighbor3[c][m] = tiles[c][m - 1];
            neighbor3[c][m - 1] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));
            list.add(new Board(neighbor3));
        } else if (c == n - 1 && m == 0) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m + 1];
            neighbor1[c][m + 1] = 0;
            neighbor2[c][m] = tiles[c - 1][m];
            neighbor2[c - 1][m] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));

        } else if (c == n - 1 && m == n - 1) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m - 1];
            neighbor1[c][m - 1] = 0;
            neighbor2[c][m] = tiles[c - 1][m];
            neighbor2[c - 1][m] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));

        } else if (c == n - 1) {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            int[][] neighbor3 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                    neighbor3[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m + 1];
            neighbor1[c][m + 1] = 0;
            neighbor2[c][m] = tiles[c][m - 1];
            neighbor2[c][m - 1] = 0;
            neighbor3[c][m] = tiles[c - 1][m];
            neighbor3[c - 1][m] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));
            list.add(new Board(neighbor3));

        } else {
            int[][] neighbor1 = new int[n][n];
            int[][] neighbor2 = new int[n][n];
            int[][] neighbor3 = new int[n][n];
            int[][] neighbor4 = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    neighbor1[i][j] = tiles[i][j];
                    neighbor2[i][j] = tiles[i][j];
                    neighbor3[i][j] = tiles[i][j];
                    neighbor4[i][j] = tiles[i][j];
                }
            }
            neighbor1[c][m] = tiles[c][m + 1];
            neighbor1[c][m + 1] = 0;
            neighbor2[c][m] = tiles[c - 1][m];
            neighbor2[c - 1][m] = 0;
            neighbor3[c][m] = tiles[c + 1][m];
            neighbor3[c + 1][m] = 0;
            neighbor4[c][m] = tiles[c][m - 1];
            neighbor4[c][m - 1] = 0;
            list.add(new Board(neighbor1));
            list.add(new Board(neighbor2));
            list.add(new Board(neighbor3));
            list.add(new Board(neighbor4));

        }
        return list;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        return new Board(tiles);

    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
