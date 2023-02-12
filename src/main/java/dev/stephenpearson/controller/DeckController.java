package dev.stephenpearson.controller;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.Deck;
import dev.stephenpearson.model.GameZone;

public class DeckController {
	
	private Map<Integer, Deck> decks;
	private Stack<Card> gameStack = new Stack<>();
	private Card topCard;
	
	public DeckController(GameZone deckZone) {
		
		
		buildDecks(4);
		buildGameStack();
		setCardPoints(deckZone.getZoneCenterPoint());
		topCard = gameStack.pop();
		
	
	}
	
	public void setCardPoints(Point deckZoneCenterPoint) {
		for(Card c : gameStack) {
			
			c.setInitPoints(deckZoneCenterPoint);
		}
	}
	
	public void buildGameStack() {
		
		decks.forEach((I,D) -> gameStack.addAll(D.getCardsInDeck()));
		
		
	}
	
	public void dealCard() {
		gameStack.pop();
		if(!gameStack.empty()) {
			topCard = gameStack.peek();
		}
	}
	
	
	public void printGameStackToConsole() {
		
		if(gameStack.isEmpty()) {
			System.out.println("The game stack is empty.");
		} else {
			gameStack.forEach(e -> System.out.println(e.getCardString()));
			System.out.println("Stack size is: " + gameStack.size());
		}

	}
	
	public void buildDecks(int numOfDecks) {
		decks = new HashMap<>();
		for(int d = 0; d < numOfDecks; d++) {
			decks.put(Integer.valueOf(d), new Deck());
		}
		
		
	}
	
	public Map<Integer, Deck> getDecks() {

		return decks;
	}
	
	public Stack<Card> getGameStack() {
		return gameStack;
	}
	
	public Card getTopStackCard() {
		topCard = gameStack.pop();
		return topCard;
	}
	
	public Card lookAtTopCard() {
		return topCard;
	}

}
