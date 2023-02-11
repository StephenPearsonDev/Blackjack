package dev.stephenpearson.controller;

import java.util.HashMap;
import java.util.Map;

import dev.stephenpearson.model.GameZone;

public class TableController {
	
	private static DeckController deckController;
	private static PlayerController playerController;
	private static Map<String, GameZone> gameZones;
	   
	public TableController() {
		initZones();
		deckController = new DeckController(gameZones.get("deckZone"));
		playerController = new PlayerController();
		
	}
	
	public void initZones() {
		gameZones = new HashMap<>();
		
		GameZone deck = new GameZone("deckZone");
		gameZones.put(deck.getZoneName(), deck);
		
		GameZone burntCards = new GameZone("burntCards");
		gameZones.put(burntCards.getZoneName(), burntCards);
		
		GameZone gameZone = new GameZone("gameZone");
		gameZones.put(gameZone.getZoneName(), gameZone);
	
		GameZone dealerHand = new GameZone("dealerHand");
		gameZones.put(dealerHand.getZoneName(), dealerHand);
		
		GameZone playerHand = new GameZone("playerHand");
		gameZones.put(playerHand.getZoneName(), playerHand);
		
		
		GameZone chipStack = new GameZone("chipStack");
		gameZones.put(chipStack.getZoneName(), chipStack);
		
		GameZone betStack = new GameZone("betStack");
		gameZones.put(betStack.getZoneName(), betStack);
	}
	
	public Map<String, GameZone> getGameZones() {
		return gameZones;
	}
	
	public GameZone getGameZone(String targetZone) {
		
		
		return gameZones.get(targetZone);
	}
	
	public DeckController getDeckController() {
		return deckController;
	}
	
	

}
