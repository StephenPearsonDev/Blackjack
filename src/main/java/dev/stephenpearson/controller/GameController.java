package dev.stephenpearson.controller;

import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.view.GameView;

public class GameController {
	
	private static GameModel gameModel;
	private static GameView gameView;
	
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		
	}
	
	
	

}
