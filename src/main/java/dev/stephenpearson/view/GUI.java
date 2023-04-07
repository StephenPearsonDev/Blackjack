package dev.stephenpearson.view;


import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.PlayerControlPanel;
import dev.stephenpearson.model.TextAreas;

public class GUI {
	
	private static PlayerControlPanel playerControlPanel;
	private static TextAreas textAreas;

	private HumanPlayer player;
	
	public GUI() {
		
	
		playerControlPanel = new PlayerControlPanel();
	
		
	
		
		textAreas = new TextAreas(player.getPlayerBank());
	}
	
	public PlayerControlPanel getPlayerControlPanel() {
		return playerControlPanel;
	}
	
	public TextAreas getTextAreas() {
		return textAreas;
	}
	
	
	
	

}
