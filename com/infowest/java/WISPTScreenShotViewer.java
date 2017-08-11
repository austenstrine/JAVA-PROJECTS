package com.infowest.java;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WISPTScreenShotViewer extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8593804664630134410L;
	
	private JPanel 
		contentPane,
		centerPane,
		southPane;
	private JScrollPane
		picHolderScroll;
	private JLabel 
		picHolder;
	private JButton
		deletePicture,
		done;
	private int 
		screenWidth,
		screenHeight;
	private ImageIcon
		pic;
	private static boolean
		editorModeIsEnabled = true,
		noInstanceOpen = true;
	
	
	public WISPTScreenShotViewer(ImageIcon p, Component parentComponent)
	{
		super();
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		addWindowListener
			(new WindowListener()
			{

				@Override
				public void windowActivated(WindowEvent e) {}
				
				@Override
				public void windowClosed(WindowEvent e)
				{
					noInstanceOpen = true;
				}

				@Override
				public void windowClosing(WindowEvent e) {}
				@Override
				public void windowDeactivated(WindowEvent e) {}
				@Override
				public void windowDeiconified(WindowEvent e) {}
				@Override
				public void windowIconified(WindowEvent e) {}

				@Override
				public void windowOpened(WindowEvent e) 
				{
					noInstanceOpen = false;
				}
				
			}
			);
		pic = p;
		fitWindowToScreen(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice(), this);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			 
			contentPane = new JPanel(new BorderLayout());
		setContentPane(contentPane);
			
				centerPane = new JPanel(new GridLayout(1,1));
			contentPane.add(centerPane, BorderLayout.CENTER);
				
				
					picHolder = new JLabel();
					picHolder.setIcon(pic);
					picHolder.addComponentListener
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
								picHolder_componentResized(ce);
							}
							@Override
							public void componentShown(ComponentEvent ce) {}
							
						}
						);
				centerPane.add(picHolder);
				
				southPane = new JPanel(new GridLayout(2,1));
			contentPane.add(southPane, BorderLayout.SOUTH);
				
					deletePicture = new JButton("Delete");
					deletePicture.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent ae)
							{
								int userChoice = JOptionPane.showConfirmDialog
										(deletePicture, 
										"Are you sure you want to delete this screenshot?", 
										"Confirm Delete", 
										JOptionPane.OK_CANCEL_OPTION, 
										JOptionPane.QUESTION_MESSAGE);
								if(userChoice == 0)//XXX not sure how to better do this.
								{
									try
									{
										((WISPTNodeBuilder)parentComponent).setLastPicOpened(null);
									}
									catch(Exception ex)
									{
										
									}//WISPTNodeBuilder try/catch
									
									try
									{
										((WISPT)parentComponent).setLastPicOpened(null);
									}
									catch(Exception ex)
									{
										
									}//WISPT try/catch
								}//if
								dispose();
							}//actionPerformed
						}//new ActionListener
						);//addActionListener
					deletePicture.setEnabled(editorModeIsEnabled);
				southPane.add(deletePicture);
			
					done = new JButton("Done");
					done.addActionListener
						(new ActionListener()
						{
							@Override
							public void
							actionPerformed(ActionEvent ae)
							{
								dispose();
							}
						}
						);
				southPane.add(done);
			
		pack();
	}
	
	public static boolean
	getNoInstanceOpen()
	{
		return noInstanceOpen;
	}
	
	public static void
	setEditorModeEnabled(boolean isEnabled)
	{
		editorModeIsEnabled = isEnabled;
	}
	
	public void
	picHolder_componentResized(ComponentEvent ce)
	{
		if(picHolder.getSize() != new Dimension(pic.getIconWidth(), pic.getIconHeight()));
		{
			Image image = pic.getImage();
			image = image.getScaledInstance(picHolder.getWidth(), picHolder.getHeight(), Image.SCALE_SMOOTH);
			picHolder.setIcon(new ImageIcon(image));
		}
	}//end picHolder resize
	
	public void
	fitWindowToScreen(GraphicsDevice gd, Component affectedComp)
	{
		screenWidth = gd.getDisplayMode().getWidth()-7;
		screenHeight = gd.getDisplayMode().getHeight()-42;
		affectedComp.setPreferredSize(new Dimension(screenWidth, screenHeight));
	}
}
