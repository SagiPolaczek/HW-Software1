package il.ac.tau.cs.sw1.hw3;



import java.util.Arrays;

public class Tester {
	public static void main(String[] args) {
		System.out.println("### Question 1 ###");
		System.out.println();

		testTransMatrix(1, 3);
		System.out.println("----------");
		
		testTransMatrix(3, 1);
		System.out.println("----------");
		
		testTransMatrix(1, 1);
		System.out.println("----------");
		
		testTransMatrix(0, 0);
		System.out.println("----------");
		
		testTransMatrix(3, 0);
		System.out.println("----------");
		
		testTransMatrix(4, 4);
		System.out.println("----------");
		
		testTransMatrix(7,3);

		
		System.out.println("### Question 2 ###");
		System.out.println();
		
		testShiftArrayCyclic(makeMatrix(1,5)[0], -1, 'R');
		System.out.println();
		System.out.println("Valid result: [2,3,4,5,1]");
		System.out.println("----------");
		
		testShiftArrayCyclic(makeMatrix(1,5)[0], 1, 'R');
		System.out.println();
		System.out.println("Valid result: [5,1,2,3,4]");
		System.out.println("----------");

		testShiftArrayCyclic(makeMatrix(1,5)[0], 1, 'r');
		System.out.println();
		System.out.println("Valid result: [1,2,3,4,5]");
		System.out.println("----------");

		testShiftArrayCyclic(makeMatrix(1,5)[0], -2, 'g');
		System.out.println();
		System.out.println("Valid result: [1,2,3,4,5]");
		System.out.println("----------");

		testShiftArrayCyclic(makeMatrix(1,5)[0], 3, 'L');
		System.out.println();
		System.out.println("Valid result: [4,5,1,2,3]");
		System.out.println("----------");
		
		testShiftArrayCyclic(makeMatrix(1,5)[0], -3, 'L');
		System.out.println();
		System.out.println("Valid result: [3,4,5,1,2]");
		System.out.println("----------");

		testShiftArrayCyclic(new int[] {0, 8, 9, 5, 6}, 6, 'L');
		System.out.println();
		System.out.println("Valid result: [8, 9, 5, 6,0]");
		System.out.println("----------");
		
		testShiftArrayCyclic(new int[] {}, 3, 'R');
		System.out.println();
		System.out.println("Valid result: []");
		System.out.println("----------");


		System.out.println("### Question 3 ###");
		System.out.println();
		
		testAlternateSum(new int[] {1, -2, 3, 4, 5});
		System.out.println();
		System.out.println("Valid result: 7");
		System.out.println("----------");
		
		testAlternateSum(new int[] {1, 2, -3, 4, 5});
		System.out.println();
		System.out.println("Valid result: 9");
		System.out.println("----------");

		testAlternateSum(new int[] {});
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");

		testAlternateSum(new int[] {1,1,1,1,1});
		System.out.println();
		System.out.println("Valid result: 1");
		System.out.println("----------");

		testAlternateSum(new int[] {1,-1,1,-1,1,-1});
		System.out.println();
		System.out.println("Valid result: 6");
		System.out.println("----------");
		
		testAlternateSum(new int[] {1,-1,1,-1,1,0});
		System.out.println();
		System.out.println("Valid result: 5");
		System.out.println("----------");


		System.out.println("### Question 4 ###");
		System.out.println();

		testFindPath(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}} , 1, 1, 1);
		System.out.println();
		System.out.println("Valid result: 1");
		System.out.println("----------");

		testFindPath(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}} , 1, 1, 2);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");
		
		testFindPath(new int[][] {{1,0,0,1}, {0,1,0,1}, {0,0,1,1}, {1,1,0,1}} , 1, 1, 9);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");

		testFindPath(new int[][] {{1,1,0}, {0,1,1}, {0,1,1}} , 0, 2, 2);
		System.out.println();
		System.out.println("Valid result: 1");
		System.out.println("----------");

		testFindPath(new int[][] {{1,1,0}, {0,1,1}, {0,1,1}} , 2, 0, 3);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");

		testFindPath(new int[][] {{1,0,0,1}, {1,1,0,1}, {0,1,1,0}, {1,1,0,1}} , 0, 2, 4);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");
		
		testFindPath(new int[][] {{1,0,0,1}, {1,1,0,1}, {0,1,1,0}, {1,1,0,1}} , 0, 2, 4);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");
		
		testFindPath(new int[][] {{1,0,0,1,0}, {1,1,1,0,0}, {0,0,1,1,1}, {0,0,0,1,1} ,{0,0,0,0,1}} , 1, 4, 0);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");
		
		testFindPath(new int[][] {{1,0,0,1,0}, {1,1,1,0,0}, {0,0,1,1,1}, {0,0,0,1,1} ,{0,0,0,0,1}} , 1, 4, 1);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");

		testFindPath(new int[][] {{1,0,0,1,0}, {1,1,1,0,0}, {0,0,1,1,1}, {0,0,0,1,1} ,{0,0,0,0,1}} , 1, 4, 3);
		System.out.println();
		System.out.println("Valid result: 0");
		System.out.println("----------");

		testFindPath(new int[][] {{1,0,0,1,0}, {1,1,1,0,0}, {0,0,1,1,1}, {0,0,0,1,1} ,{0,0,0,0,1}} , 1, 4, 2);
		System.out.println();
		System.out.println("Valid result: 1");
		System.out.println("----------");


		
		



		
		System.out.println("### Question 5 ###");
		System.out.println();
		
		testFindSortSequence("to be or not to be");
		System.out.println();
		System.out.println("Valid result: 'not to'");
		System.out.println("----------");

		testFindSortSequence("my mind is an empty zoo");
		System.out.println();
		System.out.println("Valid result: 'an empty zoo'");
		System.out.println("----------");

		testFindSortSequence("andy bought candy");
		System.out.println();
		System.out.println("Valid result: 'andy bought candy'");
		System.out.println("----------");
		
		testFindSortSequence("life is not not not fair");
		System.out.println();
		System.out.println("Valid result: 'is not not not'");
		System.out.println("----------");

		testFindSortSequence("art act");
		System.out.println();
		System.out.println("Valid result: 'act'");
		System.out.println("----------");

		testFindSortSequence("hey hey hey hey");
		System.out.println();
		System.out.println("Valid result: 'hey hey hey hey'");
		System.out.println("----------");
		
		testFindSortSequence("g f e d c b a");
		System.out.println();
		System.out.println("Valid result: 'a'");
		System.out.println("----------");


		System.out.println("### Question 6 ###");
		System.out.println();
		
		System.out.println("The String are: " +"mothEr in law" + " ; "+ "hitlEr woman");
		testIsAnagram("mothEr in law", "hitlEr woman");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsAnagram("ListeN", "SileNt");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsAnagram("software", "jeans");
		System.out.println();
		System.out.println("Valid result: false");
		System.out.println("----------");
		
		testIsAnagram("Funeral", "real Fun");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsAnagram("aA", "Aa");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsAnagram("", "     ");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsAnagram(" Rearrangement", "   greenermantra  ");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		
		

		
		System.out.println("### Question 7 ###");
		System.out.println();

		testIsEditDistance("dog", "god");
		System.out.println();
		System.out.println("Valid result: false");
		System.out.println("----------");

		testIsEditDistance("x", "x");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsEditDistance("main", "man");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsEditDistance("ab", "cab");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsEditDistance("ss", "a");
		System.out.println();
		System.out.println("Valid result: false");
		System.out.println("----------");

		testIsEditDistance("", "a");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");

		testIsEditDistance("a", "");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");
		
		testIsEditDistance(" ", "  ");
		System.out.println();
		System.out.println("Valid result: true");
		System.out.println("----------");


		

		System.out.println("##### THE END #####");
	

		
		
		
//		testAlternateSum(new int[] {1, -2, 3, 4, 5});
//		testAlternateSum(new int[] {1, 2, -3, 4, 5});
//		testAlternateSum(new int[] {1, 2, 3, 4, 5});
//		testAlternateSum(new int[] {1, 2, -3, 4, 5});
//		testAlternateSum(new int[] {1,0,3});
		
//		testFindSortSequence("andy bought candy");
//		testFindSortSequence("to be or not to be");
//		testFindSortSequence("my mind is an empty zoo");
//		testFindSortSequence("");
//		testFindSortSequence("art act");
//		testFindSortSequence("life is not not not fair");
//		testFindSortSequence("      ");
//		System.out.println("---");
		
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}} , 1, 1, 1));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}} , 1, 1, 2));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,1,0}, {0,1,1}, {0,1,1}} , 1, 2, 2));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,1,0}, {0,1,1}, {0,1,1}} , 2, 0, 2));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,1,0}, {1,1,0}, {0,0,1}} , 0, 1, 3));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,0,0,1}, {0,1,0,1}, {0,0,1,0},{1,1,0,1}} , 0, 1, 9));
//		System.out.println(ArrayUtils.findPath(new int[][] {{1,0,0,1}, {1,1,0,1}, {0,1,1,0},{1,1,0,1}} , 0, 2, 4));

//		testIsAnagram("mothEr in law", "hitlEr woman");
//		testIsAnagram("ListeN", "SileNt");
//		testIsAnagram("software", "jeans");
//		testIsAnagram("aA", "Aa");
//		testIsAnagram("", "     ");
//		testIsAnagram(" Rearrangement", "   greenermantra  ");
		

//		int [][] trans1 = {{1, 2, 3}, {1, 2, 3}};
//        int [][] trans2 = {{1,0}, {0,1}};
//        int [][] trans3 = {{1, 1, 1, 1}, {1, 2, 1, 1}, {1, 2, 3, 1}, {1, 2, 3, 4}};
//        int[] arr_cyclic1 = {3, 4, 5};
//        int [] arr_cyclic2 = {1, -8, 9, 0, 6};
//        int [] arr_cyclic3 = {1, 4, 5};
//        int [] arr_cyclic4 = {3, 9, 0, 10};
//        int [] arr_cyclic5 = {6, 7, 8};
//        int [] arr_cyclic6 = {9, 1, 1};
//        int [] alternate1 = {1, 2, -5, 6};
//        int [] alternate2 = {1, -2, 3, 4};
//        int [] alternate3 = {1, 7, 3, -20, 5, 9};
//        int [] alternate4 = {3, -10};
//        int [][] path1 = {{1, 0, 1, 0}, {0, 1, 1, 0}, {1, 0, 1, 0}, {0, 1, 0, 1}};
//        int [][] path2 = {{1, 0, 0, 0, 1}, {0, 1, 0, 1, 0}, {1, 0, 1, 0, 0}, {0, 0, 1, 1, 0}, {0, 1, 0, 0, 1}};
		

		
		

		
	}
	
	public static void testFindPath(int[][] m, int i, int j, int k) {
		System.out.println(ArrayUtils.findPath(m, i, j, k));

		
	}
	
	public static void testIsEditDistance(String a, String b) {
		System.out.println(StringUtils.isEditDistanceOne(a, b));
	}
	
	public static void testIsAnagram(String a, String b) {
		System.out.println(StringUtils.isAnagram(a, b));
	}
	
	public static void testFindSortSequence(String str) {
		System.out.println(StringUtils.findSortedSequence(str));
	}
	
	public static void testAlternateSum(int[] array) {
		int alterSum = ArrayUtils.alternateSum(array);
		System.out.println(alterSum);
	}
	
	public static void testShiftArrayCyclic(int[] array, int move, char direction) {
		System.out.println("Original Array: ");
		System.out.println(Arrays.toString(array));
		System.out.println();
		int[] shiftedArray = ArrayUtils.shiftArrayCyclic(array, move, direction);
		System.out.println("Cyclic Shifted Array  with " + move + " steps, to "+ direction +" direction");
		System.out.println(Arrays.toString(shiftedArray));

	}
	
	
// --- Matrix Utils ---	
	public static void testTransMatrix(int N, int M) {
		printMatrix(makeMatrix(N, M));
		System.out.println();
		int[][] transMat = ArrayUtils.transposeMatrix(makeMatrix(N,M));
		printMatrix(transMat);
		System.out.println();

		
	}
	
	public static int[][] makeMatrix(int N, int M){
		int[][] mat = new int[N][M];
		int num = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				mat[i][j] = num++;
			}
		}
		return mat;
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(matrix[i])); 
		}
	}
// ---    --- 
}
