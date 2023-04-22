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
			checkIfBlackjack();
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
			checkIfBlackjack();
		}
		
		public boolean dealerHasBlackjack() {
			return ((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities);
		}
		
		public void playOutDealerSolo() {
			((ComputerDealer)computerDealer).setAllCardsFaceUp();

			while (true) {
		        int dealerHandValue = computerDealer.getHand().getHandValue();
		        boolean isSoft17 = ((ComputerDealer)computerDealer).isSoft17();

		        if (dealerHandValue < 17 || (isSoft17 && dealerHandValue == 17)) {
		  
		            ((ComputerDealer)computerDealer).dealerHit(playerEntities, deckController.getMainGameStack());
		         
		        } else {
		        	checkWhoWon();
		            break;
		        }
		    }
			
			notifyTableControllerObserver(2);
			
		}
		
		public void checkWhoWon() {
			
			int playerHandTotal = humanPlayer.getHand().getHandValue();
			int dealerHandTotal = computerDealer.getHand().getHandValue();
			if(humanPlayer.getHand().isBust()) {
				processDealerWin();
				System.out.println("dealer won because player is bust");
			} else if (!humanPlayer.getHand().isBust()) {
				if(playerHandTotal == dealerHandTotal) {
					
					processDraw();
					System.out.println("it was a draw");
				} else if (playerHandTotal > dealerHandTotal) {
					processPlayerWin();
					System.out.println("player won");
				} else {
					processDealerWin();
					System.out.println("dealer won");
				}
			}

		}
		
		public void checkIfBlackjack() {
			
			if(((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities)) {
				if(((ComputerDealer)computerDealer).playerHasBlackjack(playerEntities)) {
					((ComputerDealer)computerDealer).setAllCardsFaceUp();
					notifyTableControllerObserver(3);
					System.out.println("both players have blackjack");
					processDraw();
				
				}
			} else if(!((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities) && ((ComputerDealer)computerDealer).playerHasBlackjack(playerEntities)) {
				((ComputerDealer)computerDealer).setAllCardsFaceUp();
				notifyTableControllerObserver(3);
				processPlayerWin();
				System.out.println("player has blackjack");
				
			
			} else if(((ComputerDealer)computerDealer).dealerHasBlackjack(playerEntities) && !((ComputerDealer)computerDealer).playerHasBlackjack(playerEntities)) {
				((ComputerDealer)computerDealer).setAllCardsFaceUp();
				notifyTableControllerObserver(3);
				processDealerWin();
				System.out.println("dealer has blackjack");
				
				
			}
		
			
		}
		
		public void processDraw() {
			((HumanPlayer)humanPlayer).getPlayerBank().increaseBank(betPot.getSizeOfPot());
			
		}
		
		public void processPlayerWin() {
			((HumanPlayer)humanPlayer).getPlayerBank().increaseBank(betPot.getSizeOfPot()*2);
			
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
