package com.infowest.java;

import java.io.Serializable;

public class Stringable implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9875955166960066L;
	
	private String titleString = "Seed";
	private String contentString = "Seed";
	private String[] tips = {null,null,null,null,null,null};
	private boolean[] bool = {false,false,false,false,false,false};
	
	
	public Stringable(String title, String content)
	{
		setAll(title, content);
	}
	
	public void setAll(String title, String content)
	{
		titleString = title;
		contentString = content;
	}

	public String getContent()
	{
		return contentString;
	}
	
	public void setTips(String[]inTips)
	{
		tips = inTips;
	}
	
	public String[] getTips()
	{
		return tips;
	}
	
	public String getTip(int index)
	{
		return tips[index];
	}
	
	public void setTip(int index, String tip)
	{
		tips[index] = tip;
	}
	
	public void setBools(boolean[] b)
	{
		bool = b;
	}
	
	public boolean[] getBools()
	{
		return bool;
	}
	
	public String toString()
	{
		return (titleString //+ "  Content: " + contentString
				);
	}
}
