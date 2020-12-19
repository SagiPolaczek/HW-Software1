package il.ac.tau.cs.sw1.ex8.wordsRank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import il.ac.tau.cs.sw1.ex8.histogram.HashMapHistogram;
import il.ac.tau.cs.sw1.ex8.histogram.IHistogram;
import il.ac.tau.cs.sw1.ex8.wordsRank.RankedWord.rankType;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class FileIndex {
	
	public static final int UNRANKED_CONST = 30;
	
	// Dictionary:  fileName -> file's HashMapHistogram
	HashMap<String, HashMapHistogram<String>> filesMap = new HashMap<String, HashMapHistogram<String>>();
	
	// Dictionary:  fileName -> (word -> word's rank in file).
	HashMap<String, Map<String, Integer>> fileToWordRankMap = new HashMap<String, Map<String, Integer>>();

	// Dictionary:  word -> (file -> word's rank in file)
	HashMap<String, Map<String, Integer>> WordToFileRankMap = new HashMap<String, Map<String, Integer>>();
	
	List<String> files = new ArrayList<String>();;
	Set<String> words = new HashSet<String>();;
	
	// Average for the case when a word is not in words.
	int totalAvg;
	
	
	/*
	 * @pre: the directory is no empty, and contains only readable text files
	 */
  	public void indexDirectory(String folderPath) {
		//This code iterates over all the files in the folder. add your code wherever is needed
  		
  		
		File folder = new File(folderPath);
		File[] listFiles = folder.listFiles();
		for (File file : listFiles) {
			// for every file in the folder
			if (file.isFile()) {
				String fileName = file.getName();
				
				try {
					// Retrieving the clean tokens and associate each file to an HashMapHistogram.
					List<String> cleanTokens = FileUtils.readAllTokens(file);
					HashMapHistogram<String> fileMap = new HashMapHistogram<String>();
					fileMap.addAll(cleanTokens);
					filesMap.put(fileName, fileMap);
					
					Map<String, Integer> wordRankMap = new HashMap<String, Integer>();
					int i = 1;
					for (String word : fileMap) {
						wordRankMap.put(word, i);
						words.add(word);
						i++;
					}
					fileToWordRankMap.put(fileName, wordRankMap);
					
					files.add(fileName);
					
				} catch (IOException E) {}
			}
		}
		for (String word : words) {
			WordToFileRankMap.put(word, new HashMap<String, Integer>());
			for (String file : files) {
				// Word is in file, put (file, rank)
				if (filesMap.get(file).getCountForItem(word) > 0) {
					WordToFileRankMap.get(word).put(file, fileToWordRankMap.get(file).get(word));
				} 
				// Word is not in file, put (file, #(Different Words In File) + CONSTANT)
				else { 
					WordToFileRankMap.get(word).put(file, filesMap.get(file).getItemsSet().size() + UNRANKED_CONST);
				}
			}
		}
		int totalSum = 0;
		int totalCount = 0;
		for (String file : files) {
			totalSum += filesMap.get(file).getItemsSet().size() + UNRANKED_CONST;
			totalCount++;
		}
		totalAvg = (int)Math.round(((double)totalSum)/totalCount);
	}
	
  	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getCountInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		if (filesMap.containsKey(filename)) {
			return filesMap.get(filename).getCountForItem(word);
		} else {
			throw new FileIndexException("Oops! The file '" +filename+ "' is not exist!");
		}
	}
	
	/*
	 * @pre: the index is initialized
	 * @pre filename is a name of a valid file
	 * @pre word is not null
	 */
	public int getRankForWordInFile(String filename, String word) throws FileIndexException{
		word = word.toLowerCase();
		if (!filesMap.containsKey(filename)) {
			throw new FileIndexException("Oops! The file '" +filename+ "' is not exist!");
		}
		if (filesMap.get(filename).getCountForItem(word) == 0) {
			return filesMap.get(filename).getItemsSet().size() + UNRANKED_CONST;
		}
		return WordToFileRankMap.get(word).get(filename);

	}
	
	/*
	 * @pre: the index is initialized
	 * @pre word is not null
	 */
	public int getAverageRankForWord(String word){
		word = word.toLowerCase();
		if (!words.contains(word)) { // If word never seen
			return totalAvg;
		}
		RankedWord rankedWord = new RankedWord(word, WordToFileRankMap.get(word));
		return rankedWord.getAvg();
	}
	
	public int getMiximumRankForWord(String word) {
		word = word.toLowerCase();
		RankedWord rankedWord = new RankedWord(word, WordToFileRankMap.get(word));
		return rankedWord.getMax();
	}
	
	public int getMinimumRankForWord(String word) {
		word = word.toLowerCase();
		RankedWord rankedWord = new RankedWord(word, WordToFileRankMap.get(word));
		return rankedWord.getMin();
	}
	
	
	public List<String> getWordsWithAverageRankSmallerThanK(int k) {
		// Initialize a list to store RankedWord instances
		List<RankedWord> rankedWords = new ArrayList<RankedWord>();

		// Iterate over each word and add it to the list if it satisfies the cond.
		for (String word : words) {
			if (getAverageRankForWord(word) < k) {
				rankedWords.add(new RankedWord(word, WordToFileRankMap.get(word)));
			}
		}
		// Call a method which sort the list, and filter only to words values (String).
		return sortAndFilter(rankedWords);
	}

	public List<String> getWordsWithMinRankSmallerThanK(int k) {
		// Initialize a list to store RankedWord instances
		List<RankedWord> rankedWords = new ArrayList<RankedWord>();

		// Iterate over each word and add it to the list if it satisfies the cond.
		for (String word : words) {
			if (getMinimumRankForWord(word) < k) {
				rankedWords.add(new RankedWord(word, WordToFileRankMap.get(word)));
			}
		}
		// Call a method which sort the list, and filter only to words values (String).
		return sortAndFilter(rankedWords);
	}

	public List<String> getWordsWithMaxRankSmallerThanK(int k) {
		// Initialize a list to store RankedWord instances
		List<RankedWord> rankedWords = new ArrayList<RankedWord>();

		// Iterate over each word and add it to the list if it satisfies the cond.
		for (String word : words) {
			if (getMiximumRankForWord(word) < k) {
				rankedWords.add(new RankedWord(word, WordToFileRankMap.get(word)));
			}
		}
		// Call a method which sort the list, and filter only to words values (String).
		return sortAndFilter(rankedWords);
	}

	
	/*
	 *  Method which get a list of RankedWord's instances, Sorting it using the comparator,
	 *  and filter to a new list which contains only the words' values.
	 */
	public List<String> sortAndFilter(List<RankedWord> rankedWords) {
		// Sort
		rankedWords.sort(new RankedWordComparator(RankedWord.rankType.average));

		// Filter
		// Using the *sorted order* and adding it to the result.
		List<String> result = new ArrayList<String>();
		for (RankedWord rankedWord : rankedWords) {
			result.add(rankedWord.getWord());
		}
		return result;

	}

}
