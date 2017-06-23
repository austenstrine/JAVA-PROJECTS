/*
 * this class should allow users to 
 */


package com.infowest.java;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.util.*;
import java.util.List;
import java.io.*;

enum TreeType{
BRANCH, LEAF
}

public class TreeBuilder extends JFrame implements TreeSelectionListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5870411020614345178L;
	
	ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	JTree tree;
	String treeSaveName = "name";
	JButton newNodeBtn = new JButton("New Branch/Leaf"),
			editNodeBtn = new JButton("Edit Branch/Leaf"),
			deleteNodeBtn = new JButton("Delete Branch/Leaf"),
			saveTreeBtn = new JButton("Save Tree"),
			moveUpBtn = new JButton("\u2191"),
			moveDownBtn = new JButton("\u2193"),
			loadTree = new JButton("Load Saved tree");
	JPanel buttons = new JPanel(new GridLayout(1,3)),
			allButtons = new JPanel(new BorderLayout());
	JScrollPane treeScroll,
				buttonsScroll = new JScrollPane(allButtons);
	DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(new Stringable("root", "root")),
						root,
						asideNode;
	DefaultTreeModel model;
	
	public TreeBuilder()
	{
		super("Tree Builder");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
		JFrame.setDefaultLookAndFeelDecorated(false);
		Dimension d = new Dimension(800,400);
		this.getContentPane().setPreferredSize(d);
		
		try
		{
			File img = new File("wisp-t 20.png");
			BufferedImage bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 250.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 500.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 750.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.setIconImages(icons);
		root = new DefaultMutableTreeNode(new Stringable("root", "root"));
		tree = new JTree(root);
		model = (DefaultTreeModel) tree.getModel();
		treeScroll = new JScrollPane(tree);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(treeScroll, BorderLayout.CENTER);
		newNodeBtn.addActionListener(this);
		editNodeBtn.addActionListener(this);
		deleteNodeBtn.addActionListener(this);
		saveTreeBtn.addActionListener(this);
		buttons.add(newNodeBtn);
		buttons.add(editNodeBtn);
		buttons.add(deleteNodeBtn);
		allButtons.add(buttons, BorderLayout.NORTH);
		allButtons.add(saveTreeBtn, BorderLayout.SOUTH);
		JPanel upDownPane = new JPanel(new GridLayout(1,6));
		allButtons.add(upDownPane, BorderLayout.CENTER);
		upDownPane.add(new JPanel());
		upDownPane.add(new JPanel());
		upDownPane.add(moveUpBtn);
		upDownPane.add(moveDownBtn);
		upDownPane.add(new JPanel());
		upDownPane.add(loadTree);
		loadTree.addActionListener(this);
		this.getContentPane().add(buttonsScroll, BorderLayout.SOUTH);
		this.getContentPane().setVisible(true);
		pack();	
		//loadTree("defaultTree");
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
		if(source == saveTreeBtn)
		{
			treeSaveName = JOptionPane.showInputDialog(null, 
					"Choose a name for the save file:",
					"Save Tree",
					JOptionPane.PLAIN_MESSAGE);	
			saveTree();
			
			this.dispose();
		}
		else if(source == loadTree)
		{
			treeSaveName = JOptionPane.showInputDialog(null, 
					"Please enter the filename of the pre-configured tree, without "
					+ "\nextensions. (Ex. defaultTree rather than defaultTree.ser)",
					"Load Tree",
					JOptionPane.PLAIN_MESSAGE);	
			loadTree(treeSaveName);
		}
		else
		{
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
				getNode(nodeS);
				exeNew();
			}
			else if(source == editNodeBtn)
			{
				getNode(nodeS);
				exeEdit();
			}
			else if(source == deleteNodeBtn)
			{
				if (root==selectedNode) 
				{
					JOptionPane.showMessageDialog(null, "Root cannot be deleted!");
				}
				else if(source == deleteNodeBtn)
				{
					answer = JOptionPane.showConfirmDialog(null,
							"This will delete the selected branch/leaf, " + nodeS.toString()
									+ ", and all of its branches/leaves. This cannot be undone! Are you sure you want to proceed?",
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
		}//end save if
	}//end actionPerformed
	
	public void getNode(Stringable s)
	{
		String[] nodeStrings = {s.toString(), s.getContent(), s.getTip(0), s.getTip(1), s.getTip(2), s.getTip(3), s.getTip(4), s.getTip(5)};
		Node.main(nodeStrings);
		asideNode = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("node.ser");
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
	}
	
	public void exeNew()
	{
		model.insertNodeInto(asideNode, selectedNode, 0);
		this.repaint();
		this.revalidate();
	}
	public void exeEdit()
	{
		Stringable s = (Stringable)asideNode.getUserObject();
		selectedNode.setUserObject(s);
		model.nodeChanged(selectedNode);
	}
	
	public void saveTree()
	{
		try 
		{//needs to trigger a warning if data will be overwritten
	         FileOutputStream fileOut = new FileOutputStream((treeSaveName+".ser"));
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(tree.getModel());
	         out.close();
	         fileOut.close();
	         System.out.println("Object saved in "+treeSaveName+".ser");
	    }
		catch(IOException i) 
		{
	         i.printStackTrace();
		}//end read try/catch
	}
	
	public void loadTree(String pathName)
	{
		try {
			FileInputStream fileIn = new FileInputStream((pathName+".ser"));
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tree.setModel((TreeModel)in.readObject());
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
	}
}
