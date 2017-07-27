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
	
	private JTextArea titleTxt, 
			contentTxt,
			tip1,
			tip2,
			tip3,
			tip4,
			tip5,
			tip6;
	private JButton acceptChanges,
			cancel,
			packer,
			addTip,
			removeTip;
	private JPanel contentPane,
			center,
				//centerTipBtns,
			south,
			north;
	private JScrollPane centerScroll,
			southScroll;
	private JTextArea[] tips = {null,null,null,null,null,null};
	private int switcher = 0;

	/*
	 * TODO Constructor
	 */
	
	public 
	WISPTNodeBuilder()
	{
		super();
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
		this.setPreferredSize(new Dimension(800,400));
		this.setTitle("Node");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
			contentPane = new JPanel();
			contentPane.setLayout(new BorderLayout());
		this.setContentPane(contentPane);
			
			//TODO north
				north = new JPanel(new FlowLayout());
			contentPane.add(north, BorderLayout.NORTH);
			
					packer = new JButton();
					packer.setPreferredSize(new Dimension(10,10));
					packer.setMaximumSize(new Dimension(10,10));
					packer.addActionListener
					(new ActionListener()
					{
						@Override
						public void
						actionPerformed(ActionEvent e)
						{
							packer_actionPerformed(e);
						}
					}
					);
				north.add(packer);
					
					
			//TODO center
					center = new JPanel();
				centerScroll = new JScrollPane(center);
			contentPane.add(centerScroll, BorderLayout.CENTER);
			
					center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
					
						titleTxt = new JTextArea("title");
					center.add(titleTxt);
					
						titleTxt.setBorder(BorderFactory.createTitledBorder("Node Title"));
						
						contentTxt = new JTextArea("content");
					center.add(contentTxt);
						
						contentTxt.setBorder(BorderFactory.createTitledBorder("Node Content"));
						
						
						for(int i = 0; i < tips.length; ++i)
						{
							tips[i] = new JTextArea("Tip");
							tips[i].setRows(5);
							tips[i].setVisible(false);
							tips[i].setBorder(BorderFactory.createTitledBorder("Tip "+(i+1)));
						}
						tip1 = tips[0];
						tip2 = tips[1];
						tip3 = tips[2];
						tip4 = tips[3];
						tip5 = tips[4];
						tip6 = tips[5];
						
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
		pack();
		
	}
	
	public static void 
	main(String[] args) 
	{
		WISPTNodeBuilder node = new WISPTNodeBuilder();
		node.setModalityType(ModalityType.APPLICATION_MODAL);
		System.out.println(java.util.Arrays.toString(args));
		try //XXX this needs to happen through the constructor - pass args to it.
		{
			node.setNodeTitle(args[0]);
			node.setNodeContent(args[1]);
			
			node.tip1.setText(args[2]);
			if(!(node.tip1.getText().equals("")))
			{
				node.center.add(node.tip1);
				node.tip1.setVisible(true);
				node.switcher = 1;
			}
			
			node.tip2.setText(args[3]);
			if(!(node.tip2.getText().equals("")))
			{
				node.center.add(node.tip2);
				node.tip2.setVisible(true);
				node.switcher = 2;
			}
			
			node.tip3.setText(args[4]);
			if(!(node.tip3.getText().equals("")))
			{
				node.center.add(node.tip3);
				node.tip3.setVisible(true);
				node.switcher = 3;
			}
			
			node.tip4.setText(args[5]);
			if(!(node.tip4.getText().equals("")))
			{
				node.center.add(node.tip4);
				node.tip4.setVisible(true);
				node.switcher = 4;
			}
			
			node.tip5.setText(args[6]);
			if(!(node.tip5.getText().equals("")))
			{
				node.center.add(node.tip5);
				node.tip5.setVisible(true);
				node.switcher = 5;
			}
			
			node.tip6.setText(args[7]);
			if(!(node.tip6.getText().equals("")))
			{
				node.center.add(node.tip6);
				node.tip6.setVisible(true);
				node.switcher = 6;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			/*node.setNodeTitle("Title");
			node.setNodeContent("Content");*/
		}
		node.setVisible(true);
	}
	
	public static boolean
	isClosedWithSave()
	{
		return savedClose;
	}
	
	public void
	packer_actionPerformed(ActionEvent e)
	{
		this.pack();
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
		++switcher;
		System.out.println(switcher);
		this.revalidate();
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
		--switcher;
		System.out.println(switcher);
		this.revalidate();
	}
	
	public void
	acceptChanges_actionPerformed(ActionEvent e)
	{
		WISPTNodeObject wtno = new WISPTNodeObject(titleTxt.getText(), contentTxt.getText());
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
		DefaultMutableTreeNode savedNode = new DefaultMutableTreeNode(wtno);
		
		try 
		{
	         FileOutputStream fileOut = new FileOutputStream("node.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(savedNode);
	         out.close();
	         fileOut.close();
	         System.out.println("Object saved in node.ser");
	    }
		catch(IOException i) 
		{
	         i.printStackTrace();
		}
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
	}
	public void 
	setNodeContent(String c)
	{
		contentTxt.setText(c);
	}
}
