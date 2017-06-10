package root;
/**
 * 

    Home
    Grades
    1
    Modules
    Files
    Syllabus

Chapter 7: Exercise 7

    Due No Due Date Points 10 Submitting a file upload

Write an application that demonstrates each of the following methods based on the following quote:

"You can never cross the ocean until you have the courage to lose sight
of the shore." – Christopher Columbus

    indexOf('v')
    indexOf('x')
    charAt(16)
    endsWith("bus")
    endsWith("car")
    replace('c', 'C')

Save the file as DemonstratingStringMethods.java.
 * @author student
 *
 */

import javax.swing.*;

public class DemonstratingStringMethods 
{
//variables
	private final static String QUOTE = "\"You can never cross the ocean until you have the courage to lose sight of the shore.\" – Christopher Columbus";
//constructors

//main method
	public static void main(String[] args)
	{
		String quote = QUOTE;
		quote = quote.replace('c', 'C');
		JOptionPane.showMessageDialog(null, QUOTE + "\nI will demonstrate different String methods on this quote, which is a single String.");
		JOptionPane.showMessageDialog(null, "The indexOf('v') is " + QUOTE.indexOf('v')
										+ "\nThe indexOf('x') is " + QUOTE.indexOf('x')
										+ "\nThe charAt(16) is " + QUOTE.charAt(16)
										+ "\nendsWith(\"bus\") returns " + QUOTE.endsWith("bus")
										+ "\nendsWith(\"car\") returns " + QUOTE.endsWith("car")
										+ "\nreplace('c','C') changes the String from \n" + QUOTE + "\nto\n" + quote
										);
	}
//methods
}
