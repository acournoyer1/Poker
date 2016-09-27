import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Hand {
	private LinkedList<Card> cards;
	private HandType type;
	
	private static HashMap<String, Suit> suitMap = new HashMap<String, Suit>();
	private static HashMap<String, Integer> rankMap = new HashMap<String, Integer>();
	
	
	public Hand(String hand)
	{
		cards = new LinkedList<Card>();
		String[] cardStrings = hand.split(" ");
		for(String s: cardStrings)
		{
			cards.add(new Card(rankMap.get(s.substring(1, 2)), suitMap.get(s.substring(0, 1))));
		}
		int n = suitCount();
		if(n == 5)
		{
			if(isStraight())
			{
				type = HandType.STRAIGHT_FLUSH;
				sortByRank();
			}
			else
			{
				type = HandType.FLUSH;
				sortByRank();
			}
		}
		else if(n == 4)
		{
			type = HandType.FOUR_OF_A_KIND;
		}
		else if(n == 3)
		{
			type = HandType.THREE_OF_A_KIND;
		}
		else
		{
			if(isStraight())
			{
				type = HandType.STRAIGHT;
				sortByRank();
			}
			else
			{
				int pairs = pairCount();
				if(pairs == 0)
				{
					type = HandType.HIGH_CARD;
					sortByRank();
				}
				else if(pairs == 1)
				{
					type = HandType.PAIR;
				}
				else if(pairs == 2)
				{
					type = HandType.TWO_PAIR;
				}
				else if(pairs == 3)
				{
					type = HandType.FULL_HOUSE;
				}
			}
		}
	}

	public static void setUPMaps()
	{
		suitMap.put("H", Suit.HEARTS);
		suitMap.put("D", Suit.DIAMONDS);
		suitMap.put("C", Suit.CLUBS);
		suitMap.put("S", Suit.SPADES);
		
		rankMap.put("2", 2);
		rankMap.put("3", 3);
		rankMap.put("4", 4);
		rankMap.put("5", 5);
		rankMap.put("6", 6);
		rankMap.put("7", 7);
		rankMap.put("8", 8);
		rankMap.put("9", 9);
		rankMap.put("T", 10);
		rankMap.put("J", 11);
		rankMap.put("Q", 12);
		rankMap.put("K", 13);
		rankMap.put("A", 14);
	} 
	
	public LinkedList<Card> getCards()
	{
		return cards;
	}
	
	public HandType getType()
	{
		return type;
	}
	
	public int suitCount()
	{
		sortBySuit();
		Suit s = cards.get(0).getSuit();
		int count = 1;
		for(int i = 1; i < cards.size(); i++)
		{
			if(cards.get(i).getSuit().equals(s)) count++;
			else
			{
				count = 1;
				s = cards.get(i).getSuit();
			}
		}
		return count;
	}
	
	public boolean isStraight()
	{
		sortByRank();
		return cards.getFirst().getSuit().equals(cards.getLast().getSuit());
	}
	
	public int pairCount()
	{
		sortByRank();
		int count = 0;
		for(int i=0; i<cards.size()-1; i++)
		{
			if(cards.get(i).getRank() == cards.get(i+1).getRank()) count++;
		}
		return count;
	}
	
	private void sortBySuit()
	{
		Collections.sort(cards, new Comparator<Card>()
		{
			public int compare(Card c1, Card c2)
			{
				int n = c1.compareBySuit(c2);
				if(n != 0) return n;
				return c1.compareByRank(c2);
			}
		});
	}
	
	private void sortByRank()
	{
		Collections.sort(cards, new Comparator<Card>()
		{
			public int compare(Card c1, Card c2)
			{
				int n = c1.compareByRank(c2);
				if(n != 0) return n;
				return c1.compareBySuit(c2);
			}
		});
	}
	
	private void sortForFull()
	{
		sortByRank();
		Card c = cards.remove(1);
		Card c2 = cards.removeLast();
		cards.addLast(c);
		cards.addLast(c2);
	}
	
	private void sortForKinds()
	{
		sortBySuit();
		Suit s = cards.getFirst().getSuit();
		Suit highestSuit = cards.getFirst().getSuit();
		int count = 0;
		int highestCount = 0;
		for(int i = 1; i < cards.size(); i++)
		{
			if(cards.get(i).equals(s))
			{
				count++;
				if(highestCount < count)
				{
					highestCount = count;
					highestSuit = s;
				}
			}
			else
			{
				count = 1;
				s = cards.get(i).getSuit();
			}
		}
		
		Card highestCard = null;
		for(Card c: cards)
		{
			if(c.getSuit().equals(highestSuit))
			{
				if(highestCard == null) highestCard = null;
			}
		}
	}
	
}
