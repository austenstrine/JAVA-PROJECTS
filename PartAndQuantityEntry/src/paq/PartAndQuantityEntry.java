/*
 * A company accepts user orders by part numbers interactively. Users might make the following errors as they enter data:
 * 
 * The part number is not numeric.
 * The quantity is not numeric.
 * The part number is too low (less than 0).
 * The part number is too high (more than 999).
 * The quantity ordered is too low (less than 1).
 * The quantity ordered is too high (more than 5,000).
 * 
 * Create a class that stores an array of usable error messages; save the file as DataMessages.java. Create a 
 * DataException class; each object of this class will store one of the messages. Save the file as DataException.java. 
 * Create an application that prompts the user for a part number and quantity. Allow for the possibility of nonnumeric 
 * entries as well as out-of-range entries, and display the appropriate message when an error occurs. If no error occurs, 
 * display the message “Valid entry”. Save the program as PartAndQuantityEntry.java.
 */
package paq;
import javax.swing.JOptionPane;

public class PartAndQuantityEntry {
	
	private static final Integer P_HI = 999, P_LO = 0, Q_HI = 5000, Q_LO = 1;

	public static void main(String[] args) 
	{
		String entry = "";
		Integer iEntry = null, partnum = null, quantity = null;
		while(true)
		{
			entry = JOptionPane.showInputDialog("Please enter a part number(0-999):");
			try
			{
				
				iEntry = Integer.parseInt(entry);
				if(iEntry < P_LO)
				{
					throw new DataException
					(
						new ExceptionStrings//constructs a collection of string messages
						(
							"part number"+iEntry,
							P_LO.toString()
						)
						.MESSAGES_BY_KEY.get(Key.TOO_LOW)//retrieves a message from that collection
					);
				}//if
				else if(iEntry > P_HI)
				{
					throw new DataException
					(
						new ExceptionStrings
						(
							"part number "+iEntry,
							P_HI.toString()
						)//message definition/construction
						//.MESSAGES_BY_KEY.replace(Key.GENERAL, "test")  /*This confirmed that the hashmap is unmodifiable after creation.*/
						.MESSAGES_BY_KEY.get(Key.TOO_HIGH)//message retrieval
					);//end data exception
				}//if
				else
				{
					Integer okcc = JOptionPane.showConfirmDialog(null, 
							"You entered part number "+ iEntry+ ". Is this okay?",
							"Confirmation",
							2);
					if(okcc == 0)
					{
						break;//only ends the outer while upon confirmation of correct part number
					}
				}//end if
			}//end try
			catch(IllegalArgumentException ia)
			{
				if(entry==null)
					break;
				JOptionPane.showMessageDialog
					(
						null,
						new ExceptionStrings
						(
							"part number "+entry,								
							"an Integer from "+P_LO+" to "+P_HI
						)
						.MESSAGES_BY_KEY.get(Key.NAN)
					);
				iEntry = null;
			}
			catch(DataException da)
			{
				JOptionPane.showMessageDialog(null, da.getMessage());
				iEntry = null;
			}//end try/catch
		}//end while loop
		
		partnum = iEntry;
		
		while(partnum != null)
		{
			entry = JOptionPane.showInputDialog("Please enter the quantity of parts you'd like (1-5000):");
			try
			{
				
				iEntry = Integer.parseInt(entry);
				if(iEntry < Q_LO)
				{
					throw new DataException
					(
						new ExceptionStrings
						(
							"quantity"+iEntry,
							Q_LO.toString()
						)
						.MESSAGES_BY_KEY.get(Key.TOO_LOW)
					);
				}//if
				else if(iEntry > Q_HI)
				{
					throw new DataException
					(
						new ExceptionStrings
						(
							"quantity "+iEntry,
							Q_HI.toString()
						)//message definition/construction
						.MESSAGES_BY_KEY.get(Key.TOO_HIGH)//message retrieval
					);//end data exception
				}//if
				else
				{
					Integer okcc = JOptionPane.showConfirmDialog(null, 
							"You entered a quantity of "+ iEntry+ ". Is this okay?",
							"Confirmation",
							2);
					if(okcc == 0)
					{
						break;
					}
				}//end if
			}//end try
			catch(IllegalArgumentException ia)
			{
				if(entry==null)
					break;
				JOptionPane.showMessageDialog
					(
						null,
						new ExceptionStrings
						(
							"quantity "+entry,								
							"an Integer from "+Q_LO+" to "+Q_HI
						)
						.MESSAGES_BY_KEY.get(Key.NAN)//gotta figure out how to help this make sense to more than just me
					);//hmm...not sure this saves time at all either. It's a tad too complicated. It works though!
				iEntry = null;
			}
			catch(DataException da)
			{
				JOptionPane.showMessageDialog(null, da.getMessage());
				iEntry = null;
			}//end try/catch
		}//end while
		quantity = iEntry;
		
		if(quantity != null && partnum != null)
		{
			JOptionPane.showMessageDialog(null, "You have ordered " +quantity+ " of part number "+partnum+ "."
					+"\nYour order should arrive within 5 business days. Thank you!");//doesn't say "Valid Entry", but I think this gets the point across.
		}
	}

}
