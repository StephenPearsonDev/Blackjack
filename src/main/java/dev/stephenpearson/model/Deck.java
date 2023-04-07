package dev.stephenpearson.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Deck {
	
	private Stack<Card> cardsInDeck;
	
	public Deck(Stack<Card> cards) {
		
		this.cardsInDeck = cards;
		shuffleDeck();
	}
	
	public List<Card> getCardsInDeck() {
		return cardsInDeck;
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
