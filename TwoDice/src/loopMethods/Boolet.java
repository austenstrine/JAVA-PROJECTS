package loopMethods;
/**
 * 
 * Purpose is to have a boolean that will "fire" true 
 * a certain number of times, and no more, until it is
 * 'armed again'.
 *
 */

public class Boolet
{
//variables
	private int bullets = 0, shells = 0, lastArm = 1;
	private long clicks = 0;
//constructors

	public Boolet(int i)
	{
		this.shells = 0;
		this.bullets = i;
		this.lastArm = i;
	}
	
	public Boolet(String s)
	{
		this(Integer.parseInt(s));
	}
	
	public Boolet(float f)
	{
		this((int)f);
	}
	
	public Boolet(boolean b)
	{
		this(1);
	}
	
	public Boolet()
	{
		this(1);
	}
	
//methods
	public void arm()
	{
		this.shells = 0;
		this.bullets = this.lastArm;
	}
	public void arm(int i)
	{
		this.shells = 0;
		this.bullets = i;
		this.lastArm = i;
	}
	
	
	public boolean fire()
	{
		if (this.bullets == 0)
		{
			this.clicks += 1;
			return false;
		}
		else
		{
			this.clicks += 1;
			this.bullets -= 1;
			this.shells += 1;
			return true;
		}
	}
	
	
	public long getClicks()
	{
		return this.clicks;
	}
	public void resetClicks()
	{
		this.clicks = 0;
	}
	
	
	public int getLastArm()
	{
		return this.lastArm;
	}
	
	
	public int getBullets()
	{	/**
		 * getBullets();
		 * Returns the number of bullets as an int.
		 */
		return this.bullets;
	}
	
	
	public boolean checkBullets(int i)
	{	/**
		 * checkBullets(int i);
		 * Compares the number of bullets with i;
		 * Returns true if equal, false if not.
		 */
		boolean equal = false;
		if (this.bullets == i)
		{
			equal = true;
		}
		return equal;
	}
	/*public boolean checkBullets(double i)
	{	/**
		 * checkBullets(double i);
		 * Compares the number of bullets with (int)i;
		 * Returns true if equal, false if not.
		 * Displays any lost data from the conversion to
		 * int in the console.
		 *-/
		System.out.println("Value " + i + " will be interpreted as " + (int)i + "!");
		boolean equal = false;
		if (this.bullets == (int)i)
		{
			equal = true;
		}
		return equal;
	}
	public boolean checkBullets(float i)
	{	/**
		 * checkBullets(float i);
		 * Compares the number of bullets with (int)i;
		 * Returns true if equal, false if not.
		 * Displays any lost data from the conversion to
		 * int in the console.
		 *-/
		System.out.println("Value " + i + " will be interpreted as " + (int)i + "!");
		boolean equal = false;
		if (this.bullets == (int)i)
		{
			equal = true;
		}
		return equal;
	}*/
	
	
	public int getShells()
	{	/**
		 * getShells();
		 * Returns the number of shells as an int.
		 */
		return this.shells;
	}
	
	
	public boolean checkShells(int i)
	{	/**
		 * checkShells(int i);
		 * Compares the number of shells with i;
		 * Returns true if equal, false if not.
		 */
		boolean equal = false;
		if (this.shells == i)
		{
			equal = true;
		}
		return equal;
	}
	/*public boolean checkShells(double i)
	{	/**
		 * checkShells(double i);
		 * Compares the number of shells with (int)i;
		 * Returns true if equal, false if not.
		 * Displays any lost data from the conversion to
		 * int in the console.
		 *-/
		System.out.println("Value " + i + " will be interpreted as " + (int)i + "!");
		boolean equal = false;
		if (this.shells == (int)i)
		{
			equal = true;
		}
		return equal;
	}
	public boolean checkShells(float i)
	{	/**
		 * checkShells(float i);
		 * Compares the number of shells with (int)i;
		 * Returns true if equal, false if not.
		 * Displays any lost data from the conversion to
		 * int in the console.
		 *-/
		System.out.println("Value " + i + " will be interpreted as " + (int)i + "!");
		boolean equal = false;
		if (this.shells == (int)i)
		{
			equal = true;
		}
		return equal;
	}*/
}
