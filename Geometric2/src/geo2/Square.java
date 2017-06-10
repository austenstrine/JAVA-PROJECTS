/*
 * Create two subclasses called Square and Triangle. Create an 
 * application that demonstrates creating objects of both 
 * subclasses, and store them in an array. Save the files as 
 * GeometricFigure.java, Square.java, Triangle.java, and 
 * UseGeometric.java.
 */
package geo2;

public class Square extends GeometricFigure implements SidedObject
{
	
	/*************
	 * Variables *
	 *************/
	
	/****************
	 * Constructors *
	 ****************/
	public Square(double h, double w, long s, String t)
	{
		super(h,w,s,t);
		calcArea();
	}
	public Square()
	{
		this(1d, 1d, 4l, "Square");
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	@Override
	public void calcArea()
	{
		this.setArea(this.getHeight()*this.getWidth());
	}
	@Override
	public String toString()
	{
		String s = "Type: " + this.getType() + "\nSides: " + this.getSides() +"\nArea: " + this.getArea() + "\nHeight: " + this.getHeight() + "\nWidth: " + this.getWidth();
		return s;
	}
	public void displaySides()
	{
		System.out.println(this.getSides());
	}

}
