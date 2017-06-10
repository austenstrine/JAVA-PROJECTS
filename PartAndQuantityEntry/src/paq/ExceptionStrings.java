package paq;
import java.util.*;
public class ExceptionStrings 
{
	public final Map<Key, String> MESSAGES_BY_KEY;
	/* not sure if hashmap or hashset would be a better choice for what I'm doing here - 
	 * all I'm really wanting is a key/value pair per element in a collection
	 */
	
	public ExceptionStrings(String data, String resolution)
	{
		HashMap<Key, String> errors = new HashMap<Key, String>(); 
		errors.put(Key.NAN, "The "+ data +" is not numeric! Must be " + resolution + "!");
		errors.put(Key.TOO_LOW, "The "+data+" is too low! Must be " + resolution + " or higher!");
		errors.put(Key.TOO_HIGH, "The "+data+" is too high! Must be "+resolution+ " or lower!");
		errors.put(Key.GENERAL, "The "+data+" is incorrect!\nBe advised, "+resolution+ " is required!");
		this.MESSAGES_BY_KEY = Collections.unmodifiableMap(errors);
    }
}
