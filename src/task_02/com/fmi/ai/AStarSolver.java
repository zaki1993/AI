package task_02.com.fmi.ai;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class AStarSolver {

    private Node goal;

    private class Node {

        Board board;
        Node previous;
        int moves;

        Node(Board board) {
            this.board = board;
            this.moves = 0;
        }

        void traverse() {

            System.out.println("Moves: " + this.moves);
            Node temp = this;
            while (temp.previous != null) {
                System.out.println(temp.board.getDirection());
                //System.out.println(temp.board);
                temp = temp.previous;
            }
        }
    }

    private class MinPriorityQueueComparator<T extends AStarSolver.Node> implements Comparator<T> {

        @Override
        public int compare(T a, T b) {
            int pa = a.board.getManhattan() + a.moves;
            int pb = b.board.getManhattan() + b.moves;
            return pa > pb ? 1 : (pa < pb) ? -1 : 0;
        }
    }

    public AStarSolver(Board board) {

        if (board != null) {
            this.goal = new Node(board);
        } else {
            throw new IllegalArgumentException("Invalid board!");
        }
    }

    public void solve() throws UnsolvablePuzzleException {

        if (goal != null) {
            findSolution();
        } else {
            throw new UnsolvablePuzzleException("Invalid board!");
        }
    }

    private void findSolution() {

        MinPriorityQueueComparator<Node> minPqComparator = new MinPriorityQueueComparator<>();
        PriorityQueue<Node> minPq = new PriorityQueue<>(minPqComparator);
        PriorityQueue<Node> copyMinPq = new PriorityQueue<>(minPqComparator);

        Node twinNode = new Node(goal.board);
        minPq.add(goal);
        copyMinPq.add(twinNode);

        Node min = minPq.poll();
        Node twinMin = copyMinPq.poll();

        while (!min.board.isGoal() && !twinMin.board.isGoal()) {
            minPq.addAll(processNeighbors(min, minPqComparator));
            copyMinPq.addAll(processNeighbors(twinMin, minPqComparator));

            min = minPq.poll();
            twinMin = copyMinPq.poll();
        }

        if (min.board.isGoal()) {
            goal = min;
        } else {
            goal = null;
        }

        if (goal != null) {
            goal.traverse();
        }
    }

    private PriorityQueue processNeighbors(Node node, Comparator pqComparator) {

        PriorityQueue<Node> result = new PriorityQueue<>(pqComparator);
        for (Board b : node.board.getNeighbors()) {
            if (node.previous == null || !b.equals(node.previous.board)) {
                Node n = new Node(b);
                n.moves = node.moves + 1;
                n.previous = node;
                result.add(n);
            }
        }
        return result;
    }
}
