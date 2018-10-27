package task_02.com.fmi.ai;

public class UnsolvablePuzzleException extends Exception {

    public UnsolvablePuzzleException(Board board) {
        super(new StringBuilder("Board with start state: ").append(board.toString()).append(" is not solvable!").toString());
    }
}
