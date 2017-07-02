/*
 *    Copyright 2017 Austen Loren Strine
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.infowest.java;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class WISPTNodeObject implements Serializable
{
	/**
	 * 
	 */
	private static final long 
		serialVersionUID = -9875955166960066L;
	
	private static int
	timesUserPassUsed = 0,
	userMax = 5;
	
	private static String	
	userPass = "password",
	adminPass = "WISPadmin",
	invisibleString = "";
	
	private String 
		titleString = "Seed",
		contentString = "Seed",
		visibleString = "";
	
	private int 
		counter = 0;

	private String[] 
		tips = {null,null,null,null,null,null};
	
	/*
	 * Constructors
	 */
	
	public 
	WISPTNodeObject(String title, String content)
	{
		setTitle(title);
		setContent(content);
		visibleString = contentString;
	}
	
	/*
	 * Methods
	 */
	public void
	setContentVisible(boolean visible)
	{
		if(visible)
		{
			contentString = visibleString;
		}
		else
		{
			contentString = invisibleString;
		}
	}
	public void
	setInvisibleString(String newInvisibleString)
	{
		invisibleString = newInvisibleString;
	}
	public String
	getInvisibleString()
	{
		return invisibleString;
	}
	
	/*
	 * Setters
	 */

	public void 
	setAdminPass(String newAdminPass)
	{
		adminPass = newAdminPass;
	}
	public void 
	setContent(String newContent)
	{
		contentString = newContent;
		visibleString = contentString;
	}
	public void 
	setCounter(int i)
	{
		counter = i;
	}
	public void 
	setCounter()
	{
		++counter;
	}
	public static void 
	setTimesUserPassUsed(int newTimesUserPassUsed)
	{
		timesUserPassUsed = newTimesUserPassUsed;
	}
	public static void 
	setTimesUserPassUsed()
	{
		++timesUserPassUsed;
	}
	public void 
	setTip(int index, String tip)
	{
		tips[index] = tip;
	}
	public void 
	setTips(String[]inTips)
	{
		tips = inTips;
	}
	public void 
	setTitle(String newTitle)
	{
		titleString = newTitle;
	}
	public void 
	setUserMax(int newUserMax)
	{
		userMax = newUserMax;
	}
	public void 
	setUserPass(String newUserPass)
	{
		userPass = newUserPass;
	}
	
	/*
	 * Getters
	 */
	
	public String 
	getAdminPass()
	{
		return adminPass;
	}
	public String 
	getContent()
	{
		return contentString;
	}
	public int 
	getCounter()
	{
		return counter;
	}
	public String 
	getTipAt(int index)
	{
		return tips[index];
	}
	public String[] 
	getTips()
	{
		return tips;
	}
	public int 
	getTimesUserPassUsed()
	{
		return timesUserPassUsed;
	}
	public int 
	getUserMax()
	{
		return userMax;
	}
	public String 
	getUserPass()
	{
		return userPass;
	}
	
	public void unlockNode()
	{
		String attempt = JOptionPane.showInputDialog("Please enter the unlock password:");
		if(attempt == null)
		{
			attempt = "null";
		}
		if(attempt.equals(userPass))
		{
			if(timesUserPassUsed < userMax)
			{
				setTimesUserPassUsed();
				setContentVisible(true);
				setCounter(-1);
				System.out.println("Unlocked: content is " + contentString + "!");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "You have exceeded the number of times you may use your User Password("+userMax+"). "
						+ "\nPlease contact your superior to unlock with the Administrative Password.");
				System.out.println("Locked: content is " + contentString + "!");
			}
		}
		else if(attempt.equals(adminPass))
		{
			setContentVisible(true);
			setCounter(-1);
			System.out.println("Unlocked: content is " + contentString + "!");
		}
		else
		{
			setContentVisible(false);
			JOptionPane.showMessageDialog(null, "Password Incorrect!");
			System.out.println("Locked: content is " + contentString + "!");
		}
	}
	
	public String 
	toString()
	{
		return (titleString //+ "  Content: " + contentString
				);
	}
}
