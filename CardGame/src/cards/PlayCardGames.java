package cards;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

public class PlayCardGames {

	public static void main(String[] args) 
	{
		War war = new War();
		ArrayList<Card> player1 = war.deal();
		ArrayList<Card> player2 = war.deal();
		war.displayDescription();
		JOptionPane.showMessageDialog(null, "Each player will have " + war.getPerHand() + " cards per hand.");
		JOptionPane.showMessageDialog(null, "Player 1's hand is:\n"+player1.toString());
		JOptionPane.showMessageDialog(null, "Player 2's hand is:\n"+player2.toString());
		JOptionPane.showMessageDialog(null, "These cards remain in the deck:\n"+war.getDeck().toString());
		
		Rummy rummy = new Rummy();
		rummy.displayDescription();
		JOptionPane.showMessageDialog(null, "Each player will have " + rummy.getPerHand() + " cards per hand.");
		player1 = rummy.deal();
		player2 = rummy.deal();
		Collections.sort(player1);//
		Collections.sort(player2);//
		JOptionPane.showMessageDialog(null, "Player 1's hand is:\n"+player1.toString());
		JOptionPane.showMessageDialog(null, "Player 2's hand is:\n"+player2.toString());
		JOptionPane.showMessageDialog(null, "These cards remain in the deck:\n"+rummy.getDeck().toString());
	}

}
