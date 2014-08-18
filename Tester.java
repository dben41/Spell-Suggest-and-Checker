package assignment10;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

public class Tester {

	/**
	 * @param args
	 */
	static FileWriter writer; 

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
//		DictionaryItem di= new DictionaryItem("steep",34);
//		GenerateAlternativeSpellings gas = new GenerateAlternativeSpellings(
//			 	di);
		
//		gas.transposition();
//		String s = "kolob";
//		// char[] cool = { 'c', 'o', 0, 'o', 'l' };
//		StringBuilder sb = new StringBuilder(s);
//		for (int i = 0; i < sb.length()+1; i++) {
//			 sb = new StringBuilder(s);
//			sb.deleteCharAt(i);
//		//	System.out.println(new String(sb));
//		}
//		TreeSet ts= new TreeSet();
//		ts.add(new DictionaryItem("hello",2));
//		DictionaryItem goodbye= new DictionaryItem("goodbye",4);
//		ts.add((goodbye));
//		ts.contains(goodbye.getFrequency());
//		
//		//ArrayList
//		ArrayList al = new ArrayList();
//		al.add(new DictionaryItem("hello",2));
//		al.
//		//BinarySearch bs= new BinarySearch();
//		//check if its in array and get index
//		bs.binarySearch(al, goodbye.getName() , null);
//		//get frequency number  == OR add to PriorityQueue
//		
//		//System.out.println(new String(sb));
		StringBuilder sb = new StringBuilder("the");
		//sb.
		printToTextFile("hello");
		printToTextFile("world");
	}
	
	public static void printToTextFile(String string) {
		try {
			writer.write(string);
			writer.close();
		} catch (IOException e) {
			System.out
					.println("Trouble with file writer!");
		}
	}

}
