package dev.stephenpearson.controller;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.Deck;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.PlayerEntity;

public class DeckController {
	private DeckBuilder deckBuilder;
	
	private static Map<Integer, Deck> decks;
	private static Stack<Card> gameStack = new Stack<>();
	private static Stack<Card> mainGameStack = new Stack<>();
	private Card topCard;
	
	
	private AnimationController animationController;
	private boolean firstRoundDealt = false;
	
	public DeckController() {
		
		deckBuilder = new DeckBuilder();
		init();
	}
	
	public void init() {
		buildDecks();
	}

	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
	}
	
	
	public void initGame() {

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
	
	public void dealCardTo(PlayerEntity playerEntity, String upOrDown) {
		
		switch(upOrDown) {
		case "up":
			playerEntity.getHand().addCardToHand(getTopStackCard());
			
		case "down":
			
			playerEntity.getHand().addCardToHand(getTopStackCard());
			playerEntity.getHand().getLastCardAdded().setCardFaceDown(true);
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
	
	public void buildDecks() {
		
		
		decks = new HashMap<>();
		for(int d = 0; d < 4; d++) {
		
			Deck newDeck = deckBuilder.generateStandardDeck().shuffle().build();
			mainGameStack.addAll(newDeck.getCardsInDeck());
			decks.put(Integer.valueOf(d), newDeck);
			//newDeck.printDeckToConsole();
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
	

	public Stack<Card> getMainGameStack() {
		return mainGameStack;
	}

}
