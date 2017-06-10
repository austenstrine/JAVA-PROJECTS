package com.tsugame.practice;

import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.util.Map;
import java.awt.*;

import javax.swing.*;

public class JFontSelector extends JFrame implements ActionListener 
{
	
	/*************
	 * Variables *
	 *************/

	private static final long serialVersionUID = -7225151307533039956L;
	private JButton arial, timesNew, console, verdana, mono, bold, italic, underline, larger, smaller;
	private JPanel contentPane, top, bottom, left, right, middle;
	private JTextArea demo = new JTextArea("Aa Bb Cc Dd Ee Ff\nGg Hh Ii Jj Kk Ll\nMm Nn Oo Pp Qq Rr\nSs Tt Uu Vv Ww Xx\nYy Zz"), description;
	
	
	/****************
	 * Constructors *
	 ****************/

		public JFontSelector()
		{
			super();
			contentPane = new JPanel(new BorderLayout());
			contentPane.setBorder(BorderFactory.createEmptyBorder(6,6,6,6));
			
			top = new JPanel(new FlowLayout());
			bottom = new JPanel(new FlowLayout());
			left = new JPanel(new GridLayout(5,1));
			right = new JPanel(new GridLayout(5,1));
			middle = new JPanel(new FlowLayout());
			
			contentPane.add(top, BorderLayout.NORTH);
			contentPane.add(bottom, BorderLayout.SOUTH);
			contentPane.add(left, BorderLayout.WEST);
			contentPane.add(right, BorderLayout.EAST);
			contentPane.add(middle, BorderLayout.CENTER);
			
			//top
			description = new JTextArea("Click the buttons to change the demonstration text's font, style, and size!");
			description.setEditable(false);
			description.setLineWrap(false);
			description.setBorder(BorderFactory.createEtchedBorder());
			description.setBackground(top.getBackground());
			top.add(description);
			
			//left
			arial = new JButton("Arial");
			arial.setFont(new Font("Arial", Font.PLAIN, 12));
			arial.addActionListener(this);
			left.add(arial);
			
			timesNew = new JButton("Times New Roman");
			timesNew.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			timesNew.addActionListener(this);
			left.add(timesNew);
			
			console = new JButton("Consolas");
			console.setFont(new Font("Consolas", Font.PLAIN, 12));
			console.addActionListener(this);
			left.add(console);
			
			verdana = new JButton("Verdana");
			verdana.setFont(new Font("Verdana", Font.PLAIN, 12));
			verdana.addActionListener(this);
			left.add(verdana);
			
			mono = new JButton("Monospaced");
			mono.setFont(new Font("Monospaced", Font.PLAIN, 12));
			mono.addActionListener(this);
			left.add(mono);
			
			//middle
			demo.setEditable(false);
			demo.setAutoscrolls(true);
			demo.setLineWrap(false);
			demo.setBackground(contentPane.getBackground());
			middle.add(demo);
			middle.setBorder(BorderFactory.createTitledBorder("The Demo area"));
			
			//right
			bold = new JButton("Bold");
			bold.setFont(new Font("Monospaced", Font.BOLD, 12));
			bold.addActionListener(this);
			right.add(bold);
			
			italic = new JButton("Italic");
			italic.setFont(new Font("Monospaced", Font.ITALIC, 12));
			italic.addActionListener(this);
			right.add(italic);
			
			underline = new JButton("Underline");
			underline.setFont(getUnderlineFont());
			underline.addActionListener(this);
			right.add(underline);
			
			larger = new JButton("Larger");
			larger.setFont(new Font("Monospaced", Font.PLAIN, 14));
			larger.addActionListener(this);
			right.add(larger);
			
			smaller = new JButton("Smaller");
			smaller.setFont(new Font("Monospaced", Font.PLAIN, 10));
			smaller.addActionListener(this);
			right.add(smaller);
			
			//bottom
			JTextArea note = new JTextArea("The underline bugs out when you change the size, and I don't know why "
					+ "\nConsolas is identical to Monospace, but otherwise, it works.");
			note.setEditable(false);
			note.setLineWrap(false);
			note.setBorder(BorderFactory.createEtchedBorder());
			note.setBackground(bottom.getBackground());
			bottom.add(note);
			
			this.setContentPane(contentPane);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			pack();
			this.setVisible(true);
			
		}

	/***********
	 * Methods *
	 ***********/
	
	// Setters
	
	
	
	// Getters
	
	@SuppressWarnings("unchecked")
	public Font getUnderlineFont(String fontName, int size)
	{
		Font font = new Font(fontName, Font.PLAIN, size);
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		Font newFont = new Font(attributes); 
		return newFont;
	}	
	public Font getUnderlineFont(String fontName)
	{
		return getUnderlineFont(fontName, 12);
	}	
	public Font getUnderlineFont(int size)
	{
		return getUnderlineFont("Monospaced", size);
	}	
	public Font getUnderlineFont()
	{
		return getUnderlineFont("Monospaced", 12);
	}
	
	public Font newFontSize(Font current, int newSize)
	{
		return new Font(current.getFamily(), current.getStyle(), newSize);
	}
	public Font newFontType(Font current, String fontType)
	{
		return new Font(fontType, current.getStyle(), current.getSize());
	}
	public Font newFontStyle(Font current, String style)
	{
		switch(style.toLowerCase())
		{
			case "bold":
				return new Font(current.getFamily(), Font.BOLD, current.getSize());
			case "italic":
				return new Font(current.getFamily(), Font.ITALIC, current.getSize());
			case "underline":
				return getUnderlineFont(current.getFamily(), current.getSize());
			case "plain":
				return new Font(current.getFamily(), Font.PLAIN, current.getSize());
			default:
				return current;
		}
	}
	// Other/Misc

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Object source = e.getSource();
		if(source == bold)
		{
			demo.setFont(newFontStyle(demo.getFont(), "bold"));
		}
		else if(source == italic)
		{
			demo.setFont(newFontStyle(demo.getFont(), "italic"));
		}
		else if(source == underline)
		{
			demo.setFont(newFontStyle(demo.getFont(), "underline"));
		}
		else if(source == larger)
		{
			demo.setFont(newFontSize(demo.getFont(), demo.getFont().getSize()+2));
		}
		else if(source == smaller)
		{
			demo.setFont(newFontSize(demo.getFont(), demo.getFont().getSize()-2));
		}
		else if(source == arial)
		{
			demo.setFont(newFontType(demo.getFont(), "Arial"));
		}
		else if(source == timesNew)
		{
			demo.setFont(newFontType(demo.getFont(), "Times New Roman"));
		}
		else if(source == console)
		{
			demo.setFont(newFontType(demo.getFont(), "Consolas"));
		}
		else if(source == verdana)
		{
			demo.setFont(newFontType(demo.getFont(), "Verdana"));
		}
		else if(source == mono)
		{
			demo.setFont(newFontType(demo.getFont(), "Monospace"));
		}
		pack();
		//arial, timesNew, console, verdana, mono, bold, italic, underline, larger, smaller;
	}

	/***************
	 * Main Method *
	 ***************/
	
	public static void main(String[] args) 
	{
		JFontSelector window = new JFontSelector();
		window.setVisible(true);
	}

}
