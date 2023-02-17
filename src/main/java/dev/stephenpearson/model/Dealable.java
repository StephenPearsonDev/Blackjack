package dev.stephenpearson.model;

import java.util.Stack;

public interface Dealable {
	
	
	public default void dealCardTo(PlayerEntity playerEntity, Stack<Card> gameStack, String upOrDown) {
		
		switch(upOrDown) {
		case "up":
			playerEntity.getHand().addCardToHand(gameStack.pop());
			break;
		case "down":

			playerEntity.getHand().addCardToHand(((Card)gameStack.pop()).setCardFaceDown(true));
			break;
		}
		
		
		
	}

}
