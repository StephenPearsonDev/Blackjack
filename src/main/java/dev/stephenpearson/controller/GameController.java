package dev.stephenpearson.controller;

import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.view.GameView;



public class GameController implements Runnable {
	
	private GameModel gameModel;
	private GameView gameView;
	private static TableController tableController;
	
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		tableController = new TableController();
		
		
	}
	
	public void update() {
		
	}
	
	public void render() {
		gameView.render();
	}

	@Override
	public void run() {
		
		for(;;) {
			update();
			render();
		}
		
	}

}
