/*
 * this class should allow users to 
 */


package com.infowest.java;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

//import java.util.*;

import java.io.*;

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
	DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(new Stringable("root", "root")),
						root,
						
						asideNode;////////BACK UP ON GITHUB FIRST, then implement makeNewNode and makeEditedNode methods based off of this variable
	DefaultTreeModel model;
	
	public TreeBuilder()
	{
		super("Tree Builder");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		root = new DefaultMutableTreeNode(new Stringable("root", "root"));
		tree = new JTree(root);
		model = (DefaultTreeModel) tree.getModel();
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
		//selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		//Object source = e.getSource();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		int answer = 1;
		Stringable nodeS = new Stringable("", "");
		try
		{
			selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			nodeS = (Stringable)selectedNode.getUserObject();
		}
		catch(NullPointerException npe)
		{
			JOptionPane.showMessageDialog(null, "Please make a selection first.");
			source = null;
		}
		if(source == newNodeBtn)
		{
			answer = JOptionPane.showConfirmDialog(null, "This will create a new Branch/Leaf as a child of the selected branch/leaf, \'" 
					+ nodeS.getTitle() 
					+ "\'! Are you sure you want to proceed?",
					"New Branch/Leaf",
					JOptionPane.OK_CANCEL_OPTION);
			switch(answer){
				case 0:
					getNode(nodeS);
					exeNew();
					break;
				case 1:
				case 2:
				default:
					break;
			}//end switch
		}
		else if(source == editNodeBtn)
		{
			answer = JOptionPane.showConfirmDialog(null, "This will edit the selected branch/leaf " 
					+ nodeS.getTitle()
					+ "! Are you sure you want to proceed?",
					"Edit Branch/Leaf",
					JOptionPane.OK_CANCEL_OPTION);
			switch(answer)
			{
				case 0:
				case 1:
				case 2:
				default:
					break;
			} //end switch	
		}
		else if(source == deleteNodeBtn)
		{
			if (root==selectedNode) 
			{
				JOptionPane.showMessageDialog(null, "Root cannot be deleted!");
			}
			else
			{
				answer = JOptionPane.showConfirmDialog(null,
						"This will delete the selected branch/leaf " + nodeS.getTitle()
								+ "! Are you sure you want to proceed?",
						"Delete Branch/Leaf", JOptionPane.OK_CANCEL_OPTION);
				switch (answer) 
				{
				case 0:
					model.removeNodeFromParent(selectedNode);
					break;
				case 1:
				case 2:
				default:
					break;
				}//end switch
			}//end selected if
		}//end source if
		System.out.println(answer);
	}
	
	public void getNode(Stringable s)
	{
		String[] nodeStrings = {s.getTitle(), s.getContent()};
		Node.main(nodeStrings);
		asideNode = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("/node.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			asideNode = (DefaultMutableTreeNode)in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(asideNode.getUserObject().toString());
		//Stringable sls = (Stringable)selectedNode.getUserObject();
		//System.out.println(sls);
	}
	
	public void exeNew()
	{
		model.insertNodeInto(asideNode, //(DefaultMutableTreeNode)tree.getLastSelectedPathComponent()
				selectedNode, 0);
		this.repaint();
		this.revalidate();
	}
	public void exeEdit()
	{}

}
