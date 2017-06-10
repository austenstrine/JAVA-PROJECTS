package com.tsugame.dorm;

public class Stringable 
{
	private String emptyString = "\nNot Selected";
	private String titleString = emptyString;
	private String contentString = emptyString;
	private boolean visibility = false;
	
	
	public Stringable(String string, String emptyString, boolean visible)
	{
		setAll(string, emptyString, visible);
	}
	public Stringable(String string, boolean visible)
	{
		setAll(string, "\nNot Selected", visible);
	}
	
	public Stringable(String string)
	{
		setAll(string, "\nNot Selected", false);
	}
	
	public void setAll(String string, String emptyString, boolean visible)
	{
		setString(string);
		setEmptyDisplay(emptyString);
		setVisible(visible);
	}

	public void setString(String string)
	{
		this.titleString = string;
	}
	
	public void setEmptyDisplay(String string)
	{
		this.emptyString = string;
	}
	public void setEmptyDisplay()
	{
		setEmptyDisplay("\nNot Selected");
	}
	
	public void setVisible(boolean visible)
	{
		if(visible)
			this.contentString = this.titleString;
		else
			this.contentString = this.emptyString;
		
		this.visibility =  visible;
	}
	
	public boolean getVisible()
	{
		return this.visibility;
	}
	
	public String toString()
	{
		return this.contentString;
	}
}
