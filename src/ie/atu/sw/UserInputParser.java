package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * User Input Parser - Parses user-specified file, extracts a mapping of words
 * found to pages they were found on.
 * 
 * See AbstractParser.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class UserInputParser extends AbstractParser {

	private Map<String, TreeSet<Integer>> index = new HashMap<>();
	private HashSet<String> userWordSet = new HashSet<String>();
	private int lineNumber = 0;
	private int pageCounter = 1;

	/**
	 * Gets a set of words found in user-specified input file, extracted from index
	 * map.
	 * 
	 * @return
	 */
	public HashSet<String> getUserWordSet() {
		// O(n) adds (O(1)) to userWordsSet n times.
		index.keySet().forEach(word -> userWordSet.add(word));
		return userWordSet;
	}

	/**
	 * Gets mapping of words in user-specified file to lists of pages each word
	 * occurs on.
	 * 
	 * @return mapping of words in user-specified file to lists of pages each word
	 *         occurs on.
	 */
	public Map<String, TreeSet<Integer>> getIndex() {
		// O(1) return map from memory.
		return index;
	}

	/**
	 * Parses user file for a mapping of words in user-specified file to lists of
	 * pages each word occurs on.
	 */
	@Override
	public void parse() {
		// O(n²) calls process() (O(n)) n times.
		String file = this.file;
		try {
			Files.lines(Path.of(file)).forEach(text -> process(text, ++lineNumber));
		} catch (IOException e) {
			System.out.println("[ERROR] IO Exception. Returning to Menu.");
			Menu.showMenu();
		}
	}

	private void process(String text, int line) {
		// O(n) calls updateIndex() n times.
		if (line % 40 == 0) {
			pageCounter++;
		}
		Arrays.stream(text.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+"))
				.forEach(word -> updateIndex(word, pageCounter));
	}

	private void updateIndex(String word, int pageNumber) {
		// O(1) add, get & put operations on HashSets.
		if (word == "" || word == null) {
			return;
		} else if (!index.containsKey(word)) {
			TreeSet<Integer> pageList = new TreeSet<Integer>();
			pageList.add(pageNumber);
			index.put(word, pageList);
		} else if (index.containsKey(word)) {
			TreeSet<Integer> pageList = index.get(word);
			pageList.add(pageNumber);
			index.put(word, pageList);
		}
	}
}
