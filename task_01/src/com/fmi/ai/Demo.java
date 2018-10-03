package com.fmi.ai;

import java.util.Stack;

public class Demo {
	
	private static final int BLOCK = 1;
	private static final int PATH = 0;
	
	public static void main(String[] args) {
		
		int[][] matrix = { {0, 0, 1, 1},
						   {1, 0, 1, 1},
						   {0, 0, 0, 0},
						   {0, 0, 0, 1},
						   {1, 0, 0, 1},
						   {1, 1, 0, 1} };
		
		Stack<Point> path = findPath(matrix, new Point(0, 0), new Point(5, 2), new Stack<>());
		if (path != null && !path.isEmpty()) {
			System.out.println("Path length is: " + path.size());
			System.out.println("Path is: " + path);
		} else {
			System.out.println("No path found!");
		}
	}

	/**
	 * Using recursive DFS to find if there is a path from Point A to Point B
	 * Store the path in a Stack in order to store the visited points
	 */
	private static Stack<Point> findPath(int[][] matrix, Point start, Point end, Stack<Point> path) {

		if (!path.contains(start)) {
			path.push(start);
		}
		
		if (start.equals(end)) {
			return path;
		}

		setBlock(matrix, start);
		
		// tries to visit the right child if such
		// then the down one, then the left one
		// and then the up one 
		// if no child valid then it returns to the parent if
		// the point has such
		if (isValidPath(matrix, start.goRight())) {
			path = findPath(matrix, start.goRight(), end, path);
		} else if (isValidPath(matrix, start.goDown())) {
			path = findPath(matrix, start.goDown(), end, path);
		} else if (isValidPath(matrix, start.goLeft())) {
			return findPath(matrix, start.goLeft(), end, path);
		} else if (isValidPath(matrix, start.goUp())) {
			path = findPath(matrix, start.goUp(), end, path);
		} else {
			path.pop();
			// Pops the inserted element from the path
			// an returns to it's parent
			// as we have set this point to be block we will not
			// try to move to this point again
			Point parent = start.getParent();
			if (parent != null) {
				path = findPath(matrix, parent, end, path);
			}
		}
		
		return path;
	}
	
	private static void setBlock(int[][] matrix, Point point) {
		if (isPointInMatrix(matrix, point)) {
			matrix[point.getX()][point.getY()] = BLOCK;
		}
	}

	private static boolean isValidPath(int[][] matrix, Point point) {
		return isPointInMatrix(matrix, point) && matrix[point.getX()][point.getY()] != BLOCK;
	}

	private static boolean isPointInMatrix(int[][] matrix, Point point) {
		
		boolean result = false;
		int matrixX, matrixY;
		if ((matrixX = matrix.length) > 0 && (matrixY = matrix[0].length) > 0) {
			int pointX = point.getX();
			int pointY = point.getY();
			result = pointX >= 0 && pointX < matrixX && pointY >= 0 && pointY < matrixY;
		}
		return result;
	}
}
