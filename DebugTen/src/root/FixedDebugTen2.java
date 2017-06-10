// A Vacation is 10 days
// but an ExtendedVacation is 30 days
package root;
public class FixedDebugTen2
{
   public static void main(String args[]) 
   {
      DebugVacation myVacation = new DebugVacation();
      DebugExtendedVacation yourVacation = new DebugExtendedVacation();
      System.out.println("My vacation is for " +
         myVacation.getDays() + " days");
      System.out.println("Your vacation is for " +
         yourVacation.getDays() + " days");
   }
}

class DebugVacation
{
	/*************
	 * Variables *
	 *************/
	
	private int days = 0;
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugVacation()
	{
		setDays(10);
	}
	
	/***********
	 * Methods *
	 ***********/
	// Setters
	public void setDays(int i)
	{
		this.days = i;
	}
	
	// Getters
	public int getDays()
	{
		return this.days;
	}
}

class DebugExtendedVacation extends DebugVacation
{
	/*************
	 * Variables *
	 *************/
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugExtendedVacation()
	{
		setDays(30);
	}
	
	/***********
	 * Methods *
	 ***********/
}