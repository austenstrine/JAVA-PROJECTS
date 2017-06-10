/*
 * Create two subclasses called Square and Triangle. Create an 
 * application that demonstrates creating objects of both 
 * subclasses, and store them in an array. Save the files as 
 * GeometricFigure.java, Square.java, Triangle.java, and 
 * UseGeometric.java.
 */
package geo2;

public class Triangle extends GeometricFigure implements SidedObject
{
	
	/*************
	 * Variables *
	 *************/
	private double base;
	
	/****************
	 * Constructors *
	 ****************/
	public Triangle(double h, double w, long s, String type, double b)
	{
		super(h, w, s, type);
		setBase(b);
		calcArea();
	}
	public Triangle()
	{
		this(1d, 1d, 3l, "Triangle", 1d);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setBase(double b)
	{
		this.base = b;
	}
	
	
	// Getters
	public double getBase()
	{
		return this.base;
	}
	
	
	// Other	
	public void calcArea()
	{
		this.setArea((this.getBase()/2)*this.getHeight());
	}
	@Override
	public String toString()
	{
		String s = "Type: "+this.getType() + "\nSides: " + this.getSides() + "\nArea: " + this.getArea() + "\nHeight: " + this.getHeight() + "\nWidth: " + this.getWidth() + "\nBase: " + getBase();
		return s;
	}
	public void displaySides()
	{
		System.out.println(this.getSides());
	}
}
