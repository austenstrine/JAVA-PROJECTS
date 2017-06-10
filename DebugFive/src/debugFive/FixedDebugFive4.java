package debugFive;
// DebugFive4.java
// Outputs highest of four numbers
import javax.swing.*;
//import java.util.*;
public class FixedDebugFive4
{
   public static void main (String args[]) 
   {
      int one, two, three, four;
      String str, output;
      str = enterIntegerDialog();
      one = Integer.parseInt(str);
      str = enterIntegerDialog();
      two = Integer.parseInt(str);
      str = enterIntegerDialog();
      three = Integer.parseInt(str);
      str = enterIntegerDialog();
      four = Integer.parseInt(str);
      if(one > two && one > three && one > four)
    	  output = "Highest is " + one;
      else if(two > one && two > three && two > four)
    	  output = "Highest is " + two;
      else if(three > one && three > two && three > four)
    	  output = "Highest is " + three;
      else
    	  output = "Highest is " + four;
      JOptionPane.showMessageDialog(null, output);
      
      /*would be easier to do 
       * ArrayList<Integer> ints = new ArrayList<ints>(4);
       * for(int i = 0; i < 4; ++i)
       * {ints.add(0);}
       * str = JOptionPane.showInputDialog(null,"Enter an integer");
       * ints.set(0, Integer.parseInt(str));
       * str = JOptionPane.showInputDialog(null,"Enter an integer");
       * ints.set(1, Integer.parseInt(str));
       * str = JOptionPane.showInputDialog(null,"Enter an integer");
       * ints.set(2, Integer.parseInt(str));
       * str = JOptionPane.showInputDialog(null,"Enter an integer");
       * ints.set(3, Integer.parseInt(str));
       * Collections.sort(ints);
       * output = "Highest is "+(String)ints.get(3);
       * JOptionPane.showMessageDialog(null, output);
       */
   }
   public static String enterIntegerDialog()
   {
	   return JOptionPane.showInputDialog(null,"Enter an integer:");
   }
}


