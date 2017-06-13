/*
 * here I'm just experimenting with ways to customize buttons
 */

package com.tsugame.practice;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.basic.*;

public class JButtonIconChange extends JFrame implements ActionListener {

	public JButtonIconChange()
	{
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton button = new JButton("Lots of stuff is written here");
		ImageIcon ico = new ImageIcon("images.jpeg");
		button.setIcon(ico);
		button.setBackground(Color.green);
		button.setForeground(Color.black);
		button.setFocusPainted(false);
		button.setVisible(true);
		button.setIconTextGap(-(ico.getIconWidth()/2) - (button.getText().length()*2)
				);
		button.setFont(new Font("Arial", Font.ITALIC, 12));
		this.getContentPane().add(button);
		System.out.println(button.getIconTextGap()+"");
		pack();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		JButtonIconChange window = new JButtonIconChange();
		window.setVisible(true);

	}

}

class MyButton extends BasicButtonUI
{
	
}
