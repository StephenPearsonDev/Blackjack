package dev.stephenpearson.controller;

import dev.stephenpearson.model.PlayerBank;

public class BankController {
	
	
	private PlayerBank playerBank;
	
	
	
	public BankController() {
		
		playerBank = new PlayerBank(1000);
		
	}
	
	public void betPlaced(int betAmount) {
		playerBank.decreaseBank(betAmount);
	}
	

}
