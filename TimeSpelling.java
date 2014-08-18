package assignment10;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

/**
 * Times our program.  This class will not run properly without the 
 * GenerateAlternateSpellingsForTiming class and the SpellingCorrectionTime class
 * This to correctly test the generateAltSpellOnlyWithFileWriting() method you
 * must comment out any code in the GenerateAlternateSpellingsForTiming that has
 * to do with the dictionary Hash Map and the validItems Hash Map.
 * 
 * @author Daryl Bennett & Leland Stenquist
 */
public class TimeSpelling
	{
		public static void main(String[] args) {

			// comment the ones you don't want to run 
			
			generateAltSpellOnly();
			System.out.println("test1 done");
			generateAltSpellOnlyWithFileWriting();
			System.out.println("test2 done");
			generateAltSpellOnlyWithQueue();
			System.out.println("test3 done");
			generateAltSpellOnlyWithQueueWithFiles();
			System.out.println("test4 done");
			runSpellCor();
			System.out.println("test5 done");
			runSpellCorWithFile();
			System.out.println("test6 done");
		}

		/**
		 * Times the run time of our creation of alternative spellings and 
		 * only alternative spellings.  There is no file creation.
		 */
		public static void generateAltSpellOnly() {
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				// fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 100;

				startTime = System.nanoTime();

				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						createAllAlternateSpellingsOnly(stringsArrayList.get(j));
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}
		
		/**
		 * This method times how long it takes to create all the alternate
		 * spellings and write them to a file
		 */
		public static void generateAltSpellOnlyWithFileWriting() {
			//to run this would be impossible without commenting out the appropriate code
			//from the GenerateAlternateSpellingsForTiming Class.
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				// fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 100;

				startTime = System.nanoTime();

				//remember the code is only commented out in the GenerateAlternateSpellingsForTiming class to stop it from 
				//creating a priority queue
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						GenerateAlternateSpellingsForTiming gA = new GenerateAlternateSpellingsForTiming(stringsArrayList.get(j),true);
						gA.deletion();
						gA.transposition();
						gA.substitution();
						gA.insertion();
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}
		
		/**
		 * This code times how long it takes to generate all the alternate spellings and put
		 * any valid alternatives into a HashMap to check their frequency. No file is created
		 */
		public static void generateAltSpellOnlyWithQueue() {
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				// populate the dictionary
				SpellingCorrectionTime sc =new SpellingCorrectionTime();
				sc.populate(new File("WordStats.txt"));
				
				// fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 50;

				startTime = System.nanoTime();

				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						sc.process(stringsArrayList.get(j));
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}
		
		/**
		 * This code times how long it takes to generate all the alternate spellings and put
		 * any valid alternatives into a HashMap to check their frequency. The file is created
		 */
		public static void generateAltSpellOnlyWithQueueWithFiles() {
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				SpellingCorrectionTime sc =new SpellingCorrectionTime();
				
				//set boolean to true so the file is created
				sc.printCommand =true;
				
				// populate the dictionary
				sc.populate(new File("WordStats.txt"));
				
				// fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 50;

				startTime = System.nanoTime();

				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						sc.process(stringsArrayList.get(j));
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}
		
		/**
		 * Test how long it takes to create the dictionary, create the alternate 
		 * spellings and if valid put them into a Hash Map to return the
		 * one with the highest frequency.  No file is created 
		 */
		public static void runSpellCor() {
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				SpellingCorrectionTime sc =new SpellingCorrectionTime();
				
				// don't create a file
				sc.printCommand =false;
				
				// fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 50;

				startTime = System.nanoTime();
				
				sc.populate(new File("WordStats.txt"));

				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						sc.process(stringsArrayList.get(j));
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}
		
		/**
		 * Test how long it takes to create the dictionary, create the alternate 
		 * spellings and if valid put them into a Hash Map to return the
		 * one with the highest frequency.  The file is created 
		 */
		public static void runSpellCorWithFile() {
			for (int size = 100; size <= 500; size = size + 10) {
				// SETUP TASKS
				
				SpellingCorrectionTime sc =new SpellingCorrectionTime();
				
				// create file
				sc.printCommand =true;
				
				//fill an array with random strings
				ArrayList<String> stringsArrayList = fillArray(size);
				// Make sure the size of the collection matches the size we set
				// it to.
				if (stringsArrayList.size() != size) {
					System.out.println("Size does not match");
					System.exit(0);
				}

				// Timing code starting point
				long startTime, midpointTime, stopTime;

				// First, spin computing stuff until one second has gone by.
				// This allows this thread to stabilize.
				startTime = System.nanoTime();

				while (System.nanoTime() - startTime < 1000000000) {
				} // empty block

				// Now, run the test.
				long timesToLoop = 50;

				startTime = System.nanoTime();

				sc.populate(new File("WordStats.txt"));
				
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < stringsArrayList.size(); j++) {
						sc.process(stringsArrayList.get(j));
					}
				}

				midpointTime = System.nanoTime();

				// Run an empty loop to capture the cost of running the loop.
				for (long i = 0; i < timesToLoop; i++) {
					for (int j = 0; j < size; j++) {
					}
				}

				stopTime = System.nanoTime();

				// Compute the time, subtract the cost of running the loop
				// from the cost of running the loop and computing square roots.
				// Average it over the number of runs.
				double averageTime = ((midpointTime - startTime) - (stopTime - midpointTime))
						/ timesToLoop;

				System.out.println(averageTime + "");
			}
		}

		/**
		 * this is the method that generateAltSpellOnly() calls.  It only
		 * generates the alternate spellings
		 * 
		 * @param s
		 * 		- input string
		 */
		public static void createAllAlternateSpellingsOnly(String s) {
			
			//create a char array of the alphabet
			char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z' };
			// create a char array inputChar to contain new word
			char[] inputChar;
			// create inputString
			String inputString = s;
			// initialize field variables
			inputString = s;
			inputChar = new char[inputString.length()];
			inputString.getChars(0, inputString.length(), inputChar, 0);

			// deletion----------------------------------------------------------
			// create new array
			String temp = inputString;
			for (int i = 0; i < inputString.length(); i++) {
				// create new String builder Object with inputString
				StringBuilder sb = new StringBuilder(temp);
				sb.deleteCharAt(i);
			}
			
			// transposition--------------------------------------------------------------------
			char[] tempArray = inputString.toCharArray();
			// char charTemp = temp[0];
			for (int i = 0; i < tempArray.length - 1; i++) {
				// doesn't swap elements if they are the same
				// ---saves us a useless iteration
				if (tempArray[i] == tempArray[i + 1]) {
					continue;
				}
				// swap two elements in array
				char charTemp = tempArray[i];
				tempArray[i] = tempArray[i + 1];
				tempArray[i + 1] = charTemp;
				charTemp = tempArray[i];
				tempArray[i] = tempArray[i + 1];
				tempArray[i + 1] = charTemp;
			}
			
			// substitution---------------------------------------------------------------
			// Loop through alphabet
			for (int i = 0; i < inputString.length(); i++) {
				// create new String builder Object with inputString
				StringBuilder sb = new StringBuilder(temp);
				// iterate thru the whole String inserting the items.
				for (int j = 0; j < 26; j++) {
					// if the letter is the same as the one about to be
					// inserted...
					// skip the iteration
					if (inputChar[i] == alphabet[j])
						continue;
					sb.setCharAt(i, alphabet[j]);
				}
				// restore the String to it's original form
				sb.setCharAt(i, inputChar[i]);
			}

			// insertion--------------------------------------------------------------------
			// Loop through alphabet
			for (int i = 0; i < inputString.length() + 1; i++) {
				// create new String builder Object with inputString
				StringBuilder sb = new StringBuilder(temp);
				// iterate thru the whole String inserting the items.
				for (int j = 0; j < 26; j++) {
					sb.insert(i, alphabet[j]);
					// restore the String to it's original form
					sb.delete(i, i + 1);
				}
			}
		}
		
		/**
		 * Generates random strings and puts them into an ArrayList
		 * 
		 * @param arraySize
		 * 		- size of the array to be filled
		 * @return
		 * 		- a ArrayList full of strings
		 */
		public static ArrayList<String> fillArray(int arraySize){
			
			// size of the array
			int size = arraySize;
			
			// fill a char array with the alphabet
			char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
					'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
					't', 'u', 'v', 'w', 'x', 'y', 'z' };
			
			// initialize and ArrayList of Strings
			ArrayList<String> stringsArrayList = new ArrayList<String>();
			
			// set a Random as rand
			Random rand = new Random();
			
			// make rand's seed 456
			rand.setSeed(456);
			
			// create an empty string
			String s = "";

			// loop from zero to size - 1
			for (int i = 0; i < size; i++) {
				
				// randomly generate a String size no bigger than ten
				int stringSize = rand.nextInt(10);
				
				// if the string size is zero change it to 5
				if (stringSize == 0)
					stringSize = 5;
				
				// loop through the string size and add to the string s
				for (int t = 1; t <= stringSize; t++) {
					int getChar = rand.nextInt(25);
					s = s + alphabet[getChar];
				}
				
				// add s to a the array list
				stringsArrayList.add(s);
				
				// make s empty again
				s = "";
			}
			
			// return the ArrayList
			return stringsArrayList;
		}

	}
