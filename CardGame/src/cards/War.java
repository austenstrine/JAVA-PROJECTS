package cards;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class War extends CardGame 
{
	public War(Integer p)
	{
		super(p, (MAX_DECK/p));
	}
	public War()
	{
		this(2);
	}

	@Override
	public void displayDescription() 
	{
		JOptionPane.showMessageDialog(null, "WAR: The objective of the game is to win all cards."
				+ "\nThe deck is divided evenly among the players, giving each a down stack. In unison, each player reveals the top card of their "
				+ "\ndeck - this is a \"battle\" - and the player with the higher card takes both of the cards played and moves them to their stack. "
				+ "\nAces are high, and suits are ignored."
				+ "\nIf the two cards played are of equal value, then there is a \"war\". Both players place the next card of their pile face down, "
				+ "\ndepending on the variant, and then another card face-up. The owner of the higher face-up card wins the war and adds all four "
				+ "\n(or six) cards on the table to the bottom of their deck. If the face-up cards are again equal then the battle repeats with another "
				+ "\nset of face-down/up cards. This repeats until one player's face-up card is higher than their opponent's."
				+ "\n\"https://en.wikipedia.org/wiki/War_(card_game)\"");
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
