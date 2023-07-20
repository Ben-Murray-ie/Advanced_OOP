package ie.atu.sw;

import java.util.TreeSet;

/**
 * IndexEntry - Contains details related to parsed words (dictionary definition
 * and pages on which each word appeared).
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class IndexEntry implements Comparable<IndexEntry> {
	private String definition;
	private TreeSet<Integer> pages = new TreeSet<Integer>();
	private int totalPages = this.pages.size();;

	/**
	 * Gets dictionary definition associated with a word. Is mapped to word by
	 * MapBuilder.
	 * 
	 * @return String containing dictionary definition for a word.
	 */
	public String getDefinition() {
		// O(1) return string from memory.
		return definition;
	}

	/**
	 * Sets dictionary definition associated with a word. Not used in normal
	 * operation, but available for use if required for extended functionality.
	 * 
	 * @param definition String containing dictionary definition for a word.
	 */
	public void setDefinition(String definition) {
		// O(1) save string to memory.
		this.definition = definition;
	}

	/**
	 * Gets list of pages on which a given word is found. Is mapped to word by
	 * MapBuilder.
	 * 
	 * @return TreeSet of page numbers. TreeSet is used to guarantee sorted output
	 *         when printed to file.
	 */
	public TreeSet<Integer> getPages() {
		// O(1) return set from memory.
		return pages;
	}

	/**
	 * Sets list of pages on which a given word is found.
	 * 
	 * @param pages TreeSet of page numbers.
	 */
	public void setPages(TreeSet<Integer> pages) {
		// O(n) Add to HashSet is O(1) n times, assuming set is being created.
		this.pages = pages;
	}

	/**
	 * Constructor for IndexEntry object, sets values for definition and list of
	 * pages.
	 * 
	 * @param definition String containing dictionary definition for a word.
	 * @param pageList   TreeSet of page numbers.
	 */
	public IndexEntry(String definition, TreeSet<Integer> pageList) {
		// O(n) if pages is created & populated.
		this.definition = definition;
		this.pages = pageList;
	}

	/**
	 * Comparable implementation for use in sorting.
	 */
	public int compareTo(IndexEntry next) {
		// O(n) assuming more than 2 instances are being compared.
		if (this.totalPages < next.totalPages) {
			return 1;
		} else if (this.totalPages > next.totalPages) {
			return -1;
		} else {
			return 0;
		}
	}

}
