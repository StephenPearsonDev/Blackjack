package dev.stephenpearson.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	List<Card> deck = new ArrayList<>();	
	
	public Deck() {
		buildStandardDeck();
		shuffleDeck();
	}
	
	public void buildStandardDeck() {
		
		for(Suit s : Suit.values()) {				
			for(Rank r : Rank.values()) {
				if(r != Rank.ACE_ONE) {
					deck.add(new Card(s, r));

				}
			}
		}
	}
	
	public void shuffleDeck() {
		Collections.shuffle(deck);
		
		for(Card c : deck) {
			System.out.println(c.getCardString());
		
		}
		
	}
	
	public Deck getDeck() {
		return this;
	}
	
	
}
