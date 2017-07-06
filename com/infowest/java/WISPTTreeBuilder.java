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

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

import java.util.*;
//import java.util.List;
import java.io.*;

public class WISPTTreeBuilder extends JFrame implements TreeSelectionListener, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5870411020614345178L;
	
	ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	JTree tree;
	String treeSaveName = "name";
	JButton newNodeBtn,
			editNodeBtn,
			deleteNodeBtn,
			saveTreeBtn,
			moveUpBtn,
			moveDownBtn,
			loadTree;
	JPanel contentPane,
				allButtons,
					upDownPane,
					buttons;
	JScrollPane treeScroll,
				buttonsScroll;
	DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(new WISPTNodeObject("root", "root")),
						root,
						asideNode;
	DefaultTreeModel model;
	
	public WISPTTreeBuilder()
	{
		super("Tree Builder");
		JFrame.setDefaultLookAndFeelDecorated(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
			this.setIconImages(icons);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
			contentPane = new JPanel(new BorderLayout());
		this.setContentPane(contentPane);
			contentPane.setPreferredSize(new Dimension(800,400));
		
						root = new DefaultMutableTreeNode(new WISPTNodeObject("root", "root"));
					tree = new JTree(root);
					model = (DefaultTreeModel) tree.getModel();
				treeScroll = new JScrollPane(tree);
			contentPane.add(treeScroll, BorderLayout.CENTER);
				
						
					allButtons = new JPanel(new BorderLayout());
				buttonsScroll = new JScrollPane(allButtons);
			contentPane.add(buttonsScroll, BorderLayout.SOUTH);
					
						buttons = new JPanel(new GridLayout(1,3));
					allButtons.add(buttons, BorderLayout.NORTH);
					
							newNodeBtn = new JButton("New Branch/Leaf");
						buttons.add(newNodeBtn);
							newNodeBtn.addActionListener(this);
							
							editNodeBtn = new JButton("Edit Branch/Leaf");
						buttons.add(editNodeBtn);
							editNodeBtn.addActionListener(this);
							
							deleteNodeBtn = new JButton("Delete Branch/Leaf");
						buttons.add(deleteNodeBtn);
							deleteNodeBtn.addActionListener(this);
						
					
						upDownPane = new JPanel(new GridLayout(1,6));
					allButtons.add(upDownPane, BorderLayout.CENTER);
						upDownPane.add(new JPanel());
						upDownPane.add(new JPanel());
						
							moveUpBtn = new JButton("\u2191");
						upDownPane.add(moveUpBtn);
						
							moveDownBtn = new JButton("\u2193");
						upDownPane.add(moveDownBtn);
						
						upDownPane.add(new JPanel());
						
							loadTree = new JButton("Load Saved tree");
						upDownPane.add(loadTree);
							loadTree.addActionListener(this);
					
						saveTreeBtn = new JButton("Save Tree");
					allButtons.add(saveTreeBtn, BorderLayout.SOUTH);
						saveTreeBtn.addActionListener(this);
			contentPane.setVisible(true);
		pack();	
		//loadTree("defaultTree");
	}

	public static void main(String[] args) {
		WISPTTreeBuilder tWindow = new WISPTTreeBuilder();
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
		WISPTNodeObject nodeS = new WISPTNodeObject("", "");
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
				nodeS = (WISPTNodeObject)selectedNode.getUserObject();
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
	
	public void getNode(WISPTNodeObject s)
	{
		String[] nodeStrings = new String[8];
		try
		{
			nodeStrings[0] = s.toString();
			nodeStrings[1] = s.getContent();
			String[] tipsArr = s.getTips();
			for(int i = 0; i < tipsArr.length; ++i)
			{
				nodeStrings[i+2] = tipsArr[i];
			}
		}
		catch(NullPointerException npe)
		{
			;
		}
		WISPTNodeBuilder.main(nodeStrings);
		asideNode = null;
		
		try {
			FileInputStream fileIn = new FileInputStream("node.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			asideNode = (DefaultMutableTreeNode)in.readObject();
			in.close();
			fileIn.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
		WISPTNodeObject wtno = (WISPTNodeObject)asideNode.getUserObject();
		selectedNode.setUserObject(wtno);
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
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
