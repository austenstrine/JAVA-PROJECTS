package Invoice;
import javax.swing.*;

public class Invoice2 
{
//variables
	private int num, month, dMax, day, year;
	private double due;
//constructors
	public Invoice2()
	{
		this.num = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the invoice number"));
		
		if(this.num < 1000)
		{
			this.num = 0;
		}//end if(this.num < 1000)

		
		this.due = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the balance due"));
		this.month = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Month due"));
		
		if(this.month > 12 || this.month < 1)
		{
			this.month = 0;
		}//end if(this.month > 12 || this.month < 1)
		else
		{
			switch (this.month)
			{
				case 4:
				case 6:
				case 9:
				case 11:
					this.dMax = 30;
					break;
				case 2:
					this.dMax = 28;
					break;
				default:
					this.dMax = 31;
					break;
			}//end switch (this.month)
		}//end if
		
		this.day = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Day due"));
		
		if(this.month == 0 || this.day > 31 || this.day < 1)
		{
			this.day = 0;
		}
		else if(this.day > this.dMax)
		{
			this.day = this.dMax;
		}//end if(this.day < 1 || this.day > 31)
		
		this.year = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Year due"));
		
		if(this.year < 2011 || year > 2017)
		{
			this.year = 0;
		}//end if(this.year < 2011 || year > 2017)
	}
//methods
	public void displayAll(String name)
	{
		JOptionPane.showMessageDialog(null, "For " + name + ":\n"
									+"The invoice number is "+ this.num+ " .\n"
									+"The amount due is $" + this.due + " .\n"
									+"The Date due is " + this.month + "/" + this.day + "/" + this.year + " ."
									);
	}
}
