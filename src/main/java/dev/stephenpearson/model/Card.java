package dev.stephenpearson.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Card implements RenderObject {
	
	private Suit suit;
	private Rank rank;
	private String cardString;
	private int faceValue;
	private Rectangle cardBounds;
	private Color cardColor = new Color(234, 234, 189);
	private Point centerPoint;
	private Dimension cardDimensions;
	
	
	public Card(Suit s, Rank r) {
		suit = s;
		rank = r;
		faceValue = rank.getRankValue();
		cardString =  rank.name().charAt(0) +  rank.name().substring(1).toLowerCase() + " of " + suit.name().toLowerCase();
		//cardString =  rank.name()+ " of " + suit.name();
		cardDimensions = new Dimension(80,120);
		
	}
	
	public void setInitPoints(Point deckZoneCenterPoint) {
		cardBounds = new Rectangle(deckZoneCenterPoint.x - cardDimensions.width /2, deckZoneCenterPoint.y - cardDimensions.height /2, cardDimensions.width, cardDimensions.height);
		centerPoint = new Point(cardBounds.x + cardBounds.width/2 - 10, cardBounds.y + cardBounds.height /2 -10);
	}
	
	
	public Rank getRank() {
		return rank;
	}
	
	public int getFaceValue() {
		return faceValue;
	}
	
	public String getCardString() {
		return cardString;
	}
	
	public void swapAceValue() {
		
		if(faceValue == Rank.ACE.getRankValue()) {
			rank = Rank.ACE_ONE;
			faceValue = rank.getRankValue();

		} else if(faceValue == Rank.ACE_ONE.getRankValue()) {
			rank = Rank.ACE;
			faceValue = rank.getRankValue();
		}
	}
	
	public Suit getSuit() {
		return suit;
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(cardColor);
		g.fillRoundRect(cardBounds.x, cardBounds.y, cardBounds.width, cardBounds.height, 20,20);
		g.setColor(Color.RED);
		g.fillOval(centerPoint.x, centerPoint.y, 20, 20);
	}

}
