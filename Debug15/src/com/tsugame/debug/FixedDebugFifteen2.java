package com.tsugame.debug;

// Demonstrates layout positions
// using BorderLayout
import javax.swing.*;
import java.awt.*;
//import java.awt.event.*;

public class FixedDebugFifteen2 extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton nb = new JButton("Up    ");
	JButton sb = new JButton("Down  ");
	JButton eb = new JButton("Right ");
	JButton wb = new JButton("Left  ");
	JButton cb = new JButton("Center");
	Container con = null;

  public FixedDebugFifteen2()
  {
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     con = this.getContentPane();
     con.setLayout(new BorderLayout());
     con.add(nb, BorderLayout.NORTH);
     con.add(sb, BorderLayout.SOUTH);
     con.add(eb, BorderLayout.EAST);
     con.add(wb, BorderLayout.WEST);
     con.add(cb, BorderLayout.CENTER);
   }
   public static void main(String[] args)
   {
      FixedDebugFifteen2 f = new FixedDebugFifteen2();
      f.setSize(300, 300);
      f.setVisible(true);
   }
 }