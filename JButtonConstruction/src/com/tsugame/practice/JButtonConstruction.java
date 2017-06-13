/*
 * Abandoned project before getting more than an hour or two in.
 * I realized what I really wanted to do was JButton.setIcon() 
 * & JButton.setPressedIcon().
 */

package com.tsugame.practice;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Color;

import javax.swing.*;

public class JButtonConstruction extends JFrame implements ActionListener 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -568904070999407709L;




	//vars
	
	
	
	//constructors
	
	public JButtonConstruction()
	{
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel(new GridLayout(5,5));
		this.setContentPane(contentPane);
		
		CustomJButton button = new CustomJButton();
		contentPane.add(button);
		button.setVisible(true);
		button.setPreferredSize(getSize());
		contentPane.setVisible(true);
		//buttonArea
	}
	
	//methods
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub

	}

	
	
	
	//main method
	public static void main(String[] args) 
	{
		JButtonConstruction buttonW = new JButtonConstruction();
		buttonW.setSize(440, 180);
		buttonW.setVisible(true);
		buttonW.repaint();

	}
}

class CustomJButton extends JPanel
{
	private String text = "text";
	private Dimension preferred = new Dimension(text.length()*25, 25);
	
	public CustomJButton()
	{
		super();
	}
	
	public Dimension getPreferredSize() 
	{
		
		return preferred;
	}
	public Dimension getMinimumSize()  
	{
		return preferred;
	}
	public Dimension getMaximumSize()
	{
		return preferred;
	}
	
	public void paintComponenet(Graphics gr)
	{
		super.paint(gr);
		Graphics2D g = (Graphics2D)gr;
		g.setPaint(Color.lightGray);
		RoundRectangle2D rEdge = new RoundRectangle2D.Float(0f,0f,132f,52f,26f,26f); 
		g.fill(rEdge);
		Color color1 = new Color(235,235,235), color2 = new Color(245,245,245);
		g.setPaint(new GradientPaint(100,40, color1, 100,100, color2, false));
		RoundRectangle2D rR = new RoundRectangle2D.Float(1f,1f,130f,50f,26f,26f); 
		g.fill(rR);
	}
}