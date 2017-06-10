// Application prompts user showing valid shipping codes
// accepts a shipping code
// and determines if it is valid
package root;
import javax.swing.JOptionPane;
import java.lang.Character;
public class FixedDebugEight1
{
   public static void main(String args[])
   {
      StringBuffer prompt = new StringBuffer(
    		  "Enter shipping code for this delivery\nValid codes are: "
    		  );
      char[] okayCodes = {'A','C','T','H'};
        for(int x = 0; x < okayCodes.length; ++x)
        {
            prompt.append(okayCodes[x]);
            if(x != (okayCodes.length))
               prompt.append(", ");     
        }
      String entry = JOptionPane.showInputDialog(null,
          prompt); 
      char userCode = Character.toUpperCase(entry.charAt(0));
      boolean found = false;
        for(int i = 0; i < okayCodes.length; ++i)
        {
           if(userCode == okayCodes[i])
           {
              found = true;
           }
        }
        String message;
        if(found)
        {
           message = "Good code: " + userCode;
           if(entry.length() > 1)
           {
        	   message += "\nWarning! Input was more than one character.";
           }
        }
        else
        {
        	message = "Sorry code not found: " + userCode;
            if(entry.length() > 1)
            {
         	   message += "\nWarning! Input was more than one character.";
            }
        }
        JOptionPane.showMessageDialog(null, message);
   }
}