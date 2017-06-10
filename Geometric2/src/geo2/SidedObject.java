/*
 * Modify Exercise 10, adding an interface called SidedObject that 
 * contains a method called displaySides(); this method displays 
 * the number of sides the object possesses. Modify the 
 * GeometricFigure subclasses to include the use of the interface 
 * to display the number of sides of the figure. Create an 
 * application that demonstrates the use of both subclasses.
 */
package geo2;

public interface SidedObject 
{
	public abstract void displaySides();
	//how to make it have sides without manually creating the long in each child?
}
