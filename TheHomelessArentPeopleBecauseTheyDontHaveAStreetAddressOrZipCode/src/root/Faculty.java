package root;

public class Faculty extends CollegeEmployee {
	
	/*************
	 * Variables *
	 *************/
	private boolean tenured;
	/****************
	 * Constructors *
	 ****************/
	public Faculty(String f, String l, String a, String d, long z, long n, long soc, long sal, boolean t)
	{
		super(f,l,a,d,z,n,soc,sal);
		this.tenured = t;
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setTenured(boolean b)
	{
		this.tenured = b;
	}
	
	// Getters
	
	
	// Other
	
	@Override
	public String toString()
	{
		String s = "";
		s = super.toString() + "\nTenured: " + tenured;
		return s;
	}

}
