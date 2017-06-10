package Invoice;
import javax.swing.*;

public class Invoice 
{
//variables
	private int num, month, day, year;
	private double due;
//constructors
	public Invoice()
	{
		this.num = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the invoice number"));
		if(this.num < 1000)
			this.num = 0;
		this.due = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the balance due"));
		this.month = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Month due"));
		if(this.month > 12 || this.month < 1)
			this.month = 0;
		this.day = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Day due"));
		if(this.day < 1 || this.day > 31)
			this.day = 0;
		this.year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Year due"));
		if(this.year < 2011 || year > 2017)
			this.year=0;
	}
//methods
	public void displayAll()
	{
		JOptionPane.showMessageDialog(null, ""
									+"The invoice number is "+ this.num+ ".\n"
									+"The amount due is " + this.due + ".\n"
									+"The Date due is" + this.month + "/" + this.day + "/" + this.year + "."
									);
	}
}
