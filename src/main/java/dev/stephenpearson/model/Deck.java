package dev.stephenpearson.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	List<Card> cardsInDeck = new ArrayList<>();	
	
	public Deck() {
		buildStandardDeck();
		shuffleDeck();
	}
	
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
	}
	
	public void buildStandardDeck() {
		
		for(Suit s : Suit.values()) {				
			for(Rank r : Rank.values()) {
				if(r != Rank.ACE_ONE) {
					cardsInDeck.add(new Card(s, r));
				}
			}
		}
	}
	
	public void shuffleDeck() {
		Collections.shuffle(cardsInDeck);
	}
	
	public void printDeckToConsole() {
		for(Card c : cardsInDeck) {
			System.out.println(c.getCardString());
		}
	}
	
	public Deck getDeck() {
		return this;
	}
	
	
}
