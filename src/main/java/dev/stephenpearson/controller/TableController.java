package dev.stephenpearson.controller;

import java.util.HashMap;
import java.util.Map;

import dev.stephenpearson.model.ComputerDealer;
import dev.stephenpearson.model.DealtCardZone;
import dev.stephenpearson.model.GameZone;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.PlayerEntity;
import dev.stephenpearson.model.State;

public class TableController {
	
	private static DeckController deckController;

	private AnimationController animationController;
	private static Map<String, GameZone> gameZones;
	private static PlayerEntity humanPlayer;
	private static PlayerEntity computerDealer;
	private State currentGameState;
	private String[] zoneNames = new String[] {"deck","graveyard","dealerHand","playerHand","bank", "chipStack", "betStack"};
	
	
	
	
	   
	public TableController(State currentGameState) {
		
		this.currentGameState = currentGameState;
		initZones();
		//deckController = new DeckController(gameZones);

	}
	
	public void init() {
		
		deckController = new DeckController(4);
		humanPlayer = new HumanPlayer();
		computerDealer = new ComputerDealer(deckController.getMainGameStack());
		
		
	}
	
	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
		deckController.passAnimationController(this.animationController);
	}
	
	public void initZones() {
		gameZones = new HashMap<>();
		
		for(String s : zoneNames) {
			gameZones.put(s, new GameZone(s));
		}
	}
	
	
	public Map<String, GameZone> getGameZones() {
		return gameZones;
	}
	
	public GameZone getGameZone(String targetZone) {
		
		return gameZones.get(targetZone);
	}
	
	public DealtCardZone getDealtCardZone(String targetZone) {
		
		return (DealtCardZone) gameZones.get(targetZone);
	}
	
	public DeckController getDeckController() {
		return deckController;
	}

	public PlayerEntity getHumanPlayer() {
		return humanPlayer;
	}

	public void setHumanPlayer(PlayerEntity humanPlayer) {
		TableController.humanPlayer = humanPlayer;
	}

	public PlayerEntity getComputerDealer() {
		return computerDealer;
	}

	public void setComputerDealer(PlayerEntity computerDealer) {
		TableController.computerDealer = computerDealer;
	}
	
	
	

}
