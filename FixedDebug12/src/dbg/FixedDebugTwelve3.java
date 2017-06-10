// Catch exceptions for array index out of bounds
// or dividing by 0 
package dbg;
import javax.swing.*;
public class FixedDebugTwelve3
{
  public static void main(String[] args) 
  {
    String inStr;
    Integer num = null, result;
    int[] array = {12, 4, 6, 8};
    while(num == null)
    {
    	try
    	{
		    inStr = JOptionPane.showInputDialog(null, "Enter a number ");
		    num = Integer.parseInt(inStr);
    	}
    	catch(IllegalArgumentException e)
    	{
    		num = null;
    		JOptionPane.showMessageDialog(null, "Must be an integer!");
    	}
    }
    try
    {
       for(int x = 0; x < array.length; ++x)
       {
          result =  array[x] / num;
          JOptionPane.showMessageDialog(null, "Result of division is " + result);
          result = array[num];
          JOptionPane.showMessageDialog(null, "Result accessing array is " + result);
       }
    }
    catch(ArithmeticException error)
    {
        JOptionPane.showMessageDialog(null, "Arithmetic error : \"" + error.getMessage()+"\"");   
    }
    catch(IndexOutOfBoundsException error)
    {
       JOptionPane.showMessageDialog(null, "Index error : \"" + error.getMessage() + "\" is out of bounds!");
    }
  }
}