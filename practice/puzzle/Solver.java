import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Solver {
    private SearchNode lastNode;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) throw new IllegalArgumentException();
        Comparator<SearchNode> comparator = new Comparator<SearchNode>() {
            @Override
            public int compare(SearchNode o1, SearchNode o2) {
                final int a = o1.cost + o1.manhattan;
                final int b = o2.cost + o2.manhattan;
                if (a > b) {
                    return 1;
                } else if (a == b) {
                    return 0;
                } else return -1;
            }
        };

        final MinPQ<SearchNode> pq = new MinPQ<>(comparator);
        final MinPQ<SearchNode> pqTwin = new MinPQ<>(comparator);
        pqTwin.insert(new SearchNode(initial.twin(), 0, null));
        pq.insert(new SearchNode(initial, 0, null));
        if (initial.isGoal()) {
            lastNode = new SearchNode(initial, 0, null);
            return;
        }
        while (!pq.isEmpty()) {
            if (insertNeighbors(pq)) {
                return;
            }
            if (insertNeighbors(pqTwin)) {
                lastNode = null;
                return;
            }
        }
    }

    private boolean insertNeighbors(MinPQ<SearchNode> pq) {
        final SearchNode node = pq.delMin();
        for (Board neighbor : node.board.neighbors()) {
            final SearchNode neighborNode = new SearchNode(neighbor, node.cost + 1, node);
            if (node.previousSearchNode == null || !neighbor.equals(node.previousSearchNode.board)) {
                pq.insert(neighborNode);
            }
            if (neighbor.isGoal()) {
                lastNode = neighborNode;
                return true;
            }
        }
        return false;
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return lastNode != null;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (lastNode == null) return -1;
        return lastNode.cost;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        final Stack<SearchNode> stack = new Stack<>();
        final List<Board> list = new ArrayList<>();
        SearchNode node = lastNode;
        while (node != null) {
            stack.push(node);
            node = node.previousSearchNode;
        }
        while (!stack.isEmpty()) {
            list.add(stack.pop().board);
        }

        if (!list.isEmpty()) {
            return list;
        } else return null;
    }

    // test client (see below)

    private static class SearchNode {
        private final Board board;
        private final SearchNode previousSearchNode;
        private final int cost;
        private final int manhattan;

        SearchNode(Board board, int cost, SearchNode pre) {
            this.board = board;
            this.cost = cost;
            this.previousSearchNode = pre;
            manhattan = this.board.manhattan();
        }
    }
}
