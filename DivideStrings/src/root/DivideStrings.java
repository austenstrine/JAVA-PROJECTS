package root;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class DivideStrings 
{
//variables
	
	private static final String LNG = "long", SHRT = "short", END = "/']";
	
//main method
	
	public static void main(String[] args)
	{
		String userInput = " ";
		ArrayList<String> firstList = new ArrayList<String>(1);
		
		while(firstList.size() < 20)
		{
			userInput = JOptionPane.showInputDialog(null, "Enter a String. Enter /'] when done entering strings:");
			if(userInput.equals(END))
			{
				break;
			}//end if
			firstList.add(userInput);
		}//end while
		
		Collections.sort(firstList);//probably unnecessary, but whatever. It's nice to know how to use
		ArrayList<String> secondList = new ArrayList<String>(1);
		firstList.trimToSize();
		ListIterator<String> i = firstList.listIterator();
		
		while(i.hasNext())
		{
			if(i.next().length() <= 5)
			{
				secondList.add(i.previous());
				i.remove();
			}//end if
		}//end for
		
		if(!(firstList.isEmpty() && secondList.isEmpty()))
		{	
			boolean invalid = true;//this exists so that invalid() is only run once per loop.
			
			while(invalid)
			{
				userInput = JOptionPane.showInputDialog(null, "Would you like to see the long strings, or the short strings? Type \"long\" or \"short\".");
				userInput = userInput.toLowerCase();
				invalid = invalid(userInput);
				
				if(invalid)
				{
					JOptionPane.showMessageDialog(null, "Invalid input. Please enter either \"long\" or \"short\".");
				}//end if
			}//end while
		}//end if
		
		if(userInput.equals(LNG))
		{
			if(!firstList.isEmpty())
			{	

				JOptionPane.showMessageDialog(null, "The list of long strings is: " + gramIter(firstList) + ".");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The list is empty!");
			}
		}//if
		else if(userInput.equals(SHRT))//using 'else if' prevents execution upon quitting
		{
			if(!secondList.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "The list of short strings is: " + gramIter(secondList) + ".");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "The list is empty!");
			}//end if
		}//end if
	}//end main method
	
	public static boolean invalid(String input)//probably unnecessary, but I hate writing lots of conditions multiple times.
	{
		if(input.equals(LNG) || input.equals(SHRT))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static String gramIter(ArrayList<String> a)
	{
		ListIterator<String> i = a.listIterator();
		String lister = " ";
		int size = a.size()-1;
		while(i.hasNext())
		{
			if (i.nextIndex() == 0)
			{
				lister += "\'" + i.next() + "\'";
			}
			else if(i.nextIndex() == size)
			{
				if(size > 0)//size is actually size-1
				{
					lister += ", and \'" + i.next() + "\'";
				}
				else
				{
					lister += "\'" + i.next() + "\'";
				}//end inner if
			}
			else
			{
				lister += ", \'" + i.next() + "\'";
			}//end if
		}//end for
		return lister;
	}
}
