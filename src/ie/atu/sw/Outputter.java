package ie.atu.sw;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Outputter - Methods for outputting complete indexes to file or console. Extra
 * options for output presented upon completion.
 * 
 * See methods printSortedWords, printRevSortedWords, printUniqueWords,
 * printMostFrequentWords.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class Outputter {

	private String outputPath = "";
	private int counter = 0;
	private List<String> batch;

	/**
	 * Gets the user-specified path of file index will be written to.
	 * 
	 * @return Path of file index will be written to.
	 */
	public String getOutputPath() {
		// O(1) return String from memory.
		return outputPath;
	}

	/**
	 * Sets the user-specified path of file index will be written to.
	 * 
	 * @param outputPath Path of file index will be written to.
	 */
	public void setOutputPath(String outputPath) {
		// O(1) set value of String outputPath.
		this.outputPath = outputPath;
	}

	/**
	 * Save finished Index to file specified by user.
	 * 
	 * @param finalMap Finished index of words, their definitions and list of pages
	 *                 they are found on.
	 * @param file     Path to file index will be written to.
	 */
	public void saveToFile(Map<String, IndexEntry> finalMap, String file) {
		// O(n) where n is number of words in final output map.
		try (PrintWriter pw = new PrintWriter(file)) {
			finalMap.keySet().forEach(word -> {
				String wordTitleCase = word.substring(0, 1).toUpperCase() + word.substring(1);
				pw.println(wordTitleCase);
				pw.println();
				pw.println("Definition: ");
				pw.println(finalMap.get(word).getDefinition());
				pw.println("Pages : " + finalMap.get(word).getPages().toString());
				pw.println();
			});
		} catch (FileNotFoundException e) {
			System.out.println("[ERROR] File Not Found. Please input a valid output file path.");
			Menu.showMenu();
		}
		;
	}

	/**
	 * Print finished index to console. Not used in normal operation, but available
	 * for use if required for extended functionality.
	 * 
	 * @param finalMap Finished index of words, their definitions and list of pages
	 *                 they are found on.
	 */
	public void printToConsole(Map<String, IndexEntry> finalMap) {
		// O(n) where n is number of words in final output map.
		finalMap.keySet().forEach(word -> {
			String wordTitleCase = word.substring(0, 1).toUpperCase() + word.substring(1);
			System.out.println(wordTitleCase);
			System.out.println();
			System.out.println(finalMap.get(word).getDefinition());
			System.out.println();
			System.out.println(finalMap.get(word).getPages());
			System.out.println();
		});
	}

	/**
	 * Print words in finished index to console in sorted order. User specifies
	 * number of words per row.
	 * 
	 * @param finalMap  Finished index of words, their definitions and list of pages
	 *                  they are found on.
	 * @param batchSize User defined number of words per row.
	 */
	public void printSortedWords(Map<String, IndexEntry> finalMap, int batchSize) {
		// O(n) where n is number of words in final output map.
		System.out.println();
		this.batch = new ArrayList<String>();
		List<String> words = new ArrayList<String>(finalMap.keySet());
		words.sort(Comparator.naturalOrder());
		words.forEach(word -> {
			if (counter == batchSize) {
				System.out.println(batch);
				this.batch = new ArrayList<String>();
				counter = 0;
			} else if (word != null) {
				this.batch.add(word);
				counter++;
			} else {
				return;
			}
		});
		System.out.println(this.batch);
	}

	/**
	 * Print words in finished index to console in reverse sorted order. User
	 * specifies number of words per row.
	 * 
	 * @param finalMap  Finished index of words, their definitions and list of pages
	 *                  they are found on.
	 * @param batchSize User defined number of words per row.
	 */
	public void printReverseSortedWords(Map<String, IndexEntry> finalMap, int batchSize) {
		// O(n) where n is number of words in final output map.
		System.out.println();
		this.batch = new ArrayList<String>();
		List<String> words = new ArrayList<String>(finalMap.keySet());
		words.sort(Comparator.reverseOrder());
		words.forEach(word -> {
			if (counter == batchSize) {
				System.out.println(batch);
				this.batch = new ArrayList<String>();
				counter = 0;
			} else if (word != null) {
				this.batch.add(word);
				counter++;
			} else {
				return;
			}
		});
	}

	/**
	 * Print total number of unique words in finished index to console.
	 * 
	 * @param finalMap Finished index of words, their definitions and list of pages
	 *                 they are found on.
	 */
	public void printUniqueWords(Map<String, IndexEntry> finalMap) {
		// O(n) where n is number of words in final output map.
		System.out.println();
		List<String> UniqueWords = new ArrayList<String>(finalMap.keySet());
		System.out.println("Total number of unique words = " + UniqueWords.size());
	}

	/**
	 * Print most frequently occuring words in finished index to console. User
	 * specifies number of words to display.
	 * 
	 * @param finalMap Finished index of words, their definitions and list of pages
	 *                 they are found on.
	 * @param n        User defined number of words to display.
	 */
	public void printMostFrequentWords(Map<String, IndexEntry> finalMap, int n) {
		// O(n) where n = size of finalMap keySet().
		// Implementation adapted from:
		// https://stackoverflow.com/questions/5648336/how-select-first-n-items-in-java-treemap
		TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>();
		finalMap.keySet().forEach(entry -> {
			Integer pageTotal = finalMap.get(entry).getPages().size();
			sortedMap.put(pageTotal, entry);
		});
		ArrayList<String> mostFrequentList = new ArrayList<String>();
		Collection<String> mostFrequentCol = new ArrayList<String>();
		mostFrequentCol = sortedMap.tailMap(0).values();
		mostFrequentCol.forEach(word -> {
			mostFrequentList.add(word);
		});
		System.out.println("Most frequent words : ");
		int decrementer = 1;
		System.out.println();
		while (n > 0) {
			System.out.println(mostFrequentList.get(mostFrequentList.size() - decrementer));
			decrementer++;
			n--;
		}
	}
}
