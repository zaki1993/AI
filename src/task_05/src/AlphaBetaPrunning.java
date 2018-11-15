import java.util.Arrays;
import java.util.Scanner;

public class AlphaBetaPrunning {
	
	private final static char PC = 'O'; // max
	private final static char PL = 'X'; // min
	private final static char EMPTY = '_'; // empty board place
	
	public static void main(String[] args) {
		
		char[][] board = buildBoard();
		
		// player is first
		boolean isPcMove = false;
		
		int result = 0;
		
		do {
			if (isPcMove) {
				board = minMaxMove(board, PC, Integer.MIN_VALUE, Integer.MAX_VALUE);
				isPcMove = false;
			} else {
				board = readPlayerMove(board);
				isPcMove = true;
			}
			if (!isPcMove) {
				printBoard(board);
			}
		} while (!isEndState(board));
		
		result = getValue(board);
		
		if (isPcMove) {
			printBoard(board);
		}
		
		System.out.println("Winner is: " + (result == 1 ? PL : result == -1 ? PC : "Draw"));
	}
	
	private static char[][] minMaxMove(char[][] board, char player, int alpha, int beta) {
		
		if (isEndState(board)) {
			return board;
		}
		
		int x = 0;
		int y = 0;
		
		if (player == PL) {
			
			for (int i = 0; i < board.length; i++) {
				boolean isBetaCut = false;
				for (int j = 0; j < board.length; j++) {
					if (isValidMove(board, i, j)) {
						char[][] tmp = copyBoard(board);
						tmp[i][j] = PL;	
						tmp = minMaxMove(tmp, PC, alpha, beta);
						int value = getValue(tmp);
						if (value > alpha) {
							alpha = value;
							x = i;
							y = j;
							// beta cut
							if (alpha >= beta) {
								isBetaCut = true;
								break;
							}
						}
					}
				}
				if (isBetaCut) {
					break;
				}
			}
		} else {
			
			for (int i = 0; i < board.length; i++) {
				boolean isAlphaCut = false;
				for (int j = 0; j < board.length; j++) {
					if (isValidMove(board, i, j)) {
						char[][] tmp = copyBoard(board);
						tmp[i][j] = PC;	
						tmp = minMaxMove(tmp, PL, alpha, beta);
						int value = getValue(tmp);
						if (value < beta) {
							beta = value;
							x = i;
							y = j;
							// alpha cut
							if (alpha >= beta) {
								isAlphaCut = true;
								break;
							}
						}
					}
				}
				if (isAlphaCut) {
					break;
				}
			}
		}
		
		char[][] result = copyBoard(board);
		result[x][y] = player;
		
		return result;
	}

	private static char[][] readPlayerMove(char[][] board) {
		
		Scanner sc = new Scanner(System.in);
		
		int x, y;
		do {
			x = sc.nextInt();
			y = sc.nextInt();
		} while (!isValidMove(board, x, y));
		
		char[][] result = copyBoard(board);
		result[x][y] = PL;
	
		return result;
	}
	
	private static void printBoard(char[][] board) {
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	public static char[][] buildBoard() {
		
		char[][] board = new char[3][3];
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = EMPTY;
			}
		}
		
		return board;
	}
	
	public static boolean isBoardDraw(char[][] board) {
		
		boolean result = true;
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == EMPTY) {
					result = false;
					break;
				}
			}
		}
		
		return result;
	}
	
	// returns 0 if draw
	// returns 1 if X is winner
	// returns -1 if O is winner
	public static int getValue(char[][] board) {
		
		return isWinner(board, 'X') ? 1 : isWinner(board, 'O') ? -1 : 0;
	}
	
	public static boolean isEndState(char[][] board) {
		
		return isWinner(board, 'X') || isWinner(board, 'O') || isBoardDraw(board);
	}
	
	public static boolean isWinner(char[][] board, char player) {
		
		boolean horisontal = false;
		for (int i = 0; i < 3; i++) {
			horisontal |= board[i][0] == player && board[i][1] == player && board[i][2] == player;
		}
		
		if (horisontal) {
			return true;
		} 
		
		boolean vertical = false;
		for (int i = 0; i < 3; i++) {
			 vertical |= board[0][i] == player && board[1][i] == player && board[2][i] == player;
		}
		
		if (vertical) {
			return true;
		}
		
		boolean diagonal = (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
						   (board[0][2] == player && board[1][1] == player && board[2][0] == player);
		
		if (diagonal) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isValidMove(char[][] board, int x, int y) {
		
		boolean result = false;
		
		if (x >= 0 && x <= board.length - 1 && y >= 0 && y <= board.length - 1) {
			if (board[x][y] == EMPTY) {
				result = true;
			}
		}
		
		return result;
	}
	
	public static char[][] copyBoard(char[][] board) {
		
		char[][] result = new char[board.length][board.length];
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result.length; j++) {
				result[i][j] = board[i][j];
			}
		}
		
		return result;
	}
}
