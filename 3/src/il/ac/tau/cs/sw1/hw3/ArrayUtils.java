package il.ac.tau.cs.sw1.hw3;

import java.util.LinkedList;

public class ArrayUtils {

	public static int[][] transposeMatrix(int[][] m) {
		int rowsNum = m.length;
		if (rowsNum == 0) {
			return m;
		}
		int colsNum = m[0].length;
		if (colsNum == 0) {
			return m;
		}
		int[][] transMat = new int[colsNum][rowsNum];
		for (int i = 0; i < rowsNum; i++) {
			for (int j = 0; j < colsNum; j++) {
				transMat[j][i] = m[i][j];
			}
		}

		return transMat; // Replace this with the correct returned value

	}

	public static int[] shiftArrayCyclic(int[] array, int move, char direction) {
		if (!(direction == 'R' || direction == 'L') || array.length == 0) {
			return array;
		}
		if (direction == 'L') {
			move = -move;
		}
		int n = array.length;
		move = ((move % n) + n) % n;
		
		int[] copiedArray = deepCopyArray(array);
		int moveTo;
		for (int i = 0; i < n; i++) {
			moveTo = (i + move)%n;
			array[moveTo] = copiedArray[i];
			
		}
		
		return array; //Replace this with the correct returned value

	}
	public static int[] deepCopyArray(int[] array) {
		int n = array.length;
		if (n == 0) { 
			return array;
			}
		int[] myCopy = new int[n];
		for (int i = 0; i < n; i++) {
			myCopy[i] = array[i];
		}
		return myCopy;
	}

	public static int alternateSum(int[] array) {
		int maxSum = 0;
		int currSum;
		for (int i = 0; i < array.length ; i++) {
			currSum = 0;
			for (int j = i, c = 0; j < array.length ; j++, c++) {
				currSum += Math.pow(-1, c) * array[j];
				maxSum = Math.max(maxSum, currSum);
			}
		}
		
		return maxSum;

	}
	
// LAHZOR 
//	public static int findPath(int[][] m, int i, int j, int k) {
//		int n = m.length;
//		if (k > n-1 || k < 0 || (m[i][j] == 1 && k > 1)) {
//			return 0;
//		}
//		if ((i == j && k == 1) ||(m[i][j] == 1 && k==0)) {
//			return 1;
//		}
//		
//		int[] nodeNeighbors = findNeighbors(m, i);
//		for (int neighbor : nodeNeighbors) {
//			if (findPath(m, neighbor, j, k-1) == 1) {
//				return 1;
//			}
//		}
//		
//		
//		
//				
//		return 0; 
//
//	}
	
	
	public static int findPath(int[][] m, int i, int j, int k) {
		int n = m.length;
		if (k == 0 || n == 0) {
			return 0;
		}
		if (i == j && k == 1) {
			return 1;
		}
		boolean[] nodeVisited = new boolean[n];
		LinkedList<Integer> myQueue = new LinkedList<>();
		myQueue.addLast(i);
		nodeVisited[i] = true;
		int node;
		myQueue.addLast(-1);
		
		while (myQueue.size() > 1 && k >= 0) {
			node = myQueue.pollFirst();
			if (node == -1) {
				k--;
				myQueue.addLast(-1);
				continue;
			}
			if (node == j && k == 0) {
				return 1;
			}
			for (int p = 0; p < n; p++) {
//				if (p == j && m[node][p] == 1 && k == 0) {
//					System.out.println("p is "+p);
//
//
//					return 1;
//				}
				if (nodeVisited[p] == false && m[node][p] == 1) {
					nodeVisited[p] = true;
					myQueue.addLast(p);
					
				}
			
			}
		}
		
		
		
		
		return 0;

	}
	
//	public static int findShortestPath(int[][] m, int i, int j, boolean[] nodeVisited, LinkedList<Integer> myQueue) {
//		int k = 0;
//		int node;
//		while (myQueue.size() > 0) {
//			k++;
//			node = myQueue.pollFirst();
//			for (int p = 0; p < m.length; p++) {
//				if (m[node][p])
//			}
//			
//		}
//		
//		
//		return 0;
//	}
	

}
