package dev.stephenpearson.controller;

public class BetPot {
	
	private String boilerplate = "Bet pot: ";
	private int sizeOfPot = 0;
	private String betPotString;
	
	public BetPot() {
		betPotString = boilerplate + String.valueOf(sizeOfPot);
	}
	
	public void increasePot(int bet) {
		sizeOfPot += bet;
		betPotString = boilerplate + String.valueOf(sizeOfPot);
	}
	
	public void clearPot() {
		sizeOfPot = 0;
		betPotString = boilerplate + String.valueOf(sizeOfPot);
	}
	
	public String getPotString() {
		return betPotString;
	}
	
	public int getSizeOfPot() {
		return sizeOfPot;
	}

}
