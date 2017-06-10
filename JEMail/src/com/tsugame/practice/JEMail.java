package com.tsugame.practice;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JEMail extends JFrame implements ActionListener {

	/*^***********
	 * variables *
	 *************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, north, west, center, east, south;
	private JTextArea to,subject,body;
	private JScrollPane scrollBody;
	private JButton clearNonBody, save, send;
	
	/*^**************
	 * constructors *
	 ****************/
	
	public JEMail() 
	{
		super("JEMail App");
		contentPane = new JPanel(new BorderLayout());
		contentPane.setVisible(true);
		contentPane.setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
		
		north = new JPanel(new GridLayout(2,1));
		north.setBorder(BorderFactory.createEtchedBorder());
		//west = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//west.setBorder(BorderFactory.createEtchedBorder());
		center = new JPanel(new GridLayout(1,1));
		center.setBorder(BorderFactory.createEtchedBorder());
		//east = new JPanel(new FlowLayout(FlowLayout.CENTER));
		//east.setBorder(BorderFactory.createEtchedBorder());
		south = new JPanel(new GridLayout(1,3));
		south.setBorder(BorderFactory.createEtchedBorder());
		
		contentPane.add(north, BorderLayout.NORTH);
		//contentPane.add(west, BorderLayout.WEST);
		contentPane.add(center, BorderLayout.CENTER);
		//contentPane.add(east, BorderLayout.EAST);
		contentPane.add(south, BorderLayout.SOUTH);
		
		//north
		to = new JTextArea(1,50);
		to.setBorder(BorderFactory.createTitledBorder("To"));
		north.add(to);
		subject = new JTextArea(1,50);
		subject.setBorder(BorderFactory.createTitledBorder("Subject"));
		north.add(subject);
		
		//center
		body = new JTextArea(25,50);
		body.setBorder(BorderFactory.createTitledBorder("Body"));
		body.setLineWrap(true);
		body.setTabSize(4);
		scrollBody = new JScrollPane(body);
		scrollBody.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		center.add(scrollBody);
		
		//south
		clearNonBody = new JButton("Clear");
		clearNonBody.addActionListener(this);
		save = new JButton("Save");
		save.addActionListener(this);
		send = new JButton("Send");
		send.addActionListener(this);
		south.add(clearNonBody);
		south.add(save);
		south.add(send);
		
		
		this.setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		this.setVisible(true);
	}
	
	/*^*********
	 * methods *
	 ***********/
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object source = arg0.getSource();
		if(source == clearNonBody)
		{
			clearer();
		}
		else if(source == save)
		{
			saver();
		}
		else if(source == send)
		{
			sender();
		}

	}
	
	public void clearer()
	{
		if(to.getText().equals("") && subject.getText().equals("") && body.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, 
					"E-mail is already cleared!", 
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int option = JOptionPane.showConfirmDialog(null, 
					"This will clear the TO, SUBJECT, and BODY fields. Continue?", 
					"Confirmation Needed",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(option == 0)//yes
			{
				to.setText("");
				subject.setText("");
				body.setText("");
			}
		}
	}
	public void saver()
	{
		int option = JOptionPane.showConfirmDialog(null, 
				"Save this message as a draft?", 
				"Confirmation Needed",  
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(option == 0)//yes
		{
			JOptionPane.showMessageDialog(null, 
					"Error has occurred: This is not a real e-mail application.", 
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public void sender()
	{
		if(to.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, 
					"TO required!", 
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
		else if(subject.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, 
					"SUBJECT required!", 
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
		else if(body.getText().equals(""))
		{
			JOptionPane.showMessageDialog(null, 
					"BODY required!", 
					"Error!", 
					JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			int option = JOptionPane.showConfirmDialog(null, 
					"Are you ready to send the message?", 
					"Confirmation Needed",  
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if(option == 0)//yes
			{
				JOptionPane.showMessageDialog(null, 
						"Error has occurred: This is not a real e-mail application.", 
						"Error!", 
						JOptionPane.ERROR_MESSAGE);
				body.append("\n\tMail has been sent!");
			}
		}
	}

	/*^*************
	 * main method *
	 ***************/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JEMail window = new JEMail();
		window.setVisible(true);

	}

}
