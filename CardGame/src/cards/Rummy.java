package cards;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rummy extends CardGame {
	
	public Rummy(Integer pl)
	{
		super(pl, 13);
	}
	public Rummy()
	{
		this(2);
	}

	@Override
	public void displayDescription() {
		JOptionPane.showMessageDialog(null,
				    "Rummy is a matching card game with gameplay based on matching cards of the same rank or sequence and same suit. "
				+ "\nThe basic goal of rummy is to build melds which consists of sets, three or four of a kind of the same rank; or "
				+ "\nruns, three or more cards in sequence, of the same suit. "
				+ "\nYou can also have mixed runs in all types of rummy so long as all 7 cards are in order.");

	}

	@Override
	public ArrayList<Card> deal() 
	{
		ArrayList<Card> hand = new ArrayList<Card>(this.getPerHand());//a card container to pass to the player
		for(int i = 0; i < this.getPerHand();++i)//iterates through the game's deck and moves this.perHand number of cards from the deck to the hand.
		{
			hand.add(this.getDeck().get(0));
			this.getDeck().remove(0);
		}
		return hand;
	}

}
