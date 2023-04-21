package dev.stephenpearson.model;

public class HumanPlayer extends PlayerEntity {
	
	private boolean waitingForBet = true;
	private PlayerBank playerBank;
	
	public HumanPlayer(int cardZoneY) {
		super(cardZoneY);
		init();
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
	
	public void init() {
		playerBank = new PlayerBank(1000);
	}
	
	
	

}
