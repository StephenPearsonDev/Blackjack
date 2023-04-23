package dev.stephenpearson.controller;

import dev.stephenpearson.model.ComputerDealer;
import dev.stephenpearson.model.DealerHandValueMessage;
import dev.stephenpearson.model.GameState;
import dev.stephenpearson.model.HandValueMessage;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.MenuState;
import dev.stephenpearson.model.PlayerHandValueMessage;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.Button.ButtonAction;
import dev.stephenpearson.view.GameWindow;

public class GameController implements Runnable, TableControllerObserver {
	
	//Message boxes
	private BetPotMessage betPotMessage;
	private MenuMessage menuMessage;
	private PlayerBankMessage playerBankMessage;
	private HandValueMessage playerHandValueMessage;
	private HandValueMessage dealerHandValueMessage;
	
	
	private static SpriteController spriteController;
	private static TableController tableController;
	
	private static StateController stateController;
	private static GameWindow gameWindow;
	private static BetPot betPot;
	private static State[] states;
	
	private static State currentGameState;
	
	boolean firstBetPlaced = false;
	
	boolean bettingLocked = false;
	

	public GameController() {

		states = new State[] {new MenuState(), new GameState()};
		currentGameState = states[0];
		spriteController = new SpriteController();
		stateController = new StateController();
		
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
				handleHit();
	        	break;
	        case STAND:
	        	handleStand();
	        	break;
	        case RESET:
	        	resetGame();
	        	break;
	        
	        case MENU:
	        	resetGame();
	        	currentGameState = states[0];
	        	stateController.changeState(currentGameState);
	        	break;
	        
	        case BET10:
	        	if(!bettingLocked) {
	        		handleBet(10);
	        	}
	        
	        	break;
	        case BET50:
	        	if(!bettingLocked) {
	        		handleBet(50);
	        	}
	        	
	        	break;
	        
	        case CLEARBET:
	        	handleClearBet();
	        	break;
	        	
	        case PLACEBET:
	        	handlePlaceBet();
	        	System.out.println("After placing bet");
	        	break;
	        
	        default:
	        	
	        	break;
	        } 
	    });
		
		
		tableController.addObserver(this);
		dealerHandValueMessage.addObserver(gameWindow.getGui());
		playerHandValueMessage.addObserver(gameWindow.getGui());
		playerBankMessage.addObserver(gameWindow.getGui());
		betPotMessage.addObserver(gameWindow.getGui());
		menuMessage.addObserver(gameWindow.getGui());
		gameWindow.getGui().addGuiObserver(gameWindow);
		stateController.addObserver(gameWindow);
		System.out.println("After adding observers");
		
	}
	
//	public boolean checkContinue() {
//		
//		boolean continueGame = false;
//		
//		switch(tableController.checkIfBlackjack()) {
//		case 0:
//			continueGame = false;
//			break;	
//		case 1:
//			continueGame = false;
//			break;	
//		case 2:
//			continueGame = false;
//			break;	
//		case 3:
//			continueGame = true;
//			break;	
//		
//		}
//		return continueGame;	
//	}

	public void updateMenuMessage(String message) {
		System.out.println("updating menu message");
		menuMessage.setMessage(message);
	}
	
	public void updateBetPotMessage(String message) {
		betPotMessage.setMessage(message);
	}
	
	public void updatePlayerHandValueMessage() {
	
			playerHandValueMessage.setMessage(String.valueOf(tableController.getHumanPlayer().getHand().getHandValue()));

	}
	
	public void updateDealerHandValueMessage() {
		System.out.println("Second card is hidden: " + ((ComputerDealer)tableController.getComputerDealer()).isSecondCardHidden());
		if(((ComputerDealer)tableController.getComputerDealer()).isSecondCardHidden()) {
			if(tableController.dealerHasBlackjack()) {
				dealerHandValueMessage.setMessage(String.valueOf(tableController.getComputerDealer().getHand().getHandValue()));
			} else {
				System.out.println("updating dealer message and showing only one card");
				dealerHandValueMessage.setMessage(String.valueOf(tableController.getComputerDealer().getHand().getValueOfDealerFirstCard()));	
			}
		} else {
			dealerHandValueMessage.setMessage(String.valueOf(tableController.getComputerDealer().getHand().getHandValue()));
		}
	}
	
	public void updatePlayerBankMessage() {
		playerBankMessage.setMessage(tableController.getHumanPlayer().getPlayerBank().getBankString());
	}


	@Override
	public void update(int actionType) {
		switch(actionType) {
		//player got blackjack on draw
		case 1:
	
			updateMenuMessage("You won with blackjack on draw. Place a new bet to play again");
			tableController.getHumanPlayer().getPlayerBank().increaseBank(tableController.getBetPot().getSizeOfPot()*2);
			updatePlayerBankMessage();
			break;
			
		//dealer got blackjack on draw
		case 2:
			System.out.println("in case 2");
			updateMenuMessage("Dealer won with blackjack on draw. Place a new bet to play again");
			System.out.println("After updating menu message");
			break;
		
		//dealer and player got blackjack on draw
		case 3:
		
			updateMenuMessage("You both got blackjack. Bet returned. Place a new bet to play again");
			tableController.getHumanPlayer().getPlayerBank().increaseBank(tableController.getBetPot().getSizeOfPot());
			updatePlayerBankMessage();
			
			break;
		//player won
		case 4:
			
			tableController.getHumanPlayer().getPlayerBank().increaseBank(tableController.getBetPot().getSizeOfPot()*2);
			updateMenuMessage("You won. Place a new bet to play again");
			updatePlayerBankMessage();
			break;
		
		//dealer won
		case 5:
    	
    		gameWindow.getGui().setDrawCards(true);  
			updateMenuMessage("Dealer won. Place a new bet to play again");
			break;
		
		//dealer and player have same value cards
		case 6:
			
			tableController.getHumanPlayer().getPlayerBank().increaseBank(tableController.getBetPot().getSizeOfPot());
			updateMenuMessage("You drew with the dealer. Bet returned. Place a new bet to play again");
			updatePlayerBankMessage();
			break;
		case 7:
			((ComputerDealer)tableController.getComputerDealer()).setAllCardsFaceUp();
			updateDealerHandValueMessage();
			break;
		}
		
		System.out.println("out of case switch");
		
		((ComputerDealer)tableController.getComputerDealer()).setAllCardsFaceUp();
		System.out.println("after setting all dealer cards face up");
		
		gameWindow.getGui().setDrawCards(true);  
		gameWindow.getGui().updateRenderableCards(tableController.getComputerDealer().getHand().getCardsInHand());
    	gameWindow.getGui().updateRenderableCards(tableController.getHumanPlayer().getHand().getCardsInHand());
    	System.out.println("after rendering cards");
		
		updateDealerHandValueMessage();
		System.out.println("After updating dealer hand value message");
		
		
		
    	
    	
    	
    	
    	tableController.getBetPot().clearPot();
    	updateBetPotMessage(tableController.getBetPot().getPotString());
    	System.out.println("After clearing bet pot");
    	
		softResetGame();
		System.out.println("After softReset");
	}
	
	public void handleHit() {
		if(!firstBetPlaced) {
    		updateMenuMessage("You have not placed a bet yet. " + menuMessage.getDefaultStartMessage());
    	} else {
    		if(tableController.getHumanPlayer().getHand().getHandValue() <=21) {
    			
    			if(tableController.getHumanPlayer().getHand().getHandValue() == 21) {
    				System.out.println("You can't hit. You have 21.");
    				handleStand();
    			} else {
    				tableController.progressDealer(ButtonAction.HIT);
            		updatePlayerHandValueMessage();
            		gameWindow.getGui().updateRenderableCards(tableController.getHumanPlayer().getHand().getCardsInHand());
            		gameWindow.getGui().setDrawCards(true);
            		tableController.checkIfPlayerBust();
    			}
    			
     
    		} else {
    			System.out.println("you have over 21. You have lost");
    		}
    	}
	}
	
	public void handleStand() {
		if(!firstBetPlaced) {
    		updateMenuMessage("You have not placed a bet yet. " + menuMessage.getDefaultStartMessage());
    	} else {
    		((ComputerDealer)tableController.getComputerDealer()).setSecondCardHidden(false);
    		tableController.progressDealer(ButtonAction.STAND);
    		 	
    		}
	}
	
	public void handlePlaceBet() {
		
		if(!tableController.isGameInProgress()) {
			bettingLocked = true;
    		if(tableController.getBetPot().getSizeOfPot() > 0) {
    			gameWindow.getGui().init();
        		tableController.setGameInProgress(true);
        		firstBetPlaced = true;
        		tableController.getHumanPlayer().getPlayerBank().decreaseBank(tableController.getBetPot().getSizeOfPot());
        		updatePlayerBankMessage();
        		//tableController.getBetPot().clearPot();
        		
        		updateBetPotMessage(tableController.getBetPot().getPotString());
        		updateMenuMessage("Hit or stand to continue");
        		
        		tableController.wakeDealer();
        		gameWindow.getGui().updateRenderableCards(tableController.getComputerDealer().getHand().getCardsInHand());
        		gameWindow.getGui().updateRenderableCards(tableController.getHumanPlayer().getHand().getCardsInHand());
        		
        		updatePlayerHandValueMessage();
        		updateDealerHandValueMessage();
        		
        		tableController.checkIfBlackjack();
        		System.out.println("After checking blackjack");
        		gameWindow.getGui().setDrawCards(true);
        		
        	} else {
        		updateMenuMessage("Cannot place bet of $0 - increase bet size and try again.");
        		bettingLocked = false;
        	}
    	}
	}
	
	public void handleClearBet() {
		if(tableController.isGameInProgress()) {
    		updateMenuMessage("Game is in progress you can't clear the bet!");
    	} else {
    		tableController.getBetPot().clearPot();
        	updateBetPotMessage(tableController.getBetPot().getPotString());
        	updateMenuMessage(menuMessage.getDefaultStartMessage());
    	}
	}
	
	public void handleBet(int bet) {
		if(tableController.getBetPot().getSizeOfPot() <= tableController.getHumanPlayer().getPlayerBank().getBankSize() - bet) {
    		tableController.getBetPot().increasePot(bet);
    		updateBetPotMessage(tableController.getBetPot().getPotString());
    		updateMenuMessage("Click 'Place bet' to start game with desired bet amount.");
    	} else {
    		updateMenuMessage("Not enough money place $"+bet+" bet. Try a smaller bet size or clear bet");
    	}
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
	updatePlayerBankMessage();
	}
	
	public void softResetGame() {
		
		((ComputerDealer)tableController.getComputerDealer()).setSecondCardHidden(true);
		tableController.getHumanPlayer().getHand().clear();
		tableController.getComputerDealer().getHand().clear();
		tableController.setGameInProgress(false);
		bettingLocked = false;
		
		//gameWindow.getGui().init();	
	}
}
