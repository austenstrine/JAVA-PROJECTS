package sqrt;
import java.lang.Math;
import javax.swing.JOptionPane;

public class SqrtException {

	public static void main(String[] args) 
	{
		String uInputS = "";
		double srt = 0.0;
		boolean invalid = true;
		while(true)
		{
			invalid = false;
			uInputS = JOptionPane.showInputDialog(null, "Enter a number to get the square root of, or END to end: ");
			if(uInputS.toUpperCase().equals("END"))
			{
				break;
			}
			try
			{
				srt = Double.parseDouble(uInputS);
				if(srt < 0.0)
				{
					throw new ArithmeticException("The square root of a negative number is NaN.");
				}
				srt = Math.sqrt(srt);
			}
			catch(IllegalArgumentException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid input! Must be a number: "+e.getMessage());
				invalid = true;
			}
			catch(ArithmeticException e)
			{
				JOptionPane.showMessageDialog(null, "Invalid Arithmetic: " + e.getMessage());
				invalid = true;
			}
			if(!invalid)
			{
				JOptionPane.showMessageDialog(null, "The square root of "+uInputS+ " is approximately \"" + srt + "\".");
			}
		}

	}

}
