package MathTest;
import java.lang.Math;

public class MathTest 
{
	public static void main(String[] args)
	{
		System.out.println("The square root of 37 is " + Math.sqrt(37) 
		+ "\nThe sine of 300 is " + Math.sin(300) + " and the cosine of 300 is " + Math.cos(300)
		+ "\nThe Floor, Ceiling, and Round of 22.8 are " +Math.floor(22.8)+ ", " +Math.ceil(22.8)+ ", and " +Math.round(22.8)+ ", respectively."
		+ "\nThis is a random number between 0 and 20:    " + (Math.round(Math.random()*20)));
	}
}
