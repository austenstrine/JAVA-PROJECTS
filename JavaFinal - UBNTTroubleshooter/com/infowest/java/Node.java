package com.infowest.java;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
//import javax.swing.tree.DefaultMutableTreeNode.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.*;
import java.util.ArrayList;

public class Node extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4089171883331980678L;
	
	ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	
	JTextArea titleTxt, 
			contentTxt;
	JButton acceptChanges,
			cancel,
			packer;
	JPanel contentPane,
			center,
			south,
			north;
	JScrollPane centerScroll,
			southScroll;
	
	
	public Node()
	{
		super();
		Dimension d = new Dimension(800,400);
		this.setPreferredSize(d);
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
		this.setTitle("Node");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		packer = new JButton();
		packer.addActionListener(this);
		north = new JPanel(new FlowLayout());
		Dimension packerD = new Dimension(10,10);
		packer.setPreferredSize(packerD);
		packer.setMaximumSize(packerD);
		packer.setMinimumSize(packerD);
		contentPane.add(north, BorderLayout.NORTH);
		north.add(packer);
		titleTxt = new JTextArea("title");
		titleTxt.setBorder(BorderFactory.createTitledBorder("Node Title"));
		contentTxt = new JTextArea("content");
		contentTxt.setBorder(BorderFactory.createTitledBorder("Node Content"));
		acceptChanges = new JButton("Accept Changes");
		acceptChanges.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		center = new JPanel(new GridLayout(2,1));
		south = new JPanel(new GridLayout(1,2));
		centerScroll = new JScrollPane(center);
		southScroll = new JScrollPane(south);
		center.add(titleTxt);
		center.add(contentTxt);
		south.add(acceptChanges);
		south.add(cancel);
		contentPane.add(centerScroll, BorderLayout.CENTER);
		contentPane.add(southScroll, BorderLayout.SOUTH);
		pack();
		
	}
	


	public void setNodeTitle(String t)
	{
		titleTxt.setText(t);
	}
	public void setNodeContent(String c)
	{
		contentTxt.setText(c);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == acceptChanges)
		{
			DefaultMutableTreeNode savedNode = new DefaultMutableTreeNode(new Stringable(titleTxt.getText(), contentTxt.getText()));
			try 
			{
		         FileOutputStream fileOut = new FileOutputStream("node.ser");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(savedNode);
		         out.close();
		         fileOut.close();
		         System.out.println("Object saved in /node.ser");
		    }
			catch(IOException i) 
			{
		         i.printStackTrace();
			}
			//this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); //This closes both windows?
			this.setVisible(false);
			this.dispose();
		}
		else if(source == cancel)
		{
			
		}
		else if (source == packer)
		{
			pack();
		}
	}

	public static void main(String[] args) 
	{
		Node node = new Node();
		node.setModalityType(ModalityType.APPLICATION_MODAL);
		
		try 
		{
			node.setNodeTitle(args[0]);
			node.setNodeContent(args[1]);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		node.setVisible(true);
	}
	
}
