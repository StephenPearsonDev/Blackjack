package dev.stephenpearson.model;


public abstract class PlayerEntity {
	private Hand hand;
	private String playerType;
	private boolean hasHand = false;
	
	public PlayerEntity(int zoneY) {
		
		hand = new Hand(zoneY);
		
	}
	

	
	public Hand getHand() {
		return hand;
	}
	
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	
	
	
	public String getPlayerType() {
		return playerType;
	}
	
	
	
	
	public boolean hasHand() {
		return hasHand;
	}
	
	public void setHasHand(boolean b) {
		hasHand = b;
	}

	public abstract void update();
	
	
	
	
	
	
	

}
