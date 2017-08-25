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


/*
 * Beta 1.2 ready.
 * WRITE RELEASE NOTES!
 * Send Beta 1.2 to co-workers
 * Don't forget to include converters for tree saves and user profiles that anyone may have made.
 */

package com.myriapps.java;

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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.*;

public class WISPT extends JFrame implements TreeSelectionListener//, ActionListener, ComponentListener, MouseListener 
{
/*
 *	Variables 
 */
	private static final long serialVersionUID = -1079398159072533776L;
	private ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	
	public static PrintStream ps;
	
	public static ImageIcon passedImage;
	
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
	
	@SuppressWarnings("unused")
	private Border //bevelDwn = BorderFactory.createBevelBorder(BevelBorder.LOWERED, nineGray, eightGray, sevenGray, sixGray),
			//bevel = BorderFactory.createBevelBorder(BevelBorder.RAISED, nineGray, eightGray, sevenGray, sixGray),
			pad = BorderFactory.createEmptyBorder(3,3,3,3),
			matte = BorderFactory.createMatteBorder(3,3,3,3, eightGray),
			bevelDwn = matte,
			bevel = matte;
	
/*
 * current user info
 */
	
	private boolean isAdmin,
					editorModeEnabled,
					processTooltipsEnabled,
					trainingModeEnabled,
					tipsVisible,
					treeVisible;
private static boolean successfulWrite;
	
	private String userName,
					userPassword,
					lockedMessage,
					lastTreeUsedPath,
					userProfilePath;
	private static String objectPath;
	
	private int userUnlocks;	
	
	private WISPTUserProfile userProfile;
	
/*
 * end user info FIXME all need instantiation through a user login JOptionPane
 */
	@SuppressWarnings("unused")
	private DefaultTreeModel loadedUserModel;
	
	private String	//lastTxt = "",
					emptyTipLabelString;
	
	private WISPTNodeObject selectedWTNO = null;
	
	private DefaultMutableTreeNode selectedNode = new DefaultMutableTreeNode(),
									previousSelectedNode = selectedNode;
	private DefaultMutableTreeNode clipboardNode = null;
	
	private JDialog startupSettingsDialog;
	
	//TODO finish settings
		private JTextField changeUserPassword,
							changeAdminPassword,
							changeLockedMessage;
		
		private JSpinner userUnlocksSpinner;
		
		private JCheckBox trainingModeCheckBox,
							enableEditorMode,
		  					processingOnCheck;
	
		@SuppressWarnings("unused")
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
		westCenterEastSplit;
			private JPanel
			westPane,
				westNorthPad;
					private JScrollPane 
					treeScroll;
				private JPanel
				westSouthPad,
					southNorthEditorBtns;
			
			private JSplitPane	
			centerEastSplit;	
				private JPanel
				centerPad,
					centerBevel,
						centerPane,
							centerCenterGridPane,
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
	
	private JLabel	processingLabel,
					userLabel; 
	
	private JTree tree;
	
	private DefaultTreeModel model;

	private JTextArea 	titleArea,
						contentArea;
	
	private JTextArea[] tipAreas;
	
	private String[] tipAreaStrings;
			
	private JCheckBox[] tipChecks;
	
	private JLabel[] picLabels;
	
	private ImageIcon	labelIcon,
						labelEmptyIcon; 
	
	private ImageIcon[]	pics;
	
	private int		lastPicOpened,
					visibleTipCheckIndex,
					visiblePicLabelIndex;
	
	private JRadioButton	navRadio1,
							navRadio2,
							navRadio3,
							navRadio4;
	
	private ButtonGroup navRadioButtons;
	
	private JButton addNodeBtn,
					removeNodeBtn,
					cutNodeBtn,
					pasteNodeBtn,
					upNodeBtn,
					downNodeBtn,
					addTipBtn,
					removeTipBtn,
					addPicLabelBtn,
					removePicLabelBtn;
	
	/*
	 * MENU DECLARATIONS
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
				private JMenuItem	toggleTips,
									toggleNavTree;
			private JMenuItem	unlockTreeNode;
				
		private JMenu navigate;
			private JMenuItem	previous,
								next,//is only valid if previous step was just called.
								history;
			
		private JMenu userPrefs;
			private JMenuItem startupSettings;
			private JMenuItem editName;


	
	@SuppressWarnings("unused")
	private String rootPath;
/*
 *	Constructors
 */
	
	public WISPT(PrintStream ps)
	{
		super();
		try
		{
			WISPT.ps = ps;
			rootPath = this.getClass().getClassLoader().getResource("").getPath();
			userProfilePath = "User Profiles";
			try
			{
				userProfile = (WISPTUserProfile)loadSerializedObject(this.getClass(), false, userProfilePath, "defaultAdminUserProfile", "User Profile", "user");
				if(userProfile == null)
					throw new NullPointerException();
			}
			catch(Exception ex)
			{

				p(new Exception().getStackTrace()[0], ex);
				userProfile = new WISPTUserProfile(true, "", "");
			}
			try
			{
				setUser(userProfile);
			}
			catch(Exception ex)
			{

				p(new Exception().getStackTrace()[0], ex);
				setUser(new WISPTUserProfile(true, "", ""));
			}
			this.addWindowListener
				(new WindowAdapter()
					{
						@Override
						public void
						windowClosing(WindowEvent we)
						{
							//saveSerializedObject(false, userProfilePath, "defaultUserProfile", "User Profile", "user", userProfile);
							System.exit(0);
						}
					}
				);
			try 
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} 
			catch (ClassNotFoundException | 
					InstantiationException | 
					IllegalAccessException | 
					UnsupportedLookAndFeelException e1) 
			{
				p(new Exception().getStackTrace()[0], e1);
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
				p(new Exception().getStackTrace()[0], e);
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
			 * MENU/NORTH
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
										logIn_actionPerformed(e);
									}//end actionPerformed
								}//end ActionListener
								);//ActionListener added
							logIn.setEnabled(true);
						file.add(logIn);
							
							
							logOut = new JMenuItem("Log Out");
							logOut.addActionListener
								(new ActionListener()
								{
									@Override
									public void
									actionPerformed(ActionEvent e)
									{
										logOut_actionPerformed(e);
									}//end actionPerformed
								}//end ActionListener
								);//ActionListener added
							logOut.setEnabled(true);
						file.add(logOut);
							
							
							newUser = new JMenuItem("New User");
							newUser.addActionListener
								(new ActionListener()
								{
									@Override
									public void
									actionPerformed(ActionEvent e)
									{
										newUser_actionPerformed(e);
									}//end actionPerformed
								}//end ActionListener
								);//ActionListener added
							newUser.setEnabled(true);
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
								
								toggleTips = new JMenuItem("\u2611 Tips");
								toggleTips.addActionListener
									(new ActionListener()
									{	
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											toggleTips_actionPerformed(e);
										}//end actionPerformed
									}//end ActionListener
									);//ActionListener added
							toggle.add(toggleTips);
								
									treeVisible = true;
								
								toggleNavTree = new JMenuItem("\u2611 Navigation Tree");
								toggleNavTree.addActionListener
									(new ActionListener()
									{	
										@Override
										public void
										actionPerformed(ActionEvent e)
										{
											toggleNavTree_actionPerformed(e);
										}//end actionPerformed
									}//end ActionListener
									);//actionListener added
							toggle.add(toggleNavTree);
								
						edit.addSeparator();

						
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
										startupSettings_actionPerformed(e, true);
									}//end actionPerformed
								}//end ActionListener
								);//ActionListener added
							startupSettings.setEnabled(true);
						userPrefs.add(startupSettings);
						
						//startupSettingsDialog
						
								
										
								
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
			 * TREE/WEST+CENTER_EAST	
			 */   	
			   	westCenterEastSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JPanel(), new JPanel());
				westCenterEastSplit.setResizeWeight(0.2);
				westCenterEastSplit.setBorder(pad);
				westCenterEastSplit.setBackground(white);
				westCenterEastSplit.setDividerSize(2);
			contentPane.add(westCenterEastSplit, BorderLayout.CENTER);
			
					
					westPane = new JPanel(new GridLayout(1,1));
					westPane.setBorder(pad);
					westPane.setBackground(white);
				westCenterEastSplit.setLeftComponent(westPane);
			
						westNorthPad = new JPanel(new GridLayout(1,1));
						westNorthPad.setBorder(bevelDwn);
						westNorthPad.setBackground(white);
					westPane.add(westNorthPad);
					
							treeScroll = new JScrollPane();
							treeScroll.setBorder(null);
							treeScroll.setBackground(white);
						westNorthPad.add(treeScroll);
					
								tree = new JTree();
								tree.setModel(model);
								tree.addTreeSelectionListener(this);
								tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
								tree.setBorder(null);
								tree.setBackground(white);
								tree.setExpandsSelectedPaths(true);
								tree.setEnabled(true);
							treeScroll.setViewportView(tree);
						
						westSouthPad = new JPanel(new GridLayout(1,1));
						westSouthPad.setBorder(null);
						westSouthPad.setBackground(white);/*
					westPane.setBottomComponent(westSouthPad);*/
						//FIXME cleanup/remove splitpane
							
			/*
			 * TXT/CENTER_EAST(east side of west split pane)
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
							
									centerCenterGridPane = new JPanel(new GridLayout(1,1));
									centerCenterGridPane.setBorder(null);
									centerCenterGridPane.setBackground(white);
								centerPane.add(centerCenterGridPane, BorderLayout.CENTER);
							
										centerCenterPane = new JPanel();
										centerCenterPane.setLayout(new BoxLayout(centerCenterPane, BoxLayout.PAGE_AXIS));
										centerCenterPane.setBorder(pad);
										centerCenterPane.setBackground(white);
									centerCenterGridPane.add(centerCenterPane);
									
											titleArea = new JTextArea("Welcome to WISP-T!");
											titleArea.setLineWrap(true);
											titleArea.setWrapStyleWord(true);
											titleArea.setEditable(false);
											titleArea.setBorder(null);
											titleArea.setColumns(50);
											titleArea.setRows(1);
										centerCenterPane.add(titleArea);
											
										
											contentArea = new JTextArea("Don't forget to read Release Notes!");
											contentArea.setLineWrap(true);
											contentArea.setWrapStyleWord(true);
											contentArea.setEditable(false);
											contentArea.setBorder(null);
											contentArea.setColumns(50);
										centerCenterPane.add(contentArea);
										
										 	tipAreas = new JTextArea[12];
										 	for(int i = 0; i < tipAreas.length; ++i)
										 	{
										 		tipAreas[i] = new JTextArea();
												tipAreas[i].setLineWrap(true);
												tipAreas[i].setWrapStyleWord(true);
												tipAreas[i].setEditable(false);
												tipAreas[i].setBorder(null);
										 	}/*
										centerCenterPane.add(tipAreas);*/	
										 	
										 	tipAreaStrings = new String[12];
										 	for(int i = 0; i < tipAreaStrings.length; ++i)
										 	{
										 		tipAreaStrings[i] = tipAreas[i].getText();
										 	}
							
									centerSouthPane = new JPanel(new FlowLayout());
									centerSouthPane.setBorder(pad);
									centerSouthPane.setBackground(white);
								centerPane.add(centerSouthPane, BorderLayout.SOUTH);
								
										navRadioButtons = new ButtonGroup();/*
									centerSouthPane*/
								
											navRadio1 = new JRadioButton("first");
											navRadio1.setBackground(white);
											navRadio1.addActionListener
												(new ActionListener()
												{
													@Override
													public void
													actionPerformed(ActionEvent e)
													{
														navRadio_actionPerformed(e, 0);
													}//end actionPerformed
												}//end ActionListener
												);//ActionListener added
										navRadioButtons.add(navRadio1);
									centerSouthPane.add(navRadio1);
											
	
											navRadio2 = new JRadioButton("second");
											navRadio2.setBackground(white);
											navRadio2.addActionListener
												(new ActionListener()
												{
													@Override
													public void
													actionPerformed(ActionEvent e)
													{
														navRadio_actionPerformed(e, 1);
													}//end actionPerformed
												}//end ActionListener
												);//ActionListener added
											navRadio2.setVisible(false);
										navRadioButtons.add(navRadio2);
									centerSouthPane.add(navRadio2);
											
											navRadio3 = new JRadioButton("third");
											navRadio3.setBackground(white);
											navRadio3.addActionListener
												(new ActionListener()
												{
													@Override
													public void
													actionPerformed(ActionEvent e)
													{
														navRadio_actionPerformed(e, 2);
													}//end actionPerformed
												}//end ActionListener
												);//ActionListener added
											navRadio3.setVisible(false);
										navRadioButtons.add(navRadio3);
									centerSouthPane.add(navRadio3);
											
											navRadio4 = new JRadioButton("fourth");
											navRadio4.setBackground(white);
											navRadio4.addActionListener
												(new ActionListener()
												{
													@Override
													public void
													actionPerformed(ActionEvent e)
													{
														navRadio_actionPerformed(e, 3);
													}//end actionPerformed
												}//end ActionListener
												);//ActionListener added
											navRadio4.setVisible(false);
										navRadioButtons.add(navRadio4);
									centerSouthPane.add(navRadio4);
			/*
			 * TIPS/EAST
			 */
						eastPad = new JPanel(new GridLayout(1,1));
						eastPad.setBorder(pad);
						eastPad.setBackground(white);
					centerEastSplit.setRightComponent(eastPad);
							
							eastScroll = new JScrollPane();
							eastScroll.setBorder(bevelDwn);
							eastScroll.setBackground(white);
						eastPad.add(eastScroll);
	
								eastPane = new JPanel(new GridLayout(12,2));
								eastPane.setBorder(null);
								eastPane.setBackground(white);
							eastScroll.setViewportView(eastPane);
							
									
									BufferedImage bufferedImage;
									try
									{
										bufferedImage = ImageIO.read(new File("picPlaceholder.png"));
									}
									catch(Exception e)
									{
										p(new Exception().getStackTrace()[0], e);
										bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
									}
									
									labelIcon = new ImageIcon(bufferedImage);
									
									try
									{
										bufferedImage = ImageIO.read(new File("picPlaceholderEmpty.png"));
									}
									catch(Exception e)
									{
										p(new Exception().getStackTrace()[0], e);
										bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
									}
									
									labelEmptyIcon = new ImageIcon(bufferedImage);
									
									picLabels = new JLabel[12];
									emptyTipLabelString = " (empty)";
									pics = new ImageIcon[12];
									tipChecks = new JCheckBox[12];
									
									for(int i = 0; i < tipChecks.length; ++i)
									{
										tipChecks[i] = new JCheckBox(""+(i+1));
										tipChecks[i].setBackground(white);
										final int INDEX = i;
										tipChecks[i].addActionListener
											(new ActionListener()
											{
												@Override
												public void
												actionPerformed(ActionEvent e)
												{
													tipCheck_actionPerformed(e, tipChecks[INDEX].isSelected(), INDEX);
												}//end actionPerformed
											}//end ActionListener
											);//ActionListener added
										if(i != 0)
										{
											tipChecks[i].setVisible(false);
										}
									}
									visibleTipCheckIndex = -1;
									
									for(int i = 0; i < picLabels.length; ++i)
									{
										String labelText = "Pic " + (i+1);
										picLabels[i] = new JLabel(labelText + emptyTipLabelString);
										picLabels[i].setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
										picLabels[i].setVisible(false);
										picLabels[i].setIcon(labelEmptyIcon);
										final int INDEX = i;
										picLabels[i].addMouseListener
											(new MouseListener()
											{
												@Override
												public void mouseClicked(MouseEvent me) 
												{
													picLabel_mouseClicked(me, INDEX);//XXX if listeners don't work, check here
												}//mouseClicked
					
													@Override
													public void mouseEntered(MouseEvent arg0) {}
													@Override
													public void mouseExited(MouseEvent arg0) {}
													@Override
													public void mousePressed(MouseEvent arg0) {}
													@Override
													public void mouseReleased(MouseEvent arg0) {}
											}
											);
										eastPane.add(tipChecks[i]);
										eastPane.add(picLabels[i]);
									}//end for
									visiblePicLabelIndex = -1;
				
			/*
			 * CONSOLE/SOUTH
			 */
				southPadPane = new JPanel();
				southPadPane.setLayout(new BoxLayout(southPadPane, BoxLayout.PAGE_AXIS));
				southPadPane.setBorder(null);
				southPadPane.setBackground(white);
			contentPane.add(southPadPane, BorderLayout.SOUTH);
			

					southNorthEditorBtns = new JPanel(new GridLayout(1,9));
					southNorthEditorBtns.setBorder(null);
					southNorthEditorBtns.setBackground(new Color(255,180,180));
				southPadPane.add(southNorthEditorBtns);
					
						addNodeBtn = new JButton("+ Node");
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
						addNodeBtn.setBackground(new Color(255,180,180));
						addNodeBtn.setToolTipText("Adds a Leaf/Branch to the currently selected Leaf/Branch");
					southNorthEditorBtns.add(addNodeBtn);
						
						removeNodeBtn = new JButton("- Node");
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
						removeNodeBtn.setBackground(new Color(255,180,180));
						removeNodeBtn.setToolTipText("Removes this Leaf/Branch and all of its Children");
					southNorthEditorBtns.add(removeNodeBtn);
					
						cutNodeBtn = new JButton("\u2700 Node");
						cutNodeBtn.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									cutNodeBtn_actionPerformed(e);
								}
							}
							);
						cutNodeBtn.setBackground(new Color(255,180,180));
						cutNodeBtn.setToolTipText("Removes this Branch/Leaf and all of its children and places it in/on the WISP-T Clipboard");
					southNorthEditorBtns.add(cutNodeBtn);
					
						pasteNodeBtn = new JButton("\uD83D\uDCCB Node");
						pasteNodeBtn.addActionListener
						(new ActionListener() 
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								pasteNodeBtn_actionPerformed(e);
							}
						}
						);
						pasteNodeBtn.setBackground(new Color(255,180,180));
						pasteNodeBtn.setToolTipText("Pastes the Branch/Leaf in/on the WISP-T clipboard as a child of the selected Branch/Leaf");
					southNorthEditorBtns.add(pasteNodeBtn);
						
						upNodeBtn = new JButton("\u2191 Node");
						upNodeBtn.addActionListener
							(new ActionListener()
							{
								@Override 
								public void 
								actionPerformed(ActionEvent e) 
								{
									moveNodeBtn_actionPerformed(e, true);
								}
							}
							);
						upNodeBtn.setBackground(new Color(255,180,180));
						upNodeBtn.setToolTipText("Moves this Branch/Leaf up in its order among its siblings");
					southNorthEditorBtns.add(upNodeBtn);
					
						downNodeBtn = new JButton("\u2193 Node");
						downNodeBtn.addActionListener
							(new ActionListener()
							{
								@Override 
								public void 
								actionPerformed(ActionEvent e) 
								{
									moveNodeBtn_actionPerformed(e, false);
								}
							}
							);
						downNodeBtn.setBackground(new Color(255,180,180));
						downNodeBtn.setToolTipText("Moves this Branch/Leaf down in its order among its siblings");
					southNorthEditorBtns.add(downNodeBtn);
					
						addTipBtn = new JButton("+ Tip");
						addTipBtn.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									addTipBtn_actionPerformed(e);
								}
							}
							);
						addTipBtn.setBackground(new Color(255,180,180));
					southNorthEditorBtns.add(addTipBtn);
						removeTipBtn = new JButton("- Tip");
						removeTipBtn.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									removeTipBtn_actionPerformed(e);
								}
							}
							);
						removeTipBtn.setBackground(new Color(255,180,180));
					southNorthEditorBtns.add(removeTipBtn);
						addPicLabelBtn = new JButton("+ Pic");
						addPicLabelBtn.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								addPicLabelBtn_actionPerformed(e);
							}
						}
						);
						addPicLabelBtn.setBackground(new Color(255,180,180));
					southNorthEditorBtns.add(addPicLabelBtn);
						removePicLabelBtn = new JButton("- Pic");
						removePicLabelBtn.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent ae)
								{
									removePicLabelBtn_actionPerformed(ae);
								}
							}
							);
						removePicLabelBtn.setBackground(new Color(255,180,180));
					southNorthEditorBtns.add(removePicLabelBtn);
						
			
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
			
								processingLabel = new JLabel("Load Complete");
							southPSplit.add(processingLabel);
				
							southUSplit = new JPanel();
							southUSplit.setBackground(white);
						consoleMsgs.setRightComponent(southUSplit);
							
								userLabel = new JLabel(userName); 
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
			tipChecks[0].setVisible(false);
			navRadio1.setVisible(false);
			p(new Exception().getStackTrace()[0], this.getWidth()+" width");
			p(new Exception().getStackTrace()[0], this.getHeight()+" height");
			try
			{
				startupSettings_actionPerformed(new ActionEvent(this, 0, "No commands"), false);
				saveSettings_actionPerformed(new ActionEvent(this, 0, "No commands"));
			}
			catch(Exception e)
			{
				p(new Exception().getStackTrace()[0], e);
			}
	}
	catch(Exception e)
	{
		p(new Exception().getStackTrace()[0], e);
	}
		//setUser(userProfile);
		
		/*UserProfileBuilder builderWindow = new UserProfileBuilder(this);
		builderWindow.setVisible(true);*/
		
		
	}//end constructor
	


/*
 *	Main Method 
 */
	
	public static void 
	main(String[] args) 
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
			File file = new File("logs.txt");
			PrintStream ps = new PrintStream(file);
			WISPT window = new WISPT(ps);
			window.setVisible(true);
		}
		catch(Exception e)
		{
			p(new Exception().getStackTrace()[0], e);
		}
	}

/*
 *	Methods
 */
	
	/**
	 * 
	 * @param callingClass
	 * @param showDialog
	 * @param pathForFile
	 * @param parent
	 * @return
	 */
	public static BufferedImage
	loadImage(Class<?> callingClass, boolean showDialog, String pathForFile)
	{
		p(new Exception().getStackTrace()[0], "entered");
		BufferedImage image;
		if(showDialog)
		{
			File buildFolderIfNeeded = new File(pathForFile);
			buildFolderIfNeeded.mkdirs();
			JFileChooser jfc = new JFileChooser(pathForFile);
			jfc.setAcceptAllFileFilterUsed(false);
			jfc.addChoosableFileFilter(new WISPTImageFilter());
			int choice = jfc.showOpenDialog(null);
			if(choice == JFileChooser.APPROVE_OPTION)
			{
				File selectedFile;
				try
				{
					selectedFile = jfc.getSelectedFile();
					image = ImageIO.read(selectedFile);
				}
				catch(Exception ex)
				{
					p(new Exception().getStackTrace()[0], ex);
					return null;
				}//read image try/catch
				String fileName = "";
				String extension = "";
				try
				{
					fileName = selectedFile.getName();
					int index = fileName.lastIndexOf(".");
					extension = fileName.substring(index+1);
					ImageIO.write(image, extension, new File(pathForFile+File.separator+fileName));
					p(new Exception().getStackTrace()[0], pathForFile+File.separator+fileName+" successfully written. "+extension);
				}
				catch(Exception ex)
				{
					p(new Exception().getStackTrace()[0], ex);
					p(new Exception().getStackTrace()[0], pathForFile+File.separator+fileName+" write failed. "+extension);
				}//write local image try/catch
				return image;
			}//choice/approve if
		}//showDialog if
		else
		{
			try
			{
				image = ImageIO.read(new File(pathForFile));
			}
			catch(Exception ex)
			{
				p(new Exception().getStackTrace()[0], ex);
				return null;
			}//imageread try/catch
			String fileName = "";
			String extension = "";
			try
			{
				String[] splitPath = pathForFile.split(File.separator);
				fileName = splitPath[splitPath.length-1];
				int index = fileName.lastIndexOf(".");
				extension = fileName.substring(index+1);
				ImageIO.write(image, extension, new File(pathForFile+File.separator+fileName));
				p(new Exception().getStackTrace()[0], pathForFile+File.separator+fileName+" successfully written. "+extension);
			}
			catch(Exception ex)
			{
				p(new Exception().getStackTrace()[0], ex);
				p(new Exception().getStackTrace()[0], pathForFile+File.separator+fileName+" write failed. "+extension);
			}//write local image try/catch
			return image;
		}//showDialog else
		return null;
	}//end loadImage
	
	public static String
	getObjectPath()
	{
		p(new Exception().getStackTrace()[0], "entered");
		return objectPath;
	}
	
	/**
	 * 
	 * @param mouseEvent
	 * @param index
	 */
	public void
	picLabel_mouseClicked(MouseEvent mouseEvent, int index)
	{
		p(new Exception().getStackTrace()[0], " entered, \n\t\t\tnext is if(pics[index] == null)else");
		lastPicOpened = index;
		BufferedImage bufferedImage;
		if(pics[index] == null)
		{
			p(new Exception().getStackTrace()[0], "if(pics[index] == null) entered, \n\t\t\tnext is bufferedImage");
			bufferedImage = WISPT.loadImage(this.getClass(), true, "Images");
			p(new Exception().getStackTrace()[0], "bufferedImage instantiated with WISPT.loadImage(), \n\t\t\tnext is if(bufferedImage != null)else");
			if(bufferedImage != null)
			{
				p(new Exception().getStackTrace()[0], "if(bufferedImage != null) entered, \n\t\t\tnext is pics[index] assignment");
				pics[index] = new ImageIcon(bufferedImage);
				p(new Exception().getStackTrace()[0], "pics[index] assigned with new ImageIcon(bufferedImage), \n\t\t\tnext is picLabels[index].setIcon(labelIcon)");
				picLabels[index].setIcon(labelIcon);
				p(new Exception().getStackTrace()[0], "picLabels[index].setIcon(labelIcon) successfully passed, exiting if(bufferedImage != null)");
			}//if
			else
			{
				p(new Exception().getStackTrace()[0], "if(bufferedImage != null)ELSE entered, \n\t\t\tnext is picLabels[index].setIcon(labelEmptyIcon)");
				picLabels[index].setIcon(labelEmptyIcon);
				p(new Exception().getStackTrace()[0], "picLabels[index].setIcon(labelEmptyIcon) successfully passed, exiting if(bufferedImage != null)ELSE");
			}//end null if
		}
		else
		{
			p(new Exception().getStackTrace()[0], "if(pics[index] == null)ELSE entered, \n\t\t\tnext is lastPicOpened assignment");
			lastPicOpened = index;
			p(new Exception().getStackTrace()[0], "lastPicOpened assignment successfully passed, \n\t\t\tnext is passedLabel instantiation");
			JLabel passedLabel = new JLabel(picLabels[index].getText());
			passedLabel.setIcon(pics[index]);
			p(new Exception().getStackTrace()[0], "passedLabel instantiated successfully, \n\t\t\tnext is passedLabel assignment with ScreenShotViewer.SSV_displayScreenShot(passedLabel, this, editorModeEnabled)");
			passedLabel = ScreenShotViewer.SSV_displayScreenShot(passedLabel, this, editorModeEnabled);
			p(new Exception().getStackTrace()[0], "passedLabel assigned successfully, exiting if(pics[index] == null)ELSE");
			picLabels[index].setText(passedLabel.getText());
			
			eastPane.revalidate();
		}//end if/else
		p(new Exception().getStackTrace()[0], "passed if(pics[index] == null)else structure, \n\t\t\tnext is String newText = picLabels[index].getText()");

		String newText = picLabels[index].getText();
		p(new Exception().getStackTrace()[0], newText);

		if(pics[index] != null)
		{
			if(newText.contains(emptyTipLabelString))
				newText = newText.replace(emptyTipLabelString, "");
			
			p(new Exception().getStackTrace()[0], newText + " isIcon");
			picLabels[index].setText(newText);
			p(new Exception().getStackTrace()[0], newText);
		}
	}
	
	public void
	logOut_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		//should be the same as logging into the default profile. Should only confirm.
	}
	public void
	newUser_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		UserProfileBuilder builder = new UserProfileBuilder(this);
		builder.setVisible(true);
	}
	
	public void
	logIn_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		LoginDialog lid = new LoginDialog(this);
		lid.setVisible(true);
	}
	
	public void
	unlockTreeNode_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
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
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end unlockTreeNodeAP
	
	public void
	toggleNavTree_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
			if(treeVisible)
			{
				toggleNavTree.setText("\u2610 Navigation Tree");
				treeVisible = false;
				
			}
			else
			{
				toggleNavTree.setText("\u2611 Navigation Tree");
				treeVisible = true;
			}//end inner if
			westPane.setVisible(treeVisible);
			//userProfile.setTreeVisible(treeVisible);
			westCenterEastSplit.revalidate();
			westCenterEastSplit.setDividerLocation(0.2);
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end toggleNavTreeAP
	
	public void
	newTree_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
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
				tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
				treeScroll.revalidate();
				treeScroll.repaint();
				wipeButtons();
			}//try
			catch(Exception ex)
			{
				ex.printStackTrace(ps);
			}//catch
		}//if continueChoice
	}//newTree AP
	
	public void
	loadTree_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		DefaultTreeModel inTree = (DefaultTreeModel)loadSerializedObject(this.getClass(), true, "Tree Saves", null, "Serialized Tree File", "tree");
		lastTreeUsedPath = objectPath;
		try
		{
			if(inTree == null)
			{
				throw new NullPointerException("DefaultTreeModel inTree == null");
			}
			model = inTree;
			tree.setModel(model);
			tree.addTreeSelectionListener(this);
			tree.setEnabled(true);
			treeScroll.repaint();
			tree.repaint();
			wipeButtons();
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end loadTree AP

	public void
	saveTree_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try 
		{
			File currentDirectoryFile = new File("Tree Saves"+File.separator);//creates a empty file in that directory
			currentDirectoryFile.mkdir();
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
			        p(new Exception().getStackTrace()[0], "Object saved in "+fileName+".tree");
			    }
			}
	    }
		catch(IOException i) 
		{
	         i.printStackTrace(ps);
		}//end read try/catch
	}
	
	public void
	toggleTips_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
			if(tipsVisible)
			{
				toggleTips.setText("\u2610 Tips");
				eastPad.setVisible(false);
				tipsVisible = false;
			}
			else
			{
				toggleTips.setText("\u2611 Tips");
				eastPad.setVisible(true);
				centerEastSplit.revalidate();
				centerEastSplit.setDividerLocation(0.75);
				tipsVisible = true;
			}//end if
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end tTips_AP
	
	/**
	 * 
	 * @param event
	 * @param selected
	 * @param index
	 */
	public void
	tipCheck_actionPerformed(ActionEvent event, boolean selected, int index)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
			if(selected)
			{
				tipAreas[index].setText(selectedWTNO.getTips()[index]);
				
				if(!centerCenterPane.isAncestorOf(tipAreas[index]))
				{
					centerCenterPane.add(tipAreas[index]);
				}//if centercenter is not ancestor
			}//if selected
			else
			{
				selectedWTNO.setTip(index, tipAreas[index].getText());
				centerCenterPane.remove(tipAreas[index]);
			}//else if not selected
			
			centerCenterPane.revalidate();
			centerCenterPane.repaint();
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end tipCheck_AP

	public void
	startupSettings_actionPerformed(ActionEvent e, boolean showDialog)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
			startupSettingsDialog = new JDialog(this, "Settings");
			startupSettingsDialog.setVisible(false);
			startupSettingsDialog.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
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
						adminTab.add(adminSettings);
						// Finish settings
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
									
									changeUserPassword = new JTextField(userPassword);
								adminSettingsCenter.add(changeUserPassword);
								
									changeAdminPassword = new JTextField("WISPadmin");
								adminSettingsCenter.add(changeAdminPassword);
								
									changeLockedMessage = new JTextField(lockedMessage);
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
									cancel.addActionListener
									(new ActionListener()
									{
										@Override
										public void
										actionPerformed(ActionEvent ae)
										{
											cancel_actionPerformed(ae);
										}
									}
									);
									//action listener that will hide and revert to onset state
								adminSettingsSouth.add(cancel);
							
						userTab = new JPanel(new GridLayout(1,1));
					settingsCenterTabbedPane.add("User Preferences", userTab);
							
							userSettings = new JPanel(new FlowLayout());
						userTab.add(userSettings);
							
							userSettings.add(new JCheckBox("cb"));
							userSettings.add(new JCheckBox("cb"));
							userSettings.add(new JCheckBox("cb"));
			if(showDialog)
			{
				startupSettingsDialog.setVisible(true);
			}
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end startupSettings_AP
	
	/**
	 * 
	 * @param event
	 * @param index
	 */
	public void
	navRadio_actionPerformed(ActionEvent event, int index)
	{
		p(new Exception().getStackTrace()[0], "entered");
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
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end navRadio_AP
	
	public void
	exit_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		try
		{
		//XXX there is a better way to do this, lookup
			this.dispose();
		}
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end try/catch
	}//end exit_AP
	
	
	public void
	saveSettings_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		processTooltipsEnabled = processingOnCheck.isSelected();
		trainingModeEnabled = trainingModeCheckBox.isSelected();
		editorModeEnabled = enableEditorMode.isSelected();
		if(editorModeEnabled)
		{
			if(!southPadPane.isAncestorOf(southNorthEditorBtns))
			{
				southPadPane.add(southNorthEditorBtns, 0);
			}
			southUSplit.setBackground(new Color(255,180,180));
			southPSplit.setBackground(new Color(255,180,180));
			if(!userName.contains(" (EDITOR MODE ACTIVE)"))
			{
				userName += " (EDITOR MODE ACTIVE)";
				userLabel.setText(userName);
			}
			titleArea.setEditable(true);
			contentArea.setEditable(true);
			for(int i = 0; i < 12; ++i)
			{
				tipAreas[i].setEditable(true);
			}
		}
		else
		{
			if(southPadPane.isAncestorOf(southNorthEditorBtns))
			{
				southPadPane.remove(southNorthEditorBtns);
			}
			southUSplit.setBackground(white);
			southPSplit.setBackground(white);
			if(userName.contains(" (EDITOR MODE ACTIVE)"))
			{
				userName = userName.replace(" (EDITOR MODE ACTIVE)","") ;
				userLabel.setText(userName);
			}
			titleArea.setEditable(false);
			contentArea.setEditable(false);
			for(int i = 0; i < 12; ++i)
			{
				tipAreas[i].setEditable(false);
			}
		}
		southPadPane.revalidate();
		southPadPane.repaint();
		userPassword = changeUserPassword.getText();
		WISPTNodeObject.setUserPass(userPassword);
		WISPTNodeObject.setAdminPass(changeAdminPassword.getText());
		lockedMessage = changeLockedMessage.getText();
		WISPTNodeObject.setInvisibleString(lockedMessage);
		userUnlocks = (int)userUnlocksSpinner.getValue();
		WISPTNodeObject.setUserMax(userUnlocks);
		startupSettingsDialog.dispose();
	}//end save_AP
	
	public void
	cancel_actionPerformed(ActionEvent ae)
	{
		processingOnCheck.setSelected(processTooltipsEnabled);
		trainingModeCheckBox.setSelected(trainingModeEnabled);
		enableEditorMode.setSelected(editorModeEnabled);
		changeUserPassword.setText(userPassword);
		changeAdminPassword.setText(WISPTNodeObject.getAdminPass());
		changeLockedMessage.setText(lockedMessage);
		userUnlocksSpinner.setValue(WISPTNodeObject.getUserMax());
		startupSettingsDialog.dispose();
	}
	
	
	public void 
	addNodeBtn_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		model.insertNodeInto(new DefaultMutableTreeNode(new WISPTNodeObject("Title","Content")), selectedNode, 0);
		tree.revalidate();
	}
	
	public void
	removeNodeBtn_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
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
					//tree.setModel(model);
					tree.revalidate();
			}//end inner if
		}//end outer if
	}
	
	public void
	cutNodeBtn_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		clipboardNode = selectedNode;
		model.removeNodeFromParent(selectedNode);
		tree.revalidate();
	}
	
	public void
	pasteNodeBtn_actionPerformed(ActionEvent e)
	{
		p(new Exception().getStackTrace()[0], "entered");
		if(clipboardNode != null)
		{
			model.insertNodeInto(clipboardNode, selectedNode, 0);
			tree.revalidate();
			clipboardNode = null;
		}
	}
	
	/**
	 * 
	 * @param event
	 * @param directionUp
	 */
	public void
	moveNodeBtn_actionPerformed(ActionEvent event, boolean directionUp)
	{
		p(new Exception().getStackTrace()[0], "entered");
		DefaultMutableTreeNode asideNode;
		try
		{
			p(new Exception().getStackTrace()[0], "actionPerformed entered");
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
			p(new Exception().getStackTrace()[0], "parent instantiated");
			p(new Exception().getStackTrace()[0], "selectedNode is child of parent, "+parent.isNodeChild(selectedNode));
			int selectedIndex = parent.getIndex((TreeNode)selectedNode);
			p(new Exception().getStackTrace()[0], "selectedIndex instantiated");
			if(directionUp && selectedIndex != 0)
			{
				p(new Exception().getStackTrace()[0], "upIf entered");
				model = (DefaultTreeModel)tree.getModel();
				p(new Exception().getStackTrace()[0], "model instantiated");
				asideNode = selectedNode;
				model.removeNodeFromParent(selectedNode);
				p(new Exception().getStackTrace()[0], "selected node removed");
				model.insertNodeInto(asideNode, parent, selectedIndex - 1);
				selectedNode = asideNode;
				p(new Exception().getStackTrace()[0], "selected node inserted");
				tree.setModel(model);
				p(new Exception().getStackTrace()[0], "model updated");
				tree.revalidate();
				p(new Exception().getStackTrace()[0], "tree revalidated");
				tree.setSelectionPath(new TreePath(selectedNode.getPath()));
				p(new Exception().getStackTrace()[0], "selection set");
			}
			else if(!directionUp && selectedIndex != parent.getChildCount()-1)
			{
				p(new Exception().getStackTrace()[0], "downIf entered");
				model = (DefaultTreeModel)tree.getModel();
				p(new Exception().getStackTrace()[0], "model instantiated");
				asideNode = selectedNode;
				model.removeNodeFromParent(selectedNode);
				p(new Exception().getStackTrace()[0], "selected node removed");
				model.insertNodeInto(asideNode, parent, selectedIndex + 1);
				selectedNode = asideNode;
				p(new Exception().getStackTrace()[0], "selected node inserted");
				tree.setModel(model);
				p(new Exception().getStackTrace()[0], "model updated");
				tree.revalidate();
				p(new Exception().getStackTrace()[0], "tree revalidated");
				tree.setSelectionPath(new TreePath(selectedNode.getPath()));
				p(new Exception().getStackTrace()[0], "selection set");
			}
		}
		catch(NullPointerException npe)
		{
			p(new Exception().getStackTrace()[0], npe);
		}
	}
	
	public void
	addTipBtn_actionPerformed(ActionEvent e)
	{
		if(selectedNode.isNodeAncestor( (DefaultMutableTreeNode)model.getRoot() ))
		{
			p(new Exception().getStackTrace()[0], "entered");
			switch(visibleTipCheckIndex)
			{
			case -1:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				tipChecks[visibleTipCheckIndex+1].setVisible(true);
				tipChecks[visibleTipCheckIndex+1].setSelected(true);
				tipCheck_actionPerformed(new ActionEvent(this, lastPicOpened, emptyTipLabelString), tipChecks[visibleTipCheckIndex+1].isSelected(), visibleTipCheckIndex+1);
				tipAreas[visibleTipCheckIndex+1].setText("Tip "+(visibleTipCheckIndex+2));
				visibleTipCheckIndex += 1;
				break;
			case 11:
				JOptionPane.showMessageDialog(this, "Each node may only have 12 Tips!");
				break;
			}//switch
		}//if root is ancestor of selected
	}
	
	public void
	removeTipBtn_actionPerformed(ActionEvent e)
	{
		if(selectedNode.isNodeAncestor( (DefaultMutableTreeNode)model.getRoot() ))
		{
			p(new Exception().getStackTrace()[0], "entered");
			switch(visibleTipCheckIndex)
			{
			case -1:
				JOptionPane.showMessageDialog(this, "No Tips to remove!");
				break;
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
				tipChecks[visibleTipCheckIndex].setVisible(false);
				tipAreas[visibleTipCheckIndex].setText("");
				visibleTipCheckIndex -= 1;
				break;
			}//switch
		}//if root is ancestor of selected
	}
	
	public void
	addPicLabelBtn_actionPerformed(ActionEvent e)
	{
		if(selectedNode.isNodeAncestor( (DefaultMutableTreeNode)model.getRoot() ))
		{
			p(new Exception().getStackTrace()[0], "entered");
			switch(visiblePicLabelIndex)
			{
			case -1:
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
				picLabels[visiblePicLabelIndex+1].setVisible(true);
				picLabels[visiblePicLabelIndex+1].setText("Tip "+(visiblePicLabelIndex+2));
				picLabel_mouseClicked(new MouseEvent(this, 0, 0, 0, 0, 0, 0, false), visiblePicLabelIndex+1);
				visiblePicLabelIndex += 1;
				break;
			case 11:
				JOptionPane.showMessageDialog(this, "Each node may only have 12 Pics!");
				break;
			}//switch
		}//if root is ancestor of selected
	}
	
	public void
	removePicLabelBtn_actionPerformed(ActionEvent e)
	{
		int decision = -1;
		if(selectedNode.isNodeAncestor( (DefaultMutableTreeNode)model.getRoot() ))
		{
			decision = JOptionPane.showConfirmDialog(this,
													"Are you sure you want to remove \"" 
													+ picLabels[visiblePicLabelIndex].getText() 
													+ "\" and all of it's associated information?",
													"Confirm Delete",
													JOptionPane.YES_NO_OPTION,
													JOptionPane.WARNING_MESSAGE);
		}//if root is ancestor of selected
		if(decision == 0)
		{
			p(new Exception().getStackTrace()[0], "entered");
			switch(visiblePicLabelIndex)
			{
			case -1:
				JOptionPane.showMessageDialog(this, "No Pics to remove!");
				break;
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
				picLabels[visiblePicLabelIndex].setVisible(false);
				pics[visiblePicLabelIndex] = null;
				visiblePicLabelIndex -= 1;
				break;
			}//switch
		}//if decision 0
	}
	
	public void //chassis for listener methods
	y_actionPerformed(ActionEvent e)
	{
		//stuff happens
	}//end AP
	
	//end of actionPerformed methods
	
	/**
	 * 
	 * @param newPic
	 */
	public void
	setLastPicOpened(ImageIcon newPic)
	{
		p(new Exception().getStackTrace()[0], "entered");
		pics[lastPicOpened] = newPic;
		JLabel clickedTipLabel;
		if(newPic == null)
		{
			switch(lastPicOpened)
			{
				case 0:
					clickedTipLabel = picLabels[0];
					break;
				case 1:
					clickedTipLabel = picLabels[1];
					break;
				case 2:
					clickedTipLabel = picLabels[2];
					break;
				case 3:
					clickedTipLabel = picLabels[3];
					break;
				case 4:
					clickedTipLabel = picLabels[4];
					break;
				case 5:
					clickedTipLabel = picLabels[5];
					break;
				default:
					clickedTipLabel = new JLabel();
					break;
			}//switch
			clickedTipLabel.setIcon(labelEmptyIcon);
			p(new Exception().getStackTrace()[0], clickedTipLabel.getText() + " isNullIcon");
			clickedTipLabel.setText(clickedTipLabel.getText()+emptyTipLabelString);
			p(new Exception().getStackTrace()[0], clickedTipLabel.getText());
		}//if null
	}//setLastPicOpened
	
	/**
	 * 
	 * @param profile
	 */
	public void 
	setUser(WISPTUserProfile profile) 
	{
		editorModeEnabled = profile.getEditorModeEnabled();
		processTooltipsEnabled = profile.getProcessTooltipsEnabled();
		trainingModeEnabled = profile.getTrainingModeEnabled();
		tipsVisible = profile.getTipsVisible();
		treeVisible = profile.getTreeVisible();

		userName = profile.getUserName();
		userPassword = profile.getUserPassword();
		lockedMessage = profile.getLockedMessage();
		lastTreeUsedPath = profile.getLastTreeUsedPath();

		userUnlocks = profile.getUserUnlocks();

		try 
		{
			FileInputStream fileIn = new FileInputStream(new File(lastTreeUsedPath));
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			model = (DefaultTreeModel)objectIn.readObject();
			fileIn.close();
			objectIn.close();
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			p(new Exception().getStackTrace()[0], e);
			model = new DefaultTreeModel(new DefaultMutableTreeNode(new WISPTNodeObject("Title", "Content")));
		}

		isAdmin = profile.getAdminPrivelage();
		if(!isAdmin)
		{
			editorModeEnabled = false;
			enableEditorMode.setSelected(false);
			enableEditorMode.setEnabled(false);
			
		}
	}
	
	/**This function is for loading any already serialized java object. 
	 * It can use a JFileChooser dialog to do so. 
	 * 
	 * 	@param callingClass
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		Always put the keyword "this" here, unless in a static context. Then put an anonymous object.
	 * 		<br>This is only here for the purpose of logs.
	 * 	</p>
	 * 	@param showUserDialog
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		If true, allows the user to choose the file through a dialog. If false, it does not give the
	 * 		<br>user a dialog.
	 * 	</p>
	 * 	@param folderPath
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		The folder that either A. the user will begin looking for files in through the dialog, or B. 
	 * 		<br>the file exists in. 
	 * 		<br>ex. "Saved Objects"
	 * 	</p>
	 * 	@param fileNameOnly
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		The name of the file without the extension. This value will be ignored if showUserDialog is 
	 * 		<br>false.
	 * 		<br>ex. "savedObjectFile"
	 * 	</p>
	 * 	@param fileNameExtensionFilterType
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		The type of file that the dialog should be searching for, 
	 * 		<br>ex. "Microsoft Word Document"
	 * 	</p>
	 * 	@param fileNameExtensionFilterExtension
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		The extension of the aforementioned file type, 
	 * 		<br>ex. "doc" for .doc
	 * 	</p>
	 * 	@return
	 * 	<p style="padding-left: 15px; display=inline-block">
	 * 		Will return the object found at the path specified, or Null if it fails.
	 * 	</p>
	 * 
	 */
	public static Object
	loadSerializedObject(Class<?> callingClass, boolean showUserDialog, String folderPath, String fileNameOnly, String fileNameExtensionFilterType, String fileNameExtensionFilterExtension)
	{
		Object objectToReturn = null;
		p(new Exception().getStackTrace()[0],"objectToReturn set to: null");
		try 
		{
	        p(new Exception().getStackTrace()[0],"try entered");
			File currentDirectoryFile = new File(folderPath);//creates a empty file in that directory
	        p(new Exception().getStackTrace()[0], "currentDirectoryFile instantiated");
			currentDirectoryFile.mkdir();
	        p(new Exception().getStackTrace()[0], "currentDirectoryFile.mkdir() called");
			objectPath = folderPath+File.separator+fileNameOnly+"."+fileNameExtensionFilterExtension;
	        p(new Exception().getStackTrace()[0],"objectPath defined");
			if(showUserDialog)
			{
		        p(new Exception().getStackTrace()[0],"if(showUserDialog) entered");
				JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
				p(new Exception().getStackTrace()[0],"JFileChooser instantiated");
				FileNameExtensionFilter filter = new FileNameExtensionFilter(fileNameExtensionFilterType, fileNameExtensionFilterExtension);
				p(new Exception().getStackTrace()[0],"FileNameExtensionFilter instantiated");
				fc.setFileFilter(filter);
				p(new Exception().getStackTrace()[0],"FileNameExtensionFilter set to JFileChooser");
				int val = fc.showOpenDialog(null);//opens an open file dialog
				p(new Exception().getStackTrace()[0],"JFileChooser opened, integer returned to int val");
				if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
				{
					p(new Exception().getStackTrace()[0],"if(val == JFileChooser.APPROVE_OPTION) entered, \n\t\tnext is selectedFile");
					File selectedFile = fc.getSelectedFile();//grabs the selected file
			        p(new Exception().getStackTrace()[0],"selectedFile instantiated, \n\t\tnext is fileIn");
					FileInputStream fileIn = new FileInputStream(selectedFile);
			        p(new Exception().getStackTrace()[0],"fileIn instantiated, \n\t\tnext is objectIn");
			        ObjectInputStream objectIn = new ObjectInputStream(fileIn); //processes it as a serialized object
			        p(new Exception().getStackTrace()[0],"objectIn instantiated, \n\t\tnext is objectToReturn");
			        objectToReturn = objectIn.readObject();//grabs the object and assigns to the returned variable
			        p(new Exception().getStackTrace()[0],"objectToReturn instantiated from objectIn.readObject()");
			        objectIn.close();
			        fileIn.close();
			        p(new Exception().getStackTrace()[0],"Object read from "+selectedFile.getName());
				}//end if
				objectPath = fc.getSelectedFile().toString();//FIXME might have broken something here
			}
			else
			{
		        p(new Exception().getStackTrace()[0],"if(showUserDialog)ELSE entered, \n\t\tnext is fileIn");
				FileInputStream fileIn = new FileInputStream(objectPath);
				p(new Exception().getStackTrace()[0],"fileIn instantiated with " + objectPath + ", \n\t\tnext is objectIn");
		        ObjectInputStream objectIn = new ObjectInputStream(fileIn); //processes it as a serialized object
				p(new Exception().getStackTrace()[0],"objectIn instantiated with fileIn, \n\t\tnext is objectToReturn");
		        objectToReturn = objectIn.readObject();//grabs the object and assigns to the returned variable
				p(new Exception().getStackTrace()[0],"objectToReturn instantiated with objectIn.readObject()");
		        objectIn.close();
		        fileIn.close();
		        p(new Exception().getStackTrace()[0],"Object read from "+objectPath);
			}
			p(new Exception().getStackTrace()[0],"end of try reached, object must be non-null");
			return objectToReturn;
	    }//try
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0],"catch entered, will return: null");
			p(new Exception().getStackTrace()[0], ex);
			return null;
		}//end try/catch
	}//end loadSerializedObject()
	
	/**
	 * 
	 * @param callingClass
	 * @param showUserDialog
	 * @param folderPath
	 * @param fileName
	 * @param fileType
	 * @param fileTypeExtension
	 * @param serializeableObject
	 */
	public static void
	saveSerializedObject(Class<?> callingClass, boolean showUserDialog, String folderPath, String fileName, String fileType, String fileTypeExtension, Object serializeableObject)
	{
		try 
		{
			successfulWrite = true;
			File currentDirectoryFile = new File(folderPath);//creates a empty file in that directory
			currentDirectoryFile.mkdir();
			if(showUserDialog)
			{
				JFileChooser fc = new JFileChooser(currentDirectoryFile);//passes the file to the filechooser, which uses the file's path as the displayed directory.
				FileNameExtensionFilter filter = new FileNameExtensionFilter(fileType, fileTypeExtension);
				fc.setFileFilter(filter);
				int val = fc.showSaveDialog(null);//opens an open file dialog
				if(val == JFileChooser.APPROVE_OPTION)//if the user hits okay,
				{
					boolean continueSave = true;
					try //this tests to see if the file already exists and will be overwritten
					{
						File selectedCurrentDirectoryFile = fc.getSelectedFile();//instantiates a file object by the name specified
						FileInputStream fileIn = new FileInputStream(selectedCurrentDirectoryFile);//if successful, tries to open the location
				        ObjectInputStream in = new ObjectInputStream(fileIn); //if opened, tries to processes it as a serialized object
				        Object testObject = in.readObject();//if processed, tries to assign it to a variable
				        p(new Exception().getStackTrace()[0], testObject.toString());
				        in.close();
				        fileIn.close();
				        	//if the file does not exist, or is not a valid serialized java file, this point will not be reached
				        int continueSaveInt = JOptionPane.showConfirmDialog(null, "File already exists. Write over file?", "Existing File", JOptionPane.OK_CANCEL_OPTION);
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
					    	fileName = fc.getSelectedFile().toString();
							if(fc.getSelectedFile().toString().contains("."+fileTypeExtension))
							{
								fileName = fileName.split("."+fileTypeExtension)[0];
							}
							FileOutputStream fileOut = new FileOutputStream(fileName+"."+fileTypeExtension);
					        ObjectOutputStream out = new ObjectOutputStream(fileOut);
					        out.writeObject(serializeableObject);
					        out.flush();
					        out.close();
					        fileOut.flush();
					        fileOut.close();
					        p(new Exception().getStackTrace()[0], "Object saved in "+fileName+"."+fileTypeExtension);
					    }//end inner if
					}//try
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Save Failed! Contact the administrator.");
						p(new Exception().getStackTrace()[0], ex.getStackTrace().toString());
						successfulWrite = false;
					}//end inner try/catch
				}//end if approve option
			}//end if showUserDialog
			else
			{
				FileOutputStream fileOut = new FileOutputStream(currentDirectoryFile+File.separator+fileName+"."+fileTypeExtension);
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);
		        out.writeObject(serializeableObject);
		        out.flush();
		        out.close();
		        fileOut.flush();
		        fileOut.close();
		        p(new Exception().getStackTrace()[0], "Object saved in "+currentDirectoryFile.toString());
			}
			
		}//try
		catch(Exception ex)
		{
			p(new Exception().getStackTrace()[0], ex);
		}//end outer try/catch
	}//end saveSerializedObject()
	
	public boolean 
	getEditorModeEnabled() 
	{
		return editorModeEnabled;
	}
	
	public static boolean
	getSuccessfulWrite()
	{
		return successfulWrite;
	}
	
	/*public void 
	setAsideNodeWithWISPTNodeBuilder(boolean isEditedNode)
	{
		successfulWrite = false;
		if(isEditedNode)
		{
			saveSerializedObject(this.getClass(), false, "temp", "node", "Serial File", "ser", selectedNode);
		}
		else
		{
			saveSerializedObject(this.getClass(), false, "temp", "node", "Serial File", "ser", new DefaultMutableTreeNode(new WISPTNodeObject("Title", "Content")));
		}
		
		if(successfulWrite)
		{
			WISPTNodeBuilder.main(null);
			p(new Exception().getStackTrace()[0], "WISPTNodeBuilder passed.");
		}//if successful write
		
		clipboardNode = (DefaultMutableTreeNode)WISPT.loadSerializedObject(this.getClass(), false, "temp", "node", "Serial File", "ser");
		p(new Exception().getStackTrace()[0], "clipboardNode set");
	}*/
	
	public void 
	wipeButtons()
	{
		navRadio1.setVisible(false);
		navRadio2.setVisible(false);
		navRadio3.setVisible(false);
		navRadio4.setVisible(false);
		navRadioButtons.clearSelection();
		for(int i = 0; i < tipChecks.length; ++i)
		{
			tipChecks[i].setVisible(false);
			tipChecks[i].setSelected(false);
			tipChecks[i].setText("Tip "+(i+1));
			tipAreas[i].setText("");
			if(centerCenterPane.isAncestorOf(tipAreas[i]));
			{
				centerCenterPane.remove(tipAreas[i]);
			}
		}
		for(int i = 0; i < picLabels.length; ++i)
		{
			picLabels[i].setVisible(false);
		}
		p(new Exception().getStackTrace()[0], "Buttons Wiped");
	}

	public void
	incrementCounter()
	{
		p(new Exception().getStackTrace()[0], "Counter Incremented");
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
	
	/**
	 * 
	 * @param indexOfLabelAndPic
	 */
	public void
	processTipLabel(int indexOfLabelAndPic)
	{
		try
		{
			if(pics[indexOfLabelAndPic] != null)
			{
				picLabels[indexOfLabelAndPic].setVisible(true);
				picLabels[indexOfLabelAndPic].setIcon(labelIcon);
				
				String newText = picLabels[indexOfLabelAndPic].getText();
				p(new Exception().getStackTrace()[0], newText);
				if(newText.contains(emptyTipLabelString))
					newText = newText.replace(emptyTipLabelString, "");
				
				p(new Exception().getStackTrace()[0], newText + " isIcon");
				picLabels[indexOfLabelAndPic].setText(newText);
				p(new Exception().getStackTrace()[0], newText);
			}
		}
		catch(Exception ex){}
	}
	
	public void
	saveChangesToNodeWTNO()
	{//navigating is removing already existing tips
		p(new Exception().getStackTrace()[0], "Entered");
		if(selectedWTNO != null)
		{
			WISPTNodeObject newlyConstructedWTNO = new WISPTNodeObject(titleArea.getText(), contentArea.getText());
				newlyConstructedWTNO.setTips(new String[12]);
				newlyConstructedWTNO.setTipTitles(new String[12]);
				for(int i = 0; i < tipChecks.length; ++i)
				{
					if(tipChecks[i].isVisible())
					{
						newlyConstructedWTNO.setTip(i, tipAreas[i].getText());
						newlyConstructedWTNO.setTipTitle(i, tipChecks[i].getText());
					}
					else
					{
						break;
					}
				}
				newlyConstructedWTNO.setPics(new ImageIcon[12]);
				newlyConstructedWTNO.setPicTitles(new String[12]);
				for(int i = 0; i < picLabels.length; ++i)
				{
					if(picLabels[i].isVisible())
					{
						newlyConstructedWTNO.setPic(i, pics[i]);
						newlyConstructedWTNO.setPicTitle(i, picLabels[i].getText());
					}
					else
					{
						break;
					}
				}
			selectedNode.setUserObject(newlyConstructedWTNO);
			model.nodeChanged(selectedNode);
		}
	}

// treeValueChaned override
	@Override
	public void 
	valueChanged(TreeSelectionEvent arg0) 
	{
		if(editorModeEnabled)
		{
			saveChangesToNodeWTNO();
		}
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
			try
			{
				userProfile.setLastSelectedNodePath(new TreePath(selectedNode.getPath()));
			}
			catch(Exception e)
			{
				p(new Exception().getStackTrace()[0], e);
			}
			//p(userProfile.getLastSelectedNodePath().toString());
			//training mode if/else below
			if(!selectedNode.isLeaf() && trainingModeEnabled)
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
			else if(trainingModeEnabled)
			{
				incrementCounter();
			}
			
			contentArea.setText(selectedWTNO.getContent());
			titleArea.setText(selectedWTNO.toString());
			String[] wtnoTips = selectedWTNO.getTips();
			String[] wtnoPicTitles = selectedWTNO.getPicTitles();
			pics = selectedWTNO.getPics();
			DefaultMutableTreeNode child;
			WISPTNodeObject childObject;
			
			if(!selectedNode.getUserObject().equals(previousSelectedNode.getUserObject()))
			{
				wipeButtons();
				for(int i = 0; i < tipAreas.length; ++i)
				{
					centerCenterPane.remove(tipAreas[i]);
				}
				centerCenterPane.revalidate();
				centerCenterPane.repaint();
				p(new Exception().getStackTrace()[0], selectedWTNO.getContent());
			}
			
			try
			{
				 child = (DefaultMutableTreeNode)selectedNode.getChildAt(0);
				 childObject = (WISPTNodeObject)child.getUserObject();
				navRadio1.setText(childObject.toString());
				navRadio1.setVisible(true);
	
				 child = (DefaultMutableTreeNode)selectedNode.getChildAt(1);
				 childObject = (WISPTNodeObject)child.getUserObject();
				navRadio2.setText(childObject.toString());
				navRadio2.setVisible(true);
	
				 child = (DefaultMutableTreeNode)selectedNode.getChildAt(2);
				 childObject = (WISPTNodeObject)child.getUserObject();
				navRadio3.setText(childObject.toString());
				navRadio3.setVisible(true);
		
				 child = (DefaultMutableTreeNode)selectedNode.getChildAt(3);
				 childObject = (WISPTNodeObject)child.getUserObject();
				navRadio4.setText(childObject.toString());
				navRadio4.setVisible(true);
			}
			catch(NullPointerException npe)
			{
				;
			}
			catch(ArrayIndexOutOfBoundsException aioobe)
			{
				;
			}
			
			try 
			{
				for(int i = 0; i < picLabels.length; ++i)
				{
					processTipLabel(i);
				}
			} 
			catch (Exception ex) 
			{
				p(new Exception().getStackTrace()[0], ex);
			}
			try
			{
				p(new Exception().getStackTrace()[0], wtnoTips[0] + " == non-null value?");
				for(int i = 0; i < tipChecks.length; ++i)
				{
					try
					{
						if(!wtnoTips[i].equals(""))
						{
							tipChecks[i].setVisible(true);
							tipChecks[i].setText(wtnoTips[i].split(":")[0]);
							tipAreas[i].setText(wtnoTips[i]);
						}
					}
					catch(Exception ex)
					{
						visibleTipCheckIndex = i-1;
						break;
					}//if else
				}//for
			}//try
			catch (Exception ex) 
			{
				p(new Exception().getStackTrace()[0], ex);
			}//catch
			try
			{
				for(int i = 0; i < picLabels.length;++i)
				{
					if(pics[i] != null)
					{
						picLabels[i].setVisible(true);
						picLabels[i].setText(wtnoPicTitles[i]);
					}
					else
					{
						visiblePicLabelIndex = i-1;
						break;
					}//if else
				}//for
			}//try
			catch (Exception ex) 
			{
				p(new Exception().getStackTrace()[0], ex);
			}//catch
		}
	}
	/**
	 * 
	 * @param callingClass
	 * @param stringToPrint
	 */
	public static void
	p(StackTraceElement st, String stringToPrint)
	{
		try
		{
			WISPT.ps.println(st.getClassName()+"."+st.getMethodName()+":"+st.getLineNumber()+"\n\t\t\t"+stringToPrint);
			System.out.println(st.getClassName()+"."+st.getMethodName()+":"+st.getLineNumber()+"\n\t\t\t"+stringToPrint);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param exception
	 */
	public static void
	p(StackTraceElement st, Exception exception)
	{
		try
		{
			System.out.println(st.getClassName()+"."+st.getMethodName()+":"+st.getLineNumber()+"\n");
			exception.printStackTrace();
			WISPT.ps.println(st.getClassName()+"."+st.getMethodName()+":"+st.getLineNumber()+"\n");
			exception.printStackTrace(ps);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


/**Log In Dialog is a username/password test that will find the user file under the username specified, and load it to WISPT.
 * 
 * @author Austen
 *
 */
class LoginDialog extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3139704254925565221L;
	
		private JPanel 
			LID_contentPane,
			LID_centerPane,
			LID_southPane;
			private JButton 
				LID_okayBtn,
				LID_cancelBtn;
			private JTextArea
				LID_username,
				LID_password;

	/**
	 * 
	 * @param mainWindow
	 */
	public LoginDialog(WISPT mainWindow)
	{
		super();
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(LoginDialog.DISPOSE_ON_CLOSE);
			LID_contentPane = new JPanel(new BorderLayout());
			LID_contentPane.setVisible(true);
		this.setContentPane(LID_contentPane);
		
				LID_centerPane = new JPanel(new GridLayout(2,1));
				LID_centerPane.setVisible(true);
			LID_contentPane.add(LID_centerPane, BorderLayout.CENTER);
			
					LID_username = new JTextArea("Enter Username");
					LID_username.setBorder(BorderFactory.createTitledBorder("Username"));
					LID_username.setColumns(40);
					LID_username.setMinimumSize(new Dimension(120, 30));
					LID_username.setVisible(true);
				LID_centerPane.add(LID_username);
					
					LID_password = new JTextArea("Enter Password");
					LID_password.setBorder(BorderFactory.createTitledBorder("Password"));
					LID_password.setColumns(40);
					LID_password.setVisible(true);
				LID_centerPane.add(LID_password);
				
			
				LID_southPane = new JPanel(new GridLayout(1,2));
			LID_contentPane.add(LID_southPane, BorderLayout.SOUTH);
			
					LID_okayBtn = new JButton("OK");
					LID_okayBtn.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								LID_okayBtn_actionPerformed(e, mainWindow);
							}
						}
						);
				LID_southPane.add(LID_okayBtn);
				
					LID_cancelBtn = new JButton("Cancel");
					LID_cancelBtn.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								LID_cancelBtn_actionPerformed(e);
							}
						}
						);
				LID_southPane.add(LID_cancelBtn);
			
			this.revalidate();
			this.repaint();
			this.pack();
			this.setVisible(true);
	}
	
	/**
	 * 
	 * @param event
	 * @param mainWindow
	 */
	public void
	LID_okayBtn_actionPerformed(ActionEvent event, WISPT mainWindow)
	{
		String pathOfFolder = "User Profiles";
		String fileName = LID_username.getText()+".user";
		WISPT.p(new Exception().getStackTrace()[0], pathOfFolder+File.separator+fileName);
		WISPTUserProfile profile = (WISPTUserProfile)WISPT.loadSerializedObject(this.getClass(), 
				false, 
				pathOfFolder, 
				fileName,
				"User Profile", 
				"user");
		try
		{
			/*if(profile == null)
			{
				throw new NullPointerException("The User Profile \""+LID_username.getText()+"\" does not exist!");
			}
			if(!LID_password.getText().equals(profile.getUserPassword()))
			{
				throw new IllegalArgumentException(LID_password.getText()+" is not the correct password!");
			}*/
			
			mainWindow.setUser(profile);
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.OK_OPTION);
			WISPT.p(new Exception().getStackTrace()[0], ex);
		}
		
	}//end LID_okayBtn_AP
	
	public void
	LID_cancelBtn_actionPerformed(ActionEvent e)
	{
		this.dispose();
	}//end LID_cancelBtn_AP
}

class UserProfileBuilder extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3380905246782845147L;

/*
 * variables
 */
	private JCheckBox UPB_isAdmin,
						UPB_editorModeEnabled,
						UPB_processTooltipsEnabled,
						UPB_trainingModeEnabled,
						UPB_tipsVisible,
						UPB_treeVisible;
	
	private JTextField UPB_username,
						UPB_userPassword,
						UPB_lockedMessage;
	
	private JSpinner UPB_userUnlocks;
	
	private JPanel UPB_contentPane,
					UPB_southPane,
					UPB_centerPane;
	private JButton UPB_okayBtn,
					UPB_cancelBtn;

/*
 *  constructors
 */
	/**
	 * 
	 * @param mainWindow
	 */
	public UserProfileBuilder(WISPT mainWindow)
	{
		super();
		this.setModalityType(ModalityType.APPLICATION_MODAL);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
			UPB_contentPane = new JPanel(new BorderLayout());
		this.setContentPane(UPB_contentPane);
		
				UPB_centerPane = new JPanel(new GridLayout(4,3));
				UPB_centerPane.setBorder(BorderFactory.createTitledBorder("User Profile"));
			UPB_contentPane.add(UPB_centerPane, BorderLayout.CENTER);
			
					UPB_isAdmin = new JCheckBox("Is Administrator");
				UPB_centerPane.add(UPB_isAdmin);
				
					UPB_editorModeEnabled = new JCheckBox("Enable Editor Mode");
				UPB_centerPane.add(UPB_editorModeEnabled);
					UPB_processTooltipsEnabled = new JCheckBox("Enable Tooltip Error Report");
				UPB_centerPane.add(UPB_processTooltipsEnabled);
					UPB_trainingModeEnabled = new JCheckBox("Enable Training Mode");
				UPB_centerPane.add(UPB_trainingModeEnabled);
					UPB_tipsVisible = new JCheckBox("Tips Pane Expanded");
				UPB_centerPane.add(UPB_tipsVisible);
					UPB_treeVisible = new JCheckBox("Tree Pane Expanded");
				UPB_centerPane.add(UPB_treeVisible);
					UPB_username = new JTextField("");
					UPB_username.setBorder(BorderFactory.createTitledBorder("Username"));
				UPB_centerPane.add(UPB_username);
					UPB_userPassword = new JTextField("");
					UPB_userPassword.setBorder(BorderFactory.createTitledBorder("Password"));
				UPB_centerPane.add(UPB_userPassword);
					UPB_lockedMessage = new JTextField("");
					UPB_lockedMessage.setBorder(BorderFactory.createTitledBorder("Locked Tree/Branch Message"));
				UPB_centerPane.add(UPB_lockedMessage);
					UPB_userUnlocks = new JSpinner();
					UPB_userUnlocks.setModel(new SpinnerNumberModel(1,1,12,1));
				UPB_centerPane.add(UPB_userUnlocks);
				
				UPB_southPane = new JPanel(new GridLayout(1,2));
			UPB_contentPane.add(UPB_southPane, BorderLayout.SOUTH);
			
					UPB_okayBtn = new JButton("OK");
					UPB_okayBtn.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								UPB_okayBtn_actionPerformed(e, mainWindow);
							}
						}
						);
				UPB_southPane.add(UPB_okayBtn);
				
					UPB_cancelBtn = new JButton("Cancel");
					UPB_cancelBtn.addActionListener
						(new ActionListener() 
						{
							@Override
							public void
							actionPerformed(ActionEvent e)
							{
								UPB_cancelBtn_actionPerformed(e);
							}
						}
						);
				UPB_southPane.add(UPB_cancelBtn);
				
		this.pack();
	}

	/*
	 * UPB_Methods
	 */
	/**
	 * 
	 * @param event
	 * @param mainWindow
	 */
	public void
	UPB_okayBtn_actionPerformed(ActionEvent event, WISPT mainWindow)
	{
		WISPTUserProfile profile = new WISPTUserProfile(UPB_isAdmin.isSelected(), "User Profiles", UPB_username.getText()+".user");
		profile.setEditorModeEnabled(UPB_editorModeEnabled.isSelected());
		
		DefaultTreeModel model = (DefaultTreeModel)WISPT.loadSerializedObject(this.getClass(), true, "Tree Saves", null, "Serialized Tree File", "tree");
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		TreePath rootChildPath = new TreePath(((DefaultMutableTreeNode)root.getChildAt(0)).getPath());
		
		profile.setLastTreeUsedPath(WISPT.getObjectPath());
		profile.setLastSelectedNodePath(rootChildPath);
		profile.setLockedMessage(UPB_lockedMessage.getText());
		profile.setProcessTooltipsEnabled(UPB_processTooltipsEnabled.isSelected());
		profile.setTipsVisible(UPB_tipsVisible.isSelected());
		profile.setTrainingModeEnabled(UPB_trainingModeEnabled.isSelected());
		profile.setTreeVisible(UPB_treeVisible.isSelected());
		profile.setUserName(UPB_username.getText());
		profile.setUserPassword(UPB_userPassword.getText());
		profile.setUserUnlocks((int)UPB_userUnlocks.getValue());
		
		WISPT.saveSerializedObject(this.getClass(), false, "User Profiles", UPB_username.getText()+".user", "User Profile", ".user", profile);
		mainWindow.setUser(profile);
		
		this.dispose();
	}
	
	public void
	UPB_cancelBtn_actionPerformed(ActionEvent e)
	{
		this.dispose();
	}
}

class ScreenShotViewer extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8593804664630134410L;
	
	private JPanel 
		SSV_contentPane,
		SSV_centerPane,
		SSV_southPane;
	private JLabel 
		SSV_picHolder;
	private JButton
		SSV_deletePicture,
		SSV_done;
	private JTextField
		SSV_picTitle;
	private int 
		SSV_screenWidth,
		SSV_screenHeight;
	private ImageIcon
		SSV_pic;
	private static JLabel
		SSV_labelToReturn;
	
	
	public ScreenShotViewer(JLabel p, WISPT wisptInstance, boolean isEditorModeEnabled)
	{
		super();
		SSV_labelToReturn = p;
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		SSV_pic = (ImageIcon)p.getIcon();
		SSV_fitWindowToScreen(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			 
			SSV_contentPane = new JPanel(new BorderLayout());
		setContentPane(SSV_contentPane);
		
				SSV_picTitle = new JTextField(p.getText());
				SSV_picTitle.setEditable(isEditorModeEnabled);
			SSV_contentPane.add(SSV_picTitle, BorderLayout.NORTH);
			
				SSV_centerPane = new JPanel(new GridLayout(1,1));
			SSV_contentPane.add(SSV_centerPane, BorderLayout.CENTER);
				
				
					SSV_picHolder = new JLabel();
					SSV_picHolder.setIcon(SSV_pic);
					SSV_picHolder.addComponentListener
						(new ComponentListener()
						{

							@Override
							public void componentHidden(ComponentEvent ce) {}
							@Override
							public void componentMoved(ComponentEvent ce) {}
							@Override
							public void 
							componentResized(ComponentEvent ce) 
							{
								SSV_picHolder_componentResized(ce);
							}
							@Override
							public void componentShown(ComponentEvent ce) {}
							
						}
						);
				SSV_centerPane.add(SSV_picHolder);
				
				SSV_southPane = new JPanel(new GridLayout(2,1));
			SSV_contentPane.add(SSV_southPane, BorderLayout.SOUTH);
				
					SSV_deletePicture = new JButton("Delete");
					SSV_deletePicture.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent ae)
							{
								SSV_deletePicture_actionPerformed(ae, wisptInstance);
							}//actionPerformed
						}//new ActionListener
						);//addActionListener
					SSV_deletePicture.setEnabled(isEditorModeEnabled);
				SSV_southPane.add(SSV_deletePicture);
			
					SSV_done = new JButton("Done");
					SSV_done.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent ae)
							{
								SSV_labelToReturn.setText(SSV_picTitle.getText());
								dispose();
							}
						}
						);
				SSV_southPane.add(SSV_done);
			
		pack();
		setVisible(true);
	}
	
	public static JLabel
	SSV_displayScreenShot(JLabel p, WISPT wisptInstance, boolean isEditorModeEnabled)
	{
		ScreenShotViewer ssv = new ScreenShotViewer(p, wisptInstance, isEditorModeEnabled);
		return ScreenShotViewer.SSV_labelToReturn;
	}
	
	public void
	SSV_deletePicture_actionPerformed(ActionEvent e, WISPT wisptInstance)
	{
		int userChoice = JOptionPane.showConfirmDialog
				(SSV_deletePicture, 
				"Are you sure you want to delete this screenshot?", 
				"Confirm Delete", 
				JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE);
		if(userChoice == 0)//XXX not sure how to better do this.
		{
			wisptInstance.setLastPicOpened(null);
		}//if
		dispose();
	}
	
	public void
	SSV_picHolder_componentResized(ComponentEvent ce)
	{
		if(SSV_picHolder.getSize() != new Dimension(SSV_pic.getIconWidth(), SSV_pic.getIconHeight()));
		{
			Image image = SSV_pic.getImage();
			image = image.getScaledInstance(SSV_picHolder.getWidth(), SSV_picHolder.getHeight(), Image.SCALE_SMOOTH);
			SSV_picHolder.setIcon(new ImageIcon(image));
		}
	}//end picHolder resize
	
	public void
	SSV_fitWindowToScreen(GraphicsDevice gd, Component affectedComp)
	{
		SSV_screenWidth = gd.getDisplayMode().getWidth()-7;
		SSV_screenHeight = gd.getDisplayMode().getHeight()-42;
		affectedComp.setPreferredSize(new Dimension(SSV_screenWidth, SSV_screenHeight));
	}
}