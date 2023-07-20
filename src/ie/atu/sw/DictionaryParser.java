package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

/**
 * Dictionary Parser - Leverages virtual threads to efficiently create a mapping
 * of words in a dictionary file to their definitions.
 * 
 * See AbstractThreadedParser.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class DictionaryParser extends AbstractThreadedParser {

	private AbstractMap<String, String> dictionaryWords = new ConcurrentHashMap<String, String>();
	private HashSet<String> dictionaryWordSet = new HashSet<String>();

	/**
	 * Gets a mapping of words in a dictionary file to their definitions.
	 * 
	 * @return
	 */
	public AbstractMap<String, String> getDictionaryWords() {
		// O(1) return map from memory.
		return dictionaryWords;
	}

	/**
	 * Gets a set of words found in a dictionary file.
	 * 
	 * @return
	 */
	public HashSet<String> getDictionaryWordSet() {
		// O(n) adds (O(1)) to dictionaryWordSet n times.
		dictionaryWords.keySet().forEach(key -> dictionaryWordSet.add(key));
		return dictionaryWordSet;
	}

	/**
	 * Parses a dictionary file to create a mapping of words to their definitions.
	 */
	@Override
	public void parse() {
		// O(n²) calls processDictionary() (O(n)) n times. Mitigated by use of Virtual
		// Threads, implementation adapted from VirtualThreadFileParser.java.
		String file = this.file;
		try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
			Files.lines(Paths.get(file)).forEach(text -> pool.execute(() -> processDictionary(text)));
		} catch (IOException e) {
			System.out.println("[ERROR] IO Exception. Returning to Menu.");
			Menu.showMenu();
		}
	}

	private void processDictionary(String text) {
		// O(n) where n = the number of number of characters in string before ','.
		// Rationale informed by:
		// https://softwareengineering.stackexchange.com/questions/331909/whats-the-complexity-of-javas-string-split-function
		String[] splitLine = text.split(",");
		dictionaryWords.put(splitLine[0].toLowerCase(), splitLine[1]);
	}
}
