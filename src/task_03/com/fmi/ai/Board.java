package task_03.com.fmi.ai;

import java.util.Random;

public class Board {

    private char[][] board;

    public Board(int n) {
        this.board = new char[n][n];
        addRandomQueens(n);
    }

    private void addRandomQueens(int n) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '*';
            }
        }

        for (int i = 0; i < n; i++) {
            int row = new Random().nextInt(n);
            int col = new Random().nextInt(n);
            if (board[row][col] != 'Q') {
                board[row][col] = 'Q';
            } else {
                i--;
            }
        }
    }

    public void print() {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void minConflicts() {

        System.out.println(conflicts(2, 4));
    }

    /**
     * Returns the number of queens that conflict with the current queen (row, col)
     */
    private int conflicts(int row, int col) {

        int count = 0;

        // process row
        for (int i = 0; i < board.length; i++) {
            if (board[row][i] == 'Q' && i != col) {
                count++;
            }
        }

        // process col
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == 'Q' && i != row) {
                count++;
            }
        }

        // process diagonal one(main diagonal)
        for (int i = 0; i < (row == col ? board.length : board.length - col); i++) {
            int j = col - row + i;
            if (i != row && j != col && board[i][j] == 'Q') {
                count++;
            }
        }

        // process diagonal two(secondary diagonal)
        int x = 0;
        System.out.println("from " + col + " to " + (board.length - col));
        for (int j = col; j < board.length - col; j++) {
            int i = board.length - 1 - j;
            System.out.println("i = " + i + " j = " + j);
            if (i != row && j != col && board[i][j] == 'Q') {
                x++;
            }
        }
        System.out.println(x);

        return count;
    }
}
