package ice;
import javax.swing.JOptionPane;

public class IceCreamCone 
{
	private int scoops;
	private Flavor flavor;
	
	public IceCreamCone(int scps, String flave)
	{
		setScoops(scps);
		setFlavor(flave);
	}
	public IceCreamCone()
	{
		this(1, "VANILLA");
	}
	
	public void setScoops(int scps)
	{
		try
		{
			this.scoops = scps;
			if(scps > 3)
			{
				throw new IceCreamConeException(this.scoops);
			}
		}
		catch(IceCreamConeException icce)
		{
			JOptionPane.showMessageDialog(null, icce.getMessage() 
			+ "\nDefaulting to 3 scoops.");
			this.scoops = 3;
		}
	}	
	public void setScoops(String scps)
	{
		try
		{
			this.scoops = Integer.parseInt(scps);
			if(this.scoops > 3 || this.scoops < 1)
			{
				throw new IceCreamConeException(this.scoops);
			}
		}
		catch(IceCreamConeException icce)
		{
			JOptionPane.showMessageDialog(null, icce.getMessage() 
			+ "\nDefaulting to 3 scoops.");
			this.scoops = 3;
		}
		catch(IllegalArgumentException illin)
		{
			JOptionPane.showMessageDialog(null, illin.getMessage() 
					+ "\nInput must be an integer! Defaulting to 3 scoops.");
			this.scoops = 3;
		}
	}
	public void setFlavor(String flave)
	{
		flave = flave.replace(' ', '_');//allows spaces
		flave = flave.replace('-', '_');//allows dashes
		flave = flave.replaceAll("&", "AND");//allows ampersands
		flave = flave.toUpperCase();
		try
		{
			this.flavor = Flavor.valueOf(flave);
		}
		catch(IllegalArgumentException ill)
		{
			try
			/* 
			 * The only reason I do this is to conform to the directions.
			 * I would prefer to have it just deal with the 
			 * IllegalArgumentException directly, and I didn't want to
			 * make flavors a list of strings just to conform to the 
			 * directions. Using an enum makes more sense than using 
			 * strings.
			 */
			{
				throw new IceCreamConeException(ill.getMessage() 
						+ "\n" + flave);
			}
			catch(IceCreamConeException icce)
			{
				JOptionPane.showMessageDialog(null, icce.getMessage() 
				+ "\nDefaulting to CHOCOLATE.");
				this.flavor = Flavor.valueOf("CHOCOLATE");
			}
		}
		
	}
	
	public int getScoops()
	{
		return this.scoops;
	}
	public String getFlavor()
	{
		return this.flavor.toString();
	}
}
