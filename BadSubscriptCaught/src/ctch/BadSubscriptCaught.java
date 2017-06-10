/*
 * Write an application named BadSubscriptCaught in which you declare an array of 10 first names. 
 * Write a try block in which you prompt the user for an integer and display the name in the 
 * requested position. Create a catch block that catches the potential 
 * ArrayIndexOutOfBoundsException thrown when the user enters a number that is out of range. The 
 * catch block should also display an error message. Save the file as BadSubscriptCaught.java.
 */
package ctch;
import javax.swing.*;

public class BadSubscriptCaught {

	public static void main(String[] args) 
	{
		String[] names = {"bob","thom","george","allis","ringo","paul","stan","phil","cat","charles"};
		int i = 0;
		String s = "";
		while(!s.equals("END"))
		{
			try
			{
				s = JOptionPane.showInputDialog(null, "Enter an integer between 1 and 10, or END to end the program.");
				if(!s.equals("END"))
				{
					i = Integer.parseInt(s);
					--i;
					JOptionPane.showMessageDialog(null, names[i]);
				}
			}
			catch(IndexOutOfBoundsException e)
			{
				JOptionPane.showMessageDialog(null, "Index out of bounds: "+e.getMessage());
			}
			catch(IllegalArgumentException e)
			{
				JOptionPane.showMessageDialog(null, "Argument is not a number: "+e.getMessage());
			}//end try/catch
		}//end while
	}//end main
}//end class
