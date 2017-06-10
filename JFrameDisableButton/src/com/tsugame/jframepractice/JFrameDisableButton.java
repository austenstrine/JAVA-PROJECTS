package com.tsugame.jframepractice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class JFrameDisableButton extends JFrame implements ActionListener, MouseListener
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3960393311405363654L;
	/*************
	 * Variables *
	 *************/
	ImageIcon imgAdd = new ImageIcon("android-logo.jpeg");
	JLabel text = new JLabel("I'm Android. Click the button!", imgAdd, 0);
	JTextField field = new JTextField("Click that button there!");
	JButton clickMe = new JButton("Click Me!");
	private String pretext = ""; 
	private int timesMoused = 0;
	
	
	/****************
	 * Constructors *
	 ****************/
	
	public JFrameDisableButton() 
	{
		super("Click the button!");
		this.setLayout(new FlowLayout());
		this.setSize(imgAdd.getIconWidth()+220, imgAdd.getIconHeight()+220);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		text.setSize(imgAdd.getIconWidth()+200, imgAdd.getIconHeight()+200);
		text.addMouseListener(this);
		this.add(text);
		field.addActionListener(this);
		this.add(field);
		clickMe.setToolTipText("Seriously! Just click it!");
		clickMe.addActionListener(this);
		this.add(clickMe);
		text.setVisible(true);
		field.setVisible(true);
		clickMe.setVisible(true);
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
		Object source = e.getSource();
		if(clickMe.isEnabled())
			clickMe.setEnabled(false);
		else
			clickMe.setEnabled(true);
		if(source == field)
		field.setText("You found the secret!");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		pretext = text.getText();
		if(!clickMe.isEnabled())
			text.setText("God, finally...");
		else if(timesMoused < 5)
			text.setText("GET OFFA ME!!!");
		else if(timesMoused < 10)
			text.setText("I HATE YOUUUUUU!!!");
		else if(timesMoused < 15)
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
