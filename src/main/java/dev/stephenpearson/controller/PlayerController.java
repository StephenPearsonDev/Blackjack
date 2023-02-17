package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Dealer;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.PlayerEntity;

public class PlayerController {
	
	private static List<PlayerEntity> playerList = new ArrayList<>();
	private PlayerEntity dealer;
	private PlayerEntity player;
	
	
	public PlayerController() {
		
	
		initPlayerEntities(1);
		
	}
	
	public void initPlayerEntities(int numberOfPlayers) {
		
		
		dealer = new Dealer();
		player = new Player();
		playerList.add(dealer);
		playerList.add(player);
		
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
	
	

}
