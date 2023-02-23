package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.stephenpearson.model.Dealer;
import dev.stephenpearson.model.GameZone;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.PlayerEntity;

public class PlayerController {
	
	private static List<PlayerEntity> playerList = new ArrayList<>();
	private Map<String, GameZone> gameZones;
	private PlayerEntity dealer;
	private PlayerEntity player;
	
	
	public PlayerController(Map<String, GameZone> gameZones) {
		this.gameZones = gameZones;
	
		initPlayerEntities(1);
		
	}
	
	public void initPlayerEntities(int numberOfPlayers) {
		
		
		dealer = new Dealer();
		player = new Player();
		playerList.add(dealer);
		dealer.setPlayerZone(gameZones.get("Dealer"));
		playerList.add(player);
		player.setPlayerZone(gameZones.get("Player"));
		
	}
	
	public PlayerEntity getPlayer(String playerType) {
		
		
		switch(playerType) {
		case "Dealer":
			return dealer;
		case "Player":
			return player;
		default:
			return null;
		}
		
		
	}
	
	public void initPlayers(int numOfPlayers) {
		
		for(int i = 0; i < numOfPlayers; i++) {
			playerList.add(new Player());
		}
		
	}
	
	public List<PlayerEntity> getPlayerList() {
		return playerList;
	}
	
	public void update() {
		for(PlayerEntity pi : playerList) {
			pi.update();
		}
	}
	
	

}
