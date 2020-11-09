package tests;

import org.junit.Assert;
import org.junit.Test;

import il.ac.tau.cs.sw1.hw3.ArrayUtils ;

public class ArrayTests {

    @Test
    public void TestTransposedMatrix1() {
		int [][] matrix = new int[0][];
		int [][] transposed = new int[0][]; 

		Assert.assertArrayEquals(transposed, ArrayUtils.transposeMatrix(matrix));
		
		matrix = new int[][] {{1, 2, 3}, {4,5,6}, {7,8,9}};
		transposed = new int[][] {{1,4,7}, {2,5,8}, {3,6,9}};;
		Assert.assertArrayEquals(transposed, ArrayUtils.transposeMatrix(matrix));
		
		matrix = new int[][] {{-1,8}, {7,-3}};
		transposed = new int[][] {{-1,7}, {8,-3}};;
		Assert.assertArrayEquals(transposed, ArrayUtils.transposeMatrix(matrix));
		
		matrix = new int[][] {{1, 2, 3}, {4,5,6}};
		transposed = new int[][] {{1, 4}, {2,5}, {3,6}};	
		Assert.assertArrayEquals(transposed, ArrayUtils.transposeMatrix(matrix));
    }
    
    @Test
    public void TestShiftArrayCyclic() {
		int [] array = new int[0];
		int move = 0;
		char direction = 'x';
		int [] shifted = new int[0];
		int [] returndArray = new int[0];
		
		move = 3; direction = 'R';
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move = -1; direction = 'R' ; shifted = new int[] {2,3,4,5,1};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move =  1; direction = 'R' ; shifted = new int[] {5,1,2,3,4};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move =  1; direction = 'r' ; shifted = new int[] {1,2,3,4,5};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move =  -2; direction = 'g' ; shifted = new int[] {1,2,3,4,5};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move =  3; direction = 'L' ; shifted = new int[] {4,5,1,2,3};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {1, 2, 3, 4, 5}; move =  -3; direction = 'L' ; shifted = new int[] {3,4,5,1,2};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
		array = new int[] {0, 8, 9, 5, 6}; move =  6; direction = 'L' ; shifted = new int[] {8, 9, 5, 6,0};
		returndArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		Assert.assertArrayEquals(shifted, returndArray);
		Assert.assertSame(array, returndArray);
		
	}
	
    @Test
	public void TestAlternateSum() {
		int [] array = new int[0];
		int max = 0;
		
		Assert.assertEquals(ArrayUtils.alternateSum(array), max);
		
		array = new int[] {1, -2, 3, 4, 5} ; max = 7;
		Assert.assertEquals(ArrayUtils.alternateSum(array), max);
		
		array = new int[] {1, 2,- 3, 4, 5} ; max = 9;
		Assert.assertEquals(ArrayUtils.alternateSum(array), max);

	}
	
	@Test
	public void TestFindPath() {
		int [][] m = new int[0][];
		int j=0;
		int i=0;
		int k = 0;
		int path = 0;
		
		m = new int[][] {{1,0,0},{0,1,0},{0,0,1}}; i=1;j=1; k=1; ;path= 1;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);

		m = new int[][] {{1,0,0},{0,1,0},{0,0,1}}; i=1;j=1; k=2; ;path= 0;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);

		m = new int[][] {{1,0,0,1},{0,1,0,1},{0,0,1,0},{1,1,0,1}}; i=0;j=1; k=9; ;path= 0;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);

		m = new int[][] {{1,1,0},{0,1,1},{0,1,1}}; i=0;j=2; k=2; ;path= 1;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);

		m = new int[][] {{1,1,0},{0,1,1},{0,1,1}}; i=2;j=0; k=3; ;path= 0;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);

		m = new int[][] {{1,0,0,1},{1,1,0,1},{0, 1,1,0},{1,1,0,1}}; i=0;j=2; k=4; ;path= 0;
		Assert.assertEquals(ArrayUtils.findPath(m, i, j, k), path);
	}
	
}

