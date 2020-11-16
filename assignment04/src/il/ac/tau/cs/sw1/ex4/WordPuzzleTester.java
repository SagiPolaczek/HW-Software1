package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class WordPuzzleTester {
	public static void main(String[] args){
		String vocabularyText = "I look at the floor and I see it needs sweeping while my guitar gently wheeps";
		Scanner vocabularyScanner = new Scanner(vocabularyText);
		String[] vocabulary = WordPuzzle.scanVocabulary(vocabularyScanner);

		Random generator = new MyRandom(new int[]{0,1,2,3,4,5},new float[]{0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1.0f});
		
		
		// puzzle1 = {_,_,_,e,_,_}
		char[] puzzle1 = {WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR, 'e', WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR};
		// puzzle2 = {_,_,e,e,_,_}
		char[] puzzle2 = {WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR, 'e', 'e', WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR};
		// puzzle3 = {w,_,i,_,e}
		char[] puzzle3 = {'w',WordPuzzle.HIDDEN_CHAR, 'i', WordPuzzle.HIDDEN_CHAR, 'e'};
		
		char[] puzzle2Hint = {'w', WordPuzzle.HIDDEN_CHAR, 'e', 'e', WordPuzzle.HIDDEN_CHAR, WordPuzzle.HIDDEN_CHAR};


		if (WordPuzzle.countHiddenInPuzzle(puzzle1) != 5){
			System.out.println("Error 2.1");
		}
		
		String word = WordPuzzle.getRandomWord(vocabulary,generator);
		if (word == null){
			System.out.println("Error 3.1");
		}
		
		if (WordPuzzle.checkLegal("wheeps",puzzle1)){
			System.out.println("Error 4.1");
		}
		if (!WordPuzzle.checkLegal("wheeps",puzzle2)){
			System.out.println("Error 4.2");
		}

		
		char[] puzzle4 = WordPuzzle.getRandomPuzzleCandidate(word, 0.99,generator);
		char[] puzzle5 = WordPuzzle.getRandomPuzzleCandidate(word, 0.01,generator);
		

		if (WordPuzzle.countHiddenInPuzzle(puzzle4) != 3){
			System.out.println(puzzle4);
			System.out.println(puzzle5);
			System.out.println(word);
			System.out.println("Error 5.1");
		}
			
		
		if (WordPuzzle.countHiddenInPuzzle(puzzle5) != 0){
			System.out.println("Error 5.2");
		}
			
		
		puzzle4 = WordPuzzle.getRandomPuzzle(word, 0.99,generator);
		puzzle5 = WordPuzzle.getRandomPuzzle(word, 0.01,generator);
		

		if (!WordPuzzle.checkLegal(word,puzzle4)){
			System.out.println("Error 6.1");
		}
		
		if (!WordPuzzle.checkLegal(word,puzzle5)){
			System.out.println("Error 6.2");
		}
		
		
		int numOfChangedLetters = WordPuzzle.applyGuess('h', "while", puzzle3);
		if (numOfChangedLetters != 1){
			System.out.println("Error 7.1");
		}
		numOfChangedLetters =  WordPuzzle.applyGuess('i', "while", puzzle3);
		if (numOfChangedLetters != 0){
			System.out.println("Error 7.2");
		}
		
		if (!Arrays.equals(puzzle2Hint, WordPuzzle.getHelp("wheeps", puzzle2))){
			System.out.println("Error 8.1");
		}
		
		
		System.out.println("done!");
	}
}
