package assignment10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import java.util.Map;

public class GenerateAlternateSpellingsForTiming
	{
		/**
		 * Contains our 4 algorithms to find alternative spellings. Creates a
		 * .txt file when called from SpellignCorrection class. This class has
		 * the same code as GenerateAlternativeSpellings. We created it just to
		 * be careful so that we do not accidently manipulate our code in
		 * timing.
		 * 
		 * @author Daryl Bennett, Leland Stenquist
		 */

		// Insert field variables
		static HashMap<String, Integer> dictionary;
		static Map<String, Integer> validWords;
		// create a char array containing alphabet
		static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };
		// create a char array inputChar to contain inputWord
		static char[] inputChar;
		StringBuilder sb;

		// create inputString
		static String inputString;
		// To create the file
		static File outputFile;
		static FileWriter writer;
		static String s;
		// counters for the program
		private static int alternativeCount;
		public static int totalCount;
		static HashMap<String, Integer> dictionaryStats;
		// set to true
		private static boolean shouldIPrint;
		private static StringBuilder fileString;

		public <Type extends Comparable<? super Type>> GenerateAlternateSpellingsForTiming(
				String inputDictionaryItem, boolean shouldIPrintCommand) {

			alternativeCount = 0;
			totalCount = 0;
			// initialize field variables
			inputString = inputDictionaryItem;

			// initialization of dictionary
		    SpellingCorrectionTime sc = new SpellingCorrectionTime();
			dictionary = sc.dictionaryStats; //
			// if input is a valid word, then no need to continue
			//
			validWords = new HashMap<String, Integer>();
			// place inputString into inputChar
			inputChar = new char[inputString.length()];
			inputString.getChars(0, inputString.length(), inputChar, 0);
			// determines whether or not to print file
			shouldIPrint = shouldIPrintCommand;
			s = "";
			fileString = new StringBuilder(s); 

			// if -fr command was given, then initialize all the FileWriter
			// things, if not then don't bother
			if (shouldIPrint == true) {
				// for testing I only want to print one file
				outputFile = new File("1.txt");
				// creates the file
				try {
					outputFile.createNewFile();
				} catch (IOException e) {
					System.out
							.println("Unable to create a file for the output words!");
				}
			}

		}

		/**
		 * Performs the deletion algorithm. Returns n amount of alternative
		 * spellings
		 */
		public void deletion() {

			// create new array
			String temp = inputString;
			// beginning of text file
			createStringForFile("User string: " + inputString);
			// ours
			createStringForFile(new String(" "));

			for (int i = 0; i < inputString.length(); i++) {
				// create new String builder Object with inputString

				sb = new StringBuilder(temp);
				sb.deleteCharAt(i);
				String string = new String(sb);

				// Print to document
				createStringForFile("Deletion string: " + string);
				alternativeCount++;
				// System.out.println(string);
				// see if the alternatve word is in the dictionaryBST
				if (dictionary.containsKey(string)) {
					validWords.put(string, dictionary.get(string));
				}
			}
			createStringForFile("Created " + alternativeCount
					+ " deletion alternatives\n");
			createStringForFile(new String(" "));
			totalCount = totalCount + alternativeCount;
			alternativeCount = 0;

		}

		/**
		 * Performs the transposition algorithm. Returns n-1 amount of
		 * alternative spellings
		 */
		public void transposition() {
			char[] temp = inputString.toCharArray();
			// char charTemp = temp[0];
			for (int i = 0; i < temp.length - 1; i++) {
				// doesn't swap elements if they are the same
				// ---saves us a useless iteration
				if (temp[i] == temp[i + 1]) {
					// System.out.println("Double letter detected.");
					// compare
					// see if the alternatve word is in the dictionaryBST
					String ns = new String(temp);

					if (dictionary.containsKey(ns)) {
						validWords.put(ns, dictionary.get(ns));
					}
					// Print to document
					String grey = ns; 
					createStringForFile(new String("Transposition string: "
							+ grey));
					alternativeCount++;

					// System.out.println(new String(temp));
					continue;
				}
				// swap two elements in array
				char charTemp = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = charTemp;

				String ns = new String(temp); 
				// compare
				// see if the alternative word is in the dictionaryBST
				if (dictionary.containsKey(ns)) {
					validWords.put(ns,
							dictionary.get(ns));
				}
				// swap back; to restore original spelling
				//String grey = (new String(temp));
				createStringForFile(new String("Transposition string: " + ns));
				// System.out.println(new String(temp));

				alternativeCount++;

				charTemp = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = charTemp;
			}
			createStringForFile("Created " + alternativeCount
					+ " transposition alternatives\n");
			createStringForFile(new String(" "));
			totalCount = totalCount + alternativeCount;
			alternativeCount = 0;
		}

		/**
		 * Performs the substitution algorithm. Returns 25*n amount of
		 * alternative spellings
		 */
		public void substitution() {
			// create temp String
			String temp = inputString;
			// Loop through alphabet
			for (int i = 0; i < inputString.length(); i++) {
				// create new String builder Object with inputString
				sb = new StringBuilder(temp);
				// iterate thru the whole String inserting the items.
				for (int j = 0; j < 26; j++) {
					// if the letter is the same as the one about to be
					// inserted...
					// skip the iteration
					if (inputChar[i] == alphabet[j])
						continue;
					sb.setCharAt(i, alphabet[j]);

					// Print to document
					createStringForFile(new String("Substitution string: " + sb));
					alternativeCount++;
					// System.out.println(new String(sb));
					// see if the alternative word is in the dictionaryBST
					String ns = new String(sb);
					if (dictionary.containsKey(ns)) {
						validWords.put(ns,
								dictionary.get(ns));
								}
				}
				// restore the String to it's original form
				sb.setCharAt(i, inputChar[i]);

			}
			createStringForFile("Created " + alternativeCount
					+ " substitution alternatives\n");
			createStringForFile(new String(" "));
			totalCount = totalCount + alternativeCount;
			alternativeCount = 0;
		}

		/**
		 * Performs the insertion algorithm. Returns 26*(n+1) amount of
		 * alternative spellings
		 */
		public void insertion() {
			// create temp String
			String temp = inputString;
			// Loop through alphabet
			for (int i = 0; i < inputString.length() + 1; i++) {
				// create new String builder Object with inputString
				sb = new StringBuilder(temp);
				// iterate thru the whole String inserting the items.
				for (int j = 0; j < 26; j++) {
					sb.insert(i, alphabet[j]);
					String ns = new String(sb);
					// Print to document
					createStringForFile(new String("Insertion string: "
							+ ns)); //TODo
					alternativeCount++;
					// System.out.println(new String(sb));
					// see if the alternative word is in the dictionaryBST
					if (dictionary.containsKey(ns)) { 
						validWords.put(ns,
								dictionary.get(ns)); 
					}
					// restore the String to it's original form
					sb.delete(i, i + 1);
				}
			}
			totalCount = totalCount + alternativeCount;

			createStringForFile("Created " + alternativeCount
					+ " insertion alternatives\n");
			createStringForFile(" ");
			createStringForFile("TOTAL: generated " + totalCount
					+ " alternative spellings!");
			alternativeCount = 0;

			createFile(fileString);

			// System.out.println(validWords.entrySet());
		}

		/**
		 * Helper Method for returning the alternate word with highest frequency
		 * 
		 * @return String highest -------soley for testing purposes
		 */
		public String alternateWordWithHighestFrequency() {
			// String with highest Frequency
			String highest = null;
			int highInt = 0;
			// Selection Sort--- I know that we talk down about this in class,
			// but we are making
			// very few comparisons.
			for (Map.Entry<String, Integer> entry : validWords.entrySet()) {
				if (entry.getValue() > highInt) {
					// swap elements
					highInt = entry.getValue();
					highest = entry.getKey();
				}
			}
//			if (highest == null)
//				System.out.println(inputString + " is an unknown term!");
//			else
//				System.out.println(inputString
//						+ " is an unknown term... did you mean '" + highest
//						+ "'?");
			// System.out.println("THe highest value: " + highest);
			return highest;

		}

		/**
		 * Creates a text file for the alternate spellings for the user to use
		 * if they requested in the main of the SpellingCorrection class.
		 */
		public static void createStringForFile(String string) {
			if (shouldIPrint == false)
				return;
			fileString.insert(fileString.length(), string+ "\r\n");
			
			//s = s + string + "\r\n";
		}

		public static void createFile(StringBuilder string) {
			if (shouldIPrint == false)
				return;
			s = string.toString();
			try {
				writer = new FileWriter(outputFile);
				writer.write(s);
				writer.close();
			} catch (IOException e) {
				System.out.println("Trouble with file writer!");
			}
		}
	}