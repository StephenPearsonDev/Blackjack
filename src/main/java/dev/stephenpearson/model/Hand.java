package dev.stephenpearson.model;

import java.util.ArrayList;
import java.util.List;



public class Hand {
	
	private List<Card> cardsInHand = new ArrayList<>();
	private int numCardsInHand = 0;
	private static int numberOfHands = 0;
	
	public Hand() {
		System.out.println("From Hand " + "Number of hands: " + ++numberOfHands);
	}
	
	public void addCardToHand(Card c) {
		//System.out.println(numCardsInHand);
		cardsInHand.add(c);
		numCardsInHand++;
		
	}
	
	public List<Card> getCardsInHand() {
		return cardsInHand;
	}
	
	public Card getLastCardAdded() {
		return cardsInHand.get(numCardsInHand-1);
	}
	
	public void printHand() {
		System.out.print("Cards in hand: ");
		for(Card c : cardsInHand) {
			System.out.print(c.getCardString() + " ");
		}
		System.out.println();
	}
	
}
