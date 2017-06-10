package ice;
import javax.swing.JOptionPane;
public class ThrowIceCream 
{

	public static void main(String[] args) 
	{
		IceCreamCone cone = new IceCreamCone();
		String sVal = "";
		int goodVal = 0;
		while(sVal != null)
		{
			sVal = JOptionPane.showInputDialog("Choose how many scoops you would like (up to 3):");
			System.out.println("goodVal == " + goodVal + ", sVal == " + sVal);
			if(sVal == null)
			{
				break;
			}
			cone.setScoops(sVal);
			goodVal = JOptionPane.showConfirmDialog(null, "The number of scoops is currently " + cone.getScoops() + ". Is this okay?", "Confirmation", 0);
			System.out.println("goodVal == " + goodVal + ", sVal == " + sVal);
			if(goodVal != 1)
			{
				if(goodVal == -1)//-1 is cancel or exit
				{
					sVal = null;//ensures nothing runs after this. I wish there was some kind of return 0; like in C++, that doesn't utilize a runtime error.
				}
				break;
			}
		}
		while(sVal != null)
		{
			sVal = JOptionPane.showInputDialog("Choose what flavor of ice cream would you like:\n" + java.util.Arrays.asList(Flavor.values()));
			System.out.println("goodVal == " + goodVal + ", sVal == " + sVal);
			if(sVal == null)
			{
				break;
			}
			cone.setFlavor(sVal);
			goodVal = JOptionPane.showConfirmDialog(null, "The flavor is currently " + cone.getFlavor() + ". Is this okay?", "Confirmation", 0);
			System.out.println("goodVal == " + goodVal + ", sVal == " + sVal);
			if(goodVal == 0)
			{
				break;
			}
		}
		if(sVal != null)
		{
			JOptionPane.showMessageDialog(null, "You now have an ice cream cone with " + cone.getScoops() + " scoop(s) of " + cone.getFlavor().toString() + " ice cream. Enjoy!");
		}
	}

}
