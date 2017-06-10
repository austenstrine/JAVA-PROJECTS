
import java.util.ArrayList;
import java.util.Collections;

public class DynamicAccessArray
{
//variables
	private ArrayList<Integer> one;//this is the array passed to this class
	private ArrayList<Integer> two;//this keeps track of how many times an element of the array was "found" in a search
	private int access;//this keeps track of how many times ANY element is found
//constructors
	public DynamicAccessArray(int i, int[] arr)
	{
		this.one.clear();
		for(int x = 0; x < arr.length; ++x)
		{
			this.one.add(arr[x]);
		}//end for
		this.two.clear();
		this.two = new ArrayList<Integer>(Collections.nCopies(arr.length, 0));
	}//end constructor

//methods
	public int findFirst(Object o)
	/* Returns the subscript of the first element matching 
	 * the one passed to the method. Will return -1 if no
	 * matching element is found.
	 */
	{
		int i = 0;
		int sz = this.one.size();
		
		while(i < sz)
		{
			if(this.one.get(i).equals(o))
			{
				this.two.set(i, this.two.get(i)+1);
				++access;
				return i;
			}//end if
			++i;
		}//end while
		return -1;
	}//end findFirst
	
	
	public int get(int i)
	{
		return this.one.get(i);
	}//end get
	
	public void set(int i, int e)//I know there's a way to just use the ArrayList method but I don't know how yet
	{
		this.one.set(i, e);
	}//end set
	
	
	public boolean timesFound(int i)
	{
		boolean ac = false;
		if(this.access == i)
		{
			ac = true;
		}//end if
		return ac;
	}//end timesFound
	
	
	public void sort()//maybe not the best sort, but it's what I came up with on the fly.
	{
		boolean changed = true;
		int i, 
			low = 0, 
			high = this.two.size()-1, 
			twoIGot, 
			twoIGotPlus, 
			oneIGot, 
			oneIGotPlus;
		boolean highChange, 
				lowChange;
		while(changed)
		{	
			i = low;
			highChange 	= false; 
			lowChange 	= false;
			while(i != high)
			{
				changed 	= false;
				twoIGot 	= this.two.get(i);
				twoIGotPlus = this.two.get(i+1);
				oneIGot 	= this.one.get(i);
				oneIGotPlus = this.one.get(i+1);
				if(twoIGot > twoIGotPlus)
				{
					if(twoIGot > this.two.get(high))
					{
						highChange = true;
						this.two.add(twoIGot);
						this.one.add(oneIGot);
						this.two.remove(i);
						this.one.remove(i);
						changed = true;
					}
					else
					{
						this.two.set(i, twoIGotPlus);
						this.one.set(i, oneIGotPlus);
						this.two.set(i+1, twoIGot);
						this.one.set(i+1, oneIGot);
						changed = true;
					}//end inner if
				}//if
				else
				{
					if(twoIGot < this.two.get(low))
					{
						lowChange = true;
						this.two.remove(i);
						this.one.remove(i);
						this.two.add(0, twoIGot);
						this.one.add(0, oneIGot);
						changed = true;
					}
					else
					{
						this.two.set(i, twoIGotPlus);
						this.one.set(i, oneIGotPlus);
						this.two.set(i+1, twoIGot);
						this.one.set(i+1, oneIGot);
						changed = true;
					}//end inner if
				}//end outer if
				++i;
			}//end inner while
			if(highChange)
			{
				--high;
			}
			
			if(lowChange)
			{
				++low;
			}
		}//end outer while
		this.access = 0;
	}//end sort
}//end class
