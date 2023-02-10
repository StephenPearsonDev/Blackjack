package dev.stephenpearson.model;

public class Card {
	
	private Suit suit;
	private Rank rank;
	private String cardString;
	private int faceValue;
	
	public Card(Suit s, Rank r) {
		suit = s;
		rank = r;
		faceValue = rank.getRankValue();
		cardString =  rank.name().charAt(0) +  rank.name().substring(1).toLowerCase() + " of " + suit.name().toLowerCase();
		//cardString =  rank.name()+ " of " + suit.name();
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

}
