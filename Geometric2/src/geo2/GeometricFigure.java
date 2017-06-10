/*
 * Create an abstract class called GeometricFigure. Each figure 
 * includes a height, a width, a figure type, and an area. Include 
 * an abstract method to determine the area of the figure. 
 */
package geo2;

public abstract class GeometricFigure 
{
	
	/*************
	 * Variables *
	 *************/
	private double height, width, area;
	private long sides;
	private String type;
	
	/****************
	 * Constructors *
	 ****************/
	public GeometricFigure(double h, double w, long s, String t)
	{
		this.setHeight(h);
		this.setWidth(w);
		this.setSides(s);
		this.setType(t);
	}
	public GeometricFigure()
	{
		this(1d, 1d, 1l, "Type");
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setHeight(double h)
	{
		this.height = h;
	}
	public void setWidth(double w)
	{
		this.width = w;
	}
	public void setArea(double a)
	{
		this.area = a;
	}
	public void setSides(long s)
	{
		this.sides = s;
	}
	public void setType(String t)
	{
		this.type = t;
	}
	
	
	// Getters
	public double getHeight()
	{
		return this.height;
	}
	public double getWidth()
	{
		return this.width;
	}
	public long getSides()
	{
		return this.sides;
	}
	public double getArea()
	{
		return this.area;
	}
	public String getType()
	{
		return this.type;
	}
	
	
	// Other
	public abstract void calcArea();
	
}
