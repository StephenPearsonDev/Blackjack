package dev.stephenpearson.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.stephenpearson.controller.SpriteController;

public class Card implements Renderable {
	
	private Suit suit;
	private Rank rank;
	private String cardString;
	private int faceValue;
	private Rectangle cardBounds;
	private int x;
	private int y;
	private Color cardFrontColor = new Color(234, 234, 189);
	private static int cardsInMemory = 0;
	
	private Color cardBackColor = new Color(21, 22, 100);
	private Color cardShadowColor = new Color(128,128,128);
	private Point centerPoint;
	private Point cornerPoint;
	private Dimension cardDimensions;
	private boolean faceDown = false;
	
	private BufferedImage cardFrontImage;
	private BufferedImage cardBackImage;
	
	public static enum Suit {
		HEARTS,
		DIAMONDS,
		CLUBS,
		SPADES
	}
	
	public static enum Rank {
		
		ACE_ONE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(10),
		QUEEN(10),
		KING(10),
		ACE(11);
		
		private int rankValue;
		
		private Rank(int rankValue) {
			this.setRankValue(rankValue);
		}

		public int getRankValue() {
			return rankValue;
		}

		public void setRankValue(int rankValue) {
			this.rankValue = rankValue;
		}

		
	}
	
	public Card(Suit s, Rank r) {
		System.out.println(r.name()+"OF"+s.name());
		cardFrontImage = SpriteController.getCardImageMap().get(r.name()+"OF"+s.name());
		cardBackImage = SpriteController.getCardImageMap().get("CARDBACK");
		
		cardsInMemory++;
		suit = s;
		rank = r;
		faceValue = rank.getRankValue();
		cardString =  rank.name().charAt(0) +  rank.name().substring(1).toLowerCase() + " of " + suit.name().toLowerCase();
		//cardString =  rank.name()+ " of " + suit.name();
		cardDimensions = new Dimension(100,140);
		cardBounds = new Rectangle(0,0,90,150);
	}
	
	public void setInitPoints(Point deckZoneCenterPoint) {
		cardBounds = new Rectangle(deckZoneCenterPoint.x - cardDimensions.width /2, deckZoneCenterPoint.y - cardDimensions.height /2, cardDimensions.width, cardDimensions.height);
		centerPoint = new Point(cardBounds.x + cardBounds.width/2 - 15, cardBounds.y + cardBounds.height /2 - 15);
		cornerPoint = new Point(cardBounds.x, cardBounds.y);
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
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
	
	public Point getCornerPoint() {
		return cornerPoint;
	}
	
	public void slidePoint(Point p) {
		
		cornerPoint.setLocation(p);
		cardBounds.setLocation(cornerPoint);
		centerPoint.setLocation(cornerPoint.x + cardBounds.width/2 - 15, cornerPoint.y + cardBounds.height /2 - 15);
		
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
		
		if(faceDown) {
			g.drawImage(cardBackImage, cardBounds.x, cardBounds.y, cardBackImage.getWidth()*5,cardBackImage.getHeight()*5, null);
		} else {
			g.drawImage(cardFrontImage, cardBounds.x, cardBounds.y, cardFrontImage.getWidth()*5,cardFrontImage.getHeight()*5, null);
		}
		
		
//		if(faceDown) {
//			g.setColor(cardShadowColor);
//			g.fillRoundRect(cardBounds.x-3, cardBounds.y+5, cardBounds.width, cardBounds.height, 20 ,20);
//			g.setColor(cardBackColor);
//			g.fillRoundRect(cardBounds.x, cardBounds.y, cardBounds.width, cardBounds.height, 20 ,20);
//		} else {
//			g.setColor(cardShadowColor);
//			g.fillRoundRect(cardBounds.x-3, cardBounds.y+5, cardBounds.width, cardBounds.height, 20 ,20);
//			g.setColor(cardFrontColor);
//			g.fillRoundRect(cardBounds.x, cardBounds.y, cardBounds.width, cardBounds.height, 20 ,20);
//		}
	
		
		
		
		
//		draw bounding box for debugging cardBound position
//		g.setColor(Color.GREEN);
//		g.fillRect(cardBounds.x, cardBounds.y, cardBounds.width, cardBounds.height);
//		g.setColor(Color.RED);
//		g.fillOval(centerPoint.x, centerPoint.y, 30, 30);
	}
	
	public Rectangle getCardBounds() {
		return cardBounds;
	}
	
	public Card setCardFaceDown(boolean b) {
		faceDown = b;
		return this;
	}
	
	public boolean isFaceDown() {
		return faceDown;
	}
	
	public int getCardsInMemory() {
		return cardsInMemory;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setXY(Rectangle xy) {
		
		this.x = xy.x;
		this.y = xy.y;
		cardBounds.x = this.x;
		cardBounds.y = this.y;
	}

}
