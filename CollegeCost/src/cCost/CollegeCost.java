package cCost;
import javax.swing.*;


public class CollegeCost 
{
	//variables
	final static int AGE_LIMIT = 18;
	
	//methods
	public static void main(String[] args)
	{
		int howYoung = -1;
		while(howYoung > AGE_LIMIT || howYoung < 1)
			{
				String howYoungS = JOptionPane.showInputDialog(null, "How many years old is your child?(1-18)");
				howYoung = Integer.parseInt(howYoungS);
			}
		int howExpensive = -1;
		while(howExpensive < 0 || howExpensive > 1000000)
		{
			String howExpensiveS = JOptionPane.showInputDialog(null, "How expensive is the school, in USD?(1-1000000)");
			howExpensive = Integer.parseInt(howExpensiveS);
		}
		int savings = calcYearlySavings(howYoung, howExpensive);
		JOptionPane.showMessageDialog(null, "Okay, your child is " + howYoung 
				+ " years old. Their 4-year tuition total will be $" + howExpensive
				+ ". That means you have to save $" + savings 
				+ " a year until they are 19 to pay for their college education.");
		
	}
	
	public static int calcYearlySavings (int age, int cost)
	{
		int years = AGE_LIMIT-(age-1);
		int savings = (int)(cost/years+.5);
		return savings;
	}
	
}
