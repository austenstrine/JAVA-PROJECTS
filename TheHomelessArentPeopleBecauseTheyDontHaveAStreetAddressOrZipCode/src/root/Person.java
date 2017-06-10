package root;
import javax.swing.JOptionPane;
public class Person 
{
	
	/*************
	 * Variables *
	 *************/
	private String nameFirst, nameLast, addressStreet;
	private long zip,numberPhone;
	/****************
	 * Constructors *
	 ****************/
	public Person(String f, String l, String a, long z, long n)
	{
		this.nameFirst = f;
		this.nameLast = l;
		this.addressStreet = a;
		this.zip = z;
		this.numberPhone = n;
	}
	public Person()
	{
		this("First", "Last", "Address", 00000, 1231231234);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setFN(String s)
	{
		this.nameFirst = s;
	}
	public void setLN(String s)
	{
		this.nameLast = s;
	}
	public void setAddress(String s)
	{
		this.addressStreet = s;
	}
	public void setZip(long i)
	{
		this.zip = i;
	}
	public void setPhone(long l)
	{
		this.numberPhone = l;
	}
	
	
	// Getters
	
	
	// Other
	@Override
	public String toString()
	{
		String s = "";
		String comma = "\n";
		s += "First Name: " + this.nameFirst + comma + "Last Name: "+ this.nameLast + comma + "Street Address: " + this.addressStreet + comma + "Zip Code: " + this.zip + comma + "Phone Number: " + this.numberPhone;
		return s;
	}
	
	public void display()
	{
		JOptionPane.showMessageDialog(null, "----" + this.getClass().getSimpleName()+ "----\n" + this.toString());
	}
}
