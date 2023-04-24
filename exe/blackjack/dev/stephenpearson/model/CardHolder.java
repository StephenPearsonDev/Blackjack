package dev.stephenpearson.model;

import java.awt.Point;
import java.awt.Rectangle;

public class CardHolder {
	
	private Rectangle cardHolderBounds;
	private Point cornerPoint;
	private boolean containsCard = false;
	private Card card;
	
	public CardHolder(int x, int y) {
		
		cornerPoint = new Point(x,y);
	
	}
	
	public void addCardToHolder(Card c) {
		card = c;
		containsCard = true;
	}
	
	
	public Point getCardHolderLocation() {
		return cornerPoint;
	}

}
