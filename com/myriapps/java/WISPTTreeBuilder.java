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

package com.myriapps.java;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

import java.util.*;
//import java.util.List;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
			loadTreeBtn;
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
			contentPane.setPreferredSize(new Dimension(800,400));
			contentPane.setVisible(true);
		this.setContentPane(contentPane);
		
				
				treeScroll = new JScrollPane(new JPanel());
			contentPane.add(treeScroll, BorderLayout.CENTER);
				
					
					tree = new JTree(new DefaultMutableTreeNode());
					model = (DefaultTreeModel) tree.getModel();
				treeScroll.setViewportView(tree);
					
						root = new DefaultMutableTreeNode(new WISPTNodeObject("root", "root"));
					model.setRoot(root);  model.reload();
					
				buttonsScroll = new JScrollPane(new JPanel());
			contentPane.add(buttonsScroll, BorderLayout.SOUTH);
			
					allButtons = new JPanel(new BorderLayout());
				buttonsScroll.setViewportView(allButtons);
					
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
						upDownPane.add(new JPanel());
						upDownPane.add(new JPanel());
					allButtons.add(upDownPane, BorderLayout.CENTER);
						
							moveUpBtn = new JButton("\u2191");
							moveUpBtn.addActionListener
								(new ActionListener()
								{
									public void
									actionPerformed(ActionEvent e)
									{
										moveBtn_actionPerformed(e, true);
									}
								}
								);
						upDownPane.add(moveUpBtn);
						
							moveDownBtn = new JButton("\u2193");
						upDownPane.add(moveDownBtn);
						
						upDownPane.add(new JPanel());
						
							loadTreeBtn = new JButton("Load Saved tree");
							loadTreeBtn.addActionListener
								(new ActionListener()
								{
									public void
									actionPerformed(ActionEvent e)
									{
										loadTreeBtn_actionPerformed(e);
									}
								}
								);
						upDownPane.add(loadTreeBtn);
					
						saveTreeBtn = new JButton("Save Tree");
						saveTreeBtn.addActionListener
							(new ActionListener()
							{
								public void
								actionPerformed(ActionEvent e)
								{
									saveTreeBtn_actionPerformed(e);
								}
							}
							);
					allButtons.add(saveTreeBtn, BorderLayout.SOUTH);
		pack();	
		//loadTreeBtn("defaultTree");
	}

	public static void 
	main(String[] args) 
	{
		WISPTTreeBuilder tWindow = new WISPTTreeBuilder();
		tWindow.setVisible(true);
	}


	@Override
	public void 
	valueChanged(TreeSelectionEvent e) 
	{		
		selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();	
	}

	public void
	moveBtn_actionPerformed(ActionEvent event, boolean directionUp)
	{
		try
		{
			p("actionPerformed entered");
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
			p("parent instantiated");
			p("selectedNode is child of parent, "+parent.isNodeChild(selectedNode));
			int selectedIndex = parent.getIndex((TreeNode)selectedNode);
			p("selectedIndex instantiated");
			if(directionUp && selectedIndex != 0)
			{
				p("upIf entered");
				model = (DefaultTreeModel)tree.getModel();
				p("model instantiated");
				asideNode = selectedNode;
				model.removeNodeFromParent(selectedNode);
				p("selected node removed");
				model.insertNodeInto(asideNode, parent, selectedIndex - 1);
				selectedNode = asideNode;
				p("selected node inserted");
				tree.setModel(model);
				p("model updated");
				tree.revalidate();
				p("tree revalidated");
				tree.setSelectionPath(new TreePath(selectedNode.getPath()));
				p("selection set");
			}
			else if(!directionUp && selectedIndex != parent.getChildCount()-1)
			{
				p("downIf entered");
				model = (DefaultTreeModel)tree.getModel();
				p("model instantiated");
				asideNode = selectedNode;
				model.removeNodeFromParent(selectedNode);
				p("selected node removed");
				model.insertNodeInto(asideNode, parent, selectedIndex + 1);
				selectedNode = asideNode;
				p("selected node inserted");
				tree.setModel(model);
				p("model updated");
				tree.revalidate();
				p("tree revalidated");
				tree.setSelectionPath(new TreePath(selectedNode.getPath()));
				p("selection set");
			}
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
	}
	
	public void
	saveTreeBtn_actionPerformed(ActionEvent e)
	{	
		saveTree();
		this.dispose();
	}
	
	public void
	loadTreeBtn_actionPerformed(ActionEvent e)
	{
		loadTree();
		tree.revalidate();
	}
	
	@Override
	public void 
	actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		
		int answer = 1;
		WISPTNodeObject nodeS = new WISPTNodeObject("", "");
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
					tree.setModel(model);
					tree.revalidate();
					break;
				case 1:
				case 2:
				default:
					break;
				}//end switch
			}//end selected if
		}//end source if
	}//end actionPerformed
	
	public void 
	getNode(WISPTNodeObject s)
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
		//WISPTNodeBuilder.main(nodeStrings);
		asideNode = null;
		
		try 
		{
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
		p(asideNode.getUserObject().toString());
	}
	
	public void 
	exeNew()
	{
		model.insertNodeInto(asideNode, selectedNode, 0);
		tree.setModel(model);
		tree.revalidate();
	}
	public void 
	exeEdit()
	{
		WISPTNodeObject wtno = (WISPTNodeObject)asideNode.getUserObject();
		selectedNode.setUserObject(wtno);
		model.nodeChanged(selectedNode);
		tree.setModel(model);
		tree.revalidate();
	}
	
	public void 
	saveTree()
	{
		try 
		{//needs to trigger a warning if data will be overwritten
			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString());//creates a empty file in that directory
			JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Serial Tree Files", "tree");
			fc.setFileFilter(filter);
			int val = fc.showSaveDialog(this);//opens an open file dialog
			if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
			{
				String fileName = fc.getSelectedFile().toString();
				if(fc.getSelectedFile().toString().contains(".ser"))
				{
					fileName = fileName.split(".ser")[0];
				}
				FileOutputStream fileOut = new FileOutputStream("Saved Trees/"+fileName+".tree");
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        out.writeObject(tree.getModel());
		        out.close();
		        fileOut.close();
		        p("Object saved in "+fc.getSelectedFile().getName()+".tree");
				this.dispose();
			}
	    }
		catch(IOException i) 
		{
	         i.printStackTrace();
		}//end read try/catch
	}
	
	public void 
	loadTree()
	{
		try 
		{
			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString());//creates a empty file in that directory
			JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Serial Files", "ser");
			fc.setFileFilter(filter);
			int val = fc.showOpenDialog(this);//opens an open file dialog
			if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
			{
				FileInputStream fileIn = new FileInputStream(fc.getSelectedFile());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				tree.setModel((DefaultTreeModel)in.readObject());
				tree.addTreeSelectionListener(this);
				tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				model = (DefaultTreeModel)tree.getModel();
				tree.setEnabled(true);
				treeScroll.repaint();
				tree.repaint();
				in.close();
				fileIn.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void
	p(String p)
	{
		System.out.println(p);
	}
}
