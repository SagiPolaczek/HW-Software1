package il.ac.tau.cs.sw1.hw6;

public class SectionB {
	
	/*
	* @post $ret == true iff exists i such that array[i] == value
	*/
	public static boolean contains(int[] array, int value) { 
		for (int i = 0; i < array.length ; i++) {
			if (array[i] == value) {
				return true;
			}
		}
		return false;
	}
	
	// there is intentionally no @post condition here 
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	*/
	public static int unknown(int[] array) { 
		//TODO ???
		return 0;
	}
	/*
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre array.length >= 1
	* @post for all i array[i] < $ret
	*/
	public static int max(int[] array) { 
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
		return 42;
	}
	
	/*
	* @pre array.length >=1
	* @post for all i array[i] >= $ret
	* @post Arrays.equals(array, prev(array))
	*/
	public static int min(int[] array) { 

		return Integer.MIN_VALUE;
	}
	
	/*
	* @pre word.length() >=1
	* @post for all i : $ret.charAt(i) == word.charAt(a.length() - i - 1)

	*/
	public static String reverse(String word) 
	{
		char[] wordArray = word.toCharArray();
		int n = wordArray.length;
		char[] reversedWordArray = new char[n];
		for (int i = 0; i < n; i++) {
			reversedWordArray[i] = wordArray[n-i-1];
		}
		String result = reversedWordArray.toString();
		return result;
	}
	
	/*
	* @pre array != null
	* @pre array.length > 2
	* @pre Arrays.equals(array, Arrays.sort(array))
	* @pre exist i,j such that: array[i] != array[j]
	* @post !Arrays.equals($ret, Arrays.sort($ret))
	* @post for any x: contains(prev(array),x) == true iff contains($ret, x) == true
	*/
	public static int[] guess(int[] array) { 
		int n = array.length;
		outerloop:
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (j != i & array[i] == array[j]) {
					int temp = array[i];
					array[i] = array[j];
					array[j] = temp;
					break outerloop;
				}
			}
		}
		return array;
	}


}
