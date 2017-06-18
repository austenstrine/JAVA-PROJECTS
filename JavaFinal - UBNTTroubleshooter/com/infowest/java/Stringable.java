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
	
	
	public Stringable(String title, String content)
	{
		setAll(title, content);
	}
	
	public void setAll(String title, String content)
	{
		titleString = title;
		contentString = content;
	}
	
	public String getTitle()
	{
		return titleString;
	}
	public String getContent()
	{
		return contentString;
	}
	
	public String toString()
	{
		return ("Title: "+ titleString + "  Content: " + contentString);
	}
}
