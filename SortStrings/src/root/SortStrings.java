package root;

import java.util.Arrays;
import javax.swing.JOptionPane;

public class SortStrings 
{
	public static void main(String[] args)
	{
		String[] arr = {"jan","bob","JOHN","PHIL","Sam", "Sally","thoMAS","supa-G", "conSUEla", "it-R-8"};
		String[] arr2 = Arrays.copyOf(arr, arr.length);
		for(int i = 0; i < arr2.length;++i)
		{
			arr2[i]=arr2[i].toLowerCase();
		}
		Arrays.sort(arr2);
		for(int r = 0, r2; r < arr.length; ++r)
		{
			r2 = 0;
			for( ; r2 < arr2.length; ++r2)
			{
				if(arr2[r2].equals(arr[r].toLowerCase()))
				{
					arr2[r2] = arr[r];
				}
			}
		}
		String display = "";
		{
			int i = 0;
			while(i < arr2.length)
			{
				if(i == 0)
				{
					display+= " ";
				}
				else if(i == arr2.length-1)
				{
					display+=", and ";
				}
				else
				{
					display+=", ";
				}//end if
				
				display+=arr2[i];
				++i;
			}//end while
		}//end i
		JOptionPane.showMessageDialog(null, "Strings ordered are: " + display);
	}
}
