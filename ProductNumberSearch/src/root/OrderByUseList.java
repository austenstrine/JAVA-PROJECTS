package root;
import java.util.ArrayList;
import java.util.Collections;
//import java.util.List;

public class OrderByUseList
{
//variables

	private ArrayList<AccessTracked> li;
	private int max, current, size;
	
//constructors


	public OrderByUseList(String[] col, int n)
	{
		this.size = col.length;
		this.li = new ArrayList<AccessTracked>(this.size);
		for(int i = 0; i<this.size;++i)
		{
			Object o = col[i];
			AccessTracked a = new AccessTracked(o);
			this.li.add(a);
		}
		
		this.max = n;
	}
	public OrderByUseList(char[] col, int n)
	{
		this.size = col.length;
		this.li = new ArrayList<AccessTracked>(this.size);
		for(int i = 0; i<this.size;++i)
		{
			Object o = col[i];
			AccessTracked a = new AccessTracked(o);
			this.li.add(a);
		}
		
		this.max = n;
	}
	public OrderByUseList(double[] col, int n)
	{
		this.size = col.length;
		this.li = new ArrayList<AccessTracked>(this.size);
		for(int i = 0; i<this.size;++i)
		{
			Object o = col[i];
			AccessTracked a = new AccessTracked(o);
			this.li.add(a);
		}
		
		this.max = n;
	}
	public OrderByUseList(Object[] col, int n)
	{
		this.size = col.length;
		this.li = new ArrayList<AccessTracked>(this.size);
		for(int i = 0; i<this.size;++i)
		{
			Object o = col[i];
			AccessTracked a = new AccessTracked(o);
			this.li.add(a);
		}
		
		this.max = n;
	}
	
	
//methods
	
	public void sort()
	{
		if(!this.li.isEmpty())//Necessary? Should I allow users to sort an empty array and have to deal with the consequences?
		{
			Collections.sort(this.li);
		}
	}
	
	
	public boolean has(Object o)//will increment the number of times that o has been accessed if true
	{
		for(int n = 0; n<this.size;++n)
		{
			if(o.equals(this.li.get(n).look()))
			{
				this.li.get(n).found();
				return true;
			}
		}
		return false;
	}
	public boolean touchlessHas(Object o)//will NOT increment the number of times that o has been accessed if true
	{
		for(int n = 0; n<this.size;++n)
		{
			if(o.equals(this.li.get(n).look()))
			{
				return true;
			}
		}
		return false;
	}
	public int findFirst(Object o)//increments the number of times o has been accessed
	{
		
		if(this.current >= this.max)//built-in auto-sort! Controlled by the integer passed to the constructor.
		{
			this.sort();
			this.current=0;
		}
		
		
		for(int n = 0; n<this.size;++n)
		{
			if(o.equals(this.li.get(n).look()))
			{
				this.li.get(n).found();
				++this.current;
				return n;
			}
		}
		return -1;
	}//end findFirst
	public int findLast(Object o)//increments the number of times o has been accessed, PURPOSELY REVERSED
	{
		
		if(this.current >= this.max)//built-in auto-sort! Controlled by the integer passed to the constructor.
		{
			this.sort();
			this.current=0;
		}
		
		++this.current;
		
		for(int n = this.size-1; n >= 0; --n)
		{
			if(o.equals(this.li.get(n).look()))
			{
				this.li.get(n).found();
				return n;
			}
		}
		return -1;
	}//end findLast
	public void setEach(Object o, Object j)
	{
		for(int n = 0; n<this.size;++n)
		{
			if(o.equals(this.li.get(n).look()))
			{
				this.li.get(n).set(j);
			}//end if
		}//end for
	}//end replaceEach
	public Object get(int i)//increments the number of times o has been accessed
	{
		if(this.current >= this.max)//built-in auto-sort! Controlled by the integer passed to the constructor.
		{
			this.sort();
			this.current=0;
		}
		return this.li.get(i).get();
	}
	
	public void set(int i, Object j)
	{
		this.li.get(i).set(j);
	}
	
	
	public String[] toStringArray()
	{
		this.sort();
		String[] sarr = new String[this.size];
		String e = "";
		for(int n = 0; n<this.size;++n)
		{
			e = (String)this.li.get(n).look();
			sarr[n] = e;
		}
		return sarr;
	}
	public double[] toDoubleArray()
	{
		this.sort();
		double[] arr = new double[this.size];
		double e = 0.0;
		for(int n = 0; n<this.size;++n)
		{
			e = (double)this.li.get(n).look();
			arr[n] = e;
		}
		return arr;
	}
	public char[] toCharArray()
	{
		this.sort();
		char[] arr = new char[this.size];
		char e = ' ';
		for(int n = 0; n<this.size;++n)
		{
			e = (char)this.li.get(n).look();
			arr[n] = e;
		}
		return arr;
	}
	public Object[] toObjectArray()
	{
		this.sort();
		Object[] arr = new Object[this.size];
		for(int n = 0; n<this.size;++n)
		{
			arr[n] = this.li.get(n).look();
		}
		return arr;
	}
}

class AccessTracked implements Comparable<AccessTracked>
{
//variables
	private Object o;
	private int timesAccessed = 0;
	
//constructors
	public AccessTracked(Object j)
	{
		this.o = j;
	}

//methods
	public Object get()//used for direct access when index is known
	{
		++this.timesAccessed;
		return this.o;
	}
	
	public int got()//used for comparison
	{
		return this.timesAccessed;
	}
	
	public Object look()//used when iterating over elements
	{
		return this.o;
	}
	
	public void found()//used to indicate when the desired element is found in an iteration.
	{
		++this.timesAccessed;
	}
	
	public void set(Object j)
	{
		this.o = j;
		this.timesAccessed = 0;
	}
	
	public void wipeGot()//not sure when this would be needed, but I get the feeling it would be
	{
		this.timesAccessed = 0;
	}
	
	@Override
	public int compareTo(AccessTracked a)
	{
		return a.got() - this.got();
		
	}
}
