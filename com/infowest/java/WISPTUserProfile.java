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
						lastTreeUsedPath;
	
	private int userUnlocks;	
	
	private DefaultMutableTreeNode lastSelectedNode;
	/*
	 *  TODO constructors
	 */
	public WISPTUserProfile(boolean isAdmin, boolean editorModeEnabled, boolean processTooltipsEnabled, boolean trainingModeEnabled, boolean tipsVisible, boolean treeVisible, String userName, String userPassword, String lockedMessage, String lastTreeUsedPath, int userUnlocks, DefaultMutableTreeNode lastSelectedNode)
	{
		 this.isAdmin = isAdmin;
		 this.editorModeEnabled = editorModeEnabled;
		 this.processTooltipsEnabled = processTooltipsEnabled;
		 this.trainingModeEnabled = trainingModeEnabled;
		 this.tipsVisible = tipsVisible;
		 this.treeVisible = treeVisible;
		 this.userName = userName;
		 this.userPassword = userPassword;
		 this.lockedMessage = lockedMessage;
		 this.lastTreeUsedPath = lastTreeUsedPath;
		 this.userUnlocks = userUnlocks;
		 this.lastSelectedNode = lastSelectedNode;
	}
	
	public WISPTUserProfile(boolean isDefaultRootAdmin)
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
		 this.lastSelectedNode = new DefaultMutableTreeNode("null");

			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString()+"/Tree Saves");//creates a empty file in the tree saves folder of that directory
			
		 this.lastTreeUsedPath = currentDirectoryFile.toString()+"/dt.tree";
	}
	
	/*
	 * TODO Make getters and setters for all vars
	 */
	public void
	setAdminPrivelage(boolean isAdmin)
	{
		this.isAdmin = isAdmin;
	}
	public void
	setEditorModeEnabled(boolean editorModeEnabled)
	{
		this.editorModeEnabled = editorModeEnabled;
	}
	public void
	setProcessTooltipsEnabled(boolean processTooltipsEnabled)
	{
		this.processTooltipsEnabled = processTooltipsEnabled;
	}
}