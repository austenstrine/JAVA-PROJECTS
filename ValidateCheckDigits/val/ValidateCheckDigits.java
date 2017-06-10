package val;

import java.io.*;
import java.nio.file.*;
import javax.swing.JOptionPane;
import static java.nio.file.StandardOpenOption.*;

public class ValidateCheckDigits 
{
	
	public static void main(String[] args) 
	{
		Path txt = Paths.get("accountNums.txt");
		BufferedOutputStream out = null;
		boolean caught = false;
		
		/*
		 * Create a txt file if it is not already there.
		 * Fill with valid/invalid account numbers as one
		 * line, delimited with commas.
		 */
		try 
		{
			out = new BufferedOutputStream(Files.newOutputStream(txt, CREATE_NEW));
		} 
		catch (IOException e) 
		{
			System.out.println("This file already exists!\n"+e.fillInStackTrace());;
			caught = true;
		}
		if(!caught)
		{
			String s = "758849,895776,756480,165433,690220,555331,777777,176358,809709,000099";
			byte[] b = s.getBytes();
			try{out.write(b);out.close();}
			catch(IOException e){e.printStackTrace();}
		}
		

		
		try
		{
			/*
			 * Splits the one line in the file by commas, and stores each
			 * resulting String in a String[].
			 */
			InputStream fIn = Files.newInputStream(txt);
			BufferedReader in = new BufferedReader(new InputStreamReader(fIn));
			String st = in.readLine();
			String[] array = new String[10];
			array = st.split(",", array.length);
			/* 
			 * Adds the numeric value of the characters in the array 
			 * together, and subtracts the last character's numeric 
			 * value. Stores the sumDifference and lastChar in an 
			 * int[] at 0 and 1, respectively. Stores that array in 
			 * an int[][] that's parallel with the array of strings.
			 */ 
			int[][] ger = new int[10][];
			for(int i = 0; i < array.length; ++i)
			{
				int sum = 0;
				char[] cAr = array[i].toCharArray();
				for(char ch : cAr)
				{
					sum += Character.getNumericValue(ch);
				}
				int last = Character.getNumericValue(cAr[cAr.length-1]);
				System.out.println(last + " LAST");
				sum -= last;
				System.out.println(sum + " SUM");
				int[] inte = new int[2];
				inte[0] = sum;
				inte[1] = last;
				ger[i] = inte;
			}
			/*
			 * Passes int[][] to getValidity(), which checks sumDifference%10 
			 * and lastChar for equality in each array, and marks the 
			 * parallel value in a boolean[] as true or false based on
			 * the result. Returns that boolean[], which is assigned to
			 * the name bool.
			 */
			boolean[] bool = new boolean[10];
			bool = getValidity(ger);
			/*
			 * Iterates through bool, and concatenates a message
			 * indicating whether the parallel value in the String[] is
			 * a valid account number or not based on the value stored
			 * in bool[index].
			 */
			st = "";
			for(int i = 0; i < bool.length;++i)
			{
				if(bool[i])
				{
					st += "The account number " + array[i] + " is valid.\n" ;
				}
				else
				{
					st += "The account number " + array[i] + " is NOT valid.\n" ;
				}
			}
			/*
			 * Displays that concatenated string of messages.
			 */
			JOptionPane.showMessageDialog(null, st);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	/* Checks sumDifference%10 and lastChar for equality in 
	 * each array, and marks the parallel value in a boolean[] 
	 * as true or false based on the result. Returns that 
	 * boolean[].
	 */
	public static boolean[] getValidity(int[][] ar)
	{
		boolean[] bool = new boolean[10];
		int sumMod10 = 0;
		int last = 0;
		for(int t = 0; t < ar.length; ++t)
		{
			sumMod10 = ar[t][0]%10;
			last = ar[t][1];
			if(sumMod10 == last)
			{
				bool[t] = true;
				System.out.println(sumMod10 + " " + last);
			}
			else
			{
				bool[t] = false;
				System.out.println(sumMod10 + " " + last);
			}
		}
		return bool;
	}
}
