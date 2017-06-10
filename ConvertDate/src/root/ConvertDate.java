/**
 * Write an application in which the user can enter a date using digits and slashes 
 * (for example, 6/24/2014), and receive output that displays the date with the month 
 * shown as a word (such as June 24, 2014). Allow for the fact that the user might or 
 * might not precede a month or day number with a zero (for example, the user might type 
 * 06/24/2014 or 6/24/2014). Do not allow the user to enter an invalid date, defined 
 * as one for which the month is less than 1 or more than 12, or one for which the day 
 * number is less than 1 or greater than the number of days in the specified month. Also 
 * display the dates ordinal position in the year; for example, 6/24/14 is the 175th day 
 * of the year. In this application, use your knowledge of arrays to store the month names, 
 * as well as values for the number of days in each month so that you can calculate the 
 * number of days that have passed. Save the application as ConvertDate.java.
 */
package root;

import javax.swing.JOptionPane;

public class ConvertDate 
{
	private static boolean invalid = true;
	private final static String BAD = "null ";
	
	public static void main(String[] args)
	{
		while(invalid)
		{
			String date = JOptionPane.showInputDialog(null, "Please enter a date using Digits and Slashes or Dashes, as in \'6/24/2014\': ");
			String[] dates = date.split("/");
			boolean badLength = false;
			
			if(dates.length != 3)
			{
				dates = date.split("-");
			}
			
			if(dates.length != 3)
			{
				badLength = true;
			}
			else
			{
				date = getMonth(dates) + getDay(dates) + dates[2];
			}
			
			if(!invalid && !badLength)
			{
				JOptionPane.showMessageDialog(null, date + " is the converted form of your date.");
			}
			else
			{
				String msg = "Uh-oh! Looks like something went wrong. Here's what I noticed:\n";
				
				if(invalid && !badLength)
				{
					msg += "The characters you put in weren't right for calendar dates!\n";
				}//end if
				
				if(badLength)
				{
					msg += "There were extra/missing slashes!\n";
				}//end if
				
				JOptionPane.showMessageDialog(null, msg);
			}//end if
		}//end while
	}//end main
	
	public static String getMonth(String[] ar)
	{
		String mo = ar[0];
		invalid = false;
		switch (mo)
		{
			case "1":
			case "01":
				mo = "January ";
				break;
			case "2":
			case "02":
				mo = "February ";
				break;
			case "3":
			case "03":
				mo = "March ";
				break;
			case "4":
			case "04":
				mo = "April ";
				break;
			case "5":
			case "05":
				mo = "May ";
				break;
			case "6":
			case "06":
				mo = "June ";
				break;
			case "7":
			case "07":
				mo = "July ";
				break;
			case "8":
			case "08":
				mo = "August ";
				break;
			case "9":
			case "09":
				mo = "September ";
				break;
			case "10":
				mo = "October ";
				break;
			case "11":
				mo = "November ";
				break;
			case "12":
				mo = "December ";
				break;
			default:
				invalidMsg(mo, "month");
				invalid = true;
				return BAD;
		}
		return mo;
	}
	
	public static String getDay(String[] ar)
	{
		String mo = ar[0];
		String da = ar[1];
		if(!invalid)
		{
			invalid = false;
			switch (da)
			{
				case "1":
				case "01":
					da = "1st, ";
					break;
				case "2":
				case "02":
					da = "2nd, ";
					break;
				case "3":
				case "03":
					da = "3rd, ";
					break;
				case "4":
				case "04":
					da = "4th, ";
					break;
				case "5":
				case "05":
					da = "5th, ";
					break;
				case "6":
				case "06":
					da = "6th, ";
					break;
				case "7":
				case "07":
					da = "7th, ";
					break;
				case "8":
				case "08":
					da = "8th, ";
					break;
				case "9":
				case "09":
					da = "9th, ";
					break;
				case "10":
					da = "10th, ";
					break;
				case "11":
					da = "11th, ";
					break;
				case "12":
					da = "12th, ";
					break;
				case "13":
					da = "13th, ";
					break;
				case "14":
					da = "14th, ";
					break;
				case "15":
					da = "15th, ";
					break;
				case "16":
					da = "16th, ";
					break;
				case "17":
					da = "17th, ";
					break;
				case "18":
					da = "18th, ";
					break;
				case "19":
					da = "19th, ";
					break;
				case "20":
					da = "20th, ";
					break;
				case "21":
					da = "21st, ";
					break;
				case "22":
					da = "22nd, ";
					break;
				case "23":
					da = "23rd, ";
					break;
				case "24":
					da = "24th, ";
					break;
				case "25":
					da = "25th, ";
					break;
				case "26":
					da = "26th, ";
					break;
				case "27":
					da = "27th, ";
					break;
				case "28":
					da = "28th, ";
					break;
				case "29":
					if(not28(mo))
					{
						da = "29th, ";
						break;
					}
					else
					{
						invalidMsg(da, "day");
						invalid = true;
						return BAD;
					}
				case "30":
					if(not28(mo))
					{
						da = "30th, ";
						break;
					}
					else
					{
						invalidMsg(da, "day");
						invalid = true;
						return BAD;
					}
				case "31":
					if(not30(mo))
					{
						da = "31st, ";
						break;
					}
					else
					{
						invalidMsg(da, "day");
						invalid = true;
						return BAD;
					}
				default:
					invalidMsg(da, "day");
					invalid = true;
					return BAD;
			}//end switch
			return da;
		}
		else
		{
			return BAD;
		}//end if
	}//end getDay
	
	public static void invalidMsg(String st, String ty)
	{
		JOptionPane.showMessageDialog(null, st + " is not a valid " + ty + "!");	
	}//end invalidMsg
	
	public static boolean not28(String mo)
	{
		if(mo.equals("02") || mo.equals("2"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}//end not28
	
	public static boolean not30(String mo)
	{
		switch(mo)
		{
			case "2":
			case "02":
			case "4":
			case "04":
			case "6":
			case "06":
			case "9":
			case "09":
			case "11":
				return false;
			default:
				return true;
		}//end switch
	}//end not30
}
