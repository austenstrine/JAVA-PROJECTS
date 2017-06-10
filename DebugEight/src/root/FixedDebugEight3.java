// Application looks up home price
// for different floor plans
// allows upper or lowercase data entry
package root;
import javax.swing.JOptionPane;
import java.lang.Character;
public class FixedDebugEight3
{
   public static void main(String[] args)
   { 
      String entry;
      char[] floorPlans = {'A','B','C'};
      int[] pricesInThousands = {145, 190, 235};
      char plan = ' ';
      int fp = 99;
      
      String prompt = "Please select a floor plan\n" 
        + "Our floorPlanss are:\n" 
    	+ "A - Augusta, a ranch\n" 
        + "B - Brittany, a split level\n" 
        + "C - Colonial, a two-story\n";
      
      while(codeInvalid(plan, floorPlans))
	  {
	      entry = JOptionPane.showInputDialog(null, prompt);
	      plan = Character.toUpperCase(entry.charAt(0));
	      for(int x = 0; x < floorPlans.length; ++x)
		  {
	    	  if(plan == floorPlans[x])
	    	  {
	    		  fp = x; 
	    	  }//end if
		  }//end for
		  if(fp == 99)
		  {
			  JOptionPane.showMessageDialog(null,
					  "Invalid floor plan code \"" + plan + "\" entered"
					  );
		  }
	      else
	      {
	    	  /*
		        if(fp > floorPlans.length)
		           fp = fp - floorPlans.length;
		           Not really sure why this is here - if it's not 99 it's 
		           going to be a valid subscript. It's accounting for an
		           error that isn't possible.
	           */
		        JOptionPane.showMessageDialog(null, "Model " +
		           plan + " is priced at only $" +
		           pricesInThousands[fp] + ",000");
	      }//end if
	  }//end while
   }//end main
   
   public static boolean codeInvalid(char plan, char[] floorPlans)
   {
	   boolean b = true;
	   for(int i = 0; i < floorPlans.length; i++)
	   {
		   if(plan == floorPlans[i])
		   {
			   b = false;
		   }
	   }
	   return b;
   }
}

