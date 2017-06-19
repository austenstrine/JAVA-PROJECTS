package com.infowest.java;

import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class UBNTTroubleshooter extends JFrame implements TreeSelectionListener, ActionListener {

/*
 *	Variables 
 */
	private static final long serialVersionUID = -1079398159072533776L;
	private ArrayList<BufferedImage> icons = new ArrayList<BufferedImage>(4);
	private JPanel contentPane = new JPanel(new BorderLayout()),
					northPane = new JPanel(new GridLayout(1,1)),
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
	private SerializeableTree tree = new SerializeableTree(new DefaultMutableTreeNode("(empty)"));
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
			private JMenuItem	loadTree = new JMenuItem("Load Saved Tree"),
								newTree = new JMenuItem("Create New Tree");
				
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
		
		try
		{
			File img = new File("wisp-t 20.png");
			BufferedImage bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 250.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 500.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
			img = new File("wisp-t 750.png");
			bufferedImage = ImageIO.read(img);
			icons.add(bufferedImage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.setIconImages(icons);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("WISP-T \u00A9 2017");
		this.setContentPane(contentPane);
		JFrame.setDefaultLookAndFeelDecorated(true);
		
		contentPane.add(northPane, BorderLayout.NORTH);
		contentPane.setBackground(Color.white);
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
					edit.addSeparator();
					edit.add(newTree);
					newTree.addActionListener(this);
					edit.add(loadTree);
					loadTree.addActionListener(this);
				menu.add(navigate);
					navigate.add(previous);
					navigate.add(next);
					navigate.add(history);
				menu.add(userPrefs);
					userPrefs.add(setStartup);
					userPrefs.add(editName);
					
					
		contentPane.add(westPane, BorderLayout.WEST);
			westPane.add(westSplit);
				westSplit.setBackground(Color.white);
				westSplit.setForeground(Color.white);
				westSouthScroll.setBackground(Color.white);
			westPane.setBackground(Color.white);
			Dimension westD = new Dimension(150, 450);
			treeScroll.setMaximumSize(westD);
			treeScroll.setPreferredSize(westD);
			treeScroll.setMinimumSize(westD);
			
			
		contentPane.add(centerPane, BorderLayout.CENTER);
			centerPane.add(centerSouthScroll, BorderLayout.SOUTH);
				radioButtons.add(radio1);
				radio1.setBackground(Color.white);
				radioButtons.add(radio2);
				radio2.setBackground(Color.white);
				radioButtons.add(radio3);
				radio3.setBackground(Color.white);
				radioButtons.add(radio4);
				radio4.setBackground(Color.white);
				centerSouthPane.add(radio1);
				centerSouthPane.add(radio2);
				centerSouthPane.add(radio3);
				centerSouthPane.add(radio4);
				centerSouthPane.setBackground(Color.white);
			centerPane.add(centerCenterPane, BorderLayout.CENTER);
				centerCenterPane.add(mainTxtArea);
				centerCenterPane.setBackground(Color.white);
				centerCenterPane.setBorder(BorderFactory.createEtchedBorder());
			centerPane.setBackground(Color.white);
		
				
		contentPane.add(eastScroll, BorderLayout.EAST);
			eastPane.add(check1);
			check1.setBackground(Color.white);
			eastPane.add(check2);
			check2.setBackground(Color.white);
			eastPane.add(check3);
			check3.setBackground(Color.white);
			eastPane.add(check4);
			check4.setBackground(Color.white);
			eastPane.add(check5);
			check5.setBackground(Color.white);
			eastPane.add(check6);
			check6.setBackground(Color.white);
			eastScroll.setBackground(Color.white);
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
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == newTree)
		{
			TreeModel model = getTree("defaultTree").getModel();
			tree.setModel(model);
			tree.addTreeSelectionListener(this);
		}
		treeScroll.repaint();
		tree.repaint();

	}
	
	public SerializeableTree getTree(String fileName)
	{
		SerializeableTree inTree = new SerializeableTree(new DefaultMutableTreeNode("(empty)"));
		try 
		{
	         FileInputStream fileIn = new FileInputStream((fileName+".ser"));
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         inTree = (SerializeableTree)in.readObject();
	         in.close();
	         fileIn.close();
	         System.out.println("Object read from "+fileName+".ser");
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return inTree;
	}


/*
 *	Main Method 
 */
	
	public static void main(String[] args) {
		UBNTTroubleshooter window = new UBNTTroubleshooter();
		window.setVisible(true);

	}


	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
		Stringable selectedStringable = (Stringable)node.getUserObject();
		mainTxtArea.setText(selectedStringable.getContent());
		
	}

}