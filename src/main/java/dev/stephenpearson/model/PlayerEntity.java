package dev.stephenpearson.model;

public abstract class PlayerEntity {
	private Hand hand;
	
	private String playerType;
	
	public PlayerEntity() {
		hand = new Hand();
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
	
	
	
	
	

}
