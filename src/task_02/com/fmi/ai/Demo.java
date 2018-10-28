package task_02.com.fmi.ai;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) throws UnsolvablePuzzleException {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Enter board size: ");
            int size = sc.nextInt();
            Board board = new Board(size);

            AStarSolver solver = new AStarSolver(board);
            solver.solve();
        }
    }
}
