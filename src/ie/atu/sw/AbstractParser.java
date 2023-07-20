package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;

/**
 * Abstract Parser - Parses a text file and extracts data (sets of words,
 * mappings etc.).
 * 
 * See Parsator.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public abstract class AbstractParser implements Parsator {

	protected String file = "";
	protected Collection<String> words;

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
		// O(1) return string from memory.
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
	 * Parses a text file, extracts a collection of words.
	 */
	@Override
	public void parse() {
		// O(n²) calls process() (O(n)) n times.
		setWords(this.words);
		String file = this.file;
		try {
			Files.lines(Paths.get(file)).forEach(text -> process(text));
		} catch (IOException e) {
			System.out.println("[ERROR] IO Exception. Returning to Menu.");
			Menu.showMenu();
		}
	}

	private void process(String text) {
		// O(n) (depending on collection type) adds word to collection n times.
		Arrays.stream(text.toLowerCase().split("\\s+")).forEach(w -> words.add(w));
	}
}
