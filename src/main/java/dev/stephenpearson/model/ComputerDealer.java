package dev.stephenpearson.model;

import java.util.Stack;

import dev.stephenpearson.model.Card.Rank;

public class ComputerDealer extends PlayerEntity implements DealingStrategy {
	
	private Stack<Card> mainGameStack;
	private boolean secondCardHidden = true;
	
	public ComputerDealer(Stack<Card> mainGameStack, int cardZoneY) {
		super(cardZoneY);
		this.setMainGameStack(mainGameStack);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	 public boolean isSoft17() {
	        if (super.getHand().getCardsInHand().size() != 2) {
	            return false;
	        }

	        Card firstCard = super.getHand().getCardsInHand().get(0);
	        Card secondCard = super.getHand().getCardsInHand().get(1);

	        boolean hasAce = (firstCard.getRank() == Rank.ACE) || (secondCard.getRank() == Rank.ACE);
	        boolean hasSix = (firstCard.getRank() == Rank.SIX) || (secondCard.getRank() == Rank.SIX);

	        return hasAce && hasSix;
	    }

	public Stack<Card> getMainGameStack() {
		return mainGameStack;
	}

	public void setMainGameStack(Stack<Card> mainGameStack) {
		this.mainGameStack = mainGameStack;
	}

	public boolean isSecondCardHidden() {
		return secondCardHidden;
	}

	public void setSecondCardHidden(boolean secondCardHidden) {
		this.secondCardHidden = secondCardHidden;
	}
	
	public void setAllCardsFaceUp() {
		for(Card c : super.getHand().getCardsInHand()) {
			secondCardHidden = false;
			c.setCardFaceDown(false);
		}
	}
	
	
	
	

}
