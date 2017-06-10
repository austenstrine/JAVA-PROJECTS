package com.tsugame.practice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JMovingFrame extends JFrame implements ActionListener 
{
	
	/*************
	 * Variables *
	 *************/
	private static final long serialVersionUID = -1600779767354813956L;
	
	JPanel contentPane, leftPane, centerPane, rightPane;
	JLabel label;
	JButton button;
	
	
	
	/****************
	 * Constructors *
	 ****************/
	public JMovingFrame()
	{
		super();
		contentPane = new JPanel(new GridLayout(1,3));
		leftPane = new JPanel(new FlowLayout(FlowLayout.LEFT));
		label = new JLabel("I'm a label!");
		leftPane.add(label);
		contentPane.add(leftPane);
		centerPane = new JPanel(new FlowLayout(FlowLayout.CENTER));
		button = new JButton("Click to move the label");
		button.addActionListener(this);
		centerPane.add(button);
		contentPane.add(centerPane);
		rightPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		contentPane.add(rightPane);
		
		
		
		
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		label.setVisible(false);//for some reason the visual representation of the text sticks unless I do this.
		if(label.getParent() == leftPane)
		{
			leftPane.remove(label);
			pack();
			rightPane.add(label);
		}
		else
		{
			rightPane.remove(label);
			pack();
			leftPane.add(label);
		}
		label.setVisible(true);
		pack();
	}

	/***************
	 * Main Method *
	 ***************/
	
	public static void main(String[] args) 
	{
		JMovingFrame window = new JMovingFrame();
		window.setVisible(true);
	}

}
