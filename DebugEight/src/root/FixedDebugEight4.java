// Allows user to enter a series of words
// and displays them in reverse order
package root;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class FixedDebugEight4
{
   public static void main(String[] args)
   {
      ArrayList<String> array = new ArrayList<String>(1);
      String entry = " ";
      final String STOP = "/']";
      String enter = "Enter a word\nEnter " + STOP + " when you want to stop.";
      //I hear that string concatenation is fairly inefficient, so I thought it wise to only do it once
	  for(int x = 0; !entry.equals(STOP); ++x)
      {
    	  entry = JOptionPane.showInputDialog(null, enter); 
    	  if(!entry.equals(STOP) && x != 100)
    	  {
    		  array.add(entry);
    	  }
    	  else
    	  {
    		  JOptionPane.showMessageDialog(null, "That\'s all! Let's see the results.");
    	  }//end if
      }//end for
      StringBuffer message = new StringBuffer(
    		  "The words in reverse order are:\n"
    		  );
      for(int y = array.size()-1; y >= 0; --y)
      {
         message.append(array.get(y));
         message.append(" ");
      }//end for
      JOptionPane.showMessageDialog(null, message);
   }
}

