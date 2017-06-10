package twoDice;
import twoDice.Die;
import java.util.Scanner;

public class TwoDice 
{
//variables
	
//constructors
	
//methods
	public static void main(String[] args)
	{
		Die d1 = new Die();
		Die d2 = new Die();
		System.out.println("Press enter/return to throw two dice and get their value! Enter 'end' to stop the program.");
		Scanner input = new Scanner(System.in);
		while(true)
		{
			String enter = input.nextLine();
			if (enter.equals("end"))
			{
				break;
			}
			d1.throwDie();
			d2.throwDie();
			System.out.println("You rolled a " + d1.getValue() + " and a " + d2.getValue() + "!");
		}
		input.close();
	}

}
