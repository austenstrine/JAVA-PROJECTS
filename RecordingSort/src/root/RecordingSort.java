/*
 * b. Write an application that instantiates five Recording objects and prompts 
 * the user for values for the data fields. Then prompt the user to enter which 
 * field the Recordings should be sorted by—song title, artist, or playing time. 
 * Perform the requested sort procedure, and display the Recording objects. Save 
 * the file as RecordingSort.java.
 */
package root;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Collections;


public class RecordingSort 
{
	public static void main(String[] args)
	{
		
		ArrayList<Recording> ral = new ArrayList<Recording>(5);
		for(int i = 4; i >= 0; --i)
		{
			Recording r = new Recording(
					JOptionPane.showInputDialog(null, "Please enter the title of recording #" + (i+1)),
					JOptionPane.showInputDialog(null, "Please enter the artist of recording #" + (i+1)),
					giveInt(i));
			ral.add(r);
		}//end for
		
		String s = "";
		while(notTas(s.toLowerCase()))
		{
			s = JOptionPane.showInputDialog(null,"Please enter whether you would like the recordings to be sorted by Title(t), Artist(a), or Seconds(s).");
			if(notTas(s.toLowerCase()))
			{
				JOptionPane.showMessageDialog(null, "Invalid input! Must be (t), (a), or (s)!");
			}//end if
		}//end while
		Recording.setComparison('t');
		Collections.sort(ral);
		s = "";
		int in = 0;
		for(Recording g : ral)
		{ 
			++in;
			s += g.getAllAsString(in) + "\n";
		}//end for
		
		JOptionPane.showMessageDialog(null, s);
		
	}//end main
	
	public static boolean notTas(String s)
	{
		if(s.equals("t") || s.equals("a") || s.equals("s"))////////////I feel like I should be using an enum instead of three string values, but I'm not sure how to implement it here...
		{
			return false;
		}
		return true;
	}
	
	public static int giveInt(int i)
	{
		String s = "";
		boolean notSeconds = true;
		while(notSeconds)
		{
			notSeconds = false;
			s = JOptionPane.showInputDialog(null, "Please enter the length of recording #" + (i+1) + " in seconds.");
			Character c;
			for(int n = 0; n < s.length(); ++n)
			{
				c = s.charAt(0);
				if(!Character.isDigit(c))
				{
					notSeconds = true;
				}//end if
			}//end for
			if(notSeconds)
			{
				JOptionPane.showMessageDialog(null, "Invalid input! Must be an Integer only.");
			}
		}//end while
		return Integer.parseInt(s);
	}//end giveInt
}//end class


