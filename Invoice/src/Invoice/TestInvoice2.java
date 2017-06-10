package Invoice;
//import javax.swing.*;
import Invoice.Invoice2;


public class TestInvoice2 
{
	
//methods
	public static void main(String[] args)
	{
		Invoice2 one = new Invoice2();
		one.displayAll("one");
		
		Invoice2 two = new Invoice2();
		two.displayAll("two");
		Invoice2 three = new Invoice2();
		three.displayAll("three");
		Invoice2 four = new Invoice2();
		four.displayAll("four");
		Invoice2 five = new Invoice2();
		five.displayAll("five");
		
	}
}
/* 999
 * 50
 * -5
 * -5
 * -5
 * 
 * 1000
 * 50
 * 32
 * 32
 * 3232
 * 
 * 5000
 * 50
 * 11
 * 31
 * 3131
 * 
 * 1001
 * 50
 * 2
 * 31
 * 2222
 * 
 * 3333
 * 50
 * 1
 * 27
 * 2015
 */