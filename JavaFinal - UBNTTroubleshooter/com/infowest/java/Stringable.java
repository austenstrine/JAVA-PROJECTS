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
	private String invisibleString = "";
	/*
	 * need to extend defaultmutabletreenode to include a counting integer and methods for it
	 * leaves can be accessed userDefined number of times, root can be accessed infinitely, each 
	 * branch from root can be accessed (thisbranch * (descendentleaves*userDefined) + userDefined)
	 * times, continues in the same way for each descendentbranch.
	 * when limit has been reached, the user object is accessed, and a setVisible(false, int i) method
	 * is called, which makes invisibleString = contentString, and contentString = "", for i number 
	 * of minutes(long iMinutes = i*1000l). Will have to use System.currentTimeMilis(), and have a long timeStart to compare.
	 * will also have to call a checkTime() method at times so that when System.currentTimeMilis() + 
	 * iMinutes >= timeStart + iMinutes, it calls setVisible(true, null), which also means it needs to keep track of the 
	 * nodes that have been made invisible(maybe need boolean isVisible()?)
	 * then I need to rename this class, the -able postfix is for interfaces
	 */
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
