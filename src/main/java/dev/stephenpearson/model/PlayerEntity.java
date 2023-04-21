package dev.stephenpearson.model;

import dev.stephenpearson.model.GameZone.Zone;

public abstract class PlayerEntity {
	private Hand hand;
	private Zone handZone;
	private GameZone playerZone;
	private String playerType;
	private boolean hasHand = false;
	
	public PlayerEntity(int zoneY) {
		
		hand = new Hand(zoneY);
		
	}
	
	public void setPlayerZone(GameZone playerZone) {
		this.playerZone = playerZone;
	}
	
	public Hand getHand() {
		return hand;
	}
	
	public void setPlayerType(String playerType) {
		this.playerType = playerType;
	}
	
	public void setHandZone(Zone handZone) {
		this.handZone = handZone;
	}
	
	public String getPlayerType() {
		return playerType;
	}
	
	public Zone getZone() {
		return handZone;
	}
	
	public GameZone getPlayerZone() {
		return playerZone;
	}
	
	public boolean hasHand() {
		return hasHand;
	}
	
	public void setHasHand(boolean b) {
		hasHand = b;
	}

	public abstract void update();
	
	
	
	
	
	
	

}
