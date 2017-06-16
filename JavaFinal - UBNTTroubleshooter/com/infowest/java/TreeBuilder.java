/*
 * this class should allow users to 
 */


package com.infowest.java;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.util.*;

enum TreeType{
BRANCH, LEAF
}

public class TreeBuilder extends JFrame implements TreeSelectionListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5870411020614345178L;
	
	JTree tree;
	JButton newNodeBtn = new JButton("New Branch/Leaf"),
			editNodeBtn = new JButton("Edit Branch/Leaf"),
			deleteNodeBtn = new JButton("Delete Branch/Leaf");
	JPanel buttons = new JPanel(new GridLayout(1,3));
	JScrollPane treeScroll,
				buttonsScroll = new JScrollPane(buttons);
	DefaultMutableTreeNode lastNode = new DefaultMutableTreeNode(new Stringable("Seed", "Seed"));
	
	public TreeBuilder()
	{
		super("Tree Builder");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		DefaultMutableTreeNode seed = new DefaultMutableTreeNode("Seed");
		tree = new JTree(seed);
		treeScroll = new JScrollPane(tree);
		this.getContentPane().setLayout(new GridLayout(2,1));
		this.getContentPane().add(treeScroll);
		newNodeBtn.addActionListener(this);
		editNodeBtn.addActionListener(this);
		deleteNodeBtn.addActionListener(this);
		buttons.add(newNodeBtn);
		buttons.add(editNodeBtn);
		buttons.add(deleteNodeBtn);
		this.getContentPane().add(buttonsScroll);
		this.getContentPane().setVisible(true);
		pack();
		
	}

	public static void main(String[] args) {
		TreeBuilder tWindow = new TreeBuilder();
		tWindow.setVisible(true);

	}


	@Override
	public void valueChanged(TreeSelectionEvent e) {		
		lastNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		Object source = e.getSource();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		int answer = 1;
		if(source == newNodeBtn)
		{
			answer = JOptionPane.showConfirmDialog(null, "This will delete the selected branch/leaf " 
					+ lastNode.getUserObject().toString() 
					+ "! Are you sure you want to proceed?");
			switch(answer){
				case 0:
				case 1:
				case 2:
				default:
					break;
			}	
		}
		else if(source == editNodeBtn)
		{
			answer = JOptionPane.showConfirmDialog(null, "This will edit the selected branch/leaf " 
					+ lastNode.getUserObject().toString() 
					+ "! Are you sure you want to proceed?");
			switch(answer){
				case 0:
				case 1:
				case 2:
				default:
					break;
			}	
		}
		else if(source == deleteNodeBtn)
		{
			answer = JOptionPane.showConfirmDialog(null, "This will delete the selected branch/leaf " 
					+ lastNode.getUserObject().toString() 
					+ "! Are you sure you want to proceed?");
			switch(answer){
				case 0:
				case 1:
				case 2:
				default:
					break;
			}
		}
		System.out.println(answer);
	}
	
	public void newChildNode(DefaultMutableTreeNode n)
	{
		//probably need to create a "NodeForm" class similar to JOptionPane, that extends JFrame
	}

}
