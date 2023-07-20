package ie.atu.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu - Manages display of menus, handles user inputs for paths and extra
 * options.
 * 
 * @author Ben Murray
 * @version 1.0
 * @since JDK 19
 *
 */
public class Menu {
	private static Scanner userInput = new Scanner(System.in);
	private static Scanner outputOptions = new Scanner(System.in);
	private static String userFilePath = "";
	private static String userDictionaryPath = "";
	private static String userCommonWordsPath = "";
	private static String userOutputPath = "";
	private static List<String> paths = new ArrayList<>();
	private static boolean keepRunning = true;
	private static boolean printSortedWords = false;
	private static boolean printRevSortedWords = false;
	private static boolean printTotalUniqueWords = false;
	private static boolean printMostFrequentWords = false;
	private static int sortedNumber;
	private static int revSortedNumber;
	private static int mostFrequentWordsNumber;

	/**
	 * Gets user-specified number of most frequent words to print.
	 * 
	 * @return user-specified number of most frequent words to print.
	 */
	public static int getMostFrequentNumber() {
		// O(1) Return int from memory.
		return mostFrequentWordsNumber;
	}

	/**
	 * Checks if user has chosen to print most frequent words to console.
	 * 
	 * @return True if user chooses to print most frequent words to console.
	 */
	public static boolean isPrintMostFrequentWords() {
		// O(1) Return boolean from memory.
		return printMostFrequentWords;
	}

	/**
	 * Set user choice to print most frequent words to console.
	 * 
	 * @param printMostFrequentWords False by default, set to true if user chooses
	 *                               to print most frequent words.
	 */
	public static void setPrintMostFrequentWords(boolean printMostFrequentWords) {
		// O(1) Update boolean value.
		Menu.printMostFrequentWords = printMostFrequentWords;
	}

	/**
	 * Gets user-specified number of words per row for printing sorted words to
	 * console.
	 * 
	 * @return Number of words per row.
	 */
	public static int getSortedNumber() {
		// O(1) Return int from memory.
		return sortedNumber;
	}

	/**
	 * Gets user-specified number of words per row for printing reverse sorted words
	 * to console.
	 * 
	 * @return Number of words per row.
	 */
	public static int getRevSortedNumber() {
		// O(1) Return int from memory.
		return revSortedNumber;
	}

	/**
	 * Checks if user has chosen to print words in sorted order to console.
	 * 
	 * @return True if user has chosen to print sorted words to console.
	 */
	public static boolean isPrintSortedWords() {
		// O(1) Return boolean from memory.
		return printSortedWords;
	}

	/**
	 * Sets user choice to print words in sorted order to console.
	 * 
	 * @param printSortedWords False by default, true if user chooses to print
	 *                         sorted words.
	 */
	public static void setPrintSortedWords(boolean printSortedWords) {
		// O(1) Update boolean value.
		Menu.printSortedWords = printSortedWords;
	}

	/**
	 * Checks if user has chosen to print words in reverse sorted order to console.
	 * 
	 * @return True if user has chosen to print words in reverse sorted order to
	 *         console.
	 */
	public static boolean isPrintRevSortedWords() {
		// O(1) Return boolean from memory.
		return printRevSortedWords;
	}

	/**
	 * Sets user choice to print words in reverse sorted order to console.
	 * 
	 * @param printRevSortedWords False by default, true if user chooses to print
	 *                            reverse sorted words.
	 */
	public static void setPrintRevSortedWords(boolean printRevSortedWords) {
		// O(1) Update boolean value.
		Menu.printRevSortedWords = printRevSortedWords;
	}

	/**
	 * Checks if user has chosen to print total number of unique words to console.
	 * 
	 * @return True if user has chosen to print total number of unique words to
	 *         console.
	 */
	public static boolean isPrintTotalUniqueWords() {
		// O(1) Return boolean from memory.
		return printTotalUniqueWords;
	}

	/**
	 * Sets user choice to print total number of unique words to console.
	 * 
	 * @param printTotalUniqueWords False by default, true if user wants to print
	 *                              total unique words.
	 */
	public static void setPrintTotalUniqueWords(boolean printTotalUniqueWords) {
		// O(1) Update boolean value.
		Menu.printTotalUniqueWords = printTotalUniqueWords;
	}

	/**
	 * Gets user-defined paths for file to be indexed, dictionary file, common words
	 * file and output file.
	 * 
	 * @return List of user defined paths for file to be indexed, dictionary file,
	 *         common words file and output file.
	 */
	public static List<String> getPaths() {
		// O(1) Return List from memory.
		return paths;
	}

	/**
	 * Sets list of user-defined paths for file to be indexed, dictionary file,
	 * common words file and output file. Not used in normal operation, but
	 * available for use if required for extended functionality.
	 * 
	 * @param paths List of user-defined paths for file to be indexed, dictionary
	 *              file, common words file and output file.
	 */
	public static void setPaths(List<String> paths) {
		// O(n) - if whole ArrayList needs to be created and populated.
		Menu.paths = paths;
	}

	/**
	 * Displays menu and prompts user to input paths for file to be indexed,
	 * dictionary file, common words file and output file.
	 */
	public static void showMenu() {
		// O(n) to print text, where n = number of characters to print.
		System.out.println();
		System.out.println("(1) Specify Text File");
		System.out.println("(2) Configure Dictionary");
		System.out.println("(3) Configure Common Words");
		System.out.println("(4) Specify Output File");
		System.out.println("(5) Execute");
		System.out.println("(6) Quit");

		System.out.println();
		System.out.print("Select Option [1-6]>");
		System.out.println();

		int menuOption = userInput.nextInt();

		if (menuOption < 1 || menuOption > 6) {
			System.out.println("[ERROR] Invalid Input. Please choose an option 1 - 5 ");
			showMenu();
		}

		while (keepRunning) {
			switch (menuOption) {
			case 1:
				System.out.println("Please input the location of the file to be indexed.");
				userFilePath = userInput.next();
				showMenu();
				break;
			case 2:
				System.out.println("Please input the location of your dictionary file.");
				userDictionaryPath = userInput.next();
				showMenu();
				break;
			case 3:
				System.out.println("Please input the location of your Common Words file");
				userCommonWordsPath = userInput.next();
				showMenu();
				break;
			case 4:
				System.out.println("Please specify a location for your output file.");
				userOutputPath = userInput.next();
				showMenu();
				break;
			case 5:
				System.out.println("Processing, please wait...");
				System.out.println();
				populateList();
				break;
			case 6:
				System.out.println("Exiting...");
				keepRunning = false;
				break;

			}
			return;
		}
		userInput.close();
	}

	/**
	 * Displays output menu and prompts user to select options for extra outputs.
	 */
	public static void showOutputMenu() {
		// O(n) to print text, where n = number of characters to print.
		System.out.println("Please select any additional options :");
		System.out.println("(1) Print words in sorted order to console.");
		System.out.println("(2) Print words in reverse sorted order to console.");
		System.out.println("(3) Print total number of unique words to console.");
		System.out.println("(4) Print n most frequent words to console.");
		System.out.println("(5) Continue");
		System.out.println("(6) Quit");

		int menuOption = outputOptions.nextInt();

		if (menuOption < 1 || menuOption > 6) {
			System.out.println("ERROR: Invalid Input. Please choose an option 1 - 6 ");
			showOutputMenu();
		}

		while (keepRunning) {
			switch (menuOption) {
			case 1:
				setPrintSortedWords(true);
				System.out.println("[SELECTED] Print Sorted Words. Please specify number per row: ");
				sortedNumber = outputOptions.nextInt();
				System.out.println();
				showOutputMenu();
				break;
			case 2:
				setPrintRevSortedWords(true);
				System.out.println("[SELECTED] Print Reverse Sorted Words. Please specify number per row:");
				revSortedNumber = outputOptions.nextInt();
				System.out.println();
				showOutputMenu();
				break;
			case 3:
				setPrintTotalUniqueWords(true);
				System.out.println("[SELECTED] Print Total Number of Unique Words.");
				System.out.println();
				showOutputMenu();
				break;
			case 4:
				setPrintMostFrequentWords(true);
				System.out.println("[SELECTED] Print n Most Frequent Words. Please specify a value for n: ");
				mostFrequentWordsNumber = outputOptions.nextInt();
				System.out.println(mostFrequentWordsNumber);
				System.out.println();
				showOutputMenu();
				break;
			case 5:
				System.out.println("Processing, please standby...");
				System.out.println();
				break;
			case 6:
				System.out.println("Exiting...");
				keepRunning = false;
				break;
			}
			return;
		}
		outputOptions.close();
	}

	/**
	 * Prints program header to console.
	 */
	public static void showHeader() {
		// O(n) where n = number of characters to be printed.
		System.out.println("************************************************************");
		System.out.println("*       ATU - Dept. Computer Science & Applied Physics     *");
		System.out.println("*                                                          *");
		System.out.println("*              Virtual Threaded Text Indexer               *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
	}

	private static void populateList() {
		// O(n) where n is number of paths to be added. Can expand if program becomes
		// more complex.
		paths.add(userDictionaryPath);
		paths.add(userCommonWordsPath);
		paths.add(userFilePath);
		paths.add(userOutputPath);
	}

}
