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
import dev.stephenpearson.view.Button.ButtonAction;

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
	private static BetPot betPot;

	   
	public TableController(State currentGameState) {
		
		this.currentGameState = currentGameState;
	
		//deckController = new DeckController(gameZones);

	}
	
	public void init() {
		
		deckController = new DeckController();
		
		
		humanPlayer = new HumanPlayer(400);
		computerDealer = new ComputerDealer(deckController.getMainGameStack(),100);
		
		playerEntities = new ArrayList<>();
		
		playerEntities.add(computerDealer);
		playerEntities.add(humanPlayer);
		betPot = new BetPot();
		
	}
	
	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
		deckController.passAnimationController(this.animationController);
	}
	
	public void initZones() {
		
	}
	
	
	
	
	public DealtCardZone getDealtCardZone(String targetZone) {
		
		return (DealtCardZone) gameZones.get(targetZone);
	}
	
	public DeckController getDeckController() {
		return deckController;
	}

	public HumanPlayer getHumanPlayer() {
		return (HumanPlayer) humanPlayer;
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
	
	public void progressDealer(ButtonAction buttonAction) {
		
		switch(buttonAction) {
		
		case HIT:
			((ComputerDealer)computerDealer).playerHit(playerEntities, deckController.getMainGameStack());
			break;
			
		case STAND:
			break;
			
		default:
			break;
		}
	}
	
	public void wakeDealer() {
			System.out.println("dealer woken");
		
				((ComputerDealer)computerDealer).dealFirstCards(playerEntities, deckController.getMainGameStack());
			
		}
	
	public void playRound() {
		if(((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities)) {
			System.out.println("GAME OVER");
		} else {
			System.out.println("waiting for player");
			
		}
		
	}
	
	public static List<PlayerEntity> getPlayerEntities() {
		return playerEntities;
	}

	public static void setPlayerEntities(List<PlayerEntity> playerEntities) {
		TableController.playerEntities = playerEntities;
	}

	public void requestBet() {
		
		
		
	}

	public BetPot getBetPot() {
		return betPot;
	}
	

	
	
	
	

}
