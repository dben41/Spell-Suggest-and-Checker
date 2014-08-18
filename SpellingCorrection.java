package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is our main. From this class we will define our command line
 * prompts. We will create our dictionary Hash Map from an input file. It will
 * call the GenerateAlternativeSpellings to generate alternate spellings and
 * create the .txt file on command of the user.
 * 
 * @author Daryl Bennett & Leland Stenquist
 */
public class SpellingCorrection
	{
		// declare variables
		static HashMap<String, Integer> dictionaryStats;
		public static boolean printCommand;

		public static void main(String[] args) {

			// assume user doesn't want to print unless otherwise told
			printCommand = false;

			// create File
			File dictionary = null;

			// there must only be one or two arguments passed
			if (args.length < 1 || args.length > 2) {
				System.out.println("\nIncorrect number of arguments!");
				return;
			}

			// instantiate the dictionary File using args[0]
			// args[0] is the first argument of the user or
			// the input file name of the user
			dictionary = new File(args[0]);

			// create a hash to store the dictionary in
			dictionaryStats = new HashMap<String, Integer>();

			// make sure the dictionary is a normal File
			if (!dictionary.isFile()) {
				System.out.println("Invalid word statistics file argument!");
				return;
			}

			// instantiate option
			if (args.length == 2)
				if (args[1].equalsIgnoreCase("-fr"))
					// if there is a seccond argument and it is "-fr"
					// then we tell the program it will be creating
					// a file
					printCommand = true;
				else {
					// if the second argument is invalid tell the user
					// and terminate the program
					System.out
							.println("invalid printing or filing option argument!");
					return;
				}

			// populate our dictionary
			populate(dictionary);

			// let the user know to input a word
			System.out
					.println("Spelling correction program is active... please enter a word.");

			// create a scanner to get the word entered by the user
			Scanner scanner = new Scanner(new InputStreamReader(System.in));

			// infinite loop
			while (true) {

				// input is to be the word entered by the user
				String input = scanner.nextLine().toLowerCase();

				// ends program
				if (input.equals("exit")) {
					System.out
							.println("Spelling correction program is exiting... have a nice day.");
					return;
				}

				// process the input word
				process(input);
			}
		}

		// run your spell checker

		/**
		 * This method checks to see if the input word is in our dictionary. If
		 * is is then we print it to the console and return so the user can
		 * enter another word. If it is not then we perform the methods from our
		 * GenerateAlternateSpellings class to check for alternate spellings and
		 * return the most frequent valid word. We then return so the user can
		 * enter another word
		 */
		public static void process(String word) {

			// if it contains word, no need to generate anything
			if (dictionaryStats.containsKey(word)) {
				System.out.println(word + " is a known term.");
				return;
			} else {
				// call the methods to check for alternate spellings
				GenerateAlternativeSpellings gas = new GenerateAlternativeSpellings(
						word, printCommand);
				gas.deletion();
				gas.transposition();
				gas.substitution();
				gas.insertion();
				// check the valid alternate spellings if any and return the
				// most frequent
				GenerateAlternativeSpellings
						.alternateWordWithHighestFrequency();
			}
			return;
		}

		/**
		 * this method takes in a file which contains what the user has decided
		 * are valid words it scans through these words and their frequencies
		 * and puts them into a hash map to be checked against our user's
		 * entered word and any alternate spellings if the word is not in the
		 * dictionary
		 * 
		 * @param dictionary
		 *            - the file that contains our valid words
		 */
		public static void populate(File dictionary) {
			{
				try {
					// put the file in the scanner
					Scanner fileInput = new Scanner(dictionary);

					// scan through the file and while it still has words and
					// their frequencies it will input the word as a string
					// and frequency as an int into the hash map
					while (fileInput.hasNext()) {
						String s = fileInput.next().toLowerCase();
						int i = fileInput.nextInt();
						dictionaryStats.put(s, i);
					}
					// make sure that you can find the file
				} catch (FileNotFoundException e) {
					System.err.println("File " + dictionary
							+ " cannot be found.");
					return;
				}
			}

		}
	}
