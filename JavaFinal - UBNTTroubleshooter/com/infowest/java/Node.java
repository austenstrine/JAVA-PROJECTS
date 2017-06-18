package com.infowest.java;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
//import javax.swing.tree.DefaultMutableTreeNode.*;
import javax.swing.tree.DefaultMutableTreeNode;

import java.io.*;

public class Node extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4089171883331980678L;
	
	JTextArea titleTxt, 
			contentTxt;
	JButton acceptChanges,
			cancel;
	JPanel center,
			south;
	JScrollPane centerScroll,
			southScroll;
	
	
	public Node()
	{
		super();
		this.setTitle("Node");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
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
		this.getContentPane().add(centerScroll, BorderLayout.CENTER);
		this.getContentPane().add(southScroll, BorderLayout.SOUTH);
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
		         FileOutputStream fileOut = new FileOutputStream("/node.ser");
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
	}

	public static void main(String[] args) 
	{
		Node node = new Node();
		node.setAlwaysOnTop(true);
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
