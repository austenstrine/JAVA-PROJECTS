package com.infowest.java;

import java.io.Serializable;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class SerializeableTree extends JTree implements Serializable {

	public SerializeableTree(DefaultMutableTreeNode root) {
		super(root);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4291018243791582125L;

}
