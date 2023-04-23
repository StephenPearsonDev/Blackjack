package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.ComputerDealer;
import dev.stephenpearson.model.HandValueMessageObserver;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.PlayerEntity;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.Button.ButtonAction;

public class TableController {
	
	private static DeckController deckController;
	private MenuMessage menuMessage;
	private AnimationController animationController;
	private static PlayerEntity humanPlayer;
	private static PlayerEntity computerDealer;
	private static List<PlayerEntity> playerEntities;
	private State currentGameState;
	private static boolean roundBetPlaced = false;
	private boolean gameInProgress = false;
	private static BetPot betPot;
	
	private List<TableControllerObserver> observers;
	

	private static boolean betPlaced = false;

	   
	public TableController(State currentGameState) {
		
		this.currentGameState = currentGameState;
		this.observers = new ArrayList<>();
		//deckController = new DeckController(gameZones);

	}
	
	
	public void addObserver(TableControllerObserver observer) {
        observers.add(observer);
    }

	public void removeObserver(TableControllerObserver observer) {
        observers.remove(observer);
    }
 
	private void notifyTableControllerObserver(int actionType) {
        for (TableControllerObserver observer : observers) {
        	
            observer.update(actionType);
            System.out.println("After notifying table observer");
        }
    }
	
	public void checkIfPlayerBust() {
		
		if(humanPlayer.getHand().isBust()) {
			processDealerWin();
		}
	}
	
	public void init() {
		
		deckController = new DeckController();
		humanPlayer = new HumanPlayer(450);
		computerDealer = new ComputerDealer(deckController.getMainGameStack(),150);
		playerEntities = new ArrayList<>();
		playerEntities.add(computerDealer);
		playerEntities.add(humanPlayer);
		betPot = new BetPot();
		gameInProgress = false;
		
	}
	
	public void passAnimationController(AnimationController animationController) {
		this.animationController = animationController;
		deckController.passAnimationController(this.animationController);
	}
	
	public void initZones() {
		
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
			playOutDealerSolo();
			break;
			
		default:
			break;
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
	
	public boolean isBetPlaced() {
		return betPlaced;
	}

	public void setBetPlaced(boolean betPlaced) {
		TableController.betPlaced = betPlaced;
	}
	
	//Dealer methods
		public void wakeDealer() {
			((ComputerDealer)computerDealer).dealFirstCards(playerEntities, deckController.getMainGameStack());
		
		}
		
		public boolean dealerHasBlackjack() {
			return ((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities);
		}
		
		public void playOutDealerSolo() {
		

			while (true) {
		        int dealerHandValue = computerDealer.getHand().getHandValue();
		        System.out.println("dealer hand value is: " + dealerHandValue);
		        boolean isSoft17 = ((ComputerDealer)computerDealer).isSoft17();

		        if (dealerHandValue < 17 || (isSoft17 && dealerHandValue == 17)) {
		  
		            ((ComputerDealer)computerDealer).dealerHit(playerEntities, deckController.getMainGameStack());
 
		        } else {
		        	System.out.println("Dealer standing - checking who won");
		        	checkWhoWon();
		            break;
		        }
		    }	
		}
		
		public void checkWhoWon() {
			
			
			int playerHandTotal = humanPlayer.getHand().getHandValue();
			int dealerHandTotal = computerDealer.getHand().getHandValue();
			System.out.println("final hand totals are - Player: " + playerHandTotal + " Dealer: " +  dealerHandTotal);
			if(humanPlayer.getHand().isBust()) {
				if(!computerDealer.getHand().isBust()) {
					processDealerWin();
				}
			} else if (!humanPlayer.getHand().isBust()) {
				if(computerDealer.getHand().isBust()) {
					processPlayerWin();
				
				} else if(playerHandTotal == dealerHandTotal) {				
					processDraw();
				
					System.out.println("it was a draw");
				} else if (playerHandTotal > dealerHandTotal) {
					processPlayerWin();
					
					System.out.println("player won");
				} else {
					processDealerWin();
			
				}
			}

		}
		
		public void checkIfBlackjack() {
		    boolean dealerBlackjack = ((ComputerDealer) computerDealer).dealerHasBlackjack(playerEntities);
		    boolean playerBlackjack = ((ComputerDealer) computerDealer).playerHasBlackjack(playerEntities);
		    
		    System.out.println("Dealer has blackjack: " + dealerBlackjack);
		    System.out.println("Player has blackjack: " + playerBlackjack);
		    
		    if (dealerBlackjack && playerBlackjack) {
		    	((ComputerDealer) computerDealer).setSecondCardHidden(false);
		        notifyTableControllerObserver(3); // Both dealer and player have blackjack
		    } else if (!dealerBlackjack && playerBlackjack) {
		    	((ComputerDealer) computerDealer).setSecondCardHidden(false);
		    	notifyTableControllerObserver(1); // Player has blackjack, dealer doesn't
		    } else if (dealerBlackjack && !playerBlackjack) {
		    	System.out.println("In dealer has blackjack loop setting second card to visible");
		    	((ComputerDealer) computerDealer).setSecondCardHidden(false);
		    	System.out.println("card set to visibe, calling observer");
		    	notifyTableControllerObserver(2); // Dealer has blackjack, player doesn't
		    	System.out.println("After notifying");
		    }
		}

		
		public void processDraw() {
			notifyTableControllerObserver(6);
		}
		
		
		public void processPlayerWin() {
			notifyTableControllerObserver(4);
		}
		
		public void processDealerWin() {
			notifyTableControllerObserver(5);
		}
		
		public void playRound() {
			if(((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities)) {
				System.out.println("GAME OVER");
			} else {
				System.out.println("waiting for player");
				
			}			
		}
		
		public boolean isGameInProgress() {
			return gameInProgress;
		}
		
		public void setGameInProgress(boolean gameInProgress) {
			this.gameInProgress = gameInProgress;
		}
	

	
	
	
	

}
