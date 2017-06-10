/*
 * a. Create an abstract CardGame class similar to the one described in this chapter. The class 
 * contains a �deck� of 52 playing cards that uses a Card class to hold a suit and value for each 
 * Card object. It also contains an integer field that holds the number of cards dealt to a player 
 * in a particular game. The class contains a constructor that initializes the deck of cards with 
 * appropriate values (e.g., �King of Hearts�), and a shuffle() method that randomly arranges the 
 * positions of the Cards in the array. The class also contains two abstract methods: 
 * displayDescription(), which displays a brief description of the game in each of the child 
 * classes, and deal(), which deals the appropriate number of Card objects to one player of a game. 
 * Save the file as CardGame.java.
 * 
 * b. Create two child classes that extend CardGame. You can choose any games you prefer. For 
 * example, you might create a Poker class or a Bridge class. Create a constructor for each child 
 * class that initializes the field that holds the number of cards dealt to the correct value. 
 * (For example, in standard poker, a player receives five cards, but in bridge, a player receives 
 * 13.) Create an appropriate displayDescription() and deal() method for each child class. Save 
 * each file using an appropriate name�for example, Poker.java or Bridge.java.
 * 
 * c. Create an application that instantiates one object of each game type and demonstrates that 
 * the methods work correctly. Save the application as PlayCardGames.java.
 */
package cards;

public class Card implements Comparable<Card>
{	
	private final Suit SUIT;
	private final Rank RANK;
	private String description;
	
	public Card(Suit S, Rank R)
	{
		this.SUIT = S; 
		this.RANK = R;
	}	
	public Card()
	{
		this(Suit.random(), Rank.random());
	}
	
	public Suit getSuit()
	{
		return this.SUIT;
	}
	public Rank getRank()
	{
		return this.RANK;
	}
	public boolean equals(Card c)
	{
		if(this.getSuit() == c.getSuit() && this.getRank() == c.getRank())//suit and rank are equal
		{
			return true;
		}
		//else
		return false;
	}
	
	@Override
	public int compareTo(Card card) 
	{
		/* 
		 * made each suit have increasing multiples of 13 as a custom value(0,13,26,39), 
		 * so that when the ordinal value of the rank is added to it(0-12), you 
		 * get a number between 0 and 51, with every possible suit/rank 
		 * combination having its own unique integer value.
		 */
		return (this.getSuit().getSortOrd() + this.getRank().ordinal()) - (card.getSuit().getSortOrd() + card.getRank().ordinal());
		
		/*
		 * int dis = this.getSuit().getSortOrd() + this.getRank().ordinal();
		 * int dat = card.getSuit().getSortOrd() + card.getRank().ordinal();
		 * return dis-dat;
		 ***/
		
		/*
		 * int dis = this.getSuit().getSortOrd() + this.getRank().ordinal();
		 * int dat = card.getSuit().getSortOrd() + card.getRank().ordinal();
		 * if(dis > dat)
		 * {
		 * 		return 1;
		 * }
		 * else if(dis < dat)
		 * {
		 * 		return -1;
		 * }
		 * else if(dis == dat)
		 * {
		 * 		return 0;
		 * }
		 ***/
	}
	
	@Override
	public String toString()
	{
		this.description = "\n"+this.RANK.toString() + " OF " + this.SUIT.toString();
		return this.description;
	}
}
