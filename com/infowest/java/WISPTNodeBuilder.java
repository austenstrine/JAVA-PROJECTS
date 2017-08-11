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

import java.awt.Dialog.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.*;

import java.io.*;

import java.util.*;

import javax.imageio.*;

import javax.swing.*;
import javax.swing.tree.*;

public class WISPTNodeBuilder extends JDialog

{
	/**
	 * This class is designed for serializing a DefaultMutableTreeNode filled with user data.
	 * It uses another class, WISPTNodeObject, which is serialized with it.
	 */
	private static final long serialVersionUID = -4089171883331980678L;
	
	private static boolean savedClose = false;
	
	private WISPTNodeObject wtno;
	
	private JTextArea 
			titleTxt, 
			contentTxt,
			tip1,
			tip2,
			tip3,
			tip4,
			tip5,
			tip6;
	private JLabel	tipLabel1,
					tipLabel2,
					tipLabel3,
					tipLabel4,
					tipLabel5,
					tipLabel6;
	
	private String	emptyTipLabelString;
	
	private JButton 
			acceptChanges,
			cancel,
			addTip,
			removeTip;
	private JPanel 
			contentPane,
				center,
				east,
				south;
	private JScrollPane 
			eastScroll,
			southScroll;
	private JTextArea[] 
			tips;
	private JLabel[] 
			tipLabels;
	private ImageIcon[]
			tipPics;
	private int 
			switcher = 0,
			lastPicOpened;
	private BufferedImage 
			bufferedImage;
	private ImageIcon 
			icon,
			emptyIcon;

	/*
	 * TODO Constructor
	 */
	
	public 
	WISPTNodeBuilder()
	{
		super();
		this.setModalityType(JDialog.DEFAULT_MODALITY_TYPE);
		try
		{
			ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
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
		this.addComponentListener
			(new ComponentListener()
			{
					@Override
					public void componentHidden(ComponentEvent e){/*do nothing*/}
					@Override
					public void componentMoved(ComponentEvent e){/*do nothing*/}
					@Override
					public void componentResized(ComponentEvent e){/*do nothing*/}
				@Override
				public void 
				componentShown(ComponentEvent arg0) 
				{
					savedClose = false;
				}
			}
			);
		this.setSize(new Dimension(1280, 600));
		this.setTitle("Node");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);	
					
		wtno = getSerializedWTNO();
		try
		{
			System.out.println(wtno.toString());
		}
		catch(Exception e)
		{
			wtno = new WISPTNodeObject("Title", "Content");
			wtno.setTips(new String[]{"", "", "", "", "", ""});
		}
		
		//TODO center

				center = new JPanel();
				center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
			contentPane.add(center, BorderLayout.CENTER);
				
					try
					{
						titleTxt = new JTextArea(wtno.toString());
					}
					catch(NullPointerException np)
					{
						titleTxt = new JTextArea("title");
					}
					titleTxt.setLineWrap(true);
					titleTxt.setBorder(BorderFactory.createTitledBorder("Node Title"));
				center.add(titleTxt);
				
					try
					{
						contentTxt = new JTextArea(wtno.getContent());
					}
					catch(NullPointerException np)
					{
						contentTxt = new JTextArea("content");
					}
					contentTxt.setLineWrap(true);
					contentTxt.setBorder(BorderFactory.createTitledBorder("Node Content"));
				center.add(contentTxt);
					
					
					tips = new JTextArea[6];
					for(int i = 0; i < tips.length; ++i)
					{
						tips[i] = new JTextArea("Tip");
						tips[i].setRows(5);
						tips[i].setColumns(50);
						tips[i].setVisible(false);
						tips[i].setLineWrap(true);
						tips[i].setAutoscrolls(true);
						tips[i].setBorder(BorderFactory.createTitledBorder("Tip "+(i+1)));
					}
					tip1 = tips[0];
					tip2 = tips[1];
					tip3 = tips[2];
					tip4 = tips[3];
					tip5 = tips[4];
					tip6 = tips[5];
					makeTipsVisible();
					
			//east
				eastScroll = new JScrollPane(new JPanel());
			contentPane.add(eastScroll, BorderLayout.EAST);
			
					east = new JPanel();
					east.setLayout(new BoxLayout(east, BoxLayout.PAGE_AXIS));
				eastScroll.setViewportView(east);
						
						tipLabels = new JLabel[6];
						tipPics = new ImageIcon[6];
						
						try
						{
							bufferedImage = ImageIO.read(new File("picPlaceholder.png"));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
						}
						
						icon = new ImageIcon(bufferedImage);
						
						try
						{
							bufferedImage = ImageIO.read(new File("picPlaceholderEmpty.png"));
						}
						catch(Exception e)
						{
							e.printStackTrace();
							bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_BYTE_GRAY);
						}
						
						emptyIcon = new ImageIcon(bufferedImage);
						emptyTipLabelString = " (empty)";
						
						for(int i = 0; i < tipLabels.length; ++i)
						{
							tipLabels[i] = new JLabel("Pic "+(i+1)+emptyTipLabelString);
							tipLabels[i].setVisible(true);
							tipLabels[i].setIcon(emptyIcon);
							tipLabels[i].setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
						}//end for
						tipLabel1 = tipLabels[0];
						tipLabel1.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel1, 0);
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
						tipLabel2 = tipLabels[1];
						tipLabel2.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel2, 1);
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
						tipLabel3 = tipLabels[2];
						tipLabel3.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel3, 2);
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
						tipLabel4 = tipLabels[3];
						tipLabel4.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel4, 3);
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
						tipLabel5 = tipLabels[4];
						tipLabel5.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel5, 4);
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
						tipLabel6 = tipLabels[5];
						tipLabel6.addMouseListener
							(new MouseListener()
							{
								@Override
								public void mouseClicked(MouseEvent me) 
								{
									tipLabel_mouseClicked(me, tipLabel6, 5);
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
					makeTipLabelsVisible();
						
			//south
				southScroll = new JScrollPane(new JPanel());
			contentPane.add(southScroll, BorderLayout.SOUTH);

					south = new JPanel(new GridLayout(2,2));
				southScroll.setViewportView(south);
				
						addTip = new JButton("Add Tip");
						addTip.addActionListener
							(new ActionListener()
							{
								public void
								actionPerformed(ActionEvent e)
								{
									addTip_actionPerformed(e);
								}//end action performed
							}//end action listener
							);//action listener added
					south.add(addTip);
					
						removeTip = new JButton("Remove Tip");
						removeTip.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									removeTip_actionPerformed(e);
								}//end action performed
							}//end action listener
							);//action listener added
					south.add(removeTip);

						acceptChanges = new JButton("Accept Changes");
						acceptChanges.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									acceptChanges_actionPerformed(e);
								}//end action performed
							}//end action listener
							);//action listener added
					south.add(acceptChanges);
		
						cancel = new JButton("Cancel");
						cancel.addActionListener
							(new ActionListener()
							{
								@Override
								public void
								actionPerformed(ActionEvent e)
								{
									cancel_actionPerformed(e);
								}//end action performed
							}//end action listener
							);//action listener added
					south.add(cancel);
		revalidate();
		pack();
		
	}
	
	public static void 
	main(String[] args) 
	{
		WISPTNodeBuilder node = new WISPTNodeBuilder();
		node.toFront();
		node.setVisible(true);
	}
	
	public void
	tipLabel_mouseClicked(MouseEvent me, JLabel clickedTipLabel, int index)
	{
		lastPicOpened = index;
		if(tipPics[index] == null)
		{
			bufferedImage = WISPT.loadImage(true, "Images", this);
			if(bufferedImage != null)
			{
				tipPics[index] = new ImageIcon(bufferedImage);
				clickedTipLabel.setIcon(icon);
			}//if
			else
			{
				clickedTipLabel.setIcon(emptyIcon);
			}//end null if
		}
		else
		{
			if(WISPTScreenShotViewer.getNoInstanceOpen())
			{
				lastPicOpened = index;
				WISPTScreenShotViewer ssv = new WISPTScreenShotViewer(tipPics[index], this);
				ssv.setVisible(true);
			}//if not already open
		}//end if/else

		String newText = clickedTipLabel.getText();
		System.out.println(newText);

		if(tipPics[index] != null)
		{
			if(newText.contains(emptyTipLabelString))
				newText = newText.replace(emptyTipLabelString, "");
			
			System.out.println(newText + " isIcon");
			clickedTipLabel.setText(newText);
			System.out.println(newText);
		}
	}
	
	public void
	setLastPicOpened(ImageIcon newPic)
	{
		tipPics[lastPicOpened] = newPic;
		JLabel clickedTipLabel;
		if(newPic == null)
		{
			switch(lastPicOpened)
			{
				case 0:
					clickedTipLabel = tipLabel1;
					break;
				case 1:
					clickedTipLabel = tipLabel2;
					break;
				case 2:
					clickedTipLabel = tipLabel3;
					break;
				case 3:
					clickedTipLabel = tipLabel4;
					break;
				case 4:
					clickedTipLabel = tipLabel5;
					break;
				case 5:
					clickedTipLabel = tipLabel6;
					break;
				default:
					clickedTipLabel = new JLabel();
					break;
			}//switch
			clickedTipLabel.setIcon(emptyIcon);
			System.out.println(clickedTipLabel.getText() + " isNullIcon");
			clickedTipLabel.setText(clickedTipLabel.getText()+emptyTipLabelString);
			System.out.println(clickedTipLabel.getText());
		}//if null
	}//setLastPicOpened
	
	
	
	public void
	makeTipsVisible()
	{
		try
		{
			setNodeTitle(wtno.toString());
			setNodeContent(wtno.getContent());
			
			tip1.setText(wtno.getTipAt(0));
			if(!(tip1.getText().equals("")))
			{
				center.add(tip1);
				tip1.setVisible(true);
				switcher = 1;
			}
			
			tip2.setText(wtno.getTipAt(1));
			if(!(tip2.getText().equals("")))
			{
				center.add(tip2);
				tip2.setVisible(true);
				switcher = 2;
			}
			
			tip3.setText(wtno.getTipAt(2));
			if(!(tip3.getText().equals("")))
			{
				center.add(tip3);
				tip3.setVisible(true);
				switcher = 3;
			}
			
			tip4.setText(wtno.getTipAt(3));
			if(!(tip4.getText().equals("")))
			{
				center.add(tip4);
				tip4.setVisible(true);
				switcher = 4;
			}
			
			tip5.setText(wtno.getTipAt(4));
			if(!(tip5.getText().equals("")))
			{
				center.add(tip5);
				tip5.setVisible(true);
				switcher = 5;
			}
			
			tip6.setText(wtno.getTipAt(5));
			if(!(tip6.getText().equals("")))
			{
				center.add(tip6);
				tip6.setVisible(true);
				switcher = 6;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			/*node.setNodeTitle("Title");
			node.setNodeContent("Content");*/
		}
		pack();
	}//end makeTips
	
	public void
	makeTipLabelsVisible()
	{
		
		if(tip1.isVisible())
		{
			east.add(tipLabel1);
		}
		else
		{
			east.remove(tipLabel1);
		}
		
		if(tip2.isVisible())
		{
			east.add(tipLabel2);
		}
		else
		{
			east.remove(tipLabel2);
		}
		
		if(tip3.isVisible())
		{
			east.add(tipLabel3);
		}
		else
		{
			east.remove(tipLabel3);
		}
		
		if(tip4.isVisible())
		{
			east.add(tipLabel4);
		}
		else
		{
			east.remove(tipLabel4);
		}
		
		if(tip5.isVisible())
		{
			east.add(tipLabel5);
		}
		else
		{
			east.remove(tipLabel5);
		}
		
		if(tip6.isVisible())
		{
			east.add(tipLabel6);
		}
		else
		{
			east.remove(tipLabel6);
		}
		
		east.revalidate();
		east.repaint();
	}
	
	public static boolean
	isClosedWithSave()
	{
		return savedClose;
	}
	
	public void
	addTip_actionPerformed(ActionEvent e)
	{
		switch(switcher)
		{
		case 0:
			center.add(tip1);
			tip1.setVisible(true);
			break;
		case 1:
			center.add(tip2);
			tip2.setVisible(true);
			break;
		case 2:
			center.add(tip3);
			tip3.setVisible(true);
			break;
		case 3:
			center.add(tip4);
			tip4.setVisible(true);
			break;
		case 4:
			center.add(tip5);
			tip5.setVisible(true);
			break;
		case 5:
			center.add(tip6);
			tip6.setVisible(true);
			break;
		default:
			JOptionPane.showMessageDialog(null, "Only six tips per branch/leaf are allowed!");	
			--switcher;
		}
		makeTipLabelsVisible();
		++switcher;
		System.out.println(switcher);
		this.revalidate();
		pack();
	}
	
	public void
	removeTip_actionPerformed(ActionEvent e)
	{
		switch(switcher)
		{
		case 1:
			center.remove(tip1);
			tip1.setVisible(false);
			break;
		case 2:
			center.remove(tip2);
			tip2.setVisible(false);
			break;
		case 3:
			center.remove(tip3);
			tip3.setVisible(false);
			break;
		case 4:
			center.remove(tip4);
			tip4.setVisible(false);
			break;
		case 5:
			center.remove(tip5);
			tip5.setVisible(false);
			break;
		case 6:
			center.remove(tip6);
			tip6.setVisible(false);
			break;
		default:
			JOptionPane.showMessageDialog(null, "There are no more tips to remove!");
			++switcher;
		}
		makeTipLabelsVisible();
		--switcher;
		System.out.println(switcher);
		this.revalidate();
		pack();
	}
	
	public void
	assignWTNOProperites(WISPTNodeObject assignee, WISPTNodeObject assigner)
	{
		assignee.setTitle(assigner.toString());
		assignee.setContent(assigner.getContent());
		assignee.setContentVisible(true);
		assignee.setCounter(assigner.getCounter());
		assignee.setTips(assigner.getTips());
		assignee.setTipPics(assigner.getTipPics());
	}
	
	public WISPTNodeObject
	getSerializedWTNO()
	{
		WISPTNodeObject wtno = new WISPTNodeObject("", "");
		try 
		{
	         DefaultMutableTreeNode readNode = (DefaultMutableTreeNode)WISPT.loadSerializedObject(false, "temp", "node", "Serial File", "ser");
	         assignWTNOProperites(wtno, (WISPTNodeObject)readNode.getUserObject());
	         System.out.println("Object loaded with (false, \"temp\", \"node\", \"Serial File\", \"ser\")");
	         return wtno;
	    }
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public void
	acceptChanges_actionPerformed(ActionEvent e)
	{
		WISPTNodeObject wtno = new WISPTNodeObject(titleTxt.getText(), contentTxt.getText());
		wtno.setTipPics(tipPics);
		String[] stringTips = {"","","","","",""};
		if(tip1.isVisible())
			stringTips[0] = tip1.getText();
		else
			stringTips[0] = "";
		if(tip2.isVisible())
			stringTips[1] = tip2.getText();
		else
			stringTips[1] = "";
		if(tip3.isVisible())
			stringTips[2] = tip3.getText();
		else
			stringTips[2] = "";
		if(tip4.isVisible())
			stringTips[3] = tip4.getText();
		else
			stringTips[3] = "";
		if(tip5.isVisible())
			stringTips[4] = tip5.getText();
		else
			stringTips[4] = "";
		if(tip6.isVisible())
			stringTips[5] = tip6.getText();
		else
			stringTips[5] = "";
		
		wtno.setTips(stringTips);
		DefaultMutableTreeNode nodeToSave = new DefaultMutableTreeNode(wtno);
		
		WISPT.saveSerializedObject(false, "temp", "node", "Serial File", "ser", nodeToSave);
		this.setVisible(false);
		savedClose = true;
		this.dispose();
	}

	public void
	cancel_actionPerformed(ActionEvent e)
	{
		savedClose = false;
		this.dispose();
	}
	
	public void 
	setNodeTitle(String t)
	{
		titleTxt.setText(t);
		pack();
	}
	public void 
	setNodeContent(String c)
	{
		contentTxt.setText(c);
		pack();
	}
}
