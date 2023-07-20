package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executors;

/**
 * Abstract Threaded Parser - Leverages virtual threads to parse a text file and
 * extract data (sets of words, mappings etc.).
 * 
 * See Parsator.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public abstract class AbstractThreadedParser implements Parsator {

	protected Collection<String> words;
	protected String file;

	/**
	 * Gets a collection of words extracted from a text file.
	 * 
	 * @return A collection of words extracted from a text file.
	 */
	public Collection<String> getWords() {
		// O(1) return Collection<String> from memory.
		return words;
	}

	/**
	 * Sets a collection of words extracted from a text file.
	 * 
	 * @param words A collection of words extracted from a text file.
	 */
	public void setWords(Collection<String> words) {
		// O(n) (depending on collection type) adds collection of size n to words.
		this.words = words;
	}

	/**
	 * Gets path to file to be parsed.
	 * 
	 * @return Path to file to be parsed.
	 */
	public String getFile() {
		// O(1) return String from memory.
		return file;
	}

	/**
	 * Sets path to file to be parsed.
	 * 
	 * @param file Path to file to be parsed.
	 */
	public void setFile(String file) {
		// O(1) set value of String file.
		this.file = file;
	}

	/**
	 * Parses a file using virtual threads, extracts a collection of words.
	 */
	@Override
	public void parse() {
		/*
		 * O(n²) calls processDictionary() (O(n)) n times. Mitigated by use of Virtual
		 * Threads, implementation adapted from VirtualThreadFileParser.java.
		 */
		setWords(this.words);
		String file = this.file;
		try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
			Files.lines(Paths.get(file)).forEach(text -> pool.execute(() -> process(text)));
		} catch (IOException e) {
			System.out.println("[ERROR] IO Exception. Returning to Menu.");
			Menu.showMenu();
		}
	}

	// O(n) (depending on collection type) adds word to collection n times.
	private void process(String text) {
		Arrays.stream(text.toLowerCase().split("\\s+")).forEach(w -> words.add(w));
	}
}
