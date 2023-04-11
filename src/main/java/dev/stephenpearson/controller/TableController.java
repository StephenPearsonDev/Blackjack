package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.stephenpearson.model.ComputerDealer;
import dev.stephenpearson.model.DealtCardZone;
import dev.stephenpearson.model.GameZone;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.PlayerEntity;
import dev.stephenpearson.model.State;

public class TableController {
	
	private static DeckController deckController;
	private MenuMessage menuMessage;
	private AnimationController animationController;
	private static Map<String, GameZone> gameZones;
	private static PlayerEntity humanPlayer;
	private static PlayerEntity computerDealer;
	private static List<PlayerEntity> playerEntities;
	private State currentGameState;
	private static boolean roundBetPlaced = false;

	   
	public TableController(State currentGameState) {
		
		this.currentGameState = currentGameState;
		initZones();
		//deckController = new DeckController(gameZones);

	}
	
	public void init() {
		
		deckController = new DeckController(4);
		
		
		humanPlayer = new HumanPlayer();
		computerDealer = new ComputerDealer(deckController.getMainGameStack());
		
		playerEntities = new ArrayList<>();
		playerEntities.add(humanPlayer);
		playerEntities.add(computerDealer);
		
	}
	
	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
		deckController.passAnimationController(this.animationController);
	}
	
	public void initZones() {
		gameZones = new HashMap<>();
		
		for(String s : GameZone.Zone.getZoneNames()) {
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
	
	public void wakeDealer() {
			System.out.println("dealer woken");
		
				((ComputerDealer)computerDealer).dealFirstCards(playerEntities, deckController.getMainGameStack());
			
		}
	
	public void requestBet() {
		
		
		
	}
	
	
	

}
