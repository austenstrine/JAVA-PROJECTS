package root;

public class WorkHorse extends Horse
{
	
	/*************
	 * Variables *
	 *************/
	private int yearsWorking;
	/****************
	 * Constructors *
	 ****************/
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setYearsWorking(int i)
	{
		this.yearsWorking = i;
	}
	
	// Getters
	public int getYearsWorking()
	{
		return this.yearsWorking;
	}
	
	// Other
	@Override
	public void neigh()
	{
		System.out.println("Mrhrhrm. Ppppbt.");
	}
}
