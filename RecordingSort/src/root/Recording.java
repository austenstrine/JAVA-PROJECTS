/*
 * a. Radio station JAVA wants a class to keep track of recordings it plays. 
 * Create a class named Recording that contains fields to hold methods for 
 * setting and getting a Recording’s title, artist, and playing time in 
 * seconds. Save the file as Recording.java.
 */
package root;

public class Recording implements Comparable<Recording>
{
	private String title, artist;
	private static char type;
	private int seconds;
	
	public Recording(String t, String a, int s)
	{
		this.title = t;
		this.artist = a;
		this.seconds = s;
	}
	public Recording()
	{
		this("Title", "Artist", 60);
	}
	
	
	public void setTitle(String s)
	{
		this.title = s;
	}
	public void setArtist(String s)
	{
		this.artist = s;
	}
	public void setSeconds(int i)
	{
		this.seconds = i;
	}
	public void setAll(String t, String a, int s)
	{
		this.title = t;
		this.artist = a;
		this.seconds = s;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	public String getArtist()
	{
		return this.artist;
	}
	public int getSeconds()
	{
		return this.seconds;
	}
	public String getAllAsString(int num)
	{
		String s = "For recording "+num+":\n   Title: "+this.title+"\n   Artist: "+this.artist+"\n   Seconds: "+this.seconds;
		return s;
	}
	
	public static void setComparison(char c)
	{
		type = c;
	}
	public static void setComparison()
	{
		type = 's';
	}

	public int continueToCompare(String dis, String dat, int compareI)
	{
		
		if(dis.toUpperCase().charAt(compareI) > dat.toUpperCase().charAt(compareI))
		{
			return 1;
		}
		else if(dat.toUpperCase().charAt(compareI) > dis.toUpperCase().charAt(compareI))
		{
			return -1;
		}
		else
		{
			++compareI;
			if(compareI == dat.length() && compareI == dis.length())//avoids a fragmentation error
			{
				return 0;
			}
			if(compareI == dat.length())
			{
				return 1;
			}
			if(compareI == dis.length())
			{
				return -1;
			}
			return continueToCompare(dis, dat, compareI);
		}
	}
	
	@Override
	public int compareTo(Recording o) 
	{
		String dis, dat;
		if(type == 't')
		{
			dis = this.title;
			dat = o.title;
			
		}
		else if(type == 'a')
		{
			dis = this.artist;
			dat = o.artist;
		}
		else
		{
			int i = this.seconds;
			int n = o.seconds;
			return i-n;
		}//end if
		return continueToCompare(dis, dat, 0);
	}
}
