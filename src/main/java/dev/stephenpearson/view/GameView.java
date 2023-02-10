package dev.stephenpearson.view;

import javax.swing.JFrame;

public class GameView extends JFrame {
	
	private static GameWindow gameWindow;
	
	public GameView() {
		
		gameWindow = new GameWindow();
	}
	
	public GameWindow getGameWindow() {
		
		return gameWindow;
		
	}
	
	
	
	

}
