package root;

public class Horse {
	
	/*************
	 * Variables *
	 *************/
	
	private String name, color;
	private int birthYear;
	
	/****************
	 * Constructors *
	 ****************/
	public Horse(String n, String c, int by)
	{
		this.name = n;
		this.color = c;
		this.birthYear = by;
	}
	public Horse()
	{
		this("Horse", "Brown", 2017);
	}
	
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setName(String s)
	{
		this.name = s;
	}
	public void setColor(String s)
	{
		this.color = s;
	}
	public void setBirthYear(int i)
	{
		this.birthYear = i;
	}
	
	// Getters
	public String getName()
	{
		return this.name;
	}
	public String getColor()
	{
		return this.color;
	}
	public int getBirthYear()
	{
		return this.birthYear;
	}
	
	// Other
	public void neigh()
	{
		System.out.println("Neigh!");
	}
}
