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
import java.awt.Dialog.ModalityType;

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
	
	private Border bevelDwn = BorderFactory.createBevelBorder(BevelBorder.LOWERED, nineGray, eightGray, sevenGray, sixGray),
			bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED, nineGray, eightGray, sevenGray, sixGray),
			pad = BorderFactory.createEmptyBorder(3,3,3,3);
	
/*
 * TODO current user info
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
/*
 * end user info FIXME all need instantiation through a user login JOptionPane
 */
	
	private String lastTxt = "";
	
	private WISPTNodeObject selectedWTNO = null;
	
	private DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(),
									previousSelectedNode = selectedNode,
									asideNode = previousSelectedNode;
	
	private JDialog startupSettingsDialog;
	//TODO finish settings
		private JTextArea changeUserPassword,
							changeAdminPassword,
							changeLockedMessage;
		
		private JSpinner userUnlocksSpinner;
		
		private JCheckBox trainingModeCheckBox,
							enableEditorMode,
		  					processingOnCheck;
	
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
	
	private JPanel 
	contentPane,
		northPane;
		
		private JSplitPane
		westCenterEastSplit,
			westPane;
				private JPanel
				westNorthPad;
					private JScrollPane 
					treeScroll;
				private JPanel
				westSouthPad,
					westSouthEditorBtns;
			
			private JSplitPane	
			centerEastSplit;	
				private JPanel
				centerPad,
					centerBevel,
						centerPane,
							centerCenterPane,
							centerSouthPane,
							
				eastPad;
					private JScrollPane	
					eastScroll;
						private JPanel			
						eastPane,
			
		southPadPane,
			southPane;
				private JSplitPane
				consoleMsgs;
					private JPanel
					southUSplit,
					southPSplit;
	
	private JLabel	processingLabel = new JLabel("Done"),
					userLabel = new JLabel(userName); 
	
	private JTree tree;
	
	private DefaultTreeModel model;

	private JTextArea mainTxtArea,
						tipArea;
			
	private JCheckBox	check1,
	 					check2,
	  					check3,
	  					check4,
	  					check5,
	  					check6;
	
	private JRadioButton	radio1,
							radio2,
							radio3,
							radio4;
	
	private ButtonGroup radioButtons;
	
	private JButton addNodeBtn,
					removeNodeBtn,
					editNodeBtn,
					upNodeBtn,
					downNodeBtn;
	
	/*
	 * TODO MENU DECLARATIONS
	 */
	private JMenuBar menu;
	
		private JMenu file;
			private JMenuItem	newTree,
								loadTree,
								saveTree,
								logIn,
								logOut,
								newUser,
								exit;
			
		private JMenu edit;
			private JMenu toggle;
				private JMenuItem	tTips,
									tNavTree;
			private JMenuItem	openTreeBuilder,
								unlockTreeNode;
				
		private JMenu navigate;
			private JMenuItem	previous,
								next,//is only valid if previous step was just called.
								history;
			
		private JMenu userPrefs;
			private JMenuItem startupSettings;
			private JMenuItem editName;


	

/*
 *	TODO Constructors
 */
	
	public WISPT()
	{
		super();
		//FIXME created a deafult profile from which to load XXX instantiation
		WISPTUserProfile defaultUser = new WISPTUserProfile(false);
		saveSerializedObject("/User Profiles", "User Profile", "user", defaultUser);
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
		this.setBackground(white);
		
		JFrame.setDefaultLookAndFeelDecorated(false);
		ToolTipManager.sharedInstance().setDismissDelay(60000);
		setDefaultLookAndFeelDecorated(true);
		
		 contentPane = new JPanel(new BorderLayout());
		 contentPane.setBorder(null);
		 contentPane.setBackground(white);
		this.setContentPane(contentPane);
		
		/*
		 * TODO MENU/NORTH
		 */
			northPane = new JPanel(new GridLayout(1,1));
			northPane.setBackground(white);	
			northPane.setBorder(null);
		contentPane.add(northPane, BorderLayout.NORTH);			

				menu = new JMenuBar();
				menu.setBorder(null);
			northPane.add(menu);

			
					file = new JMenu("File");
				menu.add(file);
				
						newTree = new JMenuItem("Create New Tree");
						newTree.addActionListener
							(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e)
								{
									newTree_actionPerformed(e);
								}//end actionPerformed
							}//end listener	
							);//listener added
					file.add(newTree);
				
						loadTree = new JMenuItem("Load Saved Tree");
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
					file.add(loadTree);
					
						saveTree = new JMenuItem("Save Current Tree");
						saveTree.addActionListener
							(new ActionListener()
							{
								@Override
								public void actionPerformed(ActionEvent e)
								{
									saveTree_actionPerformed(e);
								}//end actionPerformed
							}//end listener	
							);//listener added
					file.add(saveTree);
						
						
						
						logIn = new JMenuItem("Log In");
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
					file.add(logIn);
						
						
						logOut = new JMenuItem("Log Out");
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
					file.add(logOut);
						
						
						newUser = new JMenuItem("New User");
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
					file.add(newUser);
						
						
						exit = new JMenuItem("Exit");
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
					file.add(exit);
					
					
					edit = new JMenu("Edit");
				menu.add(edit);
				
						toggle = new JMenu("Toggle");
					edit.add(toggle);
					
							tipsVisible = true;
							
							tTips = new JMenuItem("\u2611 Tips");
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
						toggle.add(tTips);
							
								treeVisible = true;
							
							tNavTree = new JMenuItem("\u2611 Navigation Tree");
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
						toggle.add(tNavTree);
							
					edit.addSeparator();

						openTreeBuilder = new JMenuItem("Open TreeBuilder");
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
					edit.add(openTreeBuilder);
					
						unlockTreeNode = new JMenuItem("Unlock Selected Branch/Leaf");
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
					edit.add(unlockTreeNode);
						
					navigate = new JMenu("Navigate");
				menu.add(navigate);
	
						previous = new JMenuItem("Previous Step");
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
					navigate.add(previous);
						
						next = new JMenuItem("Next Step");//is only valid if previous step was just called.
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
					navigate.add(next);
						
						history = new JMenuItem("History");
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
					navigate.add(history);
						
					userPrefs = new JMenu(userName + " Preferences");
				menu.add(userPrefs);
				
						startupSettings = new JMenuItem("Settings");
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
					userPrefs.add(startupSettings);
					
					//TODO startupSettingsDialog
					
							startupSettingsDialog = new JDialog(this, "Settings");
							startupSettingsDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
							startupSettingsDialog.setVisible(false);
							startupSettingsDialog.setSize(800, 400);
							
								settingsContentPane = new JPanel(new BorderLayout());
								settingsContentPane.setBorder(pad);
								settingsContentPane.setBackground(white);
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
												adminSettingsCenter = new JPanel();
												adminSettingsCenter.setLayout(new BoxLayout(adminSettingsCenter, BoxLayout.PAGE_AXIS));
											adminSettings.add(adminSettingsCenter, BorderLayout.CENTER);
											
													processingOnCheck = new JCheckBox("Enable Detailed Process Tooltips");
													processingOnCheck.setSelected(processTooltipsEnabled);
												adminSettingsCenter.add(processingOnCheck);
													
													trainingModeCheckBox = new JCheckBox("Enable Training Mode");
													trainingModeCheckBox.setSelected(trainingModeEnabled);
												adminSettingsCenter.add(trainingModeCheckBox);
												
													enableEditorMode = new JCheckBox("Enable Editor Mode");
													enableEditorMode.setSelected(editorModeEnabled);
												adminSettingsCenter.add(enableEditorMode);
													
													changeUserPassword = new JTextArea(userPassword);
												adminSettingsCenter.add(changeUserPassword);
												
													changeAdminPassword = new JTextArea("WISPadmin");
												adminSettingsCenter.add(changeAdminPassword);
												
													changeLockedMessage = new JTextArea(lockedMessage);
												adminSettingsCenter.add(changeLockedMessage);
												
													userUnlocksSpinner = new JSpinner(new SpinnerNumberModel(5,1,25,1));
													userUnlocksSpinner.setMaximumSize(new Dimension(60, 30));
												adminSettingsCenter.add(userUnlocksSpinner);
												
												adminSettingsSouth = new JPanel(new GridLayout(2,1));
											adminSettings.add(adminSettingsSouth, BorderLayout.SOUTH);
												
													save = new JButton("Save");
													save.addActionListener
													(new ActionListener()
													{
														@Override
														public void
														actionPerformed(ActionEvent e)
														{
															saveSettings_actionPerformed(e);
														}//end actionPerformed
													}//end ActionListener
													);//actionListener added'
												adminSettingsSouth.add(save);
													cancel = new JButton("Cancel");
													//action listener that will hide and revert to onset state
												adminSettingsSouth.add(cancel);
											
										userTab = new JPanel(new GridLayout(1,1));
									settingsCenterTabbedPane.add("User Preferences", userTab);
											
											userSettings = new JPanel(new FlowLayout());
										userTab.add(userSettings);
											
											userSettings.add(new JCheckBox("check"));
											userSettings.add(new JCheckBox("check"));
											userSettings.add(new JCheckBox("check"));
									
							
						editName = new JMenuItem("Change Username");
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
					userPrefs.add(editName);
				

		/*
		 * TODO TREE/WEST+CENTER_EAST	
		 */   	
		   	westCenterEastSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JPanel(), new JPanel());
			westCenterEastSplit.setResizeWeight(0.2);
			westCenterEastSplit.setBorder(pad);
			westCenterEastSplit.setBackground(white);
			westCenterEastSplit.setDividerSize(2);
		contentPane.add(westCenterEastSplit, BorderLayout.CENTER);
		
				
				westPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JPanel(),new JLabel());
				westPane.setBorder(pad);
				westPane.setBackground(white);
				westPane.setDividerSize(2);
			westCenterEastSplit.setLeftComponent(westPane);
		
					westNorthPad = new JPanel(new GridLayout(1,1));
					westNorthPad.setBorder(bevelDwn);
					westNorthPad.setBackground(white);
				westPane.setTopComponent(westNorthPad);;
				
						treeScroll = new JScrollPane();
						treeScroll.setBorder(null);
						treeScroll.setBackground(white);
					westNorthPad.add(treeScroll);
				
							tree = new JTree(new DefaultMutableTreeNode(new WISPTNodeObject("(empty)","(empty)")));
							model = (DefaultTreeModel)tree.getModel();
							tree.setBorder(null);
							tree.setBackground(white);
							tree.setExpandsSelectedPaths(true);
							tree.setEnabled(false);
						treeScroll.setViewportView(tree);
					
					westSouthPad = new JPanel(new GridLayout(1,1));
					westSouthPad.setBorder(null);
					westSouthPad.setBackground(white);/*
				westPane.setBottomComponent(westSouthPad);*/
					
						westSouthEditorBtns = new JPanel(new GridLayout(2,3));
						westSouthEditorBtns.setBorder(null);
						westSouthEditorBtns.setBackground(white);
					westSouthPad.add(westSouthEditorBtns);
						
							addNodeBtn = new JButton("+");
							addNodeBtn.addActionListener
								(new ActionListener()
								{
									@Override
									public void
									actionPerformed(ActionEvent e)
									{
										addNodeBtn_actionPerformed(e);
									}
								}
								);
						westSouthEditorBtns.add(addNodeBtn);
							
							removeNodeBtn = new JButton("-");
							removeNodeBtn.addActionListener
								(new ActionListener()
								{
									@Override
									public void
									actionPerformed(ActionEvent e)
									{
										removeNodeBtn_actionPerformed(e);
									}
								}
								);
						westSouthEditorBtns.add(removeNodeBtn);
						
							editNodeBtn = new JButton("\uD83D\uDD89");
							editNodeBtn.addActionListener
								(new ActionListener()
								{
									@Override
									public void
									actionPerformed(ActionEvent e)
									{
										editNodeBtn_actionPerformed(e);
									}
								}
								);
						westSouthEditorBtns.add(editNodeBtn);
							
							upNodeBtn = new JButton("\u2191");
							upNodeBtn.addActionListener
								(new ActionListener()
								{
									@Override 
									public void 
									actionPerformed(ActionEvent e) 
									{
										moveBtn_actionPerformed(e, true);
									}
								}
								);
						westSouthEditorBtns.add(upNodeBtn);
						
							downNodeBtn = new JButton("\u2193");
							downNodeBtn.addActionListener
								(new ActionListener()
								{
									@Override 
									public void 
									actionPerformed(ActionEvent e) 
									{
										moveBtn_actionPerformed(e, false);
									}
								}
								);
						westSouthEditorBtns.add(downNodeBtn);
							
						
		/*
		 * TODO TXT/CENTER_EAST(east side of west split pane)
		 */
				//centerEastSplit.addMouseListener(this);
				centerEastSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPad, eastPad);
				centerEastSplit.setResizeWeight(0.75);
				centerEastSplit.setBorder(null);
				centerEastSplit.setBackground(white);
				centerEastSplit.setDividerSize(2);
			westCenterEastSplit.setRightComponent(centerEastSplit);
			

	  				centerPad = new JPanel(new GridLayout(1,1));
					centerPad.setBorder(pad);
					centerPad.setBackground(white);
				centerEastSplit.setLeftComponent(centerPad);
				
		
						centerBevel = new JPanel(new GridLayout(1,1));
						centerBevel.setBorder(bevelDwn);
						centerBevel.setBackground(white);
					centerPad.add(centerBevel);
					
			
							centerPane  = new JPanel(new BorderLayout());
							centerPane.setBorder(pad);
							centerPane.setBackground(white);
						centerBevel.add(centerPane);
						
								centerSouthPane = new JPanel(new FlowLayout());
								centerSouthPane.setBorder(pad);
								centerSouthPane.setBackground(white);
							centerPane.add(centerSouthPane, BorderLayout.SOUTH);
							
									radioButtons = new ButtonGroup();/*
								centerSouthPane*/
							
										radio1 = new JRadioButton("first");
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
									radioButtons.add(radio1);
								centerSouthPane.add(radio1);
										

										radio2 = new JRadioButton("second");
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
									radioButtons.add(radio2);
								centerSouthPane.add(radio2);
										
										radio3 = new JRadioButton("third");
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
									radioButtons.add(radio3);
								centerSouthPane.add(radio3);
										
										radio4 = new JRadioButton("fourth");
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
									radioButtons.add(radio4);
								centerSouthPane.add(radio4);

								
								centerCenterPane = new JPanel();
								centerCenterPane.setLayout(new BoxLayout(centerCenterPane, BoxLayout.PAGE_AXIS));
								centerCenterPane.setBorder(pad);
								centerCenterPane.setBackground(white);
							centerPane.add(centerCenterPane, BorderLayout.CENTER);
								
									mainTxtArea = new JTextArea("Welcome to WISP-T!\nPlease create or load a tree.");
									mainTxtArea.setLineWrap(true);
									mainTxtArea.setEditable(false);
									mainTxtArea.setBorder(null);
									mainTxtArea.setColumns(50);
								centerCenterPane.add(mainTxtArea);
								
								 	tipArea = new JTextArea("");
									tipArea.setLineWrap(true);
									tipArea.setEditable(false);
									tipArea.setBorder(null);/*
								centerCenterPane.add(tipArea);
								centerCenterPane.remove(tipArea);*/	
								
								
							
		/*
		 * TODO TIPS/EAST
		 */
					eastPad = new JPanel(new GridLayout(1,1));
					eastPad.setBorder(pad);
					eastPad.setBackground(white);
				centerEastSplit.setRightComponent(eastPad);
						
						eastScroll = new JScrollPane();
						eastScroll.setBorder(bevelDwn);
						eastScroll.setBackground(white);
					eastPad.add(eastScroll);

							eastPane = new JPanel(new GridLayout(6,1));
							eastPane.setBorder(null);
							eastPane.setBackground(white);
						eastScroll.setViewportView(eastPane);
					
						 		check1 = new JCheckBox("first");
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
								check1.setBackground(white);
							eastPane.add(check1);
									
								check2 = new JCheckBox("second");
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
								check2.setBackground(white);
								check2.setVisible(false);
							eastPane.add(check2);
									
								check3 = new JCheckBox("third");
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
								check3.setBackground(white);
								check3.setVisible(false);
							eastPane.add(check3);
							
								check4 = new JCheckBox("fourth");
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
								check4.setBackground(white);
								check4.setVisible(false);
							eastPane.add(check4);
								
								check5 = new JCheckBox("fifth");
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
								check5.setBackground(white);
								check5.setVisible(false);
							eastPane.add(check5);
								
								check6 = new JCheckBox("last");
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
								check6.setBackground(white);
								check6.setVisible(false);
							eastPane.add(check6);
								
						
			
		/*
		 * TODO CONSOLE/SOUTH
		 */
			southPadPane = new JPanel(new GridLayout(1,1));
			southPadPane.setBorder(null);
			southPadPane.setBackground(white);
		contentPane.add(southPadPane, BorderLayout.SOUTH);
		
				southPane = new JPanel(new GridLayout(1,2));
				southPane.setBorder(null);
				southPane.setBackground(white);
			southPadPane.add(southPane);
			
					consoleMsgs = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JLabel(), new JLabel());
					consoleMsgs.setBorder(null);
					consoleMsgs.setBackground(white);
					consoleMsgs.setDividerSize(2);
				southPane.add(consoleMsgs);		
						
						southPSplit = new JPanel();
						southPSplit.setBackground(white);
					consoleMsgs.setLeftComponent(southPSplit);
		
						
						southPSplit.add(processingLabel);
			
						southUSplit = new JPanel();
						southUSplit.setBackground(white);
					consoleMsgs.setRightComponent(southUSplit);
						
							
						southUSplit.add(userLabel);

						pack();
					consoleMsgs.setDividerLocation(0.40d);
						southPSplit.setMinimumSize(new Dimension(southPSplit.getWidth()*3, southPSplit.getHeight()));
		
		
		pack();
		centerSouthPane.setSize(centerSouthPane.getWidth(), (int)(centerSouthPane.getHeight()*1.5));
		centerSouthPane.setPreferredSize(centerSouthPane.getSize());
		centerPad.setMinimumSize(centerPad.getSize());
		treeScroll.setMinimumSize(treeScroll.getSize());
		eastScroll.setMinimumSize(eastScroll.getSize());
		this.setMinimumSize(this.getSize());
		this.setSize(1280, 600);
		westPane.setDividerLocation(westPane.getHeight()*2);
		check1.setVisible(false);
		radio1.setVisible(false);
		p(this.getWidth()+" width");
		p(this.getHeight()+" height");
		
		
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
			if(processTooltipsEnabled)
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
			if(treeVisible)
			{
				tNavTree.setText("\u2610 Navigation Tree");
				westPane.setVisible(false);
				treeVisible = false;
			}
			else
			{
				tNavTree.setText("\u2611 Navigation Tree");
				westPane.setVisible(true);
				westCenterEastSplit.revalidate();
				westCenterEastSplit.setDividerLocation(0.2);
				treeVisible = true;
			}//end inner if
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processTooltipsEnabled)
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
	newTree_actionPerformed(ActionEvent e)
	{
		int continueChoice = JOptionPane.showConfirmDialog(this, "This will erase any unsaved changes to the current tree. Continue?");
		if(continueChoice == 0)
		{
			try
			{
				model = new DefaultTreeModel(new DefaultMutableTreeNode(new WISPTNodeObject("root","root")));
				model.reload();
				tree.setModel(model);
				tree.addTreeSelectionListener(this);
				tree.setEnabled(true);
				treeScroll.revalidate();
				treeScroll.repaint();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void
	loadTree_actionPerformed(ActionEvent e)
	{
		DefaultTreeModel inTree = (DefaultTreeModel)loadSerializedObject("/Tree Saves", "Serialized Tree File", "tree");
		/*
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
		        inTree = (DefaultTreeModel)in.readObject();//grabs the TreeModel of that object and assigns to the returned variable
		        in.close();
		        fileIn.close();
		        System.out.println("Object read from "+selectedFile.getName());
			}//end if
	    }
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
			inTree = (DefaultTreeModel)tree.getModel();
		}
		*/
		
		try
		{
			model = inTree;
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
			if(processTooltipsEnabled)
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
	saveTree_actionPerformed(ActionEvent e)
	{
		try 
		{
			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString()+"/Tree Saves");//creates a empty file in that directory
			currentDirectoryFile.mkdirs();
			JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Serial Tree Files", "tree");
			fc.setFileFilter(filter);
			int val = fc.showSaveDialog(this);//opens an open file dialog
			if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
			{
				boolean continueSave = true;
				try 
				{
					File selectedCurrentDirectoryFile = fc.getSelectedFile();//instantiates a file object by the name specified
					FileInputStream fileIn = new FileInputStream(selectedCurrentDirectoryFile);//if successful, tries to open the location
			        ObjectInputStream in = new ObjectInputStream(fileIn); //if opened, tries to processes it as a serialized object
			        DefaultTreeModel inTree = (DefaultTreeModel)in.readObject();//if processed, tries to assign it to a variable
			        inTree.setRoot((TreeNode)inTree.getRoot());
			        in.close();
			        fileIn.close();
			        
			        int continueSaveInt = JOptionPane.showConfirmDialog(this, "File already exists. Write over file?", "Existing File", JOptionPane.OK_CANCEL_OPTION);
			        if(continueSaveInt != 0)
			        {
			        	continueSave = false;
			        }
				}
				catch(Exception ex)
				{
					continueSave = true;
				}
			    if(continueSave)    	
			    {
			    	String fileName = fc.getSelectedFile().toString();
					if(fc.getSelectedFile().toString().contains(".tree"))
					{
						fileName = fileName.split(".tree")[0];
					}
					FileOutputStream fileOut = new FileOutputStream(fileName+".tree");
			        ObjectOutputStream out = new ObjectOutputStream(fileOut);
			        out.writeObject(tree.getModel());
			        out.close();
			        fileOut.close();
			        p("Object saved in "+fileName+".tree");
			    }
			}
	    }
		catch(IOException i) 
		{
	         i.printStackTrace();
		}//end read try/catch
	}
	
	public void
	tTips_actionPerformed(ActionEvent e)
	{
		try
		{
			if(tipsVisible)
			{
				tTips.setText("\u2610 Tips");
				eastPad.setVisible(false);
				tipsVisible = false;
			}
			else
			{
				tTips.setText("\u2611 Tips");
				eastPad.setVisible(true);
				centerEastSplit.revalidate();
				centerEastSplit.setDividerLocation(0.75);
				tipsVisible = true;
			}//end if
		}
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end tTips_AP
	
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
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end check_AP

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
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end startupSettings_AP
	
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
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end radio_AP
	
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
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end exit_AP
	
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
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}
		}//end try/catch
	}//end openTreeBuilder_AP
	
	public void
	saveSettings_actionPerformed(ActionEvent e)
	{
		processTooltipsEnabled = processingOnCheck.isSelected();
		trainingModeEnabled = trainingModeCheckBox.isSelected();
		editorModeEnabled = enableEditorMode.isSelected();
		if(editorModeEnabled)
		{
			westPane.setBottomComponent(westSouthPad);
			westSouthPad.setVisible(true);
			southUSplit.setBackground(new Color(255,180,180));
			southPSplit.setBackground(new Color(255,180,180));
			if(!userName.contains(" (EDITOR MODE ACTIVE)"))
			{
				userName += " (EDITOR MODE ACTIVE)";
				userLabel.setText(userName);
			}
		}
		else
		{
			westSouthPad.setVisible(false);
			westPane.setBottomComponent(new JLabel());
			southUSplit.setBackground(white);
			southPSplit.setBackground(white);
			if(userName.contains(" (EDITOR MODE ACTIVE)"))
			{
				userName = userName.replace(" (EDITOR MODE ACTIVE)","") ;
				userLabel.setText(userName);
			}
			westPane.setDividerLocation(westPane.getHeight()*2);
		}
		westPane.revalidate();
		westPane.repaint();
		userPassword = changeUserPassword.getText();
		WISPTNodeObject.setUserPass(userPassword);
		WISPTNodeObject.setAdminPass(changeAdminPassword.getText());
		lockedMessage = changeLockedMessage.getText();
		WISPTNodeObject.setInvisibleString(lockedMessage);
		userUnlocks = (int)userUnlocksSpinner.getValue();
		WISPTNodeObject.setUserMax(userUnlocks);
		startupSettingsDialog.setVisible(false);
	}//end save_AP
	
	
	public void 
	addNodeBtn_actionPerformed(ActionEvent e)
	{
		setAsideNodeProperties(selectedWTNO);
		
		model.insertNodeInto(asideNode, selectedNode, 0);
		tree.setModel(model);
		tree.revalidate();
	}
	
	public void
	removeNodeBtn_actionPerformed(ActionEvent e)
	{
		if ((DefaultMutableTreeNode)model.getRoot()==selectedNode) 
		{
			JOptionPane.showMessageDialog(null, "Root cannot be deleted!");
		}
		else
		{
			int removeChoice = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove the branch/leaf \""+selectedWTNO.toString()+"\"? "
					+ "\nThis will delete all information associated with it.");
			if(removeChoice == 0)
			{
					model.removeNodeFromParent(selectedNode);
					tree.setModel(model);
					tree.revalidate();
			}//end inner if
		}//end outer if
	}
	
	public void
	editNodeBtn_actionPerformed(ActionEvent e)
	{
		setAsideNodeProperties(selectedWTNO);
		
		WISPTNodeObject wtno = (WISPTNodeObject)asideNode.getUserObject();
		selectedNode.setUserObject(wtno);
		model.nodeChanged(selectedNode);
		tree.setModel(model);
		tree.revalidate();
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
	
	
	public void //TODO chassis for listener methods
	y_actionPerformed(ActionEvent e)
	{
		//stuff happens
	}//end AP
	
	// TODO end of actionPerformed methods
	
	/**This function is for loading any serialized java object that has been saved. It uses a JFileChooser dialog to do so.
	 * 
	 * @param folderPath
	 * The path in the program's root folder that the dialog will start in, i.e. "/Documents"
	 * @param fileType
	 * The type of file that the dialog should be searching for, i.e. "Microsoft Word Document"
	 * @param fileTypeExtension
	 * The extension of the aforementioned file type, i.e. "doc" for .doc
	 * @return
	 * Will return a de-serialized Object, as selected in the dialog. If it was unable to do so, it returns null.
	 */
	public Object
	loadSerializedObject(String folderPath, String fileType, String fileTypeExtension)
	{
		Object objectToReturn = null;
		try 
		{
			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString()+folderPath);//creates a empty file in that directory
			JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
			FileNameExtensionFilter filter = new FileNameExtensionFilter(fileType, fileTypeExtension);
			fc.setFileFilter(filter);
			int val = fc.showOpenDialog(this);//opens an open file dialog
			if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
			{
				File selectedFile = fc.getSelectedFile();//grabs the selected file
				FileInputStream fileIn = new FileInputStream(selectedFile);
		        ObjectInputStream in = new ObjectInputStream(fileIn); //processes it as a serialized object
		        objectToReturn = in.readObject();//grabs the object and assigns to the returned variable
		        in.close();
		        fileIn.close();
		        System.out.println("Object read from "+selectedFile.getName());
			}//end if
			return objectToReturn;
	    }//try
		catch(Exception ex)
		{
			processingLabel.setText("Exception Ocurred!");
			if(processTooltipsEnabled)
			{
				String trace = "";
				for(StackTraceElement st : ex.getStackTrace())
				{
					trace += st.toString() + "\t";
				}
				processingLabel.setToolTipText("<html><p width=\"500\">"+trace+"</p></html>");
			}//end if
			return null;
		}//end try/catch
	}//end loadSerializedObject()
	
	public void
	saveSerializedObject(String folderPath, String fileType, String fileTypeExtension, Object serializeableObject)
	{
		try 
		{
			Path absolutePath = Paths.get("").toAbsolutePath();//gets the path to the current directory(where the program is)
			File currentDirectoryFile = new File(absolutePath.toString()+folderPath);//creates a empty file in that directory
			currentDirectoryFile.mkdirs();
			JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
			FileNameExtensionFilter filter = new FileNameExtensionFilter(fileType, fileTypeExtension);
			fc.setFileFilter(filter);
			int val = fc.showSaveDialog(this);//opens an open file dialog
			if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
			{
				boolean continueSave = true;
				try //this tests to see if the file already exists and will be overwritten
				{
					File selectedCurrentDirectoryFile = fc.getSelectedFile();//instantiates a file object by the name specified
					FileInputStream fileIn = new FileInputStream(selectedCurrentDirectoryFile);//if successful, tries to open the location
			        ObjectInputStream in = new ObjectInputStream(fileIn); //if opened, tries to processes it as a serialized object
			        Object testObject = in.readObject();//if processed, tries to assign it to a variable
			        p(testObject.toString());
			        in.close();
			        fileIn.close();
			        	//if the file does not exist, or is not a valid serialized java file, this point will not be reached
			        int continueSaveInt = JOptionPane.showConfirmDialog(this, "File already exists. Write over file?", "Existing File", JOptionPane.OK_CANCEL_OPTION);
			        if(continueSaveInt != 0)
			        {
			        	continueSave = false;
			        }//end if
				}//try
				catch(Exception ex)
				{
					continueSave = true;
				}//end inner try/catch
				
				try
				{
				    if(continueSave)//will only save if file does not already exist, or user is okay with overwriting	
				    {
				    	String fileName = fc.getSelectedFile().toString();
						if(fc.getSelectedFile().toString().contains("."+fileTypeExtension))
						{
							fileName = fileName.split("."+fileTypeExtension)[0];
						}
						FileOutputStream fileOut = new FileOutputStream(fileName+"."+fileTypeExtension);
				        ObjectOutputStream out = new ObjectOutputStream(fileOut);
				        out.writeObject(serializeableObject);
				        out.close();
				        fileOut.close();
				        p("Object saved in "+fileName+"."+fileTypeExtension);
				    }//end inner if
				}//try
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this, "Save Failed! Contact the administrator.");
					p(ex.getStackTrace().toString());
				}//end inner try/catch
			}//end if
		}//try
		catch(Exception ex)
		{
			p(ex.getStackTrace().toString());
		}//end outer try/catch
	}//end saveSerializedObject()
	
	public void 
	setAsideNodeProperties(WISPTNodeObject sourceWTNO)
	{

		String[] nodeStrings = new String[8];
		try
		{
			nodeStrings[0] = sourceWTNO.toString();
			nodeStrings[1] = sourceWTNO.getContent();
			String[] tipsArr = sourceWTNO.getTips();
			for(int i = 0; i < tipsArr.length; ++i)
			{
				nodeStrings[i+2] = tipsArr[i];
			}
		}
		catch(NullPointerException npe)
		{
			npe.printStackTrace();
		}
		WISPTNodeBuilder.main(nodeStrings);
		if(WISPTNodeBuilder.isClosedWithSave())
		{
			asideNode = null;
			try 
			{
				FileInputStream fileIn = new FileInputStream("node.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				asideNode = (DefaultMutableTreeNode)in.readObject();
				in.close();
				fileIn.close();
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
			p(asideNode.getUserObject().toString());
		}
		
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
		if(trainingModeEnabled)
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
	public void
	p(String s)
	{
		System.out.println(s);
	}
}

class WISPTUserProfileBuilder
{
/*
 * TODO variables
 */
	private JCheckBox isAdmin,
						editorModeEnabled,
						processTooltipsEnabled,
						trainingModeEnabled,
						tipsVisible,
						treeVisible;
	
	private JTextArea userName,
						userPassword,
						invisibleString;
	
	private JSpinner userUnlocks;
	
	private JDialog builder;
	
	private JPanel contentPane,
					southPane,
					centerPane;
	private JButton okayBtn,
					cancelBtn;

/*
 *  TODO constructors
 */
	public WISPTUserProfileBuilder()
	{
		builder = new JDialog();
		builder.setModalityType(ModalityType.APPLICATION_MODAL);
		builder.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel(new BorderLayout());
		builder.setContentPane(contentPane);
		
		centerPane = new JPanel(new GridLayout(4,3));
		centerPane.setBorder(BorderFactory.createTitledBorder("User Profile"));
		contentPane.add(centerPane, BorderLayout.CENTER);
		
		isAdmin = new JCheckBox("Is Administrator");
		centerPane.add(isAdmin);
		
		//editorModeEnabled
	}

}

class SaveFileChooser extends JFileChooser
{
	@Override
	public void
	approveSelection()
	{
		/*
		 * need to add the safeguard here, I'm guessing that it disposes of the JDialog 
		 * through this function, and I want the safeguard to come first, and only close/dispose
		 * if the safeguard passes (if the user is sure they want to over write the file)
		 * Internet died so I can't get the original JFileChooser.java to work with.
		 */
	}
}