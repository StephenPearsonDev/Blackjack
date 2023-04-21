package dev.stephenpearson.model;

import java.util.List;
import java.util.Stack;

public interface DealingStrategy  {
	
	
	default void dealCardTo(PlayerEntity playerEntity, Stack<Card> gameStack, String upOrDown) {
		
		switch(upOrDown) {
		case "up":
			playerEntity.getHand().addCardToHand(gameStack.pop());
			break;
		case "down":

			playerEntity.getHand().addCardToHand(((Card)gameStack.pop()).setCardFaceDown(true));
			break;
		}

	}
	
	default void playerHit(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
		
		for(PlayerEntity playerEntity : playerEntities) {
			if(playerEntity instanceof HumanPlayer) {
		
				
				Card c = gameStack.pop();
				c.setXY(playerEntity.getHand().getNextCardZone());
				playerEntity.getHand().addCardToHand(c);
			}
		}
		
	}
	
	
	
	
	default boolean dealerHasBlackjack(List<PlayerEntity> playerEntities) {
	    for (PlayerEntity playerEntity : playerEntities) {
	        if (playerEntity instanceof ComputerDealer) {
	         
	            if (playerEntity.getHand().getHandValue() == 21 && playerEntity.getHand().getCardsInHand().size() == 2) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	default void playRound(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
	    dealFirstCards(playerEntities, gameStack);

	    if (dealerHasBlackjack(playerEntities)) {
	        System.out.println("Dealer has blackjack!");
	        // Handle the situation when the dealer has blackjack (e.g., pay out, end the round, etc.)
	    } else {
	        // Allow the player to make decisions (hit or stand)
	    }
	}
	
	


	
	default void dealFirstCards(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
		
		int xCounter = 50;
		int yCounter = 80;
		for(PlayerEntity pi : playerEntities) {
			
			if(pi instanceof ComputerDealer) {
				for(int i = 0; i < 2; i++) {
					if(i == 1) {
						System.out.println("dealer card 2 dealt");
						Card c = gameStack.pop();
						c.setCardFaceDown(true);
						c.setXY(pi.getHand().getNextCardZone());
						
						pi.getHand().addCardToHand(c);
						xCounter = 50;
						yCounter = 500;
					} else {
						System.out.println("dealer card 1 dealt");
						Card c = gameStack.pop();
						c.setXY(pi.getHand().getNextCardZone());
						pi.getHand().addCardToHand(c);
					}
				}
				
			} else {
				for(int i = 0; i < 2; i++) {
					System.out.println("player card " + (i+1) +" dealt");
					Card c = gameStack.pop();
					System.out.println(yCounter);
					c.setXY(pi.getHand().getNextCardZone());
					System.out.println(c.getCardString());
					pi.getHand().addCardToHand(c);
				}
				
			}
			
			
			}
		
		}
		
	}
	
	


