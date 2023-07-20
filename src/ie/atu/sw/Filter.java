package ie.atu.sw;

import java.util.HashSet;

/**
 * Filter - Intakes keySets from dictionary, filters sets by removing common
 * words from words found in users file, then retains only the remaining words
 * that are also found in the dictionary.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class Filter {

	private HashSet<String> filteredWords;
	private HashSet<String> userSet;
	private HashSet<String> dictionarySet;
	private HashSet<String> commonSet;

	/**
	 * Constructor for Filter class, takes in keySets from UserInputParser and
	 * DictionaryParser, and set extracted from CommonWordsParser.
	 * 
	 * @param userSet       keySet() from UserInputParser.
	 * @param dictionarySet keySet() from DictionaryParser.
	 * @param commonSet     Set extracted from CommonWordsParser.
	 */
	public Filter(HashSet<String> userSet, HashSet<String> dictionarySet, HashSet<String> commonSet) {
		this.userSet = userSet;
		this.dictionarySet = dictionarySet;
		this.commonSet = commonSet;
	}

	/**
	 * Gets set of filtered words.
	 * 
	 * @return HashSet of filtered words.
	 */
	public HashSet<String> getFilteredWords() {
		// O(n) - calls filter(), which has O(n) time complexity.
		filter();
		return filteredWords;
	}

	/**
	 * Updates contents of filtered words set. Not used in normal operation, but
	 * available for use if required for extended functionality.
	 * 
	 * @param filteredWords Set of words to be added.
	 */
	public void setFilteredWords(HashSet<String> filteredWords) {
		// O(n) Add to HashSet is O(1) n times, assuming set is being created.
		this.filteredWords = filteredWords;
	}

	private void filter() {
		// O(n) - removeAll/retainAll + setFilteredWords (O(1) operations x n times)
		this.userSet.removeAll(this.commonSet);
		this.dictionarySet.retainAll(this.userSet);
		setFilteredWords(this.dictionarySet);
	}

}
