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
	
	default void dealFirstCards(List<PlayerEntity> playerEntities, Stack<Card> gameStack) {
		System.out.println("dealing");
		for(PlayerEntity pi : playerEntities) {
			
			for(int i = 0; i < 2; i++) {
				pi.getHand().addCardToHand(gameStack.pop());
				if(i == 1) {
					if(pi instanceof ComputerDealer){
						pi.getHand().addCardToHand(gameStack.pop().setCardFaceDown(true));
					}
				}
			}
			
		}
	}
	
	

}
