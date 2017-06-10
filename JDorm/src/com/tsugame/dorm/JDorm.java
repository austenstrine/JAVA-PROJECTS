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

import com.tsugame.dorm.CheckChoices;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class JDorm extends JFrame implements ItemListener 
{
	/*************
	 * Variables *
	 *************/
	private static final long serialVersionUID = -2255686783020956680L;
	private JTextArea txtArea = new JTextArea(CheckChoices.Off_Campus_Housing.toString()+ " ");
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
	public JDorm()
	{
		super();
		this.setLayout(major);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		
		txtArea.setText(CheckChoices.Off_Campus_Housing.toString()+ " ");
		txtArea.setLineWrap(true);
		txtArea.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.add(txtArea, BorderLayout.EAST);
		txtArea.setVisible(true);
		this.pack();
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
				txtArea.append(CheckChoices.Campus_Housing.toString()+ " ");//they should be uppercase, I know. Too much typing
			}
			else
			{
				enableAll(false);
				txtArea.append(CheckChoices.Off_Campus_Housing.toString()+ " ");
			}
		}
		else if(source == frat)
		{
			if(frat.isSelected())
			{
				txtArea.append(CheckChoices.Fraternity.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.Co_Ed.toString()+ " ");
			}
		}
		else if(source == handicap)
		{
			if(handicap.isSelected())
			{
				txtArea.append(CheckChoices.Handicap_Access.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.Standard_Access.toString()+ " ");
			}
		}
		else if(source == privacy)
		{
			if(privacy.isSelected())
			{
				txtArea.append(CheckChoices.Private_Bedroom.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.Shared_Bedroom.toString()+ " ");
			}
		}
		else if(source == glass)
		{
			if(glass.isSelected())
			{
				txtArea.append(CheckChoices.Windowed_Bedroom.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.No_Window.toString()+ " ");
			}
		}
		else if(source == microFridge)
		{
			if(microFridge.isSelected())
			{
				txtArea.append(CheckChoices.Microwave_AND_Mini_Fridge.toString()+ " ");
				fullKit.setEnabled(true);
			}
			else
			{
				txtArea.append(CheckChoices.No_Kitchen.toString()+ " ");
				fullKit.setEnabled(false);
			}
		}
		else if(source == fullKit)
		{
			if(fullKit.isSelected())
			{
				txtArea.append(CheckChoices.Full_Kitchen_Amenities.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.No_Stove_Mini_Dishwasher.toString()+ " ");
			}
		}
		else if(source == laundry)
		{
			if(laundry.isSelected())
			{
				txtArea.append(CheckChoices.Laundry_Amenities.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.No_Laundry.toString()+ " ");
			}
		}
		else if(source == internet)
		{
			if(internet.isSelected())
			{
				txtArea.append(CheckChoices.Internet_Service.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.No_Internet.toString()+ " ");
			}
		}
		else if(source == tv)
		{
			if(tv.isSelected())
			{
				txtArea.append(CheckChoices.Television_Service.toString()+ " ");
			}
			else
			{
				txtArea.append(CheckChoices.No_TV_Service.toString()+ " ");
			}
		}
		pack();

	}

}
