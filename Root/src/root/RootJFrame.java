package root;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class RootJFrame extends JFrame implements ActionListener {

	/*^***********
	 * variables *
	 *************/
	private static final long serialVersionUID = 1L;
	private JPanel contentPane, north, west, center, east, south;
	
	/*^**************
	 * constructors *
	 ****************/
	
	public RootJFrame() 
	{
		super();
		
		contentPane = new JPanel(new BorderLayout());
		contentPane.setVisible(true);
		
		north = new JPanel(new FlowLayout(FlowLayout.CENTER));
		north.setBorder(BorderFactory.createEtchedBorder());
		west = new JPanel(new FlowLayout(FlowLayout.CENTER));
		west.setBorder(BorderFactory.createEtchedBorder());
		center = new JPanel(new FlowLayout(FlowLayout.CENTER));
		center.setBorder(BorderFactory.createEtchedBorder());
		east = new JPanel(new FlowLayout(FlowLayout.CENTER));
		east.setBorder(BorderFactory.createEtchedBorder());
		south = new JPanel(new FlowLayout(FlowLayout.CENTER));
		south.setBorder(BorderFactory.createEtchedBorder());
		
		contentPane.add(north, BorderLayout.NORTH);
		contentPane.add(west, BorderLayout.WEST);
		contentPane.add(center, BorderLayout.CENTER);
		contentPane.add(east, BorderLayout.EAST);
		contentPane.add(south, BorderLayout.SOUTH);
		
		
		
		
		
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
		// TODO Auto-generated method stub

	}

	/*^*************
	 * main method *
	 ***************/
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RootJFrame window = new RootJFrame();
		window.setVisible(true);

	}

}
