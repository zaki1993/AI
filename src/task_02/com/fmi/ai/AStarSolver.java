package task_02.com.fmi.ai;

public class Solver {

    private int moves;
    public Solver(Board board) {

    }
    // find a solution to the initial board (using the A* algorithm)
    public boolean isSolvable()            // is the initial board solvable?

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable

    public Iterable<Board> solve()      // sequence of boards in a shortest solution; null if unsolvable
}
