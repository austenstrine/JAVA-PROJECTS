/*
 * Search the Java Web site for information on how to use a JTextArea, 
 * its constructors, and its setText() and append() methods. Write an 
 * application that allows the user to select options for a dormitory 
 * room. Use JCheckBoxes for options such as private room, Internet 
 * connection, cable TV connection, microwave, refrigerator, and so 
 * on. When the application starts, use a text area to display a 
 * message listing the options that are not yet selected. As the user 
 * selects and deselects options, add appropriate messages to the 
 * common text area so it accumulates a running list that reflects the 
 * user’s choices. Save the file as JDorm.java. 
 * Modify the JDorm application so that instead of a running list of 
 * the user’s choices, the application displays only the current 
 * choices. Save the file as JDorm2.java.
 */
package com.tsugame.dorm;

import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class JDorm2 extends JFrame implements ItemListener 
{
	/*************
	 * Variables *
	 *************/
	private static final long serialVersionUID = -2255686783020956680L;
	private ArrayList<Stringable> listed = new ArrayList<Stringable>();
	private JTextArea txtArea = new JTextArea(listed.toString());
	private JCheckBox 
		housing,
		frat,
		handicap,
		privacy,
		glass,
		microFridge,
		fullKit,
		laundry,
		internet,
		tv;

	private JPanel checkboxes = new JPanel();
	private GridLayout grid = new GridLayout(5, 2, 5, 5);
	private GridLayout major = new GridLayout(1,2,5,5);
	
	
	/****************
	 * Constructors *
	 ****************/
	public JDorm2()
	{
		super();
		this.setLayout(major);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		{int i = 0;
		while(i < 10)
		{
			listed.add(new Stringable(""));
			++i;
		}}
		listed.get(0).setAll("Campus Housing","Off-Campus Housing",false);
		listed.get(1).setAll("\nFraternity","\nCo-Ed",false);
		listed.get(2).setAll("\nHandicap Access","\nStandard Access",false);
		listed.get(3).setAll("\nPrivate Bedroom","\nShared Bedroom",false);
		listed.get(4).setAll("\nWindowed Bedroom", "\nNo Window",false);
		listed.get(5).setAll("\nMicrowave & Mini-Fridge", "\nNo Kitchen",false);
		listed.get(6).setAll("\nFull Kitchen Amenities", "\nNo Stove/Mini-Dishwasher",false);
		listed.get(7).setAll("\nLaundry Amenities", "\nNo Laundry",false);
		listed.get(8).setAll("\nInternet Service", "\nNo Internet",false);
		listed.get(9).setAll("\nTelevision Service\n", "\nNo TV Service\n",false);
		
		checkboxes.setLayout(grid);
		housing = new JCheckBox("Campus Housing", false);
		housing.addItemListener(this);
		checkboxes.add(housing);
		frat = new JCheckBox("Fraternity", false);
		basicSet(frat);
		checkboxes.add(frat, BorderLayout.WEST);
		handicap = new JCheckBox("Handicap Access", false);
		basicSet(handicap);
		checkboxes.add(handicap, BorderLayout.WEST);
		privacy = new JCheckBox("Private Bedroom", false);
		basicSet(privacy);
		checkboxes.add(privacy, BorderLayout.WEST);
		glass = new JCheckBox("Windowed Bedroom", false);
		basicSet(glass);
		checkboxes.add(glass, BorderLayout.WEST);
		microFridge = new JCheckBox("Microwave & Mini-Fridge", false);
		basicSet(microFridge);
		checkboxes.add(microFridge, BorderLayout.WEST);
		fullKit = new JCheckBox("Full Kitchen Amenities", false);
		basicSet(fullKit);
		fullKit.setEnabled(false);
		checkboxes.add(fullKit, BorderLayout.WEST);
		laundry = new JCheckBox("Laundry Amenities", false);
		basicSet(laundry);
		checkboxes.add(laundry, BorderLayout.WEST);
		internet = new JCheckBox("Internet Service", false);
		basicSet(internet);
		checkboxes.add(internet, BorderLayout.WEST);
		tv = new JCheckBox("Television Service", false);
		basicSet(tv);
		checkboxes.add(tv, BorderLayout.WEST);
		checkboxes.setVisible(true);
		this.add(checkboxes);
		
		txtArea.setText(getListedString());
		txtArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(txtArea, BorderLayout.EAST);
		txtArea.setVisible(true);
		this.pack();
		txtArea.setText(listed.get(0).toString());
	}
	
	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	public void basicSet(JCheckBox check)
	{
		check.addItemListener(this);
		check.setEnabled(false);
		grid.addLayoutComponent(check.getName(), check);
	}
	
	public void enableAll(boolean enabled)
	{
		frat.setEnabled(enabled);
		handicap.setEnabled(enabled);
		privacy.setEnabled(enabled);
		glass.setEnabled(enabled);
		microFridge.setEnabled(enabled);
		if(enabled && microFridge.isSelected())
			fullKit.setEnabled(true);
		else
			fullKit.setEnabled(false);
		laundry.setEnabled(enabled);
		internet.setEnabled(enabled);
		tv.setEnabled(enabled);
	}
	// Getters
	
	
	// Other
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object source = e.getSource();
		if(source == housing)
		{
			if(housing.isSelected())
			{
				enableAll(true);
				listed.get(0).setVisible(true);
				txtArea.setText(getListedString());
			}
			else
			{
				enableAll(false);
				listed.get(0).setVisible(false);
				txtArea.setText("Off-Campus Housing\n");
			}
		}
		else if(source == frat)
		{
			if(frat.isSelected())
			{
				listed.get(1).setVisible(true);
			}
			else
			{
				listed.get(1).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == handicap)
		{
			if(handicap.isSelected())
			{
				listed.get(2).setVisible(true);
			}
			else
			{
				listed.get(2).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == privacy)
		{
			if(privacy.isSelected())
			{
				listed.get(3).setVisible(true);
			}
			else
			{
				listed.get(3).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == glass)
		{
			if(glass.isSelected())
			{
				listed.get(4).setVisible(true);
			}
			else
			{
				listed.get(4).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == microFridge)
		{
			if(microFridge.isSelected())
			{
				listed.get(5).setVisible(true);
				fullKit.setEnabled(true);
			}
			else
			{
				listed.get(5).setVisible(false);
				fullKit.setEnabled(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == fullKit)
		{
			if(fullKit.isSelected())
			{
				listed.get(6).setVisible(true);
			}
			else
			{
				listed.get(6).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == laundry)
		{
			if(laundry.isSelected())
			{
				listed.get(7).setVisible(true);
			}
			else
			{
				listed.get(7).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == internet)
		{
			if(internet.isSelected())
			{
				listed.get(8).setVisible(true);
			}
			else
			{
				listed.get(8).setVisible(false);
			}
			txtArea.setText(getListedString());
		}
		else if(source == tv)
		{
			if(tv.isSelected())
			{
				listed.get(9).setVisible(true);
			}
			else
			{
				listed.get(9).setVisible(false);
			}
			txtArea.setText(getListedString());
		}

	}
	
	public String getListedString()
	{
		String concat = "";
		for(int i = 0; i < listed.size(); ++i)
		{
			concat+=listed.get(i).toString();
		}
		return concat;
	}

}
