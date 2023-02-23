package dev.stephenpearson.view;

import dev.stephenpearson.controller.PlayerController;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.PlayerControlPanel;
import dev.stephenpearson.model.TextAreas;

public class GUI {
	
	private static PlayerControlPanel playerControlPanel;
	private static TextAreas textAreas;
	private PlayerController playerController;
	private Player player;
	
	public GUI(PlayerController playerController) {
		
		this.playerController = playerController;
		playerControlPanel = new PlayerControlPanel();
		player = (Player)playerController.getPlayer("Player");
		
	
		
		textAreas = new TextAreas(player.getPlayerBank());
	}
	
	public PlayerControlPanel getPlayerControlPanel() {
		return playerControlPanel;
	}
	
	public TextAreas getTextAreas() {
		return textAreas;
	}
	
	
	
	

}
