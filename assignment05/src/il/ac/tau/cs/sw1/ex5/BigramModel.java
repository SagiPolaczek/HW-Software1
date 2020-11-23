package il.ac.tau.cs.sw1.ex5;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		String[] vocabulary = new String[MAX_VOCABULARY_SIZE];
		File sourceFile = new File(fileName);
		FileReader sourceReader = new FileReader(sourceFile);
		BufferedReader sourceBufferedReader = new BufferedReader(sourceReader);
		String currLine;
		int i = 0;
		boolean numberInVocabulary = false;
		while (((currLine = sourceBufferedReader.readLine())) != null && (i < MAX_VOCABULARY_SIZE)) {
			String[] currLineArray = currLine.split(" ");
			for (String word : currLineArray) {
				if (isLegalWord(word)) {
					if (!isSeen(word.toLowerCase(), vocabulary)) {
						vocabulary[i] = word.toLowerCase();
						i++;
					}
				}
				if (!numberInVocabulary) {
					if (isLegalNumber(word)) {
						vocabulary[i] = SOME_NUM;
						numberInVocabulary = true;
					}
				}
			}
		}
		return Arrays.copyOfRange(vocabulary, 0, i);
	}
	
	public boolean isLegalWord(String word) {
		word = word.toLowerCase();
		for (char ch : word.toCharArray()) {
			if (Character.isLetter(ch)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isSeen(String word, String[] vocabulary) {
		word = word.toLowerCase();
		for (String wordInVocabulary : vocabulary) {
			if (word.equals(wordInVocabulary)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isLegalNumber(String number) {
		for (char digit : number.toCharArray())
			if (!Character.isDigit(digit)) {
				return false;
			}
		return true;
	}
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int n = vocabulary.length;
		int[][] bigramCounts = new int[n][n];
		
		File sourceFile = new File(fileName);
		FileReader sourceReader = new FileReader(sourceFile);
		BufferedReader sourceBufferedReader = new BufferedReader(sourceReader);
		String currLine;
	
		while ((currLine = sourceBufferedReader.readLine()) != null) {
			String[] currLineArray = currLine.split(" ");
			for (int i = 0; i + 1 < currLineArray.length; i++) {
				String currWord = currLineArray[i].toLowerCase();
				String nextWord = currLineArray[i + 1].toLowerCase();
				if ((isLegalWord(currWord) || isLegalNumber(currWord))
										&& (isLegalWord(nextWord) || isLegalNumber(nextWord))) {
					
					int currWordIndex = isSeenIndex(currWord, vocabulary);
					int nextWordIndex = isSeenIndex(nextWord, vocabulary);
					if ((currWordIndex != -1) && (nextWordIndex != -1)) {
						bigramCounts[currWordIndex][nextWordIndex] += 1;
					}
				}
			}
		}
		
		return bigramCounts;

	}
	
	public int isSeenIndex(String word, String[] vocabulary) {
		int i = 0;
		for (String wordInVocabulary : vocabulary) {
			if (word.equals(wordInVocabulary)) {
				return i;
			} else {
				i++;
			}
		}
		return -1;

	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		
		String vocFileName = fileName + VOC_FILE_SUFFIX;
		String countsFileName = fileName + COUNTS_FILE_SUFFIX;

		File toVocFile = new File(vocFileName);
		File toCountsFile = new File(countsFileName);
		
		BufferedWriter bufferedWriterVoc = new BufferedWriter(new FileWriter(toVocFile));
		BufferedWriter bufferedWriterCounts = new BufferedWriter(new FileWriter(toCountsFile));
		
		int vocabularySize = mVocabulary.length;
		bufferedWriterVoc.write(vocabularySize + " " + "words\n");
		for (int i = 0 ; i < vocabularySize; i++) {
			String line = i + "," + mVocabulary[i] + "\n";
			bufferedWriterVoc.write(line);			
		}
		bufferedWriterVoc.close();
		
		for (int i = 0; i < vocabularySize ; i++) {
			for (int j = 0; j < vocabularySize; j++) {
				if (mBigramCounts[i][j] > 0) {
					String line = i + "," + j + ":" + mBigramCounts[i][j] + "\n";
					bufferedWriterCounts.write(line);
				}
			}
		}
		bufferedWriterCounts.close();
		
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		String vocFileName = fileName + VOC_FILE_SUFFIX;
		String countsFileName = fileName + COUNTS_FILE_SUFFIX;
		
		File sourceVocFile = new File(vocFileName);
		FileReader sourceVocReader = new FileReader(sourceVocFile);
		BufferedReader sourceVocBuffer = new BufferedReader(sourceVocReader);
		String currLine = sourceVocBuffer.readLine();
		int vocSize = Integer.parseInt(currLine.split(" ")[0]);
		String[] loadedVocabulary = new String[vocSize];
		for (int i = 0; i < vocSize; i++) {
			currLine = sourceVocBuffer.readLine();
			String currWord = currLine.split(",")[1];
			loadedVocabulary[i] = currWord;
		}
		
		File sourceCountsFile = new File(countsFileName);
		FileReader sourceCountsReader = new FileReader(sourceCountsFile);
		BufferedReader sourceCountsBuffer = new BufferedReader(sourceCountsReader);
		
		int[][] bigramCounts = new int[vocSize][vocSize];
		while ((currLine = sourceCountsBuffer.readLine()) != null) {
			String[] currLineIndexes = currLine.split(":")[0].split(",");
			int currLineVal = Integer.parseInt(currLine.split(":")[1]);
			int row = Integer.parseInt(currLineIndexes[0]);
			int col = Integer.parseInt(currLineIndexes[1]);
			
			bigramCounts[row][col] = currLineVal;
		}
		
		mVocabulary = loadedVocabulary;
		mBigramCounts = bigramCounts;
		
		
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		int n = mVocabulary.length;
		for (int i = 0; i < n; i++) {
			if (mVocabulary[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		int word1Index = getWordIndex(word1);
		int word2Index = getWordIndex(word2);
		if (word1Index == -1 || word2Index == -1) {
			return 0;
		}
		return mBigramCounts[word1Index][word2Index];
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int wordIndex = getWordIndex(word);
		int maxFreq = 0;
		int n = mVocabulary.length;
		int maxValIndex = -1;
		for (int j = 0; j < n; j++) {
			if (mBigramCounts[wordIndex][j] > maxFreq) {
				maxFreq = mBigramCounts[wordIndex][j];
				maxValIndex = j;
			}
		}
		if (maxValIndex != -1) {
			return mVocabulary[maxValIndex];
		}
		return null;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		String[] sentenceArray = sentence.split(" ");
		int n = sentenceArray.length;
		for (int i = 0; i +1< n; i++) {
			int word1Index = getWordIndex(sentenceArray[i]);
			int word2Index = getWordIndex(sentenceArray[i+1]);
			
			if (word1Index == -1 || word2Index == -1) {
				return false;
			}
			if (mBigramCounts[word1Index][word2Index] == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2) { // Q - 9
		double firstSum = vectorsSum(arr1);
		double secondSum = vectorsSum(arr2);
		if (firstSum == 0 || secondSum == 0) {
			return -1;
		}
		double bothSum = vectorsSum(arr1, arr2);

		double res = bothSum / (Math.sqrt(firstSum) * Math.sqrt(secondSum));

		return res;
	}

	public static double vectorsSum(int[] v) {
		return vectorsSum(v, v);
	}

	public static double vectorsSum(int[] v, int[] u) {
		double sum = 0;
		int n = v.length;
		for (int i = 0; i < n; i++) {
			sum += (v[i] * u[i]);
		}
		return sum;
	}
	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word) { // Q - 10
		int wordIndex = getWordIndex(word);
		int[] wordVector = mBigramCounts[wordIndex];

		int[] u;
		double maxCosineSim = 0;
		int resIndex = 0;
		for (int i = 0; i < mVocabulary.length; i++) {
			if (i == wordIndex) {
				continue;
			}
			u = mBigramCounts[i];
			double currCosineSim = calcCosineSim(wordVector, u);
			if (currCosineSim > maxCosineSim) {
				maxCosineSim = currCosineSim;
				resIndex = i;
			}
		}
		return mVocabulary[resIndex];
	}

	/*
	 * @pre: word is a String
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the number of word's occurrences in the text.
	 */
	public int getWordCount(String word){ //  Q - 11
		int wordIndex = getWordIndex(word);
		if (wordIndex == -1) {
			return 0;
		}
		int sumFront = sumVector(mBigramCounts[wordIndex]);
		int sumPrev = sumPrev(wordIndex);
		
		return Math.max(sumFront, sumPrev);
	}
	
	public int sumVector(int[] arr) {
		int sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		return sum;
	}
	
	public int sumPrev(int wordIndex) {
		int sum = 0;
		for (int i = 0; i < mBigramCounts.length; i++) {
			sum += mBigramCounts[i][wordIndex];
		}
		return sum;
	}
}
