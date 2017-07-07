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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

public class WISPT extends JFrame implements TreeSelectionListener//, ActionListener, ComponentListener, MouseListener 
{

/*
 *	TODO Variables 
 */
	private static final long serialVersionUID = -1079398159072533776L;
	private ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	
	private Color white =  new Color(1f, 1f, 1f),
			nineGray =  new Color(0.9f, 0.9f, 0.9f),
			eightGray =  new Color(0.8f, 0.8f, 0.8f),
			sevenGray =  new Color(0.7f, 0.7f, 0.7f),
			sixGray =  new Color(0.6f, 0.6f, 0.6f)/*,
			fiveGray =  new Color(0.5f, 0.5f, 0.5f),
			fourGray =  new Color(0.4f, 0.4f, 0.4f),
			threeGray =  new Color(0.3f, 0.3f, 0.3f),
			twoGray =  new Color(0.2f, 0.2f, 0.2f),
			oneGray =  new Color(0.1f, 0.1f, 0.1f),
			black =  new Color(0f, 0f, 0f)*/
			;
	
	private Border bevelUp = BorderFactory.createBevelBorder(BevelBorder.LOWERED, nineGray, eightGray, sevenGray, sixGray),
			bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED, nineGray, eightGray, sevenGray, sixGray),
			pad = BorderFactory.createEmptyBorder(3,3,3,3);
	
	private String	username = "User",
					lastTxt = ""//,
					//string1 = null,
					//string2 = null,
					//string3 = null,
					//string4 = null,
					//string5 = null,
					//string6 = null
					;
	private boolean processingOn = false;
	
	private WISPTNodeObject selectedWTNO = null;
	
	private DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(),
									previousSelectedNode = selectedNode;
	
	private JDialog startupSettingsDialog;
	//TODO finish settings
		private JTextArea changeUserPassword,
							changeAdminPassword,
							changeInvisibleString;
		
		private JCheckBox trainingModeCheckBox;
		
		private boolean trainingModeOn;
	
		private JPanel settingsContentPane,
						adminTab,
						userTab,
							adminSettings,
							userSettings,
								adminSettingsCenter,
								userSettingsCenter,
								adminSettingsSouth;
		private JButton save,
						cancel;
	
	private JTabbedPane settingsCenterTabbedPane;
	
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
	
	private JTree tree = new JTree(new DefaultMutableTreeNode(new WISPTNodeObject("(empty)","(empty)")));

	private JTextArea mainTxtArea = new JTextArea("Welcome to WISP-T!\nPlease create or load a tree."),
						tipArea = new JTextArea("");
			
	private JCheckBox	check1 = new JCheckBox("first"),
	 					check2 = new JCheckBox("second"),
	  					check3 = new JCheckBox("third"),
	  					check4 = new JCheckBox("fourth"),
	  					check5 = new JCheckBox("fifth"),
	  					check6 = new JCheckBox("last"),
	  					processingOnCheck;
	
	private JRadioButton	radio1,
							radio2,
							radio3,
							radio4;
	
	private ButtonGroup radioButtons = new ButtonGroup();
	
	private JScrollPane treeScroll,
			//centerBevel,
			eastScroll;
	
	private JSplitPane	consoleMsgs,
			centerEastSplit,
			westCenterEastSplit;
	
	/*
	 * TODO MENU DECLARATIONS
	 */
	private JMenuBar menu = new JMenuBar();
	
		private JMenu file = new JMenu("File");
			private JMenuItem	loadTree = new JMenuItem("Load Saved Tree"),
								logIn = new JMenuItem("Log In"),
								logOut = new JMenuItem("Log Out"),
								newUser = new JMenuItem("New User"),
								exit = new JMenuItem("Exit");
			
		private JMenu edit = new JMenu("Edit");
			private JMenu toggle = new JMenu("Toggle");
				private JMenuItem	tTips = new JMenuItem("\u2611 Tips"),
									tNavTree = new JMenuItem("\u2611 Navigation Tree");
				private boolean tTipsOn = true,
								tNavTreeOn = true;
			private JMenuItem	openTreeBuilder = new JMenuItem("Open TreeBuilder"),
								unlockTreeNode = new JMenuItem("Unlock Selected Branch/Leaf");
				
		private JMenu navigate = new JMenu("Navigate");
			private JMenuItem	previous = new JMenuItem("Previous Step"),
								next = new JMenuItem("Next Step"),//is only valid if previous step was just called.
								history = new JMenuItem("History");
			
		private JMenu userPrefs = new JMenu(username + " Preferences");
			private JMenuItem startupSettings = new JMenuItem("Settings");
			private JMenuItem editName = new JMenuItem("Change Username");


	

/*
 *	TODO Constructors
 */
	
	public WISPT()
	{
		super();
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | 
				InstantiationException | 
				IllegalAccessException | 
				UnsupportedLookAndFeelException e1) 
		{
			e1.printStackTrace();
		}
		setDefaultLookAndFeelDecorated(true);
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
		JFrame.setDefaultLookAndFeelDecorated(false);
		ToolTipManager.sharedInstance().setDismissDelay(60000);
		
		//contentPane.addComponentListener(this);
		/*
		 * TODO MENU/NORTH
		 */
		contentPane.add(northPane, BorderLayout.NORTH);
			northPane.add(menu);
			
				menu.add(file);
				
					file.add(loadTree);
						loadTree.addActionListener
							(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e)
								{
									loadTree_actionPerformed(e);
								}//end actionPerformed
							}//end listener	
							);//listener added
						
					file.add(logIn);
						logIn.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME logIn_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						logIn.setEnabled(false);
						
					file.add(logOut);
						logOut.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME logOut_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						logOut.setEnabled(false);
						
					file.add(newUser);
						newUser.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME newUser_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						newUser.setEnabled(false);
						
					file.add(exit);
						exit.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									 exit_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						
				menu.add(edit);
				
					edit.add(toggle);
					
						toggle.add(tTips);
							tTips.addActionListener
							(new ActionListener()
							{	
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									tTips_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
							
						toggle.add(tNavTree);
							tNavTree.addActionListener
							(new ActionListener()
							{	
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									tNavTree_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//actionListener added
							
					edit.addSeparator();
					
					edit.add(openTreeBuilder);
						openTreeBuilder.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									openTreeBuilder_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						
					edit.add(unlockTreeNode);
						unlockTreeNode.addActionListener
							(new ActionListener()
							{
								@Override
								public void 
								actionPerformed(ActionEvent e)
								{
									unlockTreeNode_actionPerformed(e);
								}
							}
							);


				menu.add(navigate);
				
					navigate.add(previous);
					
						previous.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME previous_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						previous.setEnabled(false);
						
					navigate.add(next);
						next.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME next_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						next.setEnabled(false);
						
					navigate.add(history);
						history.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME history_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						history.setEnabled(false);
						
				menu.add(userPrefs);
				
					userPrefs.add(startupSettings);
						startupSettings.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									startupSettings_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						startupSettings.setEnabled(true);
							startupSettingsDialog = new JDialog(this, "Settings");
							startupSettingsDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
							startupSettingsDialog.setVisible(false);
							startupSettingsDialog.setSize(800, 400);
							
								settingsContentPane = new JPanel(new BorderLayout());
							startupSettingsDialog.setContentPane(settingsContentPane);
							
									settingsCenterTabbedPane = new JTabbedPane();
								settingsContentPane.add(settingsCenterTabbedPane, BorderLayout.CENTER);
								
										adminTab = new JPanel(new GridLayout(1,1));
									settingsCenterTabbedPane.add("Administration", adminTab);
									
											adminSettings = new JPanel(new BorderLayout());
											/*
											 * Save button need be the only element with a listener.
											 * On action, it will set all settings according to what
											 * is currently in the settings pane elements. If a check
											 * box is checked, that setting becomes enabled. Whatever
											 * resides in a text field becomes that setting's new text.
											 * 
											 */
										adminTab.add(adminSettings);
										// TODO Finish settings
												adminSettingsCenter = new JPanel(new FlowLayout());
											adminSettings.add(adminSettingsCenter, BorderLayout.CENTER);
											
													processingOnCheck = new JCheckBox("Detailed Process Tooltips");
												adminSettingsCenter.add(processingOnCheck);
													processingOnCheck.setSelected(false);
													
													trainingModeCheckBox = new JCheckBox("Toggle Training Mode");
												adminSettingsCenter.add(trainingModeCheckBox);
													
													changeUserPassword = new JTextArea("password");
												adminSettingsCenter.add(changeUserPassword);
												
													changeAdminPassword = new JTextArea("WISPadmin");
												adminSettingsCenter.add(changeAdminPassword);
												
													changeInvisibleString = new JTextArea("This Branch/Leaf has been locked!");
												adminSettingsCenter.add(changeInvisibleString);
												
												adminSettingsSouth = new JPanel(new GridLayout(2,1));
											adminSettings.add(adminSettingsSouth, BorderLayout.SOUTH);
												
													save = new JButton("Save");
												adminSettingsSouth.add(save);
													save.addActionListener
														(new ActionListener()
															{
																@Override
																public void
																actionPerformed(ActionEvent e)
																{
																	processingOn = processingOnCheck.isSelected();
																	trainingModeOn = trainingModeCheckBox.isSelected();
																	WISPTNodeObject.setUserPass(changeUserPassword.getText());
																	WISPTNodeObject.setAdminPass(changeAdminPassword.getText());;
																	WISPTNodeObject.setInvisibleString(changeInvisibleString.getText());
																	
																	startupSettingsDialog.setVisible(false);
																}//end actionPerformed
															}//end ActionListener
														);//actionListener added
											
										userTab = new JPanel(new GridLayout(1,1));
									settingsCenterTabbedPane.add("User Preferences", userTab);
											
											userSettings = new JPanel(new FlowLayout());
										userTab.add(userSettings);
											
											userSettings.add(new JCheckBox("check"));
											userSettings.add(new JCheckBox("check"));
											userSettings.add(new JCheckBox("check"));
									
								settingsContentPane.setBorder(pad);
								settingsContentPane.setBackground(white);
							
								
							
					userPrefs.add(editName);
						editName.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									//FIXME editName_actionPerformed(e);
								}//end actionPerformed
							}//end ActionListener
							);//ActionListener added
						editName.setEnabled(false);
				
				//menu.setBackground(white);
				menu.setBorder(bevelUp);

			northPane.setBackground(white);	
			northPane.setBorder(pad);
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
				
					treeScroll.setBorder(bevelUp);
					treeScroll.setBackground(white);
					
						tree.setBorder(pad);
						tree.setBackground(white);
						tree.setExpandsSelectedPaths(true);
						tree.setEnabled(false);
						
				westPad.setBorder(pad);
				westPad.setBackground(white);
			
		/*
		 * TODO TXT/CENTER_EAST(east side of west split pane)
		 */
				centerEastSplit.setResizeWeight(0.75);
				//centerEastSplit.addMouseListener(this);
				
							centerPane  = new JPanel(new BorderLayout());
						centerBevel = new JPanel(new GridLayout(1,1));
						centerBevel.add(centerPane);
					centerPad.add(centerBevel);
						
								centerSouthPane = new JPanel(new FlowLayout());
							centerPane.add(centerSouthPane, BorderLayout.SOUTH);
							
										radio1 = new JRadioButton("first");
									radioButtons.add(radio1);
								centerSouthPane.add(radio1);
										radio1.setBackground(white);
										radio1.addActionListener
											(new ActionListener()
											{
												@Override
												public void
												actionPerformed(ActionEvent e)
												{
													radio_actionPerformed(e, 0);
												}//end actionPerformed
											}//end ActionListener
											);//ActionListener added
										radio1.setVisible(true);

										radio2 = new JRadioButton("second");
									radioButtons.add(radio2);
								centerSouthPane.add(radio2);
										radio2.setBackground(white);
										radio2.addActionListener
											(new ActionListener()
											{
												@Override
												public void
												actionPerformed(ActionEvent e)
												{
													radio_actionPerformed(e, 1);
												}//end actionPerformed
											}//end ActionListener
											);//ActionListener added
										radio2.setVisible(false);
										
										radio3 = new JRadioButton("third");
									radioButtons.add(radio3);
								centerSouthPane.add(radio3);
										radio3.setBackground(white);
										radio3.addActionListener
											(new ActionListener()
											{
												@Override
												public void
												actionPerformed(ActionEvent e)
												{
													radio_actionPerformed(e, 2);
												}//end actionPerformed
											}//end ActionListener
											);//ActionListener added
										radio3.setVisible(false);
										
										radio4 = new JRadioButton("fourth");
									radioButtons.add(radio4);
								centerSouthPane.add(radio4);
										radio4.setBackground(white);
										radio4.addActionListener
											(new ActionListener()
											{
												@Override
												public void
												actionPerformed(ActionEvent e)
												{
													radio_actionPerformed(e, 3);
												}//end actionPerformed
											}//end ActionListener
											);//ActionListener added
										radio4.setVisible(false);

								centerSouthPane.setBorder(pad);
								centerSouthPane.setBackground(white);
								
								centerCenterPane = new JPanel(new GridLayout(2,1));	
							centerPane.add(centerCenterPane, BorderLayout.CENTER);
							
								centerCenterPane.add(mainTxtArea);
								
									mainTxtArea.setLineWrap(true);
									mainTxtArea.setEditable(false);
									mainTxtArea.setBorder(null);
									mainTxtArea.setColumns(25);
									
								centerCenterPane.add(tipArea);

									tipArea.setLineWrap(true);
									tipArea.setEditable(false);
									tipArea.setBorder(null);
								
								centerCenterPane.remove(tipArea);	
									
								//centerCenterPane.addComponentListener(this);
								centerCenterPane.setBorder(pad);
								centerCenterPane.setBackground(white);
								
							centerPane.setBorder(pad);
							centerPane.setBackground(white);
							
						centerBevel.setBorder(bevelUp);
						centerBevel.setBackground(white);
						
					centerPad.setBorder(pad);
					centerPad.setBackground(white);
					
				centerEastSplit.setBorder(null);
				centerEastSplit.setBackground(white);
				
			westCenterEastSplit.setBorder(null);
			westCenterEastSplit.setBackground(white);
						
		
		/*
		 * TODO TIPS/EAST
		 */
							eastPane = new JPanel(new GridLayout(6,1));
						eastScroll = new JScrollPane(eastPane);
					eastPad.add(eastScroll);
					
							//FIXME correct checkbox blocking
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
								
								check1.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check1.isSelected(), 0);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								check2.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check2.isSelected(), 1);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								check3.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check3.isSelected(), 2);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								check4.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check4.isSelected(), 3);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								check5.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check5.isSelected(), 4);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								check6.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											check_actionPerformed(e, check6.isSelected(), 5);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
								
								check1.setBackground(white);
								check2.setBackground(white);
								check3.setBackground(white);
								check4.setBackground(white);
								check5.setBackground(white);
								check6.setBackground(white);
								
							eastPane.setBorder(pad);
							eastPane.setBackground(white);
							
						eastScroll.setBorder(bevelUp);
						eastScroll.setBackground(white);
						
					eastPad.setBorder(pad);
					eastPad.setBackground(white);
			
		/*
		 * TODO CONSOLE/SOUTH
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
					
				southPane.setBorder(bevelUp);
				southPane.setBackground(white);

			southPadPane.setBorder(pad);
			southPadPane.setBackground(white);
			
		contentPane.setBorder(bevel);
		contentPane.setBackground(white);
		this.setBackground(white);
							userLabel.setVisible(true);
			
		pack();
		centerSouthPane.setSize(centerSouthPane.getWidth(), centerSouthPane.getHeight()*2);
		centerSouthPane.setPreferredSize(centerSouthPane.getSize());
		centerBevel.setMinimumSize(centerBevel.getSize());//this used to be a JScrollPane, but I needed it to shrink so I changed it to a JPanel
		treeScroll.setMinimumSize(treeScroll.getSize());
		eastScroll.setMinimumSize(eastScroll.getSize());
		this.setMinimumSize(this.getSize());
		this.setSize(800, 400);
		check1.setVisible(false);
		radio1.setVisible(false);
		
	}//end constructor
	
/*
 *	TODO Main Method 
 */
	
	public static void 
	main(String[] args) 
	{
		WISPT window = new WISPT();
		window.setVisible(true);

	}

/*
 *	TODO Methods
 */
	
	public void
	unlockTreeNode_actionPerformed(ActionEvent e)
	{
		try
		{
			selectedWTNO.unlockNode();//dialog asking for PW
			selectedNode.setUserObject(selectedWTNO);
			tree.clearSelection();
			tree.setSelectionPath(new TreePath(selectedNode.getPath()));
			tree.revalidate();
			tree.repaint();
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end unlockTreeNodeAP
	
	public void
	tNavTree_actionPerformed(ActionEvent e)
	{
		try
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
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end tNavTreeAP
	
	public void
	loadTree_actionPerformed(ActionEvent e)
	{
		try
		{
			TreeModel model = getTreeModel();
			tree.setModel(model);
			tree.addTreeSelectionListener(this);
			tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			tree.setEnabled(true);
			treeScroll.repaint();
			tree.repaint();

		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end loadTree AP
	
	public void
	tTips_actionPerformed(ActionEvent e)
	{
		try
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
			}//end if
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end tTips AP
	
	public void
	check_actionPerformed(ActionEvent e, boolean selected, int index)
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
				 * Since fixed.
				 */
				if(tipArea.getText().equals(" "))
				{
					centerCenterPane.remove(tipArea);
				}//end emptyArea if
			}//end selected if
			centerCenterPane.revalidate();
			centerCenterPane.repaint();
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end check AP

	public void
	startupSettings_actionPerformed(ActionEvent e)
	{
		try
		{
			startupSettingsDialog.setVisible(true);
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end startupSettings AP
	
	public void
	radio_actionPerformed(ActionEvent e, int index)
	{
		try
		{
			DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(index);
			TreePath path = new TreePath(child.getPath());
			tree.setSelectionPath(path);
			tree.revalidate();
			tree.repaint();
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end radio AP
	
	public void
	exit_actionPerformed(ActionEvent e)
	{
		try
		{
		//XXX there is a better way to do this, lookup
			this.dispose();
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end exitAP
	
	public void
	openTreeBuilder_actionPerformed(ActionEvent e)
	{
		try
		{
			WISPTTreeBuilder.main(new String[1]);
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processingOn)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}
	
	// TODO end of actionPerformed methods
	public void
	y_actionPerformed(ActionEvent e)
	{
		//stuffHappens
	}//end AP
	
	// TODO end of actionPerformed methods
	
	public TreeModel 
	getTreeModel()
	{
		TreeModel inTree = tree.getModel();
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
				File selectedFile = fc.getSelectedFile();//grabs the selected file
				FileInputStream fileIn = new FileInputStream(selectedFile);
		        ObjectInputStream in = new ObjectInputStream(fileIn); //processes it as a serialized object
		        inTree = (TreeModel)in.readObject();//grabs the TreeModel of that object and assigns to the returned variable
		        in.close();
		        fileIn.close();
		        System.out.println("Object read from "+selectedFile.getName());
			}//end if
	    }
		catch(Exception e)
		{
			e.printStackTrace();
			return tree.getModel();//when something goes wrong, returns the current model: no changes are made.
		}
		return inTree;
	}
	
	public void 
	invisibleDeselectedButtons()
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

	
	
	
	
	public void
	incrementCounter()
	{
		if(trainingModeOn)
		{
			if(selectedWTNO.getCounter()<WISPTNodeObject.getUserMax())
			{
				selectedWTNO.setCounter();
			}
			else
			{
				selectedWTNO.setContentVisible(false);
				selectedNode.setUserObject(selectedWTNO);
			}//end counter or hide if
		}//end training mode if
	}
	


// TODO treeValueChaned override
	@Override
	public void 
	valueChanged(TreeSelectionEvent arg0) 
	{
		if(tree.getLastSelectedPathComponent() == null)
		{
			tree.revalidate();
			tree.repaint();
		}
		else
		{
			previousSelectedNode = selectedNode;
			selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			selectedWTNO = (WISPTNodeObject)selectedNode.getUserObject();
			if(!selectedNode.isLeaf())
			{
				boolean allChildrenInvisible = true;
				for(int i = 0; i < selectedNode.getChildCount(); ++i)
				{
					DefaultMutableTreeNode child = (DefaultMutableTreeNode)selectedNode.getChildAt(i);
					WISPTNodeObject childObject = (WISPTNodeObject)child.getUserObject();
					if(!childObject.getContent().equals(childObject.getInvisibleString()))
					{
						allChildrenInvisible = false;
						break;
					}
				}
				if(allChildrenInvisible)
				{
					incrementCounter();
				}
			}
			else
			{
				incrementCounter();
			}
			mainTxtArea.setText(selectedWTNO.getContent());
			String[] tips = selectedWTNO.getTips();
			
			DefaultMutableTreeNode child;
			WISPTNodeObject childObject;
			
			if(!selectedNode.getUserObject().equals(previousSelectedNode.getUserObject()))
			{
				invisibleDeselectedButtons();
				tipArea.setText("");
				centerCenterPane.remove(tipArea);
				centerCenterPane.revalidate();
				centerCenterPane.repaint();
				System.out.println(selectedWTNO.getContent());
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
	}
	
	/* TODO beginning of old overrrides
	@Override
	public void 
	actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
			String sourceString = source.toString();
			String[] sourceArray = sourceString.split(",");
			sourceString = sourceArray[sourceArray.length-1];
			sourceString = sourceString.replaceAll("\u2610 ", "");
			sourceString = sourceString.replaceAll("\u2611 ", "");
			sourceString = sourceString.replaceFirst("text=", "");
			sourceString = sourceString.replaceFirst("]", "");
			System.out.println(sourceString);
			
			Here I was wanting to convert the object to something that a switch
			statement could process (like an enum) but I realized there was no way
			to convert the variable names for the radio buttons and tool tips into
			a string with which to define an enum.
		try
		{
			//if(source == openTreeBuilder)
			{
				WISPTTreeBuilder.main(new String[1]);
			}
			//else if(source == loadTree)
			{
				TreeModel model = getTreeModel();
				tree.setModel(model);
				tree.addTreeSelectionListener(this);
				tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				tree.setEnabled(true);
				treeScroll.repaint();
				tree.repaint();
	
			}
			//else if(source == tTips)
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
			//else if(source == tNavTree)
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
			else if(source == unlockTreeNode)
			{
				selectedWTNO.unlockNode();//dialog asking for PW
				selectedNode.setUserObject(selectedWTNO);
				tree.clearSelection();
				tree.setSelectionPath(new TreePath(selectedNode.getPath()));
				tree.revalidate();
				tree.repaint();
			}
			else if(source == startupSettings)
			{
				startupSettingsDialog.setVisible(true);
			}
			else if(source == radio1)
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
				
			}//end source if
		}
	}*/
	// TODO end of old overrides, try/catch for future reference
	
/*
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
		
	}


	@Override
	public void componentHidden(ComponentEvent e) {
				
	}*/

}
