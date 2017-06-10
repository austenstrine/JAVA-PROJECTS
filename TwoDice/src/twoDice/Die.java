package twoDice;
import java.lang.Math;

public class Die 
{
//variables
	final private int HI_VAL = 6;
	final private int LO_VAL = 1;
	private int rVal;
//constructors
	public Die()
	{
		this.throwDie();
	}
	
//methods
	public void throwDie()
	{
		this.rVal = ((int)(Math.random()*1000) % HI_VAL + LO_VAL);
	}
	
	public int getValue()
	{
		return this.rVal;
	}
}
