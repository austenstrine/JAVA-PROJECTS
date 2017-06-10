package root;
/*
 * Write an application named CollegeList that declares an array of four regular 
 * CollegeEmployees, three Faculty, and seven Students. Prompt the user to specify 
 * which type of persons data will be entered (C, F, or S), or allow the user to 
 * quit (Q). While the user chooses to continue (that is, does not quit), accept 
 * data entry for the appropriate type of Person. If the user attempts to enter 
 * data for more than four CollegeEmployees, three Faculty, or seven Students, 
 * display an error message. When the user quits, display a report on the screen 
 * listing each group of Persons under the appropriate heading of College Employees, 
 * Faculty, or Students. If the user has not entered data for one or more types of 
 * Persons during a session, display an appropriate message under the appropriate 
 * heading.
 */
import javax.swing.JOptionPane;
public class CollegeList 
{
	private enum UIn {E, F, S, Q}
	
	private enum Cons
	{
		ZIP(5), PHONE(10), SOCIAL(9), SALARY(6);
		private int val;
		
		private Cons(int i)
		{
			val = i;
		}
		
		public int getVal()
		{
			return this.val;
		}
	}//end Cons
	
	private static Cons longs = Cons.valueOf("ZIP");
	private static UIn uInput = null;
	
	private static int[] numAdded = new int[3];
	private static Person[][] peeps = {new CollegeEmployee[4], new Faculty[3], new Student[7]};
	//wanted to make the arrays above fall under a HashMap<UIn, Object[]>, but didn't want to take the time since I'm already done
	private static boolean keepGoing = true;
	
	public static void main(String[] args) 
	{
		while(keepGoing)
		{
			uInput = null;
			while(uInput == null)
			{
				try
				{
					uInput = UIn.valueOf(
						JOptionPane.showInputDialog(null, "Please enter the type of individual for which you will be entering data: (E for college employee, F for faculty, or S for student. Enter Q to quit)")
						.toUpperCase()
						);
				}
				catch(IllegalArgumentException whoops)
				{
					JOptionPane.showMessageDialog(null, "Invalid input!");
				}//end try/catch
			}//end inner while
			
			enumSwitch();
		}//end outer while
		
		try
		{
			for(CollegeEmployee c : (CollegeEmployee[])peeps[0])
			{
				c.display();
			}
		}
		catch(NullPointerException collegeemp)
		{
			JOptionPane.showMessageDialog(null, "End of College Employees.");
		}//end
		
		try
		{
			for(Faculty f : (Faculty[])peeps[1])
			{
				f.display();
			}
		}
		catch(NullPointerException faculty)
		{
			JOptionPane.showMessageDialog(null, "End of Faculty.");
		}//end faculty try/catch
		
		try
		{
			for(Student s : (Student[])peeps[2])
			{
				s.display();
			}
		}
		catch(NullPointerException student)
		{
			JOptionPane.showMessageDialog(null, "End of Students.");
		}//end student try/catch
		
	}//end main
		

	public static void enumSwitch()
	{
		switch (uInput)
		{
			case E:
				if(numAdded[0] < peeps[0].length)
				{
					longs = Cons.valueOf("ZIP");
					peeps[0][numAdded[0]] = new CollegeEmployee( //numAdded doubles as a subscript
							JOptionPane.showInputDialog(null, "Enter the First Name:"), 
							JOptionPane.showInputDialog(null, "Enter the Last Name:"),
							JOptionPane.showInputDialog(null, "Enter the Street Address:"),
							JOptionPane.showInputDialog(null, "Enter the Department:"),
							parseConstructorLong(),
							parseConstructorLong(),
							parseConstructorLong(),
							parseConstructorLong()
							);
					++numAdded[0];
				}//if
				else
				{
					JOptionPane.showMessageDialog(null, "Only " + peeps[0].length +" "+ peeps[0][0].getClass().getSimpleName() + "\'s are allowed!");
				}//end if
				break;
			case F:
				if(numAdded[1] < peeps[1].length)
				{
					longs = Cons.valueOf("ZIP");
					peeps[1][numAdded[1]] = new Faculty(//is it okay to use input dialogs directly in the constructor like this, or is it bad practice?
							JOptionPane.showInputDialog(null, "Enter the First Name:"), 
							JOptionPane.showInputDialog(null, "Enter the Last Name:"),
							JOptionPane.showInputDialog(null, "Enter the Street Address:"),
							JOptionPane.showInputDialog(null, "Enter the Department:"),
							parseConstructorLong(),//post-code being written, I realize it probably would have been easier to make these strings
							parseConstructorLong(),
							parseConstructorLong(), 
							parseConstructorLong(),
							Boolean.parseBoolean(JOptionPane.showInputDialog(null, "Enter true if tenured, anything else if not tenured:"))
							);//end Faculty constructor
					++numAdded[1];
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Only " + peeps[1].length +" "+ peeps[1][0].getClass().getSimpleName() + "\'s are allowed!");
				}//end if
				break;
			case S:
				if(numAdded[2] < peeps[2].length)
				{
					longs = Cons.valueOf("ZIP");
					peeps[2][numAdded[2]] = new Student(
							JOptionPane.showInputDialog(null, "Enter the First Name:"), 
							JOptionPane.showInputDialog(null, "Enter the Last Name:"),
							JOptionPane.showInputDialog(null, "Enter the Street Address:"),
							JOptionPane.showInputDialog(null, "Enter the Major:"),
							parseConstructorLong(),
							parseConstructorLong(),
							parseConstructorDouble()
							);//end Student constructor
					++numAdded[2];
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Only " + peeps[2].length +" "+ peeps[2][0].getClass().getSimpleName() + "\'s are allowed!");
				}//end if
				break;
			default:
				keepGoing = false;
				break;
		}//end switch
	}//end enumSwitch
	
	public static long parseConstructorLong()
	{
		Long l;
		String sl;
		switch (longs)
		{
			case ZIP:
				l = 0l;
				sl = "";
				while(l.toString().length() != longs.getVal())
				{//System.out.println(l.toString().length() + " " + longs.getVal());
					try
					{
						sl = JOptionPane.showInputDialog(null, "Enter the Zip Code:");
						l = Long.parseLong(sl);
					}
					catch(IllegalArgumentException zipdang)
					{
						l = 404l;
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}//end try/catch
				}//end while
				longs = Cons.valueOf("PHONE");
			return l;
				
			case PHONE:
				l = 0l;
				sl = "";
				while(l.toString().length() != longs.getVal())
				{
					try
					{
						sl = JOptionPane.showInputDialog(null, "Enter the Phone Number as 10 digits:");
						l = Long.parseLong(sl);
					}
					catch(IllegalArgumentException phonedang)
					{
						l = 404l;
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}//end try/catch
				}//end while
				longs = Cons.valueOf("SOCIAL");
			return l;
				
			case SOCIAL:
				l = 0l;
				sl = "";
				while(l.toString().length() != longs.getVal())
				{
					try//forces numbers
					{
						sl = JOptionPane.showInputDialog(null, "Enter the social as 9 digits:");
						l = Long.parseLong(sl);
					}
					catch(IllegalArgumentException socialdang)
					{
						l = 404l;
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}//end try/catch
				}//end while
				longs = Cons.valueOf("SALARY");
			return l;
				
			case SALARY:
				l = 0l;
				sl = "";
				while(l.toString().length() < longs.getVal()-1 || l.toString().length() > longs.getVal())//forces either a 5- or 6-figure income
				{
					try//forces numbers
					{
						sl = JOptionPane.showInputDialog(null, "Enter the Salary:");
						l = Long.parseLong(sl);
					}
					catch(IllegalArgumentException salarydang)
					{
						l = 404l;
						JOptionPane.showMessageDialog(null, "Invalid input!");
					}//end try/catch
				}//end while
				longs = null;
			return l;
				
			default:
				JOptionPane.showMessageDialog(null, "Whoops! You shouldn't be here...");
				longs = null;
			return -1;
		}//end switch/case
	}//end parseConstructorLong
	
	public static double parseConstructorDouble()
	{
		Double d = -1d;
		while(d.doubleValue() > 5 || d.doubleValue() < 0)
		{
			try
			{
				d = Double.parseDouble(JOptionPane.showInputDialog("Enter the GPA:"));
			}
			catch(IllegalArgumentException doubebad)
			{
				JOptionPane.showMessageDialog(null, "Invalid input!");
			}//end try/catch
		}//end while
		return d.doubleValue();
	}//end parseConstructorDouble
}//end CollegeList
