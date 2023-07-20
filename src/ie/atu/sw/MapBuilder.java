package ie.atu.sw;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Map Builder - Creates a mapping of filtered words to definitions and lists of
 * pages.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class MapBuilder {
	private HashMap<String, IndexEntry> finalMap = new HashMap<String, IndexEntry>();

	/**
	 * Gets a mapping of words to dictionary definitions and lists of pages.
	 * 
	 * @return HashMap of words to dictionary definitions and lists of pages.
	 */
	public HashMap<String, IndexEntry> getFinalMap() {
		// O(1) return map from memory.
		return finalMap;
	}

	/**
	 * Sets a mapping of words to dictionary definitions and lists of pages. Not
	 * used in normal operation, but available for use if required for extended
	 * functionality. (eg. bypass earlier steps for testing.)
	 * 
	 * @param finalMap HashMap of words to dictionary definitions and lists of
	 *                 pages.
	 */
	public void setFinalMap(HashMap<String, IndexEntry> finalMap) {
		// O(n) - Add to HashMap is O(1) n times, assuming map is being created.
		this.finalMap = finalMap;
	}

	/**
	 * Builds mapping of filtered user words to dictionary definitions and lists of
	 * pages.
	 * 
	 * See DictionaryParser, UserInputParser.
	 * 
	 * @param filteredSet     Set of user words filtered by common words and
	 *                        dictionary words.
	 * @param pages           Mapping of words to lists of pages that word is found
	 *                        on.
	 * @param dictionaryWords Mapping of dictionary words to definitions.
	 */
	public void buildMap(Set<String> filteredSet, Map<String, TreeSet<Integer>> pages,
			Map<String, String> dictionaryWords) {
		// O(n²) - 1 loop, calls buildEntry (O(n)) n times.
		filteredSet.forEach(word -> buildEntry(word, pages, dictionaryWords, finalMap));
	}

	private void buildEntry(String word, Map<String, TreeSet<Integer>> map, Map<String, String> dictionaryWords,
			HashMap<String, IndexEntry> finalMap) {
		// O(n) - n number of O(1) operations on Hash based data structures.
		TreeSet<Integer> pageList = map.get(word);
		String definition = dictionaryWords.get(word);
		IndexEntry currentEntry = new IndexEntry(definition, pageList);
		finalMap.put(word, currentEntry);
	}

}
