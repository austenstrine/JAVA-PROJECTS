package com.tsugame.practice;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class JColorFrame extends JFrame implements ActionListener 
{

	/*************
	 * Variables *
	 *************/

	private static final long serialVersionUID = -7893107759336987933L;
	
	private JPanel contentPane, north, west, center, east, south;
	private JButton red, orange, yellow, green, blue;
	
	/****************
	 * Constructors *
	 ****************/
	
	public JColorFrame()
	{
		super();
		contentPane = new JPanel(new BorderLayout());
		contentPane.setVisible(true);
		
		north = new JPanel(new FlowLayout(FlowLayout.CENTER));
		north.setBorder(BorderFactory.createEtchedBorder());
		west = new JPanel(new FlowLayout(FlowLayout.CENTER));
		west.setBorder(BorderFactory.createEtchedBorder());
		center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		center.setBorder(BorderFactory.createEtchedBorder());
		east = new JPanel(new FlowLayout(FlowLayout.CENTER));
		east.setBorder(BorderFactory.createEtchedBorder());
		south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south.setBorder(BorderFactory.createEtchedBorder());
		
		contentPane.add(north, BorderLayout.NORTH);
		contentPane.add(west, BorderLayout.WEST);
		contentPane.add(center, BorderLayout.CENTER);
		contentPane.add(east, BorderLayout.EAST);
		contentPane.add(south, BorderLayout.SOUTH);
		
		red = new JButton("red");
		red.addActionListener(this);
		north.add(red);
		orange = new JButton("orange");
		orange.addActionListener(this);
		west.add(orange);
		yellow = new JButton("yellow");
		yellow.addActionListener(this);
		center.add(yellow);
		green = new JButton("green");
		green.addActionListener(this);
		east.add(green);
		blue = new JButton("blue");
		blue.addActionListener(this);
		south.add(blue);
		
		
		
		
		
		
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
		Object source = e.getSource();
		if(source == red)
		{
			if(south.getBackground() != Color.red)
			{
				south.setBackground(Color.red);
			}
			else
			{
				south.setBackground(contentPane.getBackground());
			}
		}
		else if(source == orange)
		{
			if(north.getBackground() != Color.orange)
			{
				north.setBackground(Color.orange);
			}
			else
			{
				north.setBackground(contentPane.getBackground());
			}
		}
		else if(source == yellow)
		{
			if(west.getBackground() != Color.yellow)
			{
				west.setBackground(Color.yellow);
			}
			else
			{
				west.setBackground(contentPane.getBackground());
			}
		}
		else if(source == green)
		{
			if(center.getBackground() != Color.green)
			{
				center.setBackground(Color.green);
			}
			else
			{
				center.setBackground(contentPane.getBackground());
			}
		}
		else if(source == blue)
		{
			if(east.getBackground() != Color.blue)
			{
				east.setBackground(Color.blue);
			}
			else
			{
				east.setBackground(contentPane.getBackground());
			}
		}

	}

	public static void main(String[] args) 
	{
		JColorFrame window = new JColorFrame();
		window.setVisible(true);

	}

}
