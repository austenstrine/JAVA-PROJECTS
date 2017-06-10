package ice;

public class IceCreamConeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7350012258474499505L;

	public IceCreamConeException(Flavor flavor, int scoops) 
	{
		super(scoops + " " + flavor);
	}
	public IceCreamConeException(String flavor) 
	{
		super(flavor + " is not a valid flavor!");
	}
	public IceCreamConeException(int scoops) 
	{
		super(scoops + " is not a valid number of scoops! Must be 1-3!");
	}

}
