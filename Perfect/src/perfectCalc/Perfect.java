package perfectCalc;

import javax.swing.*;
import java.util.*;

public class Perfect 
{
//variables
	private static ArrayList<Integer> evens = new ArrayList<Integer>(499), perfects = new ArrayList<Integer>(10);
	private static int sumEven, subscript, dex, mod;
	private static boolean perfect, even;
	private static String stringOfPerfects = "";
	/**
	 * I was experimenting with ultra efficient code to see if I could 
	 * do this calculation to as high as one billion without it taking a 
	 * few days. I was thinking about using multi-threading, but I decided
	 * not to try to teach myself that quite yet.
	 * Anyways, that's why most of my variables are up there instead of
	 * in a smaller scope.
	 * @param args
	 */
	
//constructors
	
	
	
//methods
	public static void main(String[] args)
	{	
		subscript = 0;
		dex = 1;
		while(dex <= 10000)
		{
			if(isPerfect(dex, 0, 1))
			{
				perfects.add(subscript, dex);
				System.out.println(dex);
				++subscript;
			}
			
			++dex;
		}
		dex = 0;
		while(dex <= perfects.size()-1)
		{
			stringOfPerfects += perfects.get(dex);
			if (dex != perfects.size()-1)
			{
				stringOfPerfects += ", ";
			}
			++dex;
		}
		JOptionPane.showMessageDialog(null, "All perfect numbers between 1 and 10,000 are as follows: " + stringOfPerfects);
		
	}
	
	public static boolean isEven(int smaller, int bigger)
	{
		mod = bigger % smaller;
		even = false; 
		if(mod == 0)
		{
			even = true;
		}
		return even;
	}
	
	public static boolean isPerfect(int number, int subs, int index)
	{
		evens.clear();
		while(index <= (int)(number/2+0.5))
		{
			if(isEven(index, number))
			{
				evens.add(subs, index);
				++subs;
			}
			++index;
		}//end while
		
		sumEven = 0;
		index = 0;
		while(index < evens.size())
		{
			sumEven += evens.get(index);
			++index;
		}
		
		perfect = false;
		if(sumEven == number)
		{
			perfect = true;
		}
		return perfect;
	}
	
}
