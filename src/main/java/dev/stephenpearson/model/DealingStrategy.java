package dev.stephenpearson.model;

import java.util.List;
import java.util.Stack;

public interface DealingStrategy  {
	
	boolean debugBlackJack = true;
	
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
				System.out.println("Adding to player hand: " + c.getCardString());
				playerEntity.getHand().addCardToHand(c);
			}
		}
		
	}
	
	default void dealerHit(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
		
		for(PlayerEntity playerEntity : playerEntities) {
			if(playerEntity instanceof ComputerDealer) {
				Card c = gameStack.pop();
				c.setXY(playerEntity.getHand().getNextCardZone());
				System.out.println("Adding to dealer hand: " + c.getCardString());
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
	
	default boolean playerHasBlackjack(List<PlayerEntity> playerEntities) {
	    for (PlayerEntity playerEntity : playerEntities) {
	        if (playerEntity instanceof HumanPlayer) {
	         
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
	      
	    } else {
	      
	    }
	}
	
	

	default void dealFirstCards(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
		
		int xCounter = 50;
		int yCounter = 80;
		
		if(debugBlackJack) {
			
			
			
		//dealer wins blackjack
			System.out.println("debugging");
			for(PlayerEntity pi : playerEntities) {
				if(pi instanceof ComputerDealer) {
					Card a = new Card(Card.Suit.HEARTS, Card.Rank.ACE);
					a.setXY(pi.getHand().getNextCardZone());
					pi.getHand().addCardToHand(a);
					Card b = new Card(Card.Suit.HEARTS, Card.Rank.QUEEN);
					b.setXY(pi.getHand().getNextCardZone());
					b.setCardFaceDown(true);
					pi.getHand().addCardToHand(b);
					
				} else {
					Card c = new Card(Card.Suit.HEARTS, Card.Rank.SEVEN);
					c.setXY(pi.getHand().getNextCardZone());
					pi.getHand().addCardToHand(c);
					
					Card d = new Card(Card.Suit.HEARTS, Card.Rank.SEVEN);
					d.setXY(pi.getHand().getNextCardZone());
					pi.getHand().addCardToHand(d);
				}
			}
		} 
		
		
		
		
		
		else {
			for(PlayerEntity pi : playerEntities) {
				
				if(pi instanceof ComputerDealer) {
					for(int i = 0; i < 2; i++) {
						if(i == 1) {
							Card c = gameStack.pop();
							c.setCardFaceDown(true);
							
							c.setXY(pi.getHand().getNextCardZone());
							
							pi.getHand().addCardToHand(c);
							System.out.println("Adding to dealer hand face down: " + c.getCardString());
							xCounter = 50;
							yCounter = 500;
						} else {
							Card c = gameStack.pop();
							c.setXY(pi.getHand().getNextCardZone());
							System.out.println("Adding to dealer hand: " + c.getCardString());
							pi.getHand().addCardToHand(c);
						}
					}
					
				} else {
					for(int i = 0; i < 2; i++) {
					
						Card c = gameStack.pop();
						c.setXY(pi.getHand().getNextCardZone());
						pi.getHand().addCardToHand(c);
						System.out.println("Adding to player hand: " + c.getCardString());
					}
					
				}
				
				
				}
		}
		
		
		
		
		}
		
	}
	
	


