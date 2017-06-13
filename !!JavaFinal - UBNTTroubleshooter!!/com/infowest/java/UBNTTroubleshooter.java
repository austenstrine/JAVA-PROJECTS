package com.infowest.java;

import java.util.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.tree.*;

public class UBNTTroubleshooter extends JFrame implements ActionListener {

/*
 *	Variables 
 */
	private static final long serialVersionUID = -1079398159072533776L;
	private JPanel contentPane = new JPanel(new BorderLayout()),
					northPane = new JPanel(new GridLayout(2,1)),
					westPane = new JPanel(new GridLayout(1,1)),
						westSouthPane = new JPanel(new FlowLayout()),
					centerPane = new JPanel(new BorderLayout()),
						centerCenterPane = new JPanel(new FlowLayout()),
						centerSouthPane = new JPanel(new FlowLayout()),
					eastPane = new JPanel(new GridLayout(6,1)),
					southPane = new JPanel(new GridLayout(1,2)),
						southUSplit = new JPanel(),
						southPSplit = new JPanel();
	
	private String	username = "User";
	private JLabel	processingLabel = new JLabel("Done"),
					userLabel = new JLabel(username); 
	private TroubleshootingTree tree = new TroubleshootingTree();
	private JScrollPane treeScroll = new JScrollPane(tree),
			westSouthScroll = new JScrollPane(westSouthPane),
			centerSouthScroll = new JScrollPane(centerSouthPane),
			eastScroll = new JScrollPane(eastPane);
	
	private JSplitPane	consoleMsgs = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, southPSplit, southUSplit),
						westSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, treeScroll, westSouthScroll);
	private JTextArea mainTxtArea = new JTextArea("Welcome to the UBNT Troubleshooter!");
	
	private JMenuBar menu = new JMenuBar();
	
		private JMenu file = new JMenu("File");
			private JMenuItem	logIn = new JMenuItem("Log In"),
								logOut = new JMenuItem("Log Out"),
								newUser = new JMenuItem("New User"),
								exit = new JMenuItem("Exit");
			
		private JMenu edit = new JMenu("Edit");
			private JMenu toggle = new JMenu("Toggle");
				private JMenuItem	tTooltips = new JMenuItem("Tooltips (ON)"),
									tCallHandling = new JMenuItem("Call Handling Suggestions (ON)");
				
		private JMenu navigate = new JMenu("Navigate");
			private JMenuItem	previous = new JMenuItem("Previous Step"),
								next = new JMenuItem("Next Step"),//is only valid if previous step was just called.
								history = new JMenuItem("History");
			
		private JMenu userPrefs = new JMenu(username + " Preferences");
			private JMenuItem setStartup = new JMenuItem("Startup Options");
			private JMenuItem editName = new JMenuItem("Change Username");
			//private JMenuItem setStartup = new JMenuItem("Startup Options");
			
	private JCheckBox	check1 = new JCheckBox("first"),
	 					check2 = new JCheckBox("second"),
	  					check3 = new JCheckBox("third"),
	  					check4 = new JCheckBox("fourth"),
	  					check5 = new JCheckBox("fifth"),
	  					check6 = new JCheckBox("last");
	private JRadioButton	radio1 = new JRadioButton("first"),
							radio2 = new JRadioButton("second"),
							radio3 = new JRadioButton("third"),
							radio4 = new JRadioButton("fourth");
	private ButtonGroup radioButtons = new ButtonGroup();
	

/*
 *	Constructors
 */
	
	public UBNTTroubleshooter()
	{
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(contentPane);
		
		contentPane.add(northPane, BorderLayout.NORTH);
			northPane.add(menu);
			northPane.setBackground(Color.white);
				menu.add(file);
					file.add(logIn);
					file.add(logOut);
					file.add(newUser);
					file.add(exit);
				menu.add(edit);
					edit.add(toggle);
						toggle.add(tTooltips);
						toggle.add(tCallHandling);
				menu.add(navigate);
					navigate.add(previous);
					navigate.add(next);
					navigate.add(history);
				menu.add(userPrefs);
					userPrefs.add(setStartup);
					userPrefs.add(editName);
					
					
		contentPane.add(westPane, BorderLayout.WEST);
			westPane.add(westSplit);
			westPane.setBackground(Color.white);
			
			
		contentPane.add(centerPane, BorderLayout.CENTER);
			centerPane.add(centerSouthScroll, BorderLayout.SOUTH);
				radioButtons.add(radio1);
				radioButtons.add(radio2);
				radioButtons.add(radio3);
				radioButtons.add(radio4);
				centerSouthPane.add(radio1);
				centerSouthPane.add(radio2);
				centerSouthPane.add(radio3);
				centerSouthPane.add(radio4);
				centerSouthPane.setBackground(Color.white);
			centerPane.add(centerCenterPane, BorderLayout.CENTER);
				centerCenterPane.add(mainTxtArea);
				centerCenterPane.setBackground(Color.white);
			centerPane.setBackground(Color.white);
		
				
		contentPane.add(eastPane, BorderLayout.EAST);
			eastPane.add(check1);
			eastPane.add(check2);
			eastPane.add(check3);
			eastPane.add(check4);
			eastPane.add(check5);
			eastPane.add(check6);
			eastPane.setBackground(Color.white);
			//check6.setVisible(false);
			
			
		contentPane.add(southPane, BorderLayout.SOUTH);
			southPane.add(consoleMsgs);
					southUSplit.add(userLabel);
					southUSplit.setBackground(Color.white);
					southPSplit.add(processingLabel);
					southPSplit.setBackground(Color.white);
					pack();
					consoleMsgs.setDividerLocation(0.40d);
					Dimension d = new Dimension(southPSplit.getWidth()*3, southPSplit.getHeight());
					southPSplit.setMinimumSize(d);
				consoleMsgs.setBackground(Color.white);
			southPane.setBackground(Color.white);
			userLabel.setVisible(true);
		
	}
	

/*
 *	Methods
 */

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


/*
 *	Main Method 
 */
	
	public static void main(String[] args) {
		UBNTTroubleshooter window = new UBNTTroubleshooter();
		window.setVisible(true);

	}

}


class TroubleshootingTree extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4550247919337161261L;
	
	public String[] stringOfTree = {
		"UBNT",
		
		"Speed", 
		"Connectivity",
		
		//speed
		"PPPoE Logs Incrementing", "PPPoE Logs Normal",
		
			//PPPoE incrementing
				"Incrementing Regularly", 
				"Incrementing Irregularly",
	
				//incrementing regularly
					"Router Accessible", 
					"Router Inacessible",
					
					//incrementing irregularly
					"SU Logs show LAN0 cycling", 
					"No LAN0 activity in logs",

			//PPPoE normal
			"SU signal/quality good", 
			"SU signal/quality poor",
			
				//SU signal good
				"Wireless Only", 
				"Wired and Wireless", 
				"Bypassed, Wired, and Wireless",
				
					//Wireless only
					"Standard Router", 
					"Dual-Band Router",
					
					//Wired AND Wireless
					"TODO",
					
					//Bypassed, Wired, and Wireless
					"TODO",
					
				//SU signal poor
				"Good signal, Bad quality", 
				"Bad signal, Bad quality",
				
		//connectivity
		"PPPoE IP, SU Access", 
		"No PPPoE IP, SU Access", 
		"No PPPoE IP, No SU Access",
			
			//PPPoE, no SU
			"LAN0 unplugged", 
			"LAN0 < 100mbps full", 
			"LAN0 100mbps full",
				
				//LAN0 unplugged
				"Router Lights ON", 
				"Router Lights OFF"
	};
	
	public String[] txtBodyStrings = {
			//UBNT
			"Is the customer calling in about speed concerns, or Connectivity in general?",
				//speed
				"Take a look at PPPoE logs. If there aren't a lot, they aren't incrementing. "
						+ "It's not unusual to see 1-3 if the customer has been rebooting the router "
						+ "to resolve their issues. But if it's more like 10-100, PPPoE is incrementing.",
					//PPPoE incrementing
					"Since the logs are incrementing, we need to determine whether it's regular "
						+ "or irregular. Regular would be, every 30 seconds, almost exactly, or every "
						+ "60 seconds, almost exactly. Irregular will have random amounts of time "
						+ "in-between", 
						//incrementing regularly
						"When PPPoE increments like this, it's almost definitely a TP-Link or "
							+ "D-Link router bugging out on PPPoE. We have a nice workaround, but it "
							+ "requires the customer to be able to log into the router and change a setting.",
						//incrementing irregularly
							"TODO",
					//PPPoE normal
					"Let's take a look at the SU. The signal should be > -72 ideally, "
						+ "and the airMax should be above 70%, both ways. Alignment shouldn't"
						+ "be farther than -3 db from each other. If all of this checks out,"
						+ "the SU's signal/quality is good. Otherwise, it's poor.",
			"TODO"
	};
	
	private JTree ubnt;
	
	public TroubleshootingTree()
	{
		super();
		this.setVisible(true);
		ubnt = new JTree(stringOfTree);
		this.add(ubnt);
		//ubnt.add
		//DefaultMutableTreeNode
	}
	
}