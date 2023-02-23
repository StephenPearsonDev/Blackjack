package dev.stephenpearson.controller;

import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.Dealer;
import dev.stephenpearson.model.DealtCardZone;
import dev.stephenpearson.model.Deck;
import dev.stephenpearson.model.GameZone;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.PlayerEntity;

public class DeckController {
	
	private static Map<Integer, Deck> decks;
	private static Stack<Card> gameStack = new Stack<>();
	private Card topCard;
	
	private PlayerController playerController;
	private AnimationController animationController;
	private static Map<String, GameZone> gameZones;
	private boolean firstRoundDealt = false;
	
	public DeckController(Map<String, GameZone> gameZones) {
	
		this.gameZones = gameZones;
		buildDecks(4);
		buildGameStack();
		topCard = gameStack.peek();
		
		
		setCardPoints(gameZones.get("deckZone").getZoneCenterPoint());
		//System.out.println("From DeckController "+ " - Cards in Memory - " + gameStack.peek().getCardsInMemory());
	
	}
	
	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
	}
	
	//The Dealer deals 1 card down to self then 1 card up to player, 1 card up to self and 1 card up to player
	public void initGame(PlayerController playerController) {
		this.playerController = playerController;
		//deal one Card to player
		
		if(!firstRoundDealt) {
		
			firstRoundDealt = true;
			((Dealer)playerController.getPlayer("Dealer")).dealCardTo(playerController.getPlayer("Player"), gameStack, "up");
			
			animationController.animateCard(playerController.getPlayer("Player").getHand().getLastCardAdded(), ((DealtCardZone)gameZones.get("playerHandZone")).getNextZone().getCardHolderLocation());
		
			//((Dealer)playerController.getPlayer("Dealer")).dealCardTo(playerController.getPlayer("Dealer"), gameStack, "down");
			//animationController.animateCard(playerController.getPlayer("Player").getHand().getLastCardAdded(), ((DealtCardZone)gameZones.get("dealerHandZone")).getNextZone().getCardHolderLocation());
			
			playerController.getPlayer("Player").setHasHand(true);
		}
		
	    
		
		//((Dealer)playerController.getPlayer("Dealer")).dealCardTo(playerController.getPlayer("Dealer"), gameStack, "down");
		//animationController.animateCard(playerController.getPlayer("Player").getHand().getLastCardAdded(), ((DealtCardZone)gameZones.get("dealerHandZone")).getNextZone().getCardHolderLocation());
		//((Dealer)playerController.getPlayer("Dealer")).dealCardTo(playerController.getPlayer("Player"), gameStack, "up");
		//animationController.animateCard(playerController.getPlayer("Player").getHand().getLastCardAdded(), ((DealtCardZone)gameZones.get("playerHandZone")).getNextZone().getCardHolderLocation());
		//((Dealer)playerController.getPlayer("Dealer")).dealCardTo(playerController.getPlayer("Dealer"), gameStack, "up");
		//animationController.animateCard(playerController.getPlayer("Player").getHand().getLastCardAdded(), ((DealtCardZone)gameZones.get("dealerHandZone")).getNextZone().getCardHolderLocation());
		
		
//		for(PlayerEntity p : playerController.getPlayerList()) {
//			p.getHand().printHand();
//		}
		
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
