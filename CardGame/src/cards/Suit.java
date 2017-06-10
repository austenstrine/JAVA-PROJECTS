package cards;
import java.util.Random;

public enum Suit 
{
	HEARTS(0), SPADES(13), CLUBS(26), DIAMONDS(39);
	private int val;
	private static Random random = new Random();
	private Suit(int i)
	{
		this.val = i;
	}
	public static Suit random()
	{
		return values()[random.nextInt(values().length)];	
	}
	public int getSortOrd()
	{
		return val;
	}
}
