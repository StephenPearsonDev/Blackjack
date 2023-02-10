package dev.stephenpearson.view;

import javax.swing.JFrame;

public class GameView extends JFrame {
	
	private static GameWindow gameWindow;
	
	public GameView() {
		
		gameWindow = new GameWindow();
		
		add(gameWindow);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		
	}
	
	public void render() {
		gameWindow.render();
	}
	
	public GameWindow getGameWindow() {
		
		return gameWindow;
		
	}
	
	
	
	

}
