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

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class UBNTTroubleshooter extends JFrame implements TreeSelectionListener, ActionListener, ComponentListener//, MouseListener 
{

/*
 *	Variables 
 */
	private static final long serialVersionUID = -1079398159072533776L;
	private ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	
	private Color white =  new Color(1f, 1f, 1f),
			nineGray =  new Color(0.9f, 0.9f, 0.9f),
			eightGray =  new Color(0.8f, 0.8f, 0.8f),
			sevenGray =  new Color(0.7f, 0.7f, 0.7f),
			sixGray =  new Color(0.6f, 0.6f, 0.6f),
			fiveGray =  new Color(0.5f, 0.5f, 0.5f),
			fourGray =  new Color(0.4f, 0.4f, 0.4f),
			threeGray =  new Color(0.3f, 0.3f, 0.3f),
			twoGray =  new Color(0.2f, 0.2f, 0.2f),
			oneGray =  new Color(0.1f, 0.1f, 0.1f),
			black =  new Color(0f, 0f, 0f);
	
	private Border bevel = BorderFactory.createBevelBorder(BevelBorder.LOWERED, nineGray, eightGray, sevenGray, sixGray),
			pad = BorderFactory.createEmptyBorder(3,3,3,3);
	
	private String	username = "User",
					lastTxt = "",
					string1 = null,
					string2 = null,
					string3 = null,
					string4 = null,
					string5 = null,
					string6 = null;
	private WISPTNodeObject selectedWTNO = null;
	
	private DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(),
									previousSelectedNode = selectedNode;
	
	/*
	 * menu stuff
	 */
	private JMenuBar menu = new JMenuBar();
	
		private JMenu file = new JMenu("File");
			private JMenuItem	logIn = new JMenuItem("Log In"),
								logOut = new JMenuItem("Log Out"),
								newUser = new JMenuItem("New User"),
								exit = new JMenuItem("Exit");
			
		private JMenu edit = new JMenu("Edit");
			private JMenu toggle = new JMenu("Toggle");
				private JMenuItem	tTips = new JMenuItem("\u2611 Tips"),
									tNavTree = new JMenuItem("\u2611 Navigation Tree");
				private boolean tTipsOn = true,
								tNavTreeOn = true;
			private JMenuItem	loadTree = new JMenuItem("Load Saved Tree"),
								newTree = new JMenuItem("Create New Tree");
				
		private JMenu navigate = new JMenu("Navigate");
			private JMenuItem	previous = new JMenuItem("Previous Step"),
								next = new JMenuItem("Next Step"),//is only valid if previous step was just called.
								history = new JMenuItem("History");
			
		private JMenu userPrefs = new JMenu(username + " Preferences");
			private JMenuItem setStartup = new JMenuItem("Startup Options");
			private JMenuItem editName = new JMenuItem("Change Username");
			//private JMenuItem setStartup = new JMenuItem("Startup Options");
			
	/*
	 * end menu stuff
	 */
	
	private JPanel contentPane = new JPanel(new BorderLayout()),
					northPane = new JPanel(new GridLayout(1,1)),
					westPad,
					centerPad,
						centerBevel,
							centerPane,
								centerCenterPane,
								centerSouthPane,
					eastPad,
						eastPane,
					southPadPane,
						southPane,
							southUSplit,
							southPSplit;
	
	private JLabel	processingLabel = new JLabel("Done"),
					userLabel = new JLabel(username); 
	
	private JTree tree = new JTree(new DefaultMutableTreeNode("(empty)"));

	private JTextArea mainTxtArea = new JTextArea("Welcome to WISP-T!\nPlease create or load a tree."),
						tipArea = new JTextArea("");
			
	private JCheckBox	check1 = new JCheckBox("first"),
	 					check2 = new JCheckBox("second"),
	  					check3 = new JCheckBox("third"),
	  					check4 = new JCheckBox("fourth"),
	  					check5 = new JCheckBox("fifth"),
	  					check6 = new JCheckBox("last");
	
	private JRadioButton	radio1 = new JRadioButton("first"),
							radio2 = new JRadioButton("second"),
							radio3 = new JRadioButton("third"),
							radio4 = new JRadioButton("fourth");
	
	private ButtonGroup radioButtons = new ButtonGroup();
	
	private JScrollPane treeScroll,
			//centerBevel,
			eastScroll;
	
	private JSplitPane	consoleMsgs,
			centerEastSplit,
			westCenterEastSplit;

/*
 *	Constructors
 */
	
	public UBNTTroubleshooter()
	{
		super();
		
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
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("WISP-T \u00A9 2017");
		this.setContentPane(contentPane);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		contentPane.addComponentListener(this);
		
		/*
		 * MENU/NORTH
		 */
		contentPane.add(northPane, BorderLayout.NORTH);
			northPane.add(menu);
			northPane.setBackground(white);
				menu.add(file);
					file.add(logIn);
						logIn.addActionListener(this);
						logIn.setEnabled(false);
					file.add(logOut);
						logOut.addActionListener(this);
						logOut.setEnabled(false);
					file.add(newUser);
						newUser.addActionListener(this);
						newUser.setEnabled(false);
					file.add(exit);
						exit.addActionListener(this);
				menu.add(edit);
					edit.add(toggle);
						toggle.add(tTips);
							tTips.addActionListener(this);
						toggle.add(tNavTree);
							tNavTree.addActionListener(this);
					edit.addSeparator();
					edit.add(newTree);
					newTree.addActionListener(this);
					edit.add(loadTree);
					loadTree.addActionListener(this);
				menu.add(navigate);
					navigate.add(previous);
						previous.addActionListener(this);
						previous.setEnabled(false);
					navigate.add(next);
						next.addActionListener(this);
						next.setEnabled(false);
					navigate.add(history);
						history.addActionListener(this);
						history.setEnabled(false);
				menu.add(userPrefs);
					userPrefs.add(setStartup);
						setStartup.addActionListener(this);
						setStartup.setEnabled(false);
					userPrefs.add(editName);
						editName.addActionListener(this);
						editName.setEnabled(false);
					
		/*
		 * TREE/WEST+CENTER_EAST	
		 */
				westPad = new JPanel(new GridLayout(1,1));
			  		centerPad = new JPanel(new GridLayout(1,1));
			   		eastPad = new JPanel(new GridLayout(1,1));
			   	centerEastSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPad, eastPad);
		   	westCenterEastSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, westPad, centerEastSplit);
		contentPane.add(westCenterEastSplit, BorderLayout.CENTER);
		
			westCenterEastSplit.setResizeWeight(0.2);
					treeScroll = new JScrollPane(tree);
				westPad.add(treeScroll);
				
					treeScroll.setBorder(bevel);
					treeScroll.setBackground(white);
					
						tree.setBorder(pad);
						tree.setBackground(white);
						tree.setExpandsSelectedPaths(true);
						
				westPad.setBorder(pad);
				westPad.setBackground(white);
			
		/*
		 * TXT/CENTER_EAST(east side of west split pane)
		 */
				centerEastSplit.setResizeWeight(0.75);
				//centerEastSplit.addMouseListener(this);
				
							centerPane  = new JPanel(new BorderLayout());
						centerBevel = new JPanel(new GridLayout(1,1));
						centerBevel.add(centerPane);
					centerPad.add(centerBevel);
						
								centerSouthPane = new JPanel(new FlowLayout());
							centerPane.add(centerSouthPane, BorderLayout.SOUTH);
							
								centerSouthPane.add(radio1);
								centerSouthPane.add(radio2);
								centerSouthPane.add(radio3);
								centerSouthPane.add(radio4);
								
									radioButtons.add(radio1);
									radioButtons.add(radio2);
									radioButtons.add(radio3);
									radioButtons.add(radio4);
									
										radio1.setBackground(white);
										radio2.setBackground(white);
										radio3.setBackground(white);
										radio4.setBackground(white);
										
										radio1.setVisible(true);
										radio2.setVisible(false);
										radio3.setVisible(false);
										radio4.setVisible(false);
										
										radio1.addActionListener(this);
										radio2.addActionListener(this);
										radio3.addActionListener(this);
										radio4.addActionListener(this);
										
								centerSouthPane.setBorder(pad);
								centerSouthPane.setBackground(white);
								
								centerCenterPane = new JPanel(new GridLayout(2,1));	
							centerPane.add(centerCenterPane, BorderLayout.CENTER);
							
								centerCenterPane.add(mainTxtArea);
								
									mainTxtArea.setLineWrap(true);
									mainTxtArea.setEditable(false);
									mainTxtArea.setBorder(null);
									mainTxtArea.setColumns(40);
									
								centerCenterPane.add(tipArea);

									tipArea.setLineWrap(true);
									tipArea.setEditable(false);
									tipArea.setBorder(null);
								
								centerCenterPane.remove(tipArea);	
									
								centerCenterPane.addComponentListener(this);
								centerCenterPane.setBorder(pad);
								centerCenterPane.setBackground(white);
								
							centerPane.setBorder(pad);
							centerPane.setBackground(white);
							
						centerBevel.setBorder(bevel);
						centerBevel.setBackground(white);
						
					centerPad.setBorder(pad);
					centerPad.setBackground(white);
					
				centerEastSplit.setBorder(null);
				centerEastSplit.setBackground(white);
				
		westCenterEastSplit.setBorder(null);
		westCenterEastSplit.setBackground(white);
						
		
		/*
		 * TIPS/EAST
		 */
					eastPane = new JPanel(new GridLayout(6,1));
				eastScroll = new JScrollPane(eastPane);
			eastPad.add(eastScroll);
			
					eastPane.add(check1);
					eastPane.add(check2);
					eastPane.add(check3);
					eastPane.add(check4);
					eastPane.add(check5);
					eastPane.add(check6);
					
						check1.setVisible(true);
						check2.setVisible(false);
						check3.setVisible(false);
						check4.setVisible(false);
						check5.setVisible(false);
						check6.setVisible(false);
						
						check1.addActionListener(this);
						check2.addActionListener(this);
						check3.addActionListener(this);
						check4.addActionListener(this);
						check5.addActionListener(this);
						check6.addActionListener(this);
						
						check1.setBackground(white);
						check2.setBackground(white);
						check3.setBackground(white);
						check4.setBackground(white);
						check5.setBackground(white);
						check6.setBackground(white);
						
					eastPane.setBorder(pad);
					eastPane.setBackground(white);
					
				eastScroll.setBorder(bevel);
				eastScroll.setBackground(white);
				
			eastPad.setBorder(pad);
			eastPad.setBackground(white);
			
		/*
		 * CONSOLE/SOUTH
		 */
			southPadPane = new JPanel(new GridLayout(1,1));
		contentPane.add(southPadPane, BorderLayout.SOUTH);
		
				southPane = new JPanel(new GridLayout(1,2));
			southPadPane.add(southPane);
			
						southUSplit = new JPanel();
						southPSplit = new JPanel();
					consoleMsgs = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, southPSplit, southUSplit);
				southPane.add(consoleMsgs);
				
						southUSplit.add(userLabel);
						southUSplit.setBackground(white);
						southPSplit.add(processingLabel);
						southPSplit.setBackground(white);
						pack();
					consoleMsgs.setDividerLocation(0.40d);
						Dimension d = new Dimension(southPSplit.getWidth()*3, southPSplit.getHeight());
						southPSplit.setMinimumSize(d);
					consoleMsgs.setBorder(null);
					consoleMsgs.setBackground(white);
					
				southPane.setBorder(bevel);
				southPane.setBackground(white);

			southPadPane.setBorder(pad);
			southPadPane.setBackground(white);
			
		contentPane.setBorder(pad);
		contentPane.setBackground(white);
							userLabel.setVisible(true);
			
		pack();
		centerBevel.setMinimumSize(centerBevel.getSize());//this used to be a JScrollPane, but I needed it to shrink so I changed it to a JPanel
		treeScroll.setMinimumSize(treeScroll.getSize());
		eastScroll.setMinimumSize(eastScroll.getSize());
		this.setMinimumSize(this.getSize());
	}//end constructor
	

/*
 *	Methods
 */
	
	public TreeModel getTree(String fileName)
	{
		TreeModel inTree = null;
		try 
		{
	         FileInputStream fileIn = new FileInputStream((fileName+".ser"));
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         inTree = (TreeModel)in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Object read from "+fileName+".ser");
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return inTree;
	}
	
	public void invisibleDeselectedButtons()
	{
		radio1.setVisible(false);
		radio2.setVisible(false);
		radio3.setVisible(false);
		radio4.setVisible(false);
		radioButtons.clearSelection();
		check1.setVisible(false);
		check1.setSelected(false);
		check2.setVisible(false);
		check2.setSelected(false);
		check3.setVisible(false);
		check3.setSelected(false);
		check4.setVisible(false);
		check4.setSelected(false);
		check5.setVisible(false);
		check5.setSelected(false);
		check6.setVisible(false);
		check6.setSelected(false);
	}


/*
 *	Main Method 
 */
	
	public static void main(String[] args) {
		UBNTTroubleshooter window = new UBNTTroubleshooter();
		window.setVisible(true);

	}


/*
 * OVERRIDES
 */
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == newTree)
		{
			TreeBuilder.main(new String[1]);
		}
		else if(source == loadTree)
		{
			String modelString = null;
			
			modelString = JOptionPane.showInputDialog("Please enter the filename of the pre-configured tree, without "
					+ "\nextensions. (Ex. defaultTree rather than defaultTree.ser)");
			if(modelString.equals("") || modelString == null)
			{
				//I know.
			}
			else
			{
				TreeModel model = getTree(modelString);
				tree.setModel(model);
				tree.addTreeSelectionListener(this);
				treeScroll.repaint();
				tree.repaint();
			}

		}
		else if(source == tTips)
		{
			if(tTipsOn)
			{
				tTips.setText("\u2610 Tips");
				eastPad.setVisible(false);
				tTipsOn = false;
			}
			else
			{
				tTips.setText("\u2611 Tips");
				eastPad.setVisible(true);
				centerEastSplit.revalidate();
				centerEastSplit.setDividerLocation(0.75);
				tTipsOn = true;
			}//end inner if
		}
		else if(source == tNavTree)
		{
			if(tNavTreeOn)
			{
				tNavTree.setText("\u2610 Navigation Tree");
				westPad.setVisible(false);
				tNavTreeOn = false;
			}
			else
			{
				tNavTree.setText("\u2611 Navigation Tree");
				westPad.setVisible(true);
				westCenterEastSplit.revalidate();
				westCenterEastSplit.setDividerLocation(0.2);
				tNavTreeOn = true;
			}//end inner if
		}
		else if(source == check1)
		{
			tipTextAreaToggler(check1.isSelected(), 0);
		}
		else if(source == check2)
		{
			tipTextAreaToggler(check2.isSelected(), 1);
		}
		else if(source == check3)
		{
			tipTextAreaToggler(check3.isSelected(), 2);
		}
		else if(source == check4)
		{
			tipTextAreaToggler(check4.isSelected(), 3);
		}
		else if(source == check5)
		{
			tipTextAreaToggler(check5.isSelected(), 4);
		}
		else if(source == check6)
		{
			tipTextAreaToggler(check6.isSelected(), 5);
		}	
		else 
		{
			try
			{
				if(source == radio1)//////////////////////////////////////////////////////////////////needs completion
				{
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(0);
					TreePath path = new TreePath(child.getPath());
					tree.setSelectionPath(path);
					tree.revalidate();
					tree.repaint();
				}	
				else if(source == radio2)
				{
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(1);
					TreePath path = new TreePath(child.getPath());
					tree.setSelectionPath(path);
					tree.revalidate();
					tree.repaint();
					
				}	
				else if(source == radio3)
				{
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(2);
					TreePath path = new TreePath(child.getPath());
					tree.setSelectionPath(path);
					tree.revalidate();
					tree.repaint();
					
				}	
				else if(source == radio4)
				{
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(3);
					TreePath path = new TreePath(child.getPath());
					tree.setSelectionPath(path);
					tree.revalidate();
					tree.repaint();
					
				}//end inner source if
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}//end try/catch
		}//end source if
	}
	
	public void tipTextAreaToggler(boolean selected,int index)
	{
		try
		{
			if(selected)
			{
				lastTxt = tipArea.getText();
				tipArea.setText(lastTxt + "\n:\n" + selectedWTNO.getTips()[index]);
				
				if(!centerCenterPane.isAncestorOf(tipArea))
					centerCenterPane.add(tipArea);
			}
			else
			{
				String tat = tipArea.getText();
				String replacement = tat.replace(("\n:\n" + selectedWTNO.getTips()[index]), " ");
				tipArea.setText(replacement);
				/*
				 * The above .replace() method does not function the first time the event calls this method.
				 * No idea why since it works perfectly every subsequent time. Interestingly, immediately 
				 * afterwards, the tipArea.setText() doesn't work either. But after that, it's all good.
				 */
				if(tipArea.getText().equals(" "))
					centerCenterPane.remove(tipArea);
			}
			centerCenterPane.revalidate();
			centerCenterPane.repaint();
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent arg0) 
	{
		previousSelectedNode = selectedNode;
		selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		selectedWTNO = (WISPTNodeObject)selectedNode.getUserObject();
		if(selectedNode.getChildCount() <= 0)
		{
			//check to see if all the descendent leaves are "invisible". If not, start checking leaf parents for visibility. If so, check if those are a parent of those leaves. If not, do nothing. If so,
		}
		mainTxtArea.setText(selectedWTNO.getContent());
		String[] tips = selectedWTNO.getTips();
		
		DefaultMutableTreeNode child;
		WISPTNodeObject childObject;
		
		if(!selectedNode.getUserObject().equals(previousSelectedNode.getUserObject()))
		{
			invisibleDeselectedButtons();
			System.out.println("IDR run");
			tipArea.setText("");
			centerCenterPane.remove(tipArea);
			centerCenterPane.revalidate();
			centerCenterPane.repaint();
		}
		
		try
		{
			 child = (DefaultMutableTreeNode)selectedNode.getChildAt(0);
			 childObject = (WISPTNodeObject)child.getUserObject();
			radio1.setText(childObject.toString());
			radio1.setVisible(true);

			 child = (DefaultMutableTreeNode)selectedNode.getChildAt(1);
			 childObject = (WISPTNodeObject)child.getUserObject();
			radio2.setText(childObject.toString());
			radio2.setVisible(true);

			 child = (DefaultMutableTreeNode)selectedNode.getChildAt(2);
			 childObject = (WISPTNodeObject)child.getUserObject();
			radio3.setText(childObject.toString());
			radio3.setVisible(true);
	
			 child = (DefaultMutableTreeNode)selectedNode.getChildAt(3);
			 childObject = (WISPTNodeObject)child.getUserObject();
			radio4.setText(childObject.toString());
			radio4.setVisible(true);
		}
		catch(NullPointerException npe)
		{
			;
		}
		catch(ArrayIndexOutOfBoundsException aioobe)
		{
			;
		}
		
		
		
		try {
			if(!tips[0].equals(""))
			{
				check1.setVisible(true);
				check1.setText(tips[0].split(":")[0]);
			}

			if(!tips[1].equals(""))
			{
				check2.setVisible(true);
				check2.setText(tips[1].split(":")[0]);
			}
			

			if(!tips[2].equals(""))
			{
				check3.setVisible(true);
				check3.setText(tips[2].split(":")[0]);
			}
			
			if(!tips[3].equals(""))
			{
				check4.setVisible(true);
				check4.setText(tips[3].split(":")[0]);
			}
			
			if(!tips[4].equals(""))
			{
				check5.setVisible(true);
				check5.setText(tips[4].split(":")[0]);
			}
			

			if(!tips[5].equals(""))
			{
				check6.setVisible(true);
				check6.setText(tips[5].split(":")[0]);
			}
			
		} catch (NullPointerException npe) {
			//npe.printStackTrace();
			check1.setVisible(false);
			check2.setVisible(false);
			check3.setVisible(false);
			check4.setVisible(false);
			check5.setVisible(false);
			check6.setVisible(false);
			//accounts for nodes that are pre "tips[]" implementation 
		}
		

		
	}


	@Override
	public void componentResized(ComponentEvent e) {
		//Object source = e.getSource();
		
	}


	@Override
	public void componentMoved(ComponentEvent e) {

		//centerPane.setSize(3*(int)(contentPane.getWidth()/5), centerPane.getHeight());
		
	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

}