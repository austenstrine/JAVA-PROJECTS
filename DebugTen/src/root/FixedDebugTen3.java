// A PhoneBook is a Book with a city
package root;
import javax.swing.JOptionPane;
public class FixedDebugTen3
{
   public static void main(String[] args)
   {
      String city, inStr;
      int pages;
      city = JOptionPane.showInputDialog(null, 
         "Enter city for phone book");
      inStr = JOptionPane.showInputDialog(null, 
         "Enter number of pages in " + city + "'s phone book");
      pages = Integer.parseInt(inStr);
      DebugPhoneBook pb = new DebugPhoneBook(pages,city);
      pb.display();
   }
}//e debugten3

class Book
{
	/*************
	 * Variables *
	 *************/
	
	private String title, author, genera;
	private static final String COMMA = ", ";
	private int pages;
	
	/****************
	 * Constructors *
	 ****************/
	
	public Book(String t, String a, String g, int p)
	{
		setAll(t,a,g,p);
	}
	public Book()
	{
		this("Title","Author","Genera",0);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setAll(String t, String a, String g, int p)
	{
		this.title = t;
		this.author = a;
		this.genera = g;
		this.pages = p;
	}
	// Getters
	public String getTitle()
	{
		return this.title;
	}
	public String getAuthor()
	{
		return this.author;
	}
	public String getGenera()
	{
		return this.genera;
	}
	public int getPages()
	{
		return this.pages;
	}
	public static String getComma()
	{
		return COMMA;
	}
	public void display()
	{
		String s = "";
		s += getTitle() + getComma() + getAuthor() + getComma() + getGenera() + getComma() + getPages() + "pp";
		JOptionPane.showMessageDialog(null, s);
	}
}//e book

class DebugPhoneBook extends Book
{
	
	/*************
	 * Variables *
	 *************/
	
	private String city;
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugPhoneBook(int p, String c)
	{
		super(c+" Phone Book",c+" Authorities","Non-Fiction",p);
		setArea(c);
	}
	public DebugPhoneBook()
	{
		this(0,"city");
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setArea(String c)
	{
		this.city = c;
	}
	// Getters
	public String getArea()
	{
		return this.city;
	}
	@Override
	public void display()
	{
		String s = "";
		s += getTitle() + getComma() + getAuthor() + getComma() + getGenera() + getComma() + getPages() +"pp"+ getComma() + getArea();
		JOptionPane.showMessageDialog(null, s);
	}
	// Other
}//e debugphonebook