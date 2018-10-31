package task_03.com.fmi.ai;

public class Pair implements Comparable<Pair> {
	
	private int row;
	private int col;
	
	public Pair(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}

	@Override
	public int compareTo(Pair other) {
		return col > other.getCol() ? 1 : col == other.getCol() ? 0 : -1;
	}
	
	@Override
	public String toString() {
		return "[" + row + " " + col + "]";
	}
}
