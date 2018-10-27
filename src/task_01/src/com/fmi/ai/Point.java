package com.fmi.ai;

public final class Point {
	
	private final int x;
	private final int y;
	private final Point parent;
	
	public Point(int x, int y) {
		this(x, y, null);
	}
	
	public Point(int x, int y, Point parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object p) {
		
		boolean result = false;
		if (p.getClass() == Point.class) {
			Point other = (Point) p;
			result = x == other.getX() && y == other.getY();
		}
		return result;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("(").append(x).append(", ").append(y).append(")").toString();
	}
	
	public Point goRight() {
		return new Point(x, y + 1, this);
	}
	
	public Point goDown() {
		return new Point(x + 1, y, this);
	}
	
	public Point goLeft() {
		return new Point(x, y - 1, this);
	}
	
	public Point goUp() {
		return new Point(x - 1, y, this);
	}
	
	public Point getParent() {
		return this.parent;
	}
}
