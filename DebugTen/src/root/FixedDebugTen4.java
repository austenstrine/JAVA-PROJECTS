// A customer's purchase is approved if the
// purchase does not exceed the credit limit
package root;
import javax.swing.JOptionPane;
public class FixedDebugTen4
{
   public static void main(String args[])
   {
      int id = 12241;
      String name = "Franklin";
      double credit = 1000.00;
      double purchase = 1325.77;
      DebugCustomerMakingPurchase cust = new DebugCustomerMakingPurchase(name, credit, id, purchase);
      cust.display();
   }
}
class DebugCustomer
{
	
	/*************
	 * Variables *
	 *************/
	
	private String name;
	private double credit;
	private int id;
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugCustomer(String n, double c, int i)
	{
		setAll(n,c,i);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setAll(String n, double c, int i)
	{
		this.name = n; this.credit = c; this.id = i;
	}
	
	// Getters
	public double getCredit()
	{
		return this.credit;
	}
	public String getName()
	{
		return this.name;
	}
	public int getId()
	{
		return this.id;
	}
	// Other

}
class DebugCustomerMakingPurchase extends DebugCustomer
{
	
	/*************
	 * Variables *
	 *************/
	
	private double purchase;
	
	/****************
	 * Constructors *
	 ****************/
	
	public DebugCustomerMakingPurchase(String n, double c, int i, double p)
	{
		super(n,c,i);
		setPurchase(p);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	public void setPurchase(double p)
	{
		this.purchase = p;
	}
	// Getters
	
	// Other
	public void display()
	{
		String a = "";
		if(getCredit() >= this.purchase)
		{
			a = "Transaction for "+getName()+", ID#"+getId()+" has been accepted. Your remaining credit is $" + (getCredit() - this.purchase) + ".";
		}
		else
		{
			a = "Transaction for "+getName()+", ID#"+getId()+" has been rejected: Insufficient Credit.\nCredit available is $" + getCredit() +"!";
		}
		
		JOptionPane.showMessageDialog(null, a);
	}
}