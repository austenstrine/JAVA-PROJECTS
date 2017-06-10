package notEarthlings2;
/*
 * Create two classes—Martian and Jupitian—that descend from Alien. 
 * Supply each with a constructor that sets the Alien data fields with 
 * values you choose. For example, you can decide that a Martian has 
 * four eyes but a Jupitian has only two. Save the files as 
 * Martian.java and Jupitian.java.
 */

public class Jupitian extends Alien
{
	
	/*************
	 * Variables *
	 *************/
	private Color color;
	
	/****************
	 * Constructors *
	 ****************/
	
	public Jupitian()
	{
		super("Radially Symmetric Hexapod", "Passive, Paternal", true);
		this.color = Color.ORANGE;
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other

}
