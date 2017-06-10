package twoDice;
import java.util.*;
import twoDice.Die;
import loopMethods.Boolet;

public class FiveDice 
{
//variables
	private static Die die = new Die();
	private static ArrayList<ArrayList<Integer>> dice = new ArrayList<ArrayList<Integer>>(3);
	private static int numOfPlayers = 2;//this is here to easily make the number of players adjustable.
	private static ArrayList<String> name = new ArrayList<String>(numOfPlayers);//same reason for this
//constructors
	
//methods
	public static void main(String[] args)
	{
		Boolet mainIt = new Boolet(6);
		Scanner keyboard = new Scanner(System.in);
		while(true)
		{	
			mainIt.fire();
			if(!mainIt.checkShells(1))
			{
				System.out.println("");
			}
			
			if(mainIt.checkShells(1))
			{
				System.out.println("What is your name?");
				String n = keyboard.nextLine();
				name.add(0, n);
				n = "The Computer";
				name.add(1, n);
				System.out.println("Welcome "+name.get(0)+"! Throw five dice, and compare them to "+name.get(1)+"'s five dice!\n"
						+"The rules are, whoever has the most recurring number wins! \n"
						+"If you both have equal amounts of recurrance, whoever has higer/more recurrances wins!");
			}
			System.out.println("Hit enter to start, or type 'end' to exit:");
			String a = keyboard.nextLine();
			
			if (a.equals("end"))
			{
				System.out.println("Okay, your final score was "+dice.get(numOfPlayers).get(0)+". Bye!");
				break;
			}
			Boolet pNum = new Boolet(numOfPlayers);
			Boolet pNum1 = new Boolet(numOfPlayers);
			while(pNum.fire())
			{
				while(pNum.checkShells(1) && !mainIt.checkShells(1) && pNum1.fire())
				{	/*Resets the die values at 0 and 1.
					 * Only runs after the first iteration of the game.
					 */
					dice.remove(0);
				}
				rollFiveTimes(0);
			}//end for
				/* dice.at(0) and (1) are now ArrayLists of five integers. 
				 * Replaces any previously existing elements
				 */
			System.out.println("Game " + mainIt.getClicks() + ":\n"
								+name.get(0)+" rolled "+ dice.get(0).get(0) +", "+ dice.get(0).get(1) 
								+", "+ dice.get(0).get(2) +", "+ dice.get(0).get(3) 
								+", and "+ dice.get(0).get(4) +". "+name.get(1)+" rolled "+ dice.get(1).get(0) 
								+", "+ dice.get(1).get(1) +", "+ dice.get(1).get(2) 
								+", "+ dice.get(1).get(3) +", and "+ dice.get(1).get(4) +".");
			
			String ans = " ";
			while(!ans.equals("a") && !ans.equals("b"))
			{
				System.out.println("Who won?\n"
							  	  +"     (a) "+name.get(0)+"!\n"
							  	  +"     (b) "+name.get(1)+"...");
				ans = keyboard.nextLine();
			}//end while
			
			if(mainIt.checkShells(1))
			{
				ArrayList<Integer> al = new ArrayList<Integer>(numOfPlayers);
				pNum.arm();
				while(pNum.fire())
				{
					al.add(pNum.getShells()-1, 0);
				}//end while
				dice.add(numOfPlayers, al);
			}//end if
			
			if(ans.equals("a"))
			{
				dice.get(numOfPlayers).set(0, dice.get(numOfPlayers).get(0)+1);
			}
			else
			{
				dice.get(numOfPlayers).set(1, dice.get(numOfPlayers).get(1)+1);
			}//end if
			
			System.out.println(	 "Great! That means "+ name.get(0) +"'s won "
								+ dice.get(numOfPlayers).get(0)+" times, and "+ name.get(1) +"'s won "
								+ dice.get(numOfPlayers).get(1)+" times!");
		}//end for
		
		keyboard.close();
	}
	
	public static void rollFiveTimes(int ss)
	{
		ArrayList<Integer> al = new ArrayList<Integer>(5);
		Boolet b = new Boolet(5);
		while(b.fire())
		{
			die.throwDie();
			al.add(b.getShells()-1, die.getValue());
		}
		Collections.sort(al);
		dice.add(ss, al);
	}
}
