package dev.stephenpearson.controller;

import dev.stephenpearson.view.GUI;

public class GUIController {
	
	private static GUI gui;
	private PlayerController playerController;
	
	public GUIController(PlayerController playerController) {
		this.playerController = playerController;
		buildGUI();
	}
	
	public void buildGUI() {
		gui = new GUI(playerController);
	}
	
	public GUI getGUI() {
		return gui;
	}

}
