/*
 * Create an application with a JFrame and five labels that contain 
 * the names of five friends. Every time the user clicks a JButton, 
 * remove one of the labels and add a different one. Save the file 
 * as JDisappearingFriends.java.
 */

package com.tsugame.friends;

import java.awt.FlowLayout;
//import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class JDisappearingFriends extends JFrame implements MouseListener
{
	/*^***********
	 * Variables *
	 *************/
	
	ImageIcon[] friendPic = {
			new ImageIcon("friend1.jpeg"), 
			new ImageIcon("friend2.jpeg"), 
			new ImageIcon("friend3.jpeg"), 
			new ImageIcon("friend4.jpeg"), 
			new ImageIcon("friend5.jpeg")
			};
	JLabel friend1, friend2, friend3, friend4, friend5;
	/**
	 * WOHOOOO, more poorly understood requirements
	 */
	private static final long serialVersionUID = 1L;
	
	/*^**************
	 * Constructors *
	 ****************/
	 
	public JDisappearingFriends()
	{
		super("Click here for a list of my IRL friends.");
		
		this.setLayout(new FlowLayout());
		this.setSize(3000, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		friend1 = new JLabel("Sometimes, your best friend is an actual friend.", friendPic[0], 0);
		friend1.setSize(800, 800);
		this.add(friend1);
		friend1.setVisible(true);
		friend1.addMouseListener(this);
		
		friend2 = new JLabel("Call me Kim. Kim Whel Hyung", friendPic[1], 0);
		friend2.setSize(800, 800);
		this.add(friend2);
		friend2.setVisible(false);
		friend2.addMouseListener(this);
		
		friend3 = new JLabel("I love you guys, but if a hammer is incoming, it's every digit for itself.", friendPic[2], 0);
		friend3.setSize(800, 800);
		this.add(friend3);
		friend3.setVisible(false);
		friend3.addMouseListener(this);
		
		friend4 = new JLabel("\'Friendbombing\' is kind of like photobombing, but IRL and very, very ceepy.", friendPic[3], 0);
		friend4.setSize(800, 800);
		this.add(friend4);
		friend4.setVisible(false);
		friend4.addMouseListener(this);
		
		friend5 = new JLabel("She's very thirsty for Pellegrino Sparkling water. So we made her a special cup...", friendPic[4], 0);
		friend5.setSize(800, 800);
		this.add(friend5);
		friend5.setVisible(false);
		friend5.addMouseListener(this);
	}
	
	/*^*********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	// Getters
	
	
	// Other



	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == friend1)
		{
			friend1.setVisible(false);
			friend2.setVisible(true);
		}
		else if(source == friend2)
		{
			friend2.setVisible(false);
			friend3.setVisible(true);
		}
		else if(source == friend3)
		{
			friend3.setVisible(false);
			friend4.setVisible(true);
		}
		else if(source == friend4)
		{
			friend4.setVisible(false);
			friend5.setVisible(true);
		}
		else if(source == friend5)
		{
			friend5.setVisible(false);
			friend1.setVisible(true);
		}
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/*^***************
	 * Main Function *
	 *****************/
	
	public static void main(String[] args)
	{
		JDisappearingFriends window = new JDisappearingFriends();
		window.setVisibility(true);
	}
}
