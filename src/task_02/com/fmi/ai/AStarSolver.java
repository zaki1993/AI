package task_02.com.fmi.ai;

import java.util.Comparator;

public final class AStarSolver {

    private int dimension;
    private Node goal;

    private class Node {

        Board board;
        Node previous;
        int moves;

        Node(Board board) {
            this.board = board;
            this.moves = 0;
        }
    }

    public AStarSolver(Board board) {

        if (board != null) {
            this.goal = new Node(board);
        } else {
            throw new IllegalArgumentException("Invalid board!");
        }
        this.dimension = board.getDimension();
    }

    public int getMoves() {
        return isSolvable() ? goal.moves : -1;
    }

    private boolean isSolvable() {
        return goal != null;
    }

    public void solve() throws UnsolvablePuzzleException {

        if (isSolvable()) {
            findSolution();
        } else {
            throw new UnsolvablePuzzleException(goal.board);
        }
    }

    private void findSolution() {

    }

    private class PriorityOrder implements Comparator<Node> {
        public int compare(Node a, Node b) {
            int pa = a.board.getManhattan() + a.moves;
            int pb = b.board.getManhattan() + b.moves;
            if (pa > pb)   return 1;
            if (pa < pb)   return -1;
            else              return 0;
        }
    }
}
