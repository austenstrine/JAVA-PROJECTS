// Sum and average an array of integers
package root;
public class FixedDebugEight2
{
   public static void main(String args[])
   {
      int[] someNums = {4, 17, 22, 8, 35};
      int tot = 0;
      for(int x = 0; x < someNums.length; ++x)
      {  
    	  tot += someNums[x];
      }
      System.out.println("Sum is " + tot);
      System.out.println("Average is " + (tot+0.0)/someNums.length);
   }
/**   
   public static double d(int i)
   {
	   double d = i;
	   return d;
   }
   
   */
}
