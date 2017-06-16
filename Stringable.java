package com.infowest.java;

public class Stringable 
{
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
		return ("Title: "+ titleString + "\nContent: " + contentString);
	}
}
