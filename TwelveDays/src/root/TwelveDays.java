package root;
//import java.util.*;
import javax.swing.*;

import loopMethods.Boolet;

public class TwelveDays 
{
//variables	
	final static private String[] ON_THE = {"first","second","third",
			"fourth","fifth","sixth",
			"seventh","eighth","ninth",
			"tenth","eleventh","twelfth"
			};
	final static private String[] GAVE_TO_ME = {"A partridge in a pear tree.","Two turtle doves, and","Three french hens,",
			"Four calling birds,","Five gold rings,","Six geese a-laying,",
			"Seven swans a-swimming,","Eight maids a-milking,","Nine ladies dancing,",
			"Ten lords a-leaping","Eleven pipers piping,","Twelve drummers drumming,"
			};
	private static String pre, post, in;
//constructors
	
//methods
	public static void main(String[] args)
	{ 
		boolean badInput = true;
		while(badInput)
		{
			in = JOptionPane.showInputDialog(null, "Enter how many days of christmas you would like to see(1-12):");
			badInput = mustBe1Thru12(in);
		}
		int intIn = Integer.parseInt(in);
		
		Boolet reps = new Boolet(intIn);
		
		while(reps.fire())
		{
			badInput = true;
			while(badInput)
			{
				in = JOptionPane.showInputDialog(null,"Enter what day of christmas you want to see(1-12):");
				/**
				 * The switch statement compares the String object in its expression with the expressions 
				 * associated with each case label as if it were using the String.equals method; consequently, 
				 * the comparison of String objects in switch statements is case sensitive. The Java compiler 
				 * generates generally more efficient bytecode from switch statements that use String objects 
				 * than from chained if-then-else statements.
				 */
				badInput = mustBe1Thru12(in);
				
				
			}//end while
			String preDay = "";
			
			switch (in) 
			{
				case "1":
					preDay = ON_THE[0];
					break;
				case "2":
					preDay = ON_THE[1];
					break;
				case "3":
					preDay = ON_THE[2];
					break;
				case "4":
					preDay = ON_THE[3];
					break;
				case "5":
					preDay = ON_THE[4];
					break;
				case "6":
					preDay = ON_THE[5];
					break;
				case "7":
					preDay = ON_THE[6];
					break;
				case "8":
					preDay = ON_THE[7];
					break;
				case "9":
					preDay = ON_THE[8];
					break;
				case "10":
					preDay = ON_THE[9];
					break;
				case "11":
					preDay = ON_THE[10];
					break;
				default:
					preDay = ON_THE[11];
					break;
			}
			pre = "On the " + preDay + " day of christmas, my true love gave to me,"; 
			Boolet one = new Boolet();
			switch (in) 
			{
				case "12":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[11];
				case "11":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[10];
				case "10":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[9];
				case "9":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[8];
				case "8":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[7];
				case "7":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[6];
				case "6":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[5];
				case "5":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[4];
				case "4":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[3];
				case "3":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[2];
				case "2":
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[1];
				default:
					if(one.fire())
					{
					post = "";	
					}
					post += "\n"+GAVE_TO_ME[0];
					break;
			}//end switch
			JOptionPane.showMessageDialog(null, pre+post);
		}
		JOptionPane.showMessageDialog(null, "Thank you for using the 12 days of christmas recital application. Have a nice day.");
	}
	
	public static boolean mustBe1Thru12(String i)
	{
		boolean b = true;
		switch (i) 
		{
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":
			case "10":
			case "11":
			case "12":
				b = false;
				break;
			default:
				b = true;
				break;
		}//end switch
	return b;
	}
}
