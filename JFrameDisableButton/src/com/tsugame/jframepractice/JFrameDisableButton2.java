package com.tsugame.jframepractice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFrameDisableButton2 extends JFrame implements ActionListener, MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3960393311405363654L;
	/*************
	 * Variables *
	 *************/
	ImageIcon imgAdd = new ImageIcon("android-logo.jpeg");
	JLabel text = new JLabel("I'm Android. Click the button 10 times!", imgAdd, 0);
	JTextField field = new JTextField("You clicked it 10 times!");
	JButton clickMe = new JButton("Click Me!");
	private String pretext = ""; 
	private int timesMoused = 0;
	private int timesClicked = 0;
	
	
	/****************
	 * Constructors *
	 ****************/
	
	public JFrameDisableButton2() 
	{
		super("Click the button!");
		this.setLayout(new FlowLayout());
		this.setSize(imgAdd.getIconWidth()+280, imgAdd.getIconHeight()+220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.text.setSize(imgAdd.getIconWidth()+200, imgAdd.getIconHeight()+200);
		this.text.addMouseListener(this);
		this.add(text);
		this.field.addActionListener(this);
		this.add(field);
		this.clickMe.setToolTipText("Seriously! Just click it!");
		this.clickMe.addActionListener(this);
		this.add(clickMe);
		this.text.setVisible(true);
		this.field.setVisible(false);
		this.clickMe.setVisible(true);
		this.setVisible(true);
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	
	/********
	 * Main *
	 ********/
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(timesClicked < 9)
		{
			timesClicked+=1;
		}
		else if(clickMe.isEnabled())
		{
			clickMe.setEnabled(false);
			this.field.setVisible(true);
			text.setText("That's enough! Thanks.");
		}
		else
		{
			clickMe.setEnabled(true);
		}
		Object source = e.getSource();
		if(source == field)
		{
			field.setText("You found the secret!");
			text.setText("Oh...You found it.     ");
			timesClicked = 0;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		pretext = text.getText();
		if(!clickMe.isEnabled())
			text.setText("God, finally...        ");
		else if(timesMoused <= 3)
			text.setText("GET OFFA ME!!!         ");
		else if(timesMoused <= 6)
			text.setText("I HATE YOUUUUUU!!!");
		else if(timesMoused <= 9)
			text.setText("CLICK THE FREAKIN BUTTON!!!");
		else
			text.setText("...YOU HAD ONE JOB!!");
		++timesMoused;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		text.setText(pretext);
		pretext = "";
	}
}
