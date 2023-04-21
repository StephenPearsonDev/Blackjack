package dev.stephenpearson.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;



public class Hand {
	
	private List<Card> cardsInHand = new ArrayList<>();
	private int numCardsInHand = 0;
	private static int numberOfHands = 0;
	private Card newCard;
	private boolean handContainsAce = false;
	private boolean handHasBlackjack = false;
	private int aceCount = 0;
	private List<Rectangle> cardZones = new ArrayList<>();
	private int cardZoneCounter = 0;
	private int handValue;
	
	private int cardZoneX = 100;
	private int cardZoneY;
	
	public Hand(int cardZoneY) {
		this.cardZoneY = cardZoneY;
		initCardZones();
		System.out.println("From Hand " + "Number of hands: " + ++numberOfHands);
	}
	
	public void initCardZones() {
		
		int counter = 0;
		for(int i = 0; i < 8; i++) {
			if(i < 4) {
				cardZones.add(new Rectangle(cardZoneX*counter++, cardZoneY,18*5,27*5));
				if(i == 3) {
					counter = 0;
				}
			} else if(i >= 4) {
				cardZones.add(new Rectangle(cardZoneX*counter+++40, cardZoneY + 80,18*5,27*5));
				
			}
		}
	}
	
	public Rectangle getNextCardZone() {
		
		return cardZones.get(cardZoneCounter++);
	}
	
	public void addCardToHand(Card c) {
	    if (c.getRank() == Card.Rank.ACE) {
	        aceCount++;
	    }

	    handValue += c.getFaceValue();
	    
	    cardsInHand.add(c);
	    numCardsInHand++;
	    newCard = c;

	    calculateHandValue();
	}
	
	public int getHandValue() {
	    return handValue;
	}


	
	public void calculateHandValue() {
	    int totalValue = 0;
	    int aceCount = 0;

	    for (Card card : cardsInHand) {
	        int cardValue = card.getFaceValue();

	        // Check if the card is an Ace
	        if (card.getRank() == Card.Rank.ACE || card.getRank() == Card.Rank.ACE_ONE) {
	            aceCount++;
	        }
	        totalValue += cardValue;
	    }

	    // Handle Aces
	    while (totalValue > 21 && aceCount > 0) {
	        totalValue -= 10;
	        aceCount--;
	    }

	    handValue = totalValue;
	    if(handValue == 21) {
	    	System.out.println("you win");
	    } else if(handValue > 21) {
	    	System.out.println("you lose");
	    }
	}

	
	public List<Card> getCardsInHand() {
		return cardsInHand;
	}
	
	
	
	public Card getLastCardAdded() {
		return cardsInHand.get(numCardsInHand-1);
	}
	
	public void printHand() {
		System.out.println("In Hand");
		System.out.print("Cards in hand: ");
		for(Card c : cardsInHand) {
			System.out.print(c.getCardString() + " ");
		}
		System.out.println();
	}
	
	public Card getNewCard() {
		return newCard;
	}
	
}
