/*
 * Create an application for Paula’s Portraits, a photography studio. Paula’s 
 * base price is $40 for a photo session with one person. The in-studio fee 
 * is $75 for a session with two or more subjects, and $95 for a session with 
 * a pet. A $90 fee is added to take photos on location instead of in the 
 * studio. The application allows users to compute the price of a photography 
 * session. Include a set of mutually exclusive check boxes to select the 
 * portrait subject and another set of mutually exclusive check boxes for the 
 * session location. Include labels as appropriate to explain the 
 * application’s functionality. Save the file as JPhotoFrame.java.
 */

package com.tsugame.paulaportraits;

import java.awt.event.*;
import java.awt.*;


import javax.swing.*;
import javax.swing.border.*;

public class JPhotoFrame extends JFrame implements ItemListener, ComponentListener {
	
	/*************
	 * Variables *
	 *************/

	private static final long serialVersionUID = -8570913915794473613L;
	private JButton eastButton;
	private ButtonGroup locations, prices;
	private JTextArea description, southTotal;
	private JLabel centerPic;
	private Border botPad = BorderFactory.createEmptyBorder(-5,-5,0,-5)
			,topPad = BorderFactory.createEmptyBorder(0,-5,-5,-5) 
			//,leftPad = BorderFactory.createEmptyBorder(-5,0,-5,-5)
			//,rightPad = BorderFactory.createEmptyBorder(-5,-5,-5,0)
			;
	private JPanel contentPane, northPane, westPane, eastPane, southPane, centerPane, locationPanel, participantPanel;
	private JCheckBox onLocation, inStudio, singlePrice, multiPrice, petPrice;
	private double locationChoice = 0.0, participantChoice = 40.0;
	
	/****************
	 * Constructors *
	 ****************/
	
	public JPhotoFrame()
	{
		super();
		pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
		
		/*
		 * North Pane
		 */
		
		
		northPane = new JPanel();
		northPane.setBorder(botPad);
		
		description = new JTextArea("Select from the following options below to get a final price. Each item in the two groups is mutually exclusive.");
		description.setLineWrap(false);
		description.setEditable(false);
		description.setBackground(contentPane.getBackground());
		northPane.add(description);
		
		contentPane.add(northPane, BorderLayout.NORTH);
		
		/*
		 * West Pane
		 */
		
		westPane = new JPanel();
		westPane.setLayout(new BoxLayout(westPane, BoxLayout.PAGE_AXIS));
		westPane.addComponentListener(this);
		//westPane.setBorder(BorderFactory.createTitledBorder(""));
		
		JPanel spacePane = new JPanel();
		spacePane.setBorder(BorderFactory.createEmptyBorder(-6,-6,-6,-6));
		westPane.add(spacePane);
		
			//create checkboxes
		inStudio = new JCheckBox("In-Studio",false);
		inStudio.setSelected(true);
		inStudio.addItemListener(this);
		onLocation = new JCheckBox("On location : +$90",false);
		onLocation.addItemListener(this);
			//add to button group
		locations = new ButtonGroup();
		locations.add(inStudio);
		locations.add(onLocation);
			//create a panel to put them in, put 'em in
		locationPanel = new JPanel();
		locationPanel.setBorder(BorderFactory.createTitledBorder("Location:"));
		locationPanel.setLayout(new BoxLayout(locationPanel, BoxLayout.PAGE_AXIS));
		locationPanel.add(inStudio);
		locationPanel.add(onLocation);
			//add panel to west panel
		westPane.add(locationPanel);
		
		JPanel spacePane2 = new JPanel();
		spacePane2.setBorder(BorderFactory.createEmptyBorder(-6,-6,-6,-6));
		westPane.add(spacePane2);
		
		participantPanel = new JPanel();
		participantPanel.setLayout(new BoxLayout(participantPanel, BoxLayout.PAGE_AXIS));
		participantPanel.setBorder(BorderFactory.createTitledBorder("Participants:"));
		singlePrice = new JCheckBox("Single : $40",false);
		singlePrice.setSelected(true);
		singlePrice.addItemListener(this);
		multiPrice = new JCheckBox("Multiple : $75",false);
		multiPrice.addItemListener(this);
		petPrice = new JCheckBox("Pet : $95");
		petPrice.addItemListener(this);
		prices = new ButtonGroup();
		prices.add(singlePrice);
		prices.add(multiPrice);
		prices.add(petPrice);
		participantPanel.add(singlePrice);
		participantPanel.add(multiPrice);
		participantPanel.add(petPrice);
		westPane.add(participantPanel);
		
		JPanel spacePane3 = new JPanel();
		spacePane3.setBorder(BorderFactory.createEmptyBorder(-6,-6,-6,-6));
		westPane.add(spacePane3);

		contentPane.add(westPane, BorderLayout.WEST);
		
		/*
		 * Center Pane
		 */
		
		centerPane = new JPanel();
		
		ImageIcon pic = new ImageIcon("Image.jpeg");
		centerPic = new JLabel();
		centerPic.setIcon(pic);
		centerPane.add(centerPic);
		
		centerPane.setVisible(true);
		contentPane.add(centerPane, BorderLayout.CENTER);
		
		/*
		 * East Pane
		 */
		eastPane = new JPanel();
		eastPane.setVisible(true);
		eastPane.addComponentListener(this);
		eastButton = new JButton("Order Now!");
		eastPane.add(eastButton);
		//eastPane.setBorder(BorderFactory.createTitledBorder(""));
		contentPane.add(eastPane, BorderLayout.EAST);
		
		
		/*
		 * South Pane
		 */
		
		southPane = new JPanel();
		southPane.setBorder(topPad);
		//southPane.setBorder(BorderFactory.createTitledBorder(""));
		
		southTotal = new JTextArea("Your total is " + (locationChoice + participantChoice));
		southTotal.setLineWrap(false);
		southTotal.setEditable(false);
		southTotal.setBackground(contentPane.getBackground());
		southTotal.setVisible(true);
		southPane.add(southTotal);
		
		southPane.setVisible(true);
		contentPane.add(southPane, BorderLayout.PAGE_END);
		
		this.setContentPane(contentPane);
		
	
		
		westPane.setVisible(false);
		westPane.setVisible(true);//makes eastPane.size() == westPane.size()
		
		pack();
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if(source == inStudio)
		{
			if(inStudio.isSelected())
				locationChoice = 0.0;
		}
		else if(source == onLocation)
		{
			if(onLocation.isSelected())
				locationChoice = 90.0;
		}
		else if(source == singlePrice)
		{
			if(singlePrice.isSelected())
				participantChoice = 40.0;
		}
		else if(source == multiPrice)
		{
			if(multiPrice.isSelected())
				participantChoice = 75.0;
		}
		else if(source == petPrice)
		{
			if(petPrice.isSelected())
				participantChoice = 95.0;
		}
		southTotal.setText("Your total is $" + (locationChoice + participantChoice)+"");
	}

	@Override
	public void componentResized(ComponentEvent e) {
		Dimension ebps = new Dimension(westPane.getWidth()-7, westPane.getHeight()-7);
		eastButton.setPreferredSize(ebps);
	}

	@Override
	public void componentMoved(ComponentEvent e) {
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		Dimension epps = new Dimension(westPane.getWidth(), westPane.getHeight());
		eastPane.setPreferredSize(epps);
		Dimension ebps = new Dimension(westPane.getWidth()-7, westPane.getHeight()-7);
		eastButton.setPreferredSize(ebps);
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {

		
	}



}
