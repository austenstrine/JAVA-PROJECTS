package ice;
import java.util.Random;

public enum Flavor 
{
	CHOCOLATE, VANILLA, STRAWBERRY, NEAPOLITAN, RAINBOW_SHERBERT, BUTTER_PECAN, MINT_CHOCOLATE_CHIP, CHOCOLATE_CHIP_COOKIE_DOUGH, ROCKY_ROAD, COOKIES_AND_CREAM;
	
	private static Random random = new Random();
	
	public static Flavor random()
	{
		return values()[random.nextInt(values().length)];	
	}
}
