/*
 * Create two subclasses called Square and Triangle. Create an 
 * application that demonstrates creating objects of both 
 * subclasses, and store them in an array. Save the files as 
 * GeometricFigure.java, Square.java, Triangle.java, and 
 * UseGeometric.java.
 */
package geo2;

public class UseGeometric
{
	
	/*************
	 * Variables *
	 *************/
	
	/****************
	 * Constructors *
	 ****************/
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other

	public static void main(String[] args)
	{
		GeometricFigure[] geo = {new Square(), new Triangle()};
		System.out.println(":"+geo[0].getClass().getSimpleName()+" data:\n"+geo[0].toString());
		System.out.println(":"+geo[1].getClass().getSimpleName()+" data:\n"+geo[1].toString());
		System.out.print(geo.getClass().getSimpleName() + " is the array class.");
	}
}
