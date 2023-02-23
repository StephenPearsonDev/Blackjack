package dev.stephenpearson.model;

public class Player extends PlayerEntity {
	
	private boolean betPlaced = false;
	private PlayerBank playerBank;
	
	public Player() {
		
		playerBank = new PlayerBank(1000);

	}
	
	public PlayerBank getPlayerBank() {
		return playerBank;
	}
	
	public boolean isBetPlaced() {
		return betPlaced;
	}
	
	public void setBetPlaced(boolean b) {
		betPlaced = b;
		playerBank.setBetPlaced(b);
	}
	
	public void update() {
		if(playerBank.isBetPlaced()) {
			betPlaced = true;
		}
	}
	
	
	

}
