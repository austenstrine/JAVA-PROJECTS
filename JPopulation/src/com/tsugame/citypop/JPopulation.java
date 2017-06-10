/*
 * Write an application that allows a user to select a city 
 * from a list box that contains at least seven options. 
 * Display the population of the city in a text field after 
 * the user makes a selection. Save the file as 
 * JPopulation.java.
 */
package com.tsugame.citypop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JPopulation extends JFrame implements ItemListener
{
	/*************
	 * Variables *
	 *************/
	private static final long serialVersionUID = 6749246006630396766L;
	private final String[] 
		cityAr = 
		{
			"",
			"Washington D.C., USA",
			"Rio de Janeiro, Brazil",
			"Anchorage AK, USA",
			"Tokyo, Japan",
			"Seoul, South Korea",
			"Berlin, Germany",
			"Sydney, Australia",
			"Johannesburg, South Africa",
			"Jerusalem, Israel",
			"Mumbai, India"
			
		}, 
			
		popAr = 
		{
			"______________",
			"672,228",
			"6,093,472",
			"301,010",
			"13,506,000",
			"10,290,000",
			"3,671,000",
			"5,005,400",
			"957,441",
			"865,721",
			"12,442,373"
		};
	private JComboBox<String> cities = new JComboBox<String>(cityAr);
	private Integer selection = cities.getSelectedIndex();
	private JTextField populationTF = new JTextField();
	
	/****************
	 * Constructors *
	 ****************/
	
	public JPopulation()
	{
		super();
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(cities);
		//cities.setSize(width, height);
		this.add(populationTF);
		populationTF.setVisible(false);
		populationTF.setSize(144, 24);
		cities.addItemListener(this);
		cities.setVisible(true);
		this.pack();
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		String population = "";
		selection = cities.getSelectedIndex();
		if(source == cities)
		{
			switch(selection)
			{
			case 1:
				population = popAr[1];
				visible(true);
				break;
			case 2:
				population = popAr[2];
				visible(true);
				break;
			case 3:
				population = popAr[3];
				visible(true);
				break;
			case 4:
				population = popAr[4];
				visible(true);
				break;
			case 5:
				population = popAr[5];
				visible(true);
				break;
			case 6:
				population = popAr[6];
				visible(true);
				break;
			case 7:
				population = popAr[7];
				visible(true);
				break;
			case 8:
				population = popAr[8];
				visible(true);
				break;
			case 9:
				population = popAr[9];
				visible(true);
				break;
			case 10:
				population = popAr[10];
				visible(true);
				break;
			default:
				visible(false);
				break;
			}
			populationTF.setText(population);
		}
		this.pack();
		
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other
	public void visible(boolean b)
	{
		if(b)
		{
			if(!populationTF.isVisible())
			{
				populationTF.setVisible(true);
			}
		}
		else
		{
			populationTF.setVisible(false);
		}
	}
	
}
