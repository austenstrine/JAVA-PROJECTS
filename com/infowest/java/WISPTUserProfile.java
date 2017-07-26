package com.infowest.java;

import java.io.Serializable;

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
					tipsVisible,
					treeVisible;
	private String adminPassword,
					invisibleString;
	private int userUnlocks;
	
}
