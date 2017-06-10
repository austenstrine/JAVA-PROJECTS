package notEarthlings2;
/*
 * Create two classes—Martian and Jupitian—that descend from Alien. 
 * Supply each with a constructor that sets the Alien data fields with 
 * values you choose. For example, you can decide that a Martian has 
 * four eyes but a Jupitian has only two. Save the files as 
 * Martian.java and Jupitian.java.
 */

public class Martian extends Alien
{
	
	/*************
	 * Variables *
	 *************/
	private Ideal_pH ph;
	
	/****************
	 * Constructors *
	 ****************/
	
	public Martian()
	{
		super("Spherically Symmetric Myriapod", "Opportunistic", false);
		this.ph = Ideal_pH.SEVEN;
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	
}
