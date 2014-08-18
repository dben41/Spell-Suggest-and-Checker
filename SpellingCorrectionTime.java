package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class is our main. From this class we will define our command line
 * prompts. We will create our dictionary BST from an input file. It will call
 * the GenerateAlternativeSpellings to create the .txt file on command of the
 * user.
 * 
 * @author Daryl Bennett & Leland Stenquist
 */
public class SpellingCorrectionTime
	{
		// static File document = null;

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
			dictionary = new File(args[0]);

			dictionaryStats = new HashMap<String, Integer>();

			// make sure the dictionary is a normal File
			if (!dictionary.isFile()) {
				System.out.println("Invalid word statistics file argument!");
				return;
			}

			// instantiate option
			if (args.length == 2)
				if (args[1].equalsIgnoreCase("-fr"))
					printCommand = true;
				// option = args[1];
				else {
					System.out
							.println("invalid printing or filing option argument!");
					return;
				}

			// populate
			populate(dictionary);
			// let the user know to input a word
			System.out
					.println("Spelling correction program is active... please enter a word.");

			Scanner scanner = new Scanner(new InputStreamReader(System.in));
			// infinite loop
			while (true) {
				String input = scanner.nextLine().toLowerCase();
				if (dictionaryStats.containsValue(input)) {
					//
					System.out.println(input + " is a known term.");

					return;
				}
				// ends program
				if (input.equals("exit")) {
					System.out
							.println("Spelling correction program is exiting... have a nice day.");
					return;
				}
				process(input);
			}
		}

		// run your spell checker

		/**
		 * 
		 * @param dic
		 * @param doc
		 * @param option
		 */
		public static void process(String word) {

			// if it contains word, no need to generate anything
			if (dictionaryStats.containsKey(word)) {
				//
				// System.out.println(word + " is a valid word!");
				//
				return;
			}
			// } else {
			// GenerateAlternativeSpellings gas = new
			// GenerateAlternativeSpellings(
			// word, printCommand);
			// gas.deletion();
			// gas.transposition();
			// gas.substitution();
			// gas.insertion();
			// GenerateAlternativeSpellings
			// .alternateWordWithHighestFrequency();
			// }
			else {
				GenerateAlternateSpellingsForTiming gas = new GenerateAlternateSpellingsForTiming(
						word, printCommand);
				gas.deletion();
				gas.transposition();
				gas.substitution();
				gas.insertion();
				gas.alternateWordWithHighestFrequency();
			}
			return;
		}

		/**
		 * 
		 * @param dictionary
		 * @return
		 */
		public static void populate(File dictionary) {
			{
				dictionaryStats = new HashMap<String, Integer>();

				try {
					/*
					 * Java's Scanner class is a simple lexer for Strings and
					 * primitive types (see the Java API, if you are
					 * unfamiliar).
					 */
					Scanner fileInput = new Scanner(dictionary);

					/*
					 * The scanner can be directed how to delimit (or divide)
					 * the input. By default, it uses whitespace as the
					 * delimiter. The following statement specifies anything
					 * other than alphabetic characters as a delimiter (so that
					 * punctuation and such will be ignored). The string
					 * argument is a regular expression that specifies
					 * "anything but an alphabetic character". You need not
					 * understand any of this for the assignment.
					 */

					while (fileInput.hasNext()) {
						String s = fileInput.next().toLowerCase();
						int i = fileInput.nextInt();
						dictionaryStats.put(s, i);
						// System.out.println(dictionaryStats.entrySet());
					}

				} catch (FileNotFoundException e) {
					System.err.println("File " + dictionary
							+ " cannot be found.");
					return;
				}
			}

		}
	}
