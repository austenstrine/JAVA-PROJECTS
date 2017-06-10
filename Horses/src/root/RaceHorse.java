package root;

public class RaceHorse extends Horse 
{
	
	/*************
	 * Variables *
	 *************/
	private int numOfRaces;
	
	/****************
	 * Constructors *
	 ****************/
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setNumOfRaces(int i)
	{
		this.numOfRaces = i;
	}
	
	
	// Getters
	public int getNumOfRaces()
	{
		return this.numOfRaces;
	}
	
	
	// Other
	@Override
	public void neigh()
	{
		System.out.println("NEIGHEIGHEIGH! Ppppbt.");
	}
	
	
}
