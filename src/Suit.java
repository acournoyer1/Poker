
public enum Suit {
	HEARTS(4),
	SPADES(3),
	DIAMONDS(2),
	CLUBS(1);
	
	private int strength;
	
	private Suit(int n)
	{
		strength = n;
	}
	
	public int getStrength()
	{
		return strength;
	}
	
	public boolean equals(Suit s)
	{
		return this.strength == s.strength;
	}
}
