package task_02.com.fmi.ai;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Board implements Cloneable {

    private int[] state;
    private int dimension;

    public Board(int dimension) {

        init(dimension);
        readStates(dimension);
    }

    /**
     * Used to create exact copy of the board
     */
    private Board(Board other) {

        int dimension = other.getDimension();
        init(dimension);
        int[] state = other.getState();
        for (int i = 0; i < dimension * dimension; i++) {
            this.state[i] = state[i];
        }
    }

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();

        for(int i = 0; i < dimension * dimension; i++) {
            result.append(state[i]).append(" ");
            if (i % dimension == 0) {
                result.append("\n");
            }
        }

        return result.toString();
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = false;

        if (obj == null) {
            result = false;
        } else {
            if (obj.getClass() != Board.class){
                result = false;
            } else if (obj == this) {
                result = true;
            } else {
                Board other = (Board) obj;
                result = Arrays.equals(this.state, other.state);
            }
        }

        return result;
    }

    @Override
    public Board clone() {

        Board result = null;
        if (dimension != 1) {
            result = new Board(this);
            // if the first two blocks in first row are not empty, exchange them.
            // otherwise, exchange the first two blocks on second row.
            if (state[0] != 0 && state[1] != 0) {
                swap(result, 0, 1);
            } else {
                swap(result, dimension, dimension + 1);
            }
        }

        return result;
    }

    // swaps two elements in the state
    // and returns the board
    private Board swap(Board other, int i, int j) {

        int temp = other.state[i];
        other.state[j] = other.state[i];
        other.state[i] = temp;
        return other;
    }

    public void print(PrintStream out) {
        out.println(toString());
    }

    public int getDimension() {
        return dimension;
    }

    public int[] getState() {
        return state;
    }

    /**
     * @return if we reached the goal state [1, 2, 3, 4, 5, 6, 7, 8, ...,  0]
     */
    public boolean isGoal() {
        return IntStream.range(0, dimension * dimension - 1)
                        .allMatch(idx -> state[idx] == idx + 1);
    }

    public int getManhattan() {               // sum of Manhattan distances between blocks and goal
        return IntStream.range(0, dimension * dimension)
                        .filter(i -> state[i] != i + 1 && state[i] != 0)
                        .map(i->getManhattan(state[i], i))
                        .sum();
        /*for (int i = 0; i < dimension * dimension; i++) {
            if (state[i] != i + 1 && state[i] != 0) {
                sum += getManhattan(state[i], i);
            }
        }*/
    }

    public int getHamming() {
        return (int) IntStream.range(0, dimension * dimension)
                              .filter(i->state[i] != i + 1 && state[i] != 0)
                              .count();
        /*int count = 0;
        for (int i = 0; i < dimension * dimension; i++) {
            if (state[i] != i + 1 && state[i] != 0) {
                count++;
            }
        }
        return count;*/
    }

    // Returns all neighbors boards
    public Iterable<Board> getNeighbors() {

        int index = 0;
        boolean found = false;
        Board neighbor;
        Queue<Board> q = new Queue<Board>();

        for (int i = 0; i < board.length; i++)    // search for empty block
            if (board[i] == 0) {
                index = i;
                found = true;
                break;
            }
        if (!found)  return null;

        if (index / N != 0) {                      // if not first row
            neighbor = new Board(board);
            exch(neighbor, index, index - N);  // exchange with upper block
            q.enqueue(neighbor);
        }

        if (index / N != (N - 1)) {               // if not last row
            neighbor = new Board(board);
            exch(neighbor, index, index + N);  // exchange with lower block
            q.enqueue(neighbor);
        }

        if ((index % N) != 0) {                        // if not leftmost column
            neighbor = new Board(board);
            exch(neighbor, index, index - 1);  // exchange with left block
            q.enqueue(neighbor);
        }

        if ((index % N) != N - 1) {                          // if not rightmost column
            neighbor = new Board(board);
            exch(neighbor, index, index + 1);  // exchange with left block
            q.enqueue(neighbor);
        }

        return q;
    }

    private int getManhattan(int goal, int current) {
        return Math.abs((goal - 1) / dimension - current / dimension) +
                Math.abs((goal - 1) % dimension - current % dimension);
    }

    private void readStates(int size) {

        try(Scanner sc = new Scanner(System.in)) {

            System.out.println("Enter board initial state: ");
            this.state = readState(sc, size);
        }
    }

    private int[] readState(Scanner sc, int dimension) {

        int[] result = new int[dimension * dimension];

        for (int i = 0; i < dimension * dimension; i++) {
            int stateNum = sc.nextInt();
            result[i] = stateNum;
        }

        return result;
    }

    private void init(int dimension) {
        this.state = new int[dimension * dimension];
        this.dimension = dimension;
    }
}
