package dev.stephenpearson.model;

public class Player extends PlayerEntity {
	
	private boolean waitingForBet = true;
	private PlayerBank playerBank;
	
	public Player() {
		
		playerBank = new PlayerBank(1000);

	}
	
	public PlayerBank getPlayerBank() {
		return playerBank;
	}
	
	public boolean isBetPlaced() {
		return waitingForBet;
	}
	
	public void setBetPlaced(boolean b) {
		
		waitingForBet = b;
		playerBank.setBetPlaced(b);
	}
	
	public void update() {
		if(playerBank.isBetPlaced()) {
			waitingForBet = false;
		}
	}
	
	
	

}
