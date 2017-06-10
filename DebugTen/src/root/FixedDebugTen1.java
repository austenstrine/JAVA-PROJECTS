// A Play has a title and author
// A Musical is a Play that also has a composer
package root;
import javax.swing.JOptionPane;

public class FixedDebugTen1
{
   public static void main(String args[])
   {
      DebugPlay performance1 = new DebugPlay("Death of a Salesman", "Arthur Miller");
      DebugMusical performance2 = new DebugMusical("Guys and Dolls", "Jo Swerling", "Frank Loesser");
      performance1.display();
      performance2.display();
   }//e main
}//e debug

class DebugPlay
{
	/*************
	 * Variables *
	 *************/
	
	private String title, author;
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugPlay(String t, String a)
	{
		setTitle(t);
		setAuthor(a);
	}
	public DebugPlay()
	{
		this("Title", "Author");
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Getters
	public String getTitle()
	{
		return this.title;
	}
	public String getAuthor()
	{
		return this.author;
	}
	
	// Setters
	public void setTitle(String s)
	{
		this.title = s;
	}
	public void setAuthor(String s)
	{
		this.author = s;
	}
	
	// Other
	public void display()
	{
		String s = "";
		s += "    Title: " + getTitle() + "\n    Author: " + getAuthor();
		JOptionPane.showMessageDialog(null, s);
	}
}//e play

class DebugMusical extends DebugPlay
{
	/*************
	 * Variables *
	 *************/
	
	private String composer;
	
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugMusical(String t, String a, String c)
	{
		super(t, a);
		setComposer(c);
	}
	public DebugMusical()
	{
		this("Title", "Author", "Composer");
	}

	
	/***********
	 * Methods *
	 ***********/
	
	// Getters
	public String getComposer()
	{
		return this.composer;
	}
	
	// Setters
	public void setComposer(String s)
	{
		this.composer = s;
	}
	
	// Other
	@Override
	public void display()
	{
		String s = "";
		s += "    Title: " + getTitle() + "\n    Author: " + getAuthor() + "\n    Composer: " + getComposer();
		JOptionPane.showMessageDialog(null, s);
	}
}//e musical