package dev.stephenpearson.main;

import dev.stephenpearson.controller.GameController;



public class BlackJack implements Runnable {
	
	private Thread gameThread;
	private static GameController gameController;

	
	public BlackJack() {
		
		initGame();
	}
	
	public void initGame() {
	
		gameController = new GameController();
		gameThread = new Thread(gameController);
		gameThread.start();
	}
	
	
	

	@Override
	public void run() {
		

	}

}
