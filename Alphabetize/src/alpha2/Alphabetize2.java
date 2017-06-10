
package alpha2;

import javax.swing.*;
import java.util.*;

public class Alphabetize2 
{
//variables
	private static String one = "_", two = "_", three = "_";
	private static boolean repeat = false;
//constructors
	
//main method
	
	public static void main(String[] args)
	{
		while(!isNotAllLetters(one))
		{
			if(repeat)
			{
				JOptionPane.showMessageDialog(null, "Word must only have letters. Try again.");
			}
			one = JOptionPane.showInputDialog(null, "Enter a word:");
			repeat = true;
		}
		repeat = false;
		while(!isNotAllLetters(two))
		{
			if(repeat)
			{
				JOptionPane.showMessageDialog(null, "Word must only have letters. Try again.");
			}
			two = JOptionPane.showInputDialog(null, "Enter a second word:");
			repeat = true;
		}
		repeat = false;
		while(!isNotAllLetters(three))
		{
			if(repeat)
			{
				JOptionPane.showMessageDialog(null, "Word must only have letters. Try again.");
			}
			three = JOptionPane.showInputDialog(null, "Enter a third word:");
			repeat = true;
		}
		repeat = false;
		
		JOptionPane.showMessageDialog(null, "The words you entered are " + one + ", " + two + ", and " + three + ".");
		
		ArrayList<String> words = new ArrayList<String>(0);
		words.add(one);
		words.add(two);
		words.add(three);
		ArrayList<String> wordsSort = new ArrayList<String>(words);//makes a new ArrayList that's the same as words
		for(int i = 0; i < 3; ++i)
		{
			wordsSort.set(i, wordsSort.get(i).toLowerCase());//replaces each element with the lowercase version of itself
		}
		Collections.sort(wordsSort);//sorts the words in alphabetical order
		for(int i = 0; i < 3; ++i)
		{
			for(int n = 0; n < 3; ++n)
			{
				if(words.get(i).equalsIgnoreCase(wordsSort.get(n)))
				{
					wordsSort.set(n, words.get(i));//reinstates any case variance that was lost
				}
			}
		}
		
		JOptionPane.showMessageDialog(null, "In alphabetical order, the words you entered are " + wordsSort.get(0) + ", " + wordsSort.get(1) + ", and " + wordsSort.get(2) + ".");
		
	}
	
//methods
	
	public static boolean isNotAllLetters(String s)
	{
		boolean letterFound = true;
		String alphabet = "aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ";
		for(int i = 0; i < s.length() && letterFound; ++i)
		{
			letterFound = false;
			for(int n = 0; n < alphabet.length() && !letterFound; ++n)
			{
				if(s.charAt(i) == alphabet.charAt(n))
				{
					letterFound = true;
				}//end if
			}//end inner alphabet for
		}//end outer s for
		return letterFound;
	}
	

}
