package dev.stephenpearson.main;

import dev.stephenpearson.controller.GameController;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.view.GameView;


public class BlackJack implements Runnable {
	
	private Thread gameThread;
	private static GameController gameController;
	private static GameModel gameModel;
	private static GameView gameView;
	
	public BlackJack() {
		
		initControllers();
	}
	
	public void initControllers() {
	
		gameModel = new GameModel();
		gameView = new GameView();
		gameController = new GameController(gameModel, gameView);
		gameThread = new Thread(gameController);
		gameThread.start();
	}
	

	@Override
	public void run() {
		
		
	}

}
