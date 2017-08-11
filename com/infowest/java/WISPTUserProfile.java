package com.infowest.java;

import java.awt.Dialog.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.imageio.*;

import javax.swing.*;
import javax.swing.tree.*;



public class WISPTUserProfile implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7805455907947127832L;
	
	/*
	 * TODO variables
	 */
	private boolean isAdmin,
						editorModeEnabled,
						processTooltipsEnabled,
						trainingModeEnabled,
						tipsVisible,
						treeVisible;
	
	private String userName,
						userPassword,
						lockedMessage,
						lastTreeUsedPath,
						userProfileFolderPath,
						userProfileName;
	
	private int userUnlocks;	
	
	private TreePath lastUserSelectedNodePath;
	/*
	 *  TODO constructors
	 */
	public WISPTUserProfile(boolean isDefaultRootAdmin, String userProfileFolderPath, String userProfileName)
	{
		if(isDefaultRootAdmin)
		{
			 this.isAdmin = true;
			 this.editorModeEnabled = true;
			 this.processTooltipsEnabled = true;
			 this.trainingModeEnabled = false;
			 this.userName = "Default Admin";
		}
		else
		{
			 this.isAdmin = false;
			 this.editorModeEnabled = false;
			 this.processTooltipsEnabled = false;
			 this.trainingModeEnabled = true;
			 this.userName = "Default User";
		}
		 this.tipsVisible = true;
		 this.treeVisible = true;
		 this.userPassword = "password";
		 this.lockedMessage = "This step has been locked!";
		 this.userUnlocks = 3;
		 //this.lastUserSelectedNodePath = new TreePath(null);

			File currentDirectoryFile = new File("Tree Saves"+File.separator);//creates a empty file in the tree saves folder of that directory
			
		 this.lastTreeUsedPath = currentDirectoryFile.toString()+"dt.tree";
		 this.userProfileFolderPath = userProfileFolderPath;
		 this.userProfileName = userProfileName;
	}
	
	/*
	 * TODO Make getters and setters for all vars
	 */
	public void
	setAdminPrivelage(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	public boolean
	getAdminPrivelage()
	{
		return isAdmin;
	}
	
	public void
	setEditorModeEnabled(boolean editorModeEnabled)
	{
		this.editorModeEnabled = editorModeEnabled;
	}	
	public boolean
	getEditorModeEnabled()
	{
		return editorModeEnabled;
	}
	
	public void
	setProcessTooltipsEnabled(boolean processTooltipsEnabled)
	{
		this.processTooltipsEnabled = processTooltipsEnabled;
	}	
	public boolean
	getProcessTooltipsEnabled()
	{
		return processTooltipsEnabled;
	}
	
	public void
	setTrainingModeEnabled(boolean trainingModeEnabled)
	{
		this.trainingModeEnabled = trainingModeEnabled;
	}	
	public boolean
	getTrainingModeEnabled()
	{
		return trainingModeEnabled;
	}
	
	public void
	setTipsVisible(boolean tipsVisible)
	{
		this.tipsVisible = tipsVisible;
	}	
	public boolean
	getTipsVisible()
	{
		return tipsVisible;
	}
	
	public void
	setTreeVisible(boolean treeVisible)
	{
		this.treeVisible = treeVisible;
	}	
	public boolean
	getTreeVisible()
	{
		return treeVisible;
	}
	
	public void
	setUserName(String userName)
	{
		this.userName = userName;
	}	
	public String
	getUserName()
	{
		return userName;
	}
	
	public void
	setUserPassword(String userPassword)
	{
		this.userPassword = userPassword;
	}	
	public String
	getUserPassword()
	{
		return userPassword;
	}
	
	public void
	setLockedMessage(String lockedMessage)
	{
		this.lockedMessage = lockedMessage;
	}	
	public String
	getLockedMessage()
	{
		return lockedMessage;
	}
	
	public void
	setLastTreeUsedPath(String lastTreeUsedPath)
	{
		this.lastTreeUsedPath = lastTreeUsedPath;
	}	
	public String
	getLastTreeUsedPath()
	{
		return lastTreeUsedPath;
	}
	
	public void
	setUserUnlocks(int userUnlocks)
	{
		this.userUnlocks = userUnlocks;
	}	
	public int
	getUserUnlocks()
	{
		return userUnlocks;
	}
	
	public void
	setLastSelectedNodePath(TreePath lastUserSelectedNodePath)
	{
		this.lastUserSelectedNodePath = lastUserSelectedNodePath;
	}	
	public TreePath
	getLastSelectedNodePath()
	{
		return lastUserSelectedNodePath;
	}

	public String 
	getUserProfileFolderPath() 
	{
		return this.userProfileFolderPath;
	}
	public void 
	setUserProfilePath(String userProfileFolderPath) 
	{
		this.userProfileFolderPath = userProfileFolderPath;
	}
	
	public String 
	getUserProfileName() 
	{
		return this.userProfileFolderPath;
	}
	public void 
	setUserProfileName(String userProfileName) 
	{
		this.userProfileName = userProfileName;
	}
}