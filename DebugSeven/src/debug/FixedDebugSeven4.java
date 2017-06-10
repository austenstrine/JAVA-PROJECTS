// Converts a string to lowercase, and 
// displays the string's length
// as well as a count of letters
package debug;
public class FixedDebugSeven4
{
   public static void main(String[] args)
   {
      String aString = "HELP!! I need to get 36 things DONE today!!";
      int numLetters = 0;
      int stringLength = aString.length();
      System.out.println("In all lowercase, the sentence is: ");
      for(int i = 0; i < stringLength; ++i)
      {
         char ch = Character.toLowerCase(aString.charAt(i));
         if(ch >= 'a' && ch <= 'z')
         {
        	 ++numLetters;
         }
         System.out.print(ch);
      }
      System.out.println();
      System.out.println
         ("The number of CHARACTERS in the string is " + stringLength);
      System.out.println("The number of LETTERS is " + numLetters);
   }
}