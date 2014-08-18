package assignment10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

/**
 * Contains our 4 algorithms to find alternative spellings. Creates a
 * .txt file when called from SpellignCorrection class.
 * 
 * @author Daryl Bennett, Leland Stenquist
 */
public class GenerateAlternativeSpellings
	{
		// Insert field variables
		
		// to hold our dictionary from SpellingCorrection
		static HashMap<String, Integer> dictionary;
		
		// to hold any valid alternate spelling created
		static Map<String, Integer> validWords;
		
		// create a char array containing alphabet
		static char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z' };
		
		// create a char array inputChar to contain inputWord
		static char[] inputChar;
		
		// create inputString
		static String inputString;
		
		// To create the file
		static File outputFile;
		
		// to create the file writer
		static FileWriter writer;
		
		// a string to be written to the file
		static String s;
		
		// counters for the program
		private static int alternativeCount;
		public static int totalCount;
		
		// controlled by the -fr command
		private static boolean shouldIPrint;
		
		// for the createion of the string to be put in the .txt file
		private static StringBuilder fileString;
		
		// for the creation ofalternate spellings
		private static StringBuilder sb;

		/**
		 * initialize variables
		 * 
		 * @param inputDictionaryItem
		 * 		- the string input by the user
		 * @param shouldIPrintCommand
		 * 		- a boolean to tell if we should create a file
		 */
		public <Type extends Comparable<? super Type>> GenerateAlternativeSpellings(
				String inputDictionaryItem, boolean shouldIPrintCommand) {

			// set counters to 0
			alternativeCount = 0;
			totalCount = 0;
			
			// initialize field variables
			inputString = inputDictionaryItem;
			
			// initialization of dictionary
			dictionary = SpellingCorrection.dictionaryStats;
			
			// initialize the HashMap to keep our validWords created
			validWords = new HashMap<String, Integer>();
			
			// place inputString into inputChar
			inputChar = new char[inputString.length()];
			inputString.getChars(0, inputString.length(), inputChar, 0);
			
			// determines whether or not to print file
			shouldIPrint = shouldIPrintCommand;
			
			// initialize the empty string
			s = "";
			
			// put that string into the string builder for it to 
			// be manipulated before it is put into the file
			fileString = new StringBuilder(s);

			// if -fr command was given, then initialize all the FileWriter
			// things, if not then don't bother
			if (shouldIPrint == true) {
				outputFile = new File(inputString + ".txt");
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

			// temp to hold input string
			String temp = inputString;
			// beginning of text file
			createStringForFile("User string: " + inputString);
			// add a blank line
			createStringForFile(new String(" "));

			//loop through the inputString which will be a string builder
			// object
			for (int i = 0; i < inputString.length(); i++) {
				
				// create new String builder Object with inputString
				sb = new StringBuilder(temp);
				
				// delete the values in the string at different locations
				sb.deleteCharAt(i);
				
				// turn it back into a string for our file creation
				// and to check if its in the dictionary
				String string = new String(sb);

				// Print to file
				createStringForFile("Deletion string: " + string);
				
				// count how many alternates were created
				alternativeCount++;
				
				// see if the alternate word is in the Hash Map
				// if so put it in validWords
				if (dictionary.containsKey(string)) {
					validWords.put(string, dictionary.get(string));
				}
			}
			
			// final statement for deletion part of file
			createStringForFile("Created " + alternativeCount
					+ " deletion alternatives\n");
			createStringForFile(new String(" "));
			
			// set totalCount
			totalCount = totalCount + alternativeCount;
			
			// set alternativeCount back to zero
			alternativeCount = 0;
		}

		/**
		 * Performs the transposition algorithm. Returns n-1 amount of
		 * alternative spellings
		 */
		public void transposition() {
			
			// put the input string into a char array
			char[] temp = inputString.toCharArray();
			
			// loop through temp
			for (int i = 0; i < temp.length - 1; i++) {
				
				// doesn't swap elements if they are the same
				// ---saves us a useless swap
				if (temp[i] == temp[i + 1]) {
					// compare
					
					// turn it back into a string for our file creation
					// and to check if its in the dictionary
					String ns = new String(temp);

					// see if the alternate word is in the Hash Map
					// if so put it in validWords
					if (dictionary.containsKey(ns)) {
						validWords.put(ns, dictionary.get(ns));
					}
					
					// Print alternate to document
					createStringForFile(new String("Transposition string: " + ns));
					
					// count how many alternates were created
					alternativeCount++;

					// System.out.println(new String(temp));
					continue;
				}
				
				// swap two elements in array
				char charTemp = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = charTemp;

				// turn it back into a string for our file creation
				// and to check if its in the dictionary
				String ns = new String(temp);
				
				// see if the alternative word is in the Hash Map
				// if so put it in validWords
				if (dictionary.containsKey(ns)) {
					validWords.put(ns, dictionary.get(ns));
				}
				
				// Print alternate to document
				createStringForFile(new String("Transposition string: " + ns));

				// count how many alternates were created
				alternativeCount++;

				//swap the array back to normal
				charTemp = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = charTemp;
			}
			
			// final statement for transposition part of file
			createStringForFile("Created " + alternativeCount
					+ " transposition alternatives\n");
			createStringForFile(new String(" "));
			
			// set totalCount
			totalCount = totalCount + alternativeCount;
						
			// set alternativeCount back to zero
			alternativeCount = 0;
		}

		/**
		 * Performs the substitution algorithm. Returns 25*n amount of
		 * alternative spellings
		 */
		public void substitution() {
			
			// temp to hold inputString
			String temp = inputString;
			
			// Loop through string
			for (int i = 0; i < inputString.length(); i++) {
				
				// create new String builder Object with inputString
				sb = new StringBuilder(temp);
				
				// iterate through the alphabet placing each letter at 
				// your position in the string in place of that position's
				// char
				for (int j = 0; j < 26; j++) {
					
					// if the letter is the same as the one about to be
					// inserted...
					// skip the iteration
					if (inputChar[i] == alphabet[j])
						continue;
					
					// set the letter in the alphabet into the location i
					sb.setCharAt(i, alphabet[j]);

					// Print to document
					createStringForFile(new String("Substitution string: " + sb));
					
					// count how many alternates were created
					alternativeCount++;
					
					// turn it back into a string for our file creation
					// and to check if its in the dictionary
					String ns = new String(sb);
					
					// see if the alternative word is in the Hash Map
					// if so put it in validWords
					if (dictionary.containsKey(ns)) {
						validWords.put(ns, dictionary.get(ns));
					}
				}
				
				// restore the String to it's original form
				sb.setCharAt(i, inputChar[i]);

			}
			
			// final statement for substitution part of file
			createStringForFile("Created " + alternativeCount
					+ " substitution alternatives\n");
			createStringForFile(new String(" "));
			
			// set totalCount
			totalCount = totalCount + alternativeCount;
									
			// set alternativeCount back to zero
			alternativeCount = 0;
		}

		/**
		 * Performs the insertion algorithm. Returns 26*(n+1) amount of
		 * alternative spellings
		 */
		public void insertion() {
			
			// temp to hold inputString
			String temp = inputString;
			
			// Loop through string
			for (int i = 0; i < inputString.length() + 1; i++) {
				
				// create new String builder Object with inputString
				sb = new StringBuilder(temp);
				
				// iterate through the alphabet inserting each letter between 
				//  the chars at your position in the string
				for (int j = 0; j < 26; j++) {
					
					// perform the insertion
					sb.insert(i, alphabet[j]);
					
					// turn it back into a string for our file creation
					// and to check if its in the dictionary
					String ns = new String(sb);
					
					// Print to document
					createStringForFile(new String("Insertion string: " + ns));
					
					// count how many alternates were created
					alternativeCount++;
					
					// see if the alternative word is in the Hash Map
					// if so put it in validWords
					if (dictionary.containsKey(ns)) {
						validWords.put(ns, dictionary.get(ns));
					}
					
					// restore the String to it's original form
					sb.delete(i, i + 1);
				}
			}
			
			// get the final total count
			totalCount = totalCount + alternativeCount;

			// final statement for insertion part of file
			createStringForFile("Created " + alternativeCount
					+ " insertion alternatives\n");
			createStringForFile(new String(" "));
			
			// create the final statement for the file
			createStringForFile("TOTAL: generated " + totalCount
					+ " alternative spellings!");
			
			// set alternativeCount back to zero
			alternativeCount = 0;
			
			// call method to create your final file
			createFile(fileString);
		}

		/**
		 * Helper Method for returning the alternate word with highest frequency
		 * 
		 * @return String highest -------soley for testing purposes
		 */
		public static String alternateWordWithHighestFrequency() {
			
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
			
			// of nothing was in the validWords the term was unknown
			if (highest == null)
				System.out.println(inputString + " is an unknown term!");
			// print the possible alternate if you got one
			else
				System.out.println(inputString
						+ " is an unknown term... did you mean '" + highest
						+ "'?");
			
			// return the highest
			return highest;

		}

		/**
		 * takes in a string to build a string builder object which will
		 * be used to hold the contents of the file
		 * 
		 * @param string
		 * 		- a string to be inserted into the string builder object
		 */
		public static void createStringForFile(String string) {
			
			// don't waste time on the file if it was not requested
			if (shouldIPrint == false)
				return;
			
			// add the string to the string builder
			fileString.insert(fileString.length(), string + "\r\n");
		}

		/**
		 * Creates a text file for the alternate spellings for the user to use
		 * if they requested in the main of the SpellingCorrection class.
		 * 
		 * @param string
		 * 		- a string builder object containing the contents to be put
		 * 			into the file.
		 */
		public static void createFile(StringBuilder string) {
			
			// don't waste time on the file if it was not requested
			if (shouldIPrint == false)
				return;
			
			// set s as the contents of the string builder
			s = string.toString();
			
			//write the contents of s to the file
			try {
				writer = new FileWriter(outputFile);
				writer.write(s);
				writer.close();
			} catch (IOException e) {
				System.out.println("Trouble with file writer!");
			}
		}
	}