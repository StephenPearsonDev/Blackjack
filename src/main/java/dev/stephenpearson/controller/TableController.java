package dev.stephenpearson.controller;

import java.util.Map;

import dev.stephenpearson.model.GameZone;

public class TableController {
	
	private static DeckController deckController;
	private static PlayerController playerController;
	private static Map<String, GameZone> gameZones;
	   
	public TableController() {
		
		deckController = new DeckController();
		playerController = new PlayerController();
		
	}
	
	public void initZones() {
		
		GameZone deck = new GameZone("deck");
		
	}
	
	public Map<String, GameZone> getGameZones() {
		return gameZones;
	}

}
