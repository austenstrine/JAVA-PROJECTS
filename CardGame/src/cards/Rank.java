package cards;
import java.util.Random;

public enum Rank 
{
	ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	
	private static Random random = new Random();
	
	public static Rank random()
	{
		return values()[random.nextInt(values().length)];	
	}
}
