package debugFive;
// Decides if two numbers are evenly divisible
import java.util.*;
public class FixedDebugFive2
{
   public static void main(String args[])
   {
      ArrayList<Integer> num = new ArrayList<Integer>(2);
      Scanner input = new Scanner(System.in);
      System.out.print("Enter a number ");
      int i = input.nextInt();
      num.add(i);
      System.out.print("Enter another number ");
      i = input.nextInt();
      num.add(i);
      if((num.get(0) % num.get(1) == 0) || (num.get(1) % num.get(0) == 0))
         System.out.println("One of these numbers is evenly divisible into the other");
      else
         System.out.println("Neither of these numbers is evenly divisible into the other");
      
      input.close();
   }

}