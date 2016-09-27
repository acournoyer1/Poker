import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Poker {
	LinkedList<Hand> hands;
	Scanner s;
	
	public Poker()
	{
		hands = new LinkedList<Hand>();
		s = new Scanner(System.in);
	}
	
	public void addHand(String hand)
	{
		hands.add(new Hand(hand));
		Collections.sort(hands, new Comparator<Hand>()
		{
			public int compare(Hand h1, Hand h2)
			{
				int n = h1.getType().getRank() - h2.getType().getRank();
				if(n != 0) return n;
				
				return h1.getCards().getLast().compareByRank(h2.getCards().getLast());
			}
		});
	}
	
	public void run()
	{
		System.out.println("Enter a hand of five cards in the format: AH 9S 5C TD KH");
		String hand = " ";
		while(hand != "")
		{
			hand = s.next();
			addHand(hand);
			System.out.println("Enter another hand or press enter to begin the game.");
		}
		
	}
	
	public static void main(String args[])
	{
		Poker p = new Poker();
		Hand.setUPMaps();
		p.run();
	}
	
	
}
