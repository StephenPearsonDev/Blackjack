package dev.stephenpearson.controller;

import dev.stephenpearson.model.DealerHandValueMessage;
import dev.stephenpearson.model.GameState;
import dev.stephenpearson.model.HandValueMessage;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.MenuState;
import dev.stephenpearson.model.PlayerHandValueMessage;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.Button.ButtonAction;
import dev.stephenpearson.view.GameWindow;

public class GameController implements Runnable {
	
	//Message boxes
	private static BetPotMessage betPotMessage;
	private static MenuMessage menuMessage;
	private static PlayerBankMessage playerBankMessage;
	private static HandValueMessage playerHandValueMessage;
	private static HandValueMessage dealerHandValueMessage;
	
	
	private static SpriteController spriteController;
	private static TableController tableController;
	private static RenderController renderController;
	private static StateController stateController;
	private static GameWindow gameWindow;
	private static BetPot betPot;
	private static State[] states;
	
	private static State currentGameState;
	
	boolean firstBetPlaced = false;
	

	public GameController() {

		states = new State[] {new MenuState(), new GameState()};
		currentGameState = states[0];
		spriteController = new SpriteController();
		stateController = new StateController();
		renderController = new RenderController();
		tableController = new TableController(stateController.getCurrentGameState());
		menuMessage = new MenuMessage();
		betPotMessage = new BetPotMessage();
		playerBankMessage = new PlayerBankMessage();
		dealerHandValueMessage = new DealerHandValueMessage(50,300,200,100,"Dealer hand: ");
		playerHandValueMessage = new PlayerHandValueMessage(50,600,200,100,"Player hand: ");
		
		
	
	}

	
	


	
	
	public void render() {
		gameWindow.repaint();
	}
	
	
	public void initGame() {
		System.out.println("init called");
	
		tableController.init();
		
		gameWindow = new GameWindow(currentGameState,buttonAction -> {
	        switch(buttonAction) {
	        
	        case EXIT:
	        	System.exit(0);
	        	break;
	        
	        case START:
	        	currentGameState = states[1];
	        	stateController.changeState(currentGameState);
	        	updateMenuMessage(menuMessage.getDefaultStartMessage());
	        	break;
	        
	        case HIT:
	        	System.out.println("hit");
	        	
				if(!firstBetPlaced) {
	        		updateMenuMessage(menuMessage.getDefaultStartMessage());
	        	} else {
	        		
	        		tableController.progressDealer(ButtonAction.HIT);
	        		gameWindow.getGui().updateRenderableCards(tableController.getHumanPlayer().getHand().getCardsInHand());
	        		gameWindow.getGui().setDrawCards(true);
	        	}
	        	
	        	break;
	        
	        case STAND:
	        	System.out.println("stand");
	        	break;
	        case RESET:
	        	resetGame();
	        	
	        	System.out.println("reset");
	        	break;
	        case MENU:
	        	resetGame();
	        	currentGameState = states[0];
	        	stateController.changeState(currentGameState);
	        	break;
	        case BET10:
	        	if(tableController.getBetPot().getSizeOfPot() <= tableController.getHumanPlayer().getPlayerBank().getBankSize() - 10) {
	        		tableController.getBetPot().increasePot(10);
	        		updateBetPotMessage(tableController.getBetPot().getPotString());
	        	} else {
	        		updateMenuMessage("Not enough money place $10 bet. Try a smaller bet size or clear bet");
	        	}
	        	
	        	
	        	break;
	        case BET50:
	        	
	        	if(tableController.getBetPot().getSizeOfPot() <= tableController.getHumanPlayer().getPlayerBank().getBankSize() - 50) {
	        		tableController.getBetPot().increasePot(50);
	        		updateBetPotMessage(tableController.getBetPot().getPotString());
	        	} else {
	        		updateMenuMessage("Not enough money place $50 bet. Try a smaller bet size or clear bet");
	        	}
	        	break;
	        case CLEARBET:
	        	tableController.getBetPot().clearPot();
	        	updateBetPotMessage(tableController.getBetPot().getPotString());
	        	updateMenuMessage(menuMessage.getDefaultStartMessage());
	        	break;
	        	
	        case PLACEBET:
	        	System.out.println("place bet");
	        	if(tableController.getBetPot().getSizeOfPot() > 0) {
	        		firstBetPlaced = true;
	        		System.out.println(tableController.getBetPot().getSizeOfPot());
	        		tableController.getHumanPlayer().getPlayerBank().decreaseBank(tableController.getBetPot().getSizeOfPot());
	        		updatePlayerBankMessage(tableController.getHumanPlayer().getPlayerBank().getBankString());
	        		tableController.getBetPot().clearPot();
	        		updateBetPotMessage(tableController.getBetPot().getPotString());
	        		updateMenuMessage("Hit or stand to continue");
	        		tableController.wakeDealer();
	        		gameWindow.getGui().updateRenderableCards(tableController.getComputerDealer().getHand().getCardsInHand());
	        		gameWindow.getGui().updateRenderableCards(tableController.getHumanPlayer().getHand().getCardsInHand());
	        		updatePlayerHandValueMessage(String.valueOf(tableController.getHumanPlayer().getHand().getHandValue()));
	        		gameWindow.getGui().setDrawCards(true);
	        		
	        		
	        		System.out.println("Value of player hand is: " + tableController.getHumanPlayer().getHand().getHandValue());
	        		System.out.println("Value of dealer hand is: " + tableController.getComputerDealer().getHand().getHandValue());
	        		tableController.playRound();
	        	}
	        	break;
	        
	        default:
	        	
	        	break;
	        } 
	    });
		
		dealerHandValueMessage.addObserver(gameWindow.getGui());
		playerHandValueMessage.addObserver(gameWindow.getGui());
		playerBankMessage.addObserver(gameWindow.getGui());
		betPotMessage.addObserver(gameWindow.getGui());
		menuMessage.addObserver(gameWindow.getGui());
		
		
		gameWindow.getGui().addGuiObserver(gameWindow);
		stateController.addObserver(gameWindow);
		
	}
	


	public void updateMenuMessage(String message) {
		menuMessage.setMessage(message);
	}
	
	public void updateBetPotMessage(String message) {
		betPotMessage.setMessage(message);
	}
	
	public void updatePlayerHandValueMessage(String message) {
		playerHandValueMessage.setMessage(message);
	}
	
	public void updatePlayerBankMessage(String message) {
		playerBankMessage.setMessage(message);
	}





	@Override
	public void run() {
		initGame();	
	}
	
	public void resetGame() {
		
		
	gameWindow.getGui().init();	
	tableController.init();
	updateMenuMessage(menuMessage.getDefaultStartMessage());
	updateBetPotMessage(tableController.getBetPot().getPotString());
	updatePlayerBankMessage(tableController.getHumanPlayer().getPlayerBank().getBankString());
	}







	
	
}
