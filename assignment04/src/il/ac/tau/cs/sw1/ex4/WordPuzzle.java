package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] validWords = new String[MAX_VOCABULARY_SIZE];
		int i = 0;
		while (scanner.hasNext() && i < MAX_VOCABULARY_SIZE) {
			String currWord = scanner.next();
			if (isValidWord(currWord, validWords)) {
				validWords[i] = currWord.toLowerCase();
				i++;
			}
		}
		if (i == 0) {
			return new String[0];
		}
		String[] res = Arrays.copyOfRange(validWords, 0, i);
		Arrays.sort(res);
		return res;
	}
	
	public static boolean isValidWord(String currWord, String[] validWords) { // Check if word is valid for game
		if (currWord.length() < 2 || !isAlphabet(currWord)) {
			return false;
		}
		
		for (String word : validWords) {
			if (word != null) {
				if (word.equals(currWord.toLowerCase())) {
					return false;
				}
			}
		}

		return true;
	}
	
	public static boolean isAlphabet(String word) { // Check if a word is Alphabet
		
		char[] chars = word.toCharArray();
		for (char ch : chars) {
			if (!Character.isLetter(ch)) {
				return false;
			}
		}
		
		return true;
	}

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
		int hiddenCounter = 0;
		for (char ch : puzzle) {
			if (ch == HIDDEN_CHAR) {
				hiddenCounter++;
			}
		}
		return hiddenCounter;
	}

	public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
		int n = vocabulary.length;
		int randomInt = generator.nextInt(n);
		String randWord = vocabulary[randomInt];
		return randWord;
	}

	public static boolean checkLegal(String word, char[] puzzle) { // Q - 4
		int n = puzzle.length;
		if (n < 2) {
			return false;
		}
		int hiddenAmount = countHiddenInPuzzle(puzzle);
		if (hiddenAmount == 0 || hiddenAmount == n) {
			return false;
		}
		char[] wordArray = word.toCharArray();
		for (int i = 0; i < n-1; i++) {
			int pos = indexOf(Arrays.copyOfRange(wordArray,i+1, n), wordArray[i]);
			if (pos != -1) {
				if (puzzle[i] != puzzle[i + 1 +pos]) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	public static int indexOf(char[] arr, char target) { // return first index of char in array or -1 if not exist
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return i;
			}
		}
		return -1;
	}
	
	public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
		char[] currPuzzle = word.toCharArray();
		double currProb;
		for (int i = 0; i < currPuzzle.length; i++) {
			currProb = generator.nextDouble();
			if (currProb <= prob) {
				currPuzzle[i] = HIDDEN_CHAR;
			}
		}
		return currPuzzle;
	}

	public static char[] getRandomPuzzle(String word, double prob, Random generator) { // Q - 6
		int i = 0;
		char[] puzzleCandidate;
		while (i < 1000) {
			puzzleCandidate = getRandomPuzzleCandidate(word, prob, generator);
			if (checkLegal(word, puzzleCandidate)) {
				return puzzleCandidate;
			}
			i++;
		}
		throwPuzzleGenerationException();
		return null;
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		char[] solutionArray = solution.toCharArray();
		int n = solutionArray.length;
		int numOfChanges = 0;
		for (int i = 0; i < n; i++) {
			if (solutionArray[i] == guess && puzzle[i] == HIDDEN_CHAR) {
				puzzle[i] = guess;
				numOfChanges++;
			}
		}

		return numOfChanges;
	}

	public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
		for (int i = 0; i < puzzle.length; i++) {
			if (puzzle[i] == HIDDEN_CHAR) {
				applyGuess(solution.charAt(i), solution, puzzle);
				return puzzle;
			}
				
		}

		return puzzle;
	}

	public static void main(String[] args) throws Exception { // Q - 9
		int vocabularySize = -1; // Replace -1 with size of vocabulary.
		if (args.length == 0) {
			System.out.println("Missing Arguments");
			return;
		}
		String filePath = args[0];
		File f = new File(filePath);
		Scanner scanner = new Scanner(f);
		String[] vocabulary = scanVocabulary(scanner);
		vocabularySize = vocabulary.length;
		System.out.println("Read "+vocabularySize+" words from " +filePath);
		
		// Uncomment only one of the generators:
		// Random generator = new MyRandom(new int[]{0,1,2,3,4,5},new float[]{0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1.0f});
		Random generator = new MyRandom(getRrandomIntArr(vocabularySize), getRandomFloatArr());
		// Random generator = new Random ();
		
		System.out.println("--- Setting stage ---");
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter your hiding probability:");
		double userProb = userInput.nextDouble();
		
		boolean choosePuzzle = true;
		String word = new String();
		char[] puzzle = new char[0];
		while (choosePuzzle) {
			word = getRandomWord(vocabulary, generator);
			puzzle = getRandomPuzzle(word, userProb, generator);
			System.out.println(puzzle);
			
			boolean validAns = false;
			while (!validAns) {
				System.out.println("Replace puzzle?");
				String replaceAnswer = userInput.next();
				if (replaceAnswer.equals("no")) {
					choosePuzzle = false;
					validAns = true;
				} else if (replaceAnswer.equals("yes")) {
					validAns = true;
				}
			}
		}
		
		System.out.println("--- Game stage ---");
		int lives = countHiddenInPuzzle(puzzle) + 3;
		while (lives > 0) {
			System.out.println(puzzle);
			System.out.println("Enter your guess:");
			char guess = userInput.next().charAt(0);
			int guessResult = applyGuess(guess, word, puzzle);
			if (guess == 'H') {
				getHelp(word, puzzle);
				if (countHiddenInPuzzle(puzzle) == 0) {
					System.out.println("Congratulations! You solved the puzzle");
					break;
				} 
				lives--;
			}
			else if (guessResult > 0) {
				if (countHiddenInPuzzle(puzzle) == 0) {
					System.out.println("Congratulations! You solved the puzzle");
					break;
				} else {
					lives--;
					System.out.println("Correct Guess, " +lives+ " guesses left");
				}
			} else {
				lives--;
				System.out.println("Wrong Guess, " +lives+ " guesses left");
			}
		}
		if (lives == 0) {
			System.out.println("Game over!");
		}
		
		
	}
	
	
	
	
	

	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	private static float[] getRandomFloatArr() {
		Double[] doubleArr = new Double[] { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 };
		List<Double> doubleList = Arrays.asList(doubleArr);
		Collections.shuffle(doubleList);
		double[] unboxed = doubleList.stream().mapToDouble(Double::doubleValue).toArray();

		// cast double array to float array
		float[] floatArr = new float[unboxed.length];
		for (int i = 0; i < unboxed.length; i++) {
			floatArr[i] = (float) unboxed[i];
		}
		return floatArr;
	}

	private static int[] getRrandomIntArr(int vocabularySize) {
		
		if(vocabularySize<0) {
			throw new RuntimeException("Wrong use of getRandomIntArr(int vocabularySize)");
		}
		
		int i = 0;
		Integer[] intArr = new Integer[vocabularySize];
		while (i < vocabularySize) {
			intArr[i] = i;
			i++;
		}
		List<Integer> doubleList = Arrays.asList(intArr);
		Collections.shuffle(doubleList);
		int[] unboxed = doubleList.stream().mapToInt(Integer::intValue).toArray();
		return unboxed;
	}

	public static void throwPuzzleGenerationException() {
		throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
	}

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterHidingProbability() {
		System.out.println("Enter your hiding probability:");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printReplacePuzzleMessage() {
		System.out.println("Replace puzzle?");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}

}
