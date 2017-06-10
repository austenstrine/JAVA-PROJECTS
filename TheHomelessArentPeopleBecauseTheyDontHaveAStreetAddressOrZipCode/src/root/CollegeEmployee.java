package root;

public class CollegeEmployee extends Person 
{
	
	/*************
	 * Variables *
	 *************/
	private long socialSecurity, salary;
	private String dept;
	
	
	/****************
	 * Constructors *
	 ****************/
	public CollegeEmployee(String f, String l, String a, String d, long z, long n, long soc, long sal)
	{
		super(f, l, a, z, n);
		this.socialSecurity = soc;
		this.salary = sal;
		this.dept = d;
	}
	public CollegeEmployee()
	{
		this("First", "Last", "Address", "Department", 00000, 1231231234, 123121234, 00000);
		
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setSocialSec(long i)
	{
		this.socialSecurity = i;
	}
	public void setSalary(long i)
	{
		this.salary = i;
	}
	public void setDepartment(String s)
	{
		this.dept = s;
	}
	
	// Getters
	
	
	// Other
	@Override
	public String toString()
	{
		String comma = "\n";
		String s = "";
		s = super.toString() + comma + "Social Security: " + this.socialSecurity + comma + "Salary: " + this.salary + comma + "Department: " + this.dept;
		return s;
	}
	
}
