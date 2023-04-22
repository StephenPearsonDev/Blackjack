package dev.stephenpearson.controller;

public class BetPot {
	
	private int sizeOfPot = 0;
	
	public BetPot() {

	}
	
	public void increasePot(int bet) {
		sizeOfPot += bet;
	}
	
	public void clearPot() {
		sizeOfPot = 0;
	}
	
	public String getPotString() {
		return String.valueOf(sizeOfPot);
	}
	
	public int getSizeOfPot() {
		return sizeOfPot;
	}

}
