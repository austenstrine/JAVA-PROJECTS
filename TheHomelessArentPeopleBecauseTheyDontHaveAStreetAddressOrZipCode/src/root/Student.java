package root;

public class Student extends Person 
{
	
	/*************
	 * Variables *
	 *************/
	private String major;
	private double gpa;
	
	/****************
	 * Constructors *
	 ****************/
	public Student(String f, String l, String a, String m, long z, long n, double avg)
	{
		super(f,l,a,z,n);
		this.major = m;
		this.gpa = avg;
	}
	public Student()
	{
		this("First", "Last", "Address", "Major", 00000, 1231231234, 2.0d);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setMajor(String s)
	{
		this.major = s;
	}
	public void setGPA(double d)
	{
		this.gpa = d;
	}
	
	// Getters
	
	
	// Other
	
	@Override
	public String toString()
	{
		String s = "";
		s = super.toString() + "\nMajor: " + this.major + "\nGPA: " + this.gpa;
		return s;
	}
}
