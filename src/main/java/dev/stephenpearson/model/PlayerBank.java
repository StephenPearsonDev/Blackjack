package dev.stephenpearson.model;

public class PlayerBank {
	
	private int tempBankSize;
	private int bankSize;
	private boolean playerBroke = false;
	private String bankString;
	private boolean firstBetPlaced = false;
	private boolean betPlaced = false;
	private String boilerplate = "Player bank: ";
	
	
	public PlayerBank(int bankSize) {
		this.bankSize = bankSize;
		this.tempBankSize = bankSize;
		bankString = boilerplate + String.valueOf(bankSize);
	}
	
	public int getBankSize() {
		return bankSize;
	}
	
	public void decreaseBank(int deduct) {
		
		
			if(bankSize>=deduct) {
				if(!firstBetPlaced) {
					
					bankSize -= deduct;
					bankString = boilerplate + String.valueOf(bankSize);
					
					System.out.println("bank decreased - bank is: " + bankString);
					if(bankSize <= 0) {
						playerBroke = true;
						System.out.println("player broke");
					}
				}
			}
	}
	
	public void increaseBank(int increase) {
	
		bankSize += increase;
	
		bankString = boilerplate + String.valueOf(bankSize);
	}

	public boolean isPlayerBroke() {
		return playerBroke;
	}
	
	public String getBankString() {
		return bankString;
	}
	
	public void setBetPlaced(boolean b) {
		
	}
	
	public boolean isBetPlaced() {
		return firstBetPlaced;
	}
	
	
	
}
