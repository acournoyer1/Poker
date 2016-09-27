
public class Card {
	int rank;
	Suit suit;
	
	public Card(int rank, Suit suit)
	{
		this.rank = rank;
		this.suit = suit;
	}
	
	public int compareByRank(Card c)
	{
		return this.rank - c.rank;
	}
	
	public int compareBySuit(Card c)
	{
		return this.suit.getStrength() - c.suit.getStrength();
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public int getRank()
	{
		return rank;
	}
}
