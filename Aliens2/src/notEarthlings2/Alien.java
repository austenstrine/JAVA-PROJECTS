package notEarthlings2;
/*
 * Create an Alien class. Include at least three protected data members 
 * of your choice, such as the number of eyes the Alien has. Include a 
 * constructor that requires a value for each data field and a toString() 
 * method that returns a String containing a complete description of the 
 * Alien. Save the file as Alien.java.
 */
import javax.swing.JOptionPane;
import notEarthlings2.*;
public abstract class Alien 
{
	
	/*************
	 * Variables *
	 *************/
	
	//not sure why they have to be protected, but okay
	protected String bodyType, disposition;
	protected Boolean ally;
	
	/****************
	 * Constructors *
	 ****************/
	public Alien(String b, String d, boolean a)
	{
		this.bodyType = b;
		this.disposition = d;
		this.ally = a;
	}
	public Alien()
	{
		this("Body Type", "Disposition", true);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	@Override
	public String toString()
	{
		String s = this.getClass().getSimpleName() + "\nBody Type: "+this.bodyType+ "\nDisposition: "+this.disposition+"\nAlly: "+this.ally;
		return s;
	}
	public void infoBox(String s)
	{
		JOptionPane.showMessageDialog(null, s);
	}

}
