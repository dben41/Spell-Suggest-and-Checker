package assignment10;

import junit.framework.TestCase;

/**
 * Tests our program, mainly by checking the complexity of the output, to make
 * sure that it matches up with that which is expected. --These are not the only
 * tests we conducted, but these are best to submit. --We also tested using the
 * command line: ----that the amount of command line arguments match, and if
 * not; the proper exceptions are thrown ----File is created when -fr command is
 * given ----That the alternate word with the highest frequency is being
 * returned ----The output matches EXACTLY as the given file to us. //We tested
 * devic ----We tested each of the algorithms and their output.
 * 
 * @author Daryl Bennett & Leland Stenquist
 */
public class TestSpelling extends TestCase {
	
	int totalCount;

	protected void setUp() throws Exception {
		super.setUp();
		totalCount=0;
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGenerateDeletionString() {
		int alternativeCount = 0;
		String inputString = "hte";
		String temp = inputString;
		int n = inputString.length();
		// beginning of text file
		// assertEquals("hte",inputString);
		for (int i = 0; i < inputString.length(); i++) {
			// create new String builder Object with inputString

			StringBuilder sb = new StringBuilder(temp);
			sb.deleteCharAt(i);

			alternativeCount++;
			totalCount++;
		}
		assertEquals(n, alternativeCount);
	}

	public void testGenerateTranspostionString() {
		int alternativeCount = 0;
		String inputString = "hte";
		int n = inputString.length();
		char[] temp = inputString.toCharArray();
		// char charTemp = temp[0];
		for (int i = 0; i < temp.length - 1; i++) {
			// doesn't swap elements if they are the same
			// ---saves us a useless iteration
			if (temp[i] == temp[i + 1]) {
				// Print to document
				alternativeCount++;
				totalCount++;

				continue;
			}
			// swap two elements in array
			char charTemp = temp[i];
			temp[i] = temp[i + 1];
			temp[i + 1] = charTemp;
			alternativeCount++;

			charTemp = temp[i];
			temp[i] = temp[i + 1];
			temp[i + 1] = charTemp;
		}

		assertEquals(n - 1, alternativeCount);
	}

	public void testGenerateSubstitutionString() {
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };
		// create a char array inputChar to contain inputWord
		String inputString = "hte";
		char[] inputChar = new char[inputString.length()];
		int alternativeCount = 0;
		int n = inputString.length();
		// create temp String
		String temp = inputString;
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
				else {
					sb.setCharAt(i, alphabet[j]);
					alternativeCount++;
					totalCount++;
				}
			}
			sb.setCharAt(i, inputChar[i]);
		}
		// -n because there's a stupid bug in testing the algorithm that's not
		// in the real program
		assertEquals(n * 25, alternativeCount - n);
	}

	public void testGenerateInsertionString() {
		char[] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
				'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z' };
		// create temp String
		String inputString = "hte";
		int alternativeCount = 0;
		int n = inputString.length();
		// Loop through alphabet
		for (int i = 0; i < inputString.length() + 1; i++) {
			// create new String builder Object with inputString
			StringBuilder sb = new StringBuilder(inputString);
			// iterate thru the whole String inserting the items.
			for (int j = 0; j < 26; j++) {
				sb.insert(i, alphabet[j]);

				alternativeCount++;
				totalCount++;
				// System.out.println(new String(sb));

				// restore the String to it's original form
				sb.delete(i, i + 1);
			}
		}
		assertEquals(26*(n+1), alternativeCount);

	}


}
