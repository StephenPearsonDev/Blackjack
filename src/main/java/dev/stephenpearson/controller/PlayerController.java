package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Dealer;
import dev.stephenpearson.model.Player;

public class PlayerController {
	
	private List<Player> playerList = new ArrayList<>();
	private Dealer dealer;
	
	
	public PlayerController() {
		
		dealer = new Dealer();
		initPlayers(1);
		
	}
	
	public void initPlayers(int numOfPlayers) {
		
		for(int i = 0; i < numOfPlayers; i++) {
			playerList.add(new Player());
		}
		
	}
	
	

}
