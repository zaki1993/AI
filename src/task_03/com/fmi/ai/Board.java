package task_03.com.fmi.ai;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {

    private static final char QUEEN_CHAR = '*';
    private static final char EMPTY_SPACE_CHAR = '_';
    private char[][] board;
    private int[] queens;

    public Board(int n) {
    	
        addRandomQueens(n);
    }

    private void addRandomQueens(int n) {

    	this.queens = new int[n];
    	// generate random position for each column
        for (int i = 0; i < n; i++) {
        	int row = new Random().nextInt(n);
        	queens[i] = row;
        }
    }

    private void buildBoard() {
    	
    	int n = queens.length;
    	this.board = new char[n][n];
    	
        for (int i = 0; i < n; i++) {
            board[queens[i]][i] = QUEEN_CHAR;
        }

        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (board[i][j] != QUEEN_CHAR) {
        			board[i][j] = EMPTY_SPACE_CHAR;
        		}
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
    	
    	List<Pair> conflicted = null;

    	do {
    		addRandomQueens(queens.length);
    		conflicted = runMinConflicts();
    	} while (conflicted != null && !conflicted.isEmpty());
    	
    	// print result
    	buildBoard();
    	print();
    }

    private List<Pair> runMinConflicts() {
    	
    	List<Pair> conflicted = new ArrayList<>();
		for (int i = 0; i < 5000; i++) {

    		// for each queen find the number of conflicts she is with the other queens
    		for (int j = 0; j < queens.length; j++) {

    			int conflictsNumber = conflicts(queens[j], j);
    			// if the number of conflicts is greater than 0 then add it to the list
    			if (conflictsNumber > 0) {
    				conflicted.add(new Pair(queens[j], j));
    			}
    		}
    		if (!conflicted.isEmpty()) {

    			// choose random queen from the list with the conflicted queens
        		int randIdx = new Random().nextInt(conflicted.size());

        		Pair queenPosition = conflicted.get(randIdx);
        		// find the row with the minimal number of conflicts
    			int queenCol = queenPosition.getCol();        		
        		Pair[] indexesOfConflicts = new Pair[queens.length];

        		for (int j = 0; j < queens.length; j++) {
        			queens[queenCol] = j; // set the queen position to be j, this is only temporary
        			int possibleConflicts = conflicts(j, queenCol);
        			indexesOfConflicts[j] = new Pair(j, possibleConflicts);
        		}
        		Arrays.sort(indexesOfConflicts);

        		// if there are some minimal indexes pick random of them
        		List<Integer> equalConflictIndexes = findEqualConflictIndexes(indexesOfConflicts, indexesOfConflicts[0].getCol());
        		int minConflictsRandIdx = new Random().nextInt(equalConflictIndexes.size());

        		queens[queenCol] = equalConflictIndexes.get(minConflictsRandIdx);

        		conflicted.clear();
    		} else {
    			break;
    		}
    	}
		return conflicted;
	}

	private List<Integer> findEqualConflictIndexes(Pair[] arr, int col) {
		
		List<Integer> result = new ArrayList<>();
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].getCol() == col) {
				result.add(arr[i].getRow());
			}
		}
		
		return result;
	}

	/**
     * Returns the number of queens that conflict with the current queen (row, col)
     */
    private int conflicts(int queenRow, int queenCol) {
    	
    	int count = 0;
    	
    	// loop through all the queens
    	for (int col = 0; col < queens.length; col++) {
			int row = queens[col];
			
			// This is the current queen so we ignore that
			if (queenRow == row && queenCol == col) {
				continue;
			}
			
			if (queenRow == row) {
				count++; // check if they are on the same row
			} else if (col - row == queenCol - queenRow) {
				count++; // this is the main diagonal
			} else if (col + row == queenCol + queenRow) {
				count++; // this is the secondary diagonal
			}
		}
    	
    	return count;
    }
}
