package dev.stephenpearson.model;

public class PlayerBank {
	
	private int bankSize;
	private boolean playerBroke = false;
	private String bankString;
	private boolean firstBetPlaced = false;
	
	public PlayerBank(int bankSize) {
		this.bankSize = bankSize;
		bankString = String.valueOf(bankSize);
	}
	
	public int getBankSize() {
		return bankSize;
	}
	
	public void decreaseBank(int deduct) {
		
		if(bankSize>=deduct) {
			if(!firstBetPlaced) {
				firstBetPlaced = true;
				bankSize -= deduct;
				bankString = String.valueOf(bankSize);
				
				System.out.println("bet decreased - bank is: " + bankString);
				if(bankSize <= 0) {
					playerBroke = true;
					System.out.println("player broke");
				}
			}
			
		}
		
		
	}
	
	public void increasBank(int increase) {
		bankSize += increase;
		bankString = String.valueOf(bankSize);
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
