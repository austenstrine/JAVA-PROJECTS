/*
 * Purpose of this class is to provide a program capable of
 * storing product numbers, accessing them, and enabling a
 * "optimization mode" that keeps track of which products are 
 * accessed most often, altering the data structure they are 
 * stored in accordingly to increase average retrieval time.
 */
package root;

import javax.swing.JOptionPane;
import root.OrderByUseList;
import java.util.Random;

public class ProductNumberSearch 
{

	private static final String END = "/']";
	private static String[] productArr = new String[30];
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		Integer ig;
		for(int i = 0; i < 29; ++i)
		{
			ig = (rand.nextInt(100) + 1);
			productArr[i] = ig.toString();
		}
		/*{int i = 0;
		while(true)
		{
			String productString = " ";
			boolean notNum = isntNumbers(productString);//method will run once instead of twice per pass
			while(notNum)//here
			{
				productString = JOptionPane.showInputDialog(null, "Please enter a product number, or enter /'] to end: ");
				if(productString.equals(END))
				{
					break;
				}//end if
				notNum = isntNumbers(productString);//here
				if(notNum)
				{
					JOptionPane.showMessageDialog(null, "Error! Entry contained non-numeric characters!");
				}//end if
			}//end while
			if(productString.equals(END) || i == 30)
			{
				JOptionPane.showMessageDialog(null, "Moving on...");
				break;
			}//end if

			productIntArr[i] = Integer.parseInt(productString);
			++i;
		}//end outer while
		}//end int i*/
		OrderByUseList obu = new OrderByUseList(productArr, 5);
		while(true)
		{
			String searchString;
			while(true)
			{
				searchString = JOptionPane.showInputDialog(null, "Enter the the product number to search(/'] ends, opt optimizes):");
				if(searchString.equals(END) || searchString.equals("opt"))
				{
					break;
				}//end if
				if(isntNumbers(searchString))
				{
					JOptionPane.showMessageDialog(null, "Error! Entry contained non-numeric characters!");
				}
				else
				{
					break;
				}//end if
			}//end inner while
			boolean noOpt = true; 
			if(searchString.equals(END))
			{
				break;
			}
			else if(searchString.equals("opt"))
			{
				obu.sort();
				noOpt = false;
			}//end if
			if(noOpt)
			{
				int subs = obu.findFirst((Object)searchString);
				if(subs != -1)
				{
					JOptionPane.showMessageDialog(null, "Product number " + searchString + " is in position " + subs);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Product not found!");
				}//end inner if
			}//end if
		}//end outer while
	}
	
	public static boolean isntNumbers(String s)
	{
		int i = 0;
		int l = s.length();
		boolean notNum = false;
		while(i < l)
		{
			char c = s.charAt(i);
			if(!(c >= '0' && c <= '9'))
			{
				notNum = true;
			}//end if
			++i;
		}//end while
		return notNum;
	}
}
/*
 * 123
 * 234
 * 345
 * 456
 * 567
 * 678
 * 789
 * 890
 * 135
 * 246
 * 357
 * 468
 * 579
 * 680
 */
