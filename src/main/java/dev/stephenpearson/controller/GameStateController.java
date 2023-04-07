package dev.stephenpearson.controller;

import dev.stephenpearson.model.GameState;
import dev.stephenpearson.model.HumanPlayer;

public class GameStateController {
	
	private GameState gameState;
	
	public GameStateController(GameState gameState) {
		this.gameState = gameState;
	}
	
	public void changeGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public GameState getGameState() {
		return gameState;
	}
	
	
	

}
