/**
 * Create an application that instantiates one Martian and one Jupiterian. 
 * Call the toString() method with each object and display the results. 
 * Save the application as CreateAliens.java.
 */
package notEarthlings2;

public class CreateAliens 
{
	public static void main(String[] args) 
	{
		Jupitian jup = new Jupitian();
		Martian mar = new Martian();
		jup.infoBox(jup.toString());
		mar.infoBox(mar.toString());
	}//end main
}//end CreateAliens
