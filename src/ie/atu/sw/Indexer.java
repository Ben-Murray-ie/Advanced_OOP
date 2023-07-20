package ie.atu.sw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Indexer - Encapsulates index generating process, delegates
 * responsibilities to relevant classes.
 * 
 * See DictionaryParser, CommonWordParser, UserInputParser, MapBuilder,
 * Outputter.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class Indexer {

	private DictionaryParser dp = new DictionaryParser();
	private CommonWordsParser cwp = new CommonWordsParser();
	private UserInputParser uip = new UserInputParser();
	private MapBuilder mb = new MapBuilder();
	private Outputter o = new Outputter();
	private Map<String, IndexEntry> Index;
	private long time;

	/**
	 * Gets index for first time.
	 * 
	 * @return Index of user words, definitions and lists of pages where words
	 *         occur.
	 */
	public Map<String, IndexEntry> getNewIndex() {
		// O(n²) - calls generateIndex() which has O(n²) time complexity.
		generateIndex();
		return Index;
	}

	/**
	 * Gets index for subsequent uses elsewhere in program. Will not work if
	 * getNewIndex has not already been called.
	 * 
	 * @return Index of user words, definitions and lists of pages where words
	 *         occur.
	 */
	public Map<String, IndexEntry> getCurrentIndex() {
		// O(1) return map from memory.
		return Index;
	}

	/**
	 * Gets total time in milliseconds to perform full indexing operation.
	 * 
	 * @return Total time in milliseconds to perform full indexing operation.
	 */
	public long getTime() {
		// O(1) return long from memory.
		return time;
	}

	/**
	 * Sets total time in milliseconds to perform full indexing operation.
	 * 
	 * @param time Total time in milliseconds to perform full indexing operation.
	 */
	public void setTime(long time) {
		// O(1) set value of time variable.
		this.time = time;
	}

	private void generateIndex() {

		// O(n²) - includes buildMap() & getFilteredWords() functions, O(n²) dominates.
		List<String> menuPaths = new ArrayList<String>();

		menuPaths = Menu.getPaths();

		dp.setFile(menuPaths.get(0));
		cwp.setFile(menuPaths.get(1));
		uip.setFile(menuPaths.get(2));
		o.setOutputPath(menuPaths.get(3));

		long start = System.currentTimeMillis();

		dp.parse();
		cwp.parse();
		uip.parse();

		Filter f = new Filter(uip.getUserWordSet(), dp.getDictionaryWordSet(), cwp.getCommonWordSet());
		HashSet<String> filteredSet = new HashSet<String>();
		filteredSet = f.getFilteredWords();

		mb.buildMap(filteredSet, uip.getIndex(), dp.getDictionaryWords());

		this.Index = mb.getFinalMap();

		o.saveToFile(Index, o.getOutputPath());

		long finish = System.currentTimeMillis();
		long total = finish - start;
		setTime(total);

		System.out.println("Index saved to file in " + time + " milliseconds.");

	}
}
