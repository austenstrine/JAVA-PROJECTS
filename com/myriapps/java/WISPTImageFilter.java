package com.myriapps.java;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class WISPTImageFilter extends FileFilter 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8627663354980173696L;

	enum Ext 
	{
		jpeg, jpg, png, gif, bmp;
	}
	
	/**
	 * 
	 */

	@Override
	public boolean
	accept(File file)
	{
		if(file.isDirectory())
		{
			return true;
		}
		for(Ext e : Ext.values())
		{
			if(file.getName().toLowerCase().endsWith(e.toString().toLowerCase()))
			{
				return true;
			}
		}
		return false;
	}//end accept

	@Override
	public String 
	getDescription() 
	{	
		return "*.jpeg, *.jpg, *.png, *.gif, *.bmp";
	}//end get description

	public static long 
	getSerialVersionUID() 
	{
		return serialVersionUID;
	}//end get serial id
}