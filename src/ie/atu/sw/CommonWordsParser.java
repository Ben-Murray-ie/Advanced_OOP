package ie.atu.sw;

import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Common Words Parser - Leverages virtual threads to efficiently extract a set
 * of commonly used words from a file.
 * 
 * See AbstractThreadedParser.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class CommonWordsParser extends AbstractThreadedParser {

	/**
	 * Sets generic collection words to ConcurrentSkipListSet, for use with virtual
	 * threads.
	 */
	@Override
	public void setWords(Collection<String> words) {
		// O(1) declare a new ConcurrentSkipListSet with no elements.
		this.words = new ConcurrentSkipListSet<String>();
	}

	/**
	 * Adds extracted common words to HashSet for use in Filter class.
	 * 
	 * @return HashSet of common words.
	 */
	public HashSet<String> getCommonWordSet() {
		// O(n) adds (O(1)) to commonWordSet n times.
		HashSet<String> commonWordSet = new HashSet<String>();
		words.forEach(word -> commonWordSet.add(word));
		return commonWordSet;
	}
}
