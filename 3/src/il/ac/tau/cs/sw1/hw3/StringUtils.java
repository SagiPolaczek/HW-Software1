package il.ac.tau.cs.sw1.hw3;
import java.util.Arrays;


public class StringUtils {

	public static String findSortedSequence(String str) {
		String[] arrStr = str.split(" ");
		if (arrStr.length == 0) {
			return "";
		}
		int startPos = 0, endPos = 0;
		int maxLen = 1;
		int currLen;
		String currWord, nextWord;
		
		for (int i = 0; i < arrStr.length; i++) {
			currLen = 0;
			for (int j = i-1; j < arrStr.length-1; j++) {
				currWord = arrStr[Math.max(j,i)];
				nextWord = arrStr[j+1];
				if (currWord.compareTo(nextWord) <= 0) {
					currLen++;
					if (currLen >= maxLen) {
						maxLen = currLen;
						startPos = i;
						endPos = j+1;
					}
				} else {
					break;
				}
			}
		}

		String[] resultArr = Arrays.copyOfRange(arrStr, startPos, endPos+1);
		return String.join(" ", resultArr);

	}

	public static boolean isAnagram(String a, String b) {
		a = a.toLowerCase().replaceAll(" ", "");
		b = b.toLowerCase().replaceAll(" ", "");
		char[] aArr = a.toCharArray();
		char[] bArr = b.toCharArray();
		Arrays.sort(aArr);
		Arrays.sort(bArr);
		boolean isAnagram = Arrays.equals(aArr, bArr);
		
		return isAnagram; 
	}
	
	
	public static boolean isEditDistanceOne(String a, String b){
		int aLength = a.length();
		int bLength = b.length();
		if (Math.abs(aLength - bLength) > 1) {
			return false;
		}
		if (aLength == bLength + 1) {
			return isDeleteDistance(a, b);
		} else if (bLength == aLength + 1) {
			return isDeleteDistance(b, a);
		} else {
			return isSwapDistance(a, b);
		}
	}
	
	public static boolean isDeleteDistance(String a, String b) {
		if (a.length() == 0 || b.length() == 0) {
			return true;
		}
		
		int idCounter, adder;
		for (int i = 0; i < a.length(); i++) {
			idCounter = 0;
			adder = 0;
			for (int j = 0; j < b.length(); j++) {
				if (i == j) {
					adder = 1;
				} 
				if (a.charAt(j + adder) == b.charAt(j)) {
					idCounter++;
					if (idCounter == b.length()) {
						return true;
					}
				} else {
					break;
				}
			}
		}
		return false;
	}
	
	public static boolean isSwapDistance(String a, String b) {
		if (a.compareTo(b) == 0 || a.length() == 1) {
			return true;
		}
		
		int idCounter;
		for (int i = 0; i < a.length(); i++) {
			idCounter = 1;
			for (int j = 0; j < b.length(); j++) {
				if (i == j) {
					continue;
				} 
				if (a.charAt(j) == b.charAt(j)) {
					idCounter++;
					if (idCounter == b.length()) {
						return true;
					}
				} else {
					break;
				}
			}
		}
		return false;
	}
	
}
