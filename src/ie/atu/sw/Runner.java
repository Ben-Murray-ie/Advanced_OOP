package ie.atu.sw;

/**
 * Runner for Multithreaded Indexing API version 1.0. Contains main method.
 * 
 * See Indexer, Outputter.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 * 
 */
public class Runner {

	/**
	 * Main - Runs program and manages Menu calls.
	 */
	public static void main(String[] args) {
		// O(n²) method with greatest time complexity = O(n²)
		Indexer i = new Indexer();
		Outputter o = new Outputter();

		Menu.showHeader();
		Menu.showMenu();

		i.getNewIndex();

		Menu.showOutputMenu();

		if (Menu.isPrintSortedWords()) {
			o.printSortedWords(i.getCurrentIndex(), Menu.getSortedNumber());
		}
		if (Menu.isPrintRevSortedWords()) {
			o.printReverseSortedWords(i.getCurrentIndex(), Menu.getRevSortedNumber());
		}
		if (Menu.isPrintTotalUniqueWords()) {
			o.printUniqueWords(i.getCurrentIndex());
		}
		if (Menu.isPrintMostFrequentWords()) {
			o.printMostFrequentWords(i.getCurrentIndex(), Menu.getMostFrequentNumber());
		}

	}
}