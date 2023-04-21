package dev.stephenpearson.model;

import java.util.Stack;

public class ComputerDealer extends PlayerEntity implements DealingStrategy {
	
	private Stack<Card> mainGameStack;
	
	public ComputerDealer(Stack<Card> mainGameStack, int cardZoneY) {
		super(cardZoneY);
		this.setMainGameStack(mainGameStack);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public Stack<Card> getMainGameStack() {
		return mainGameStack;
	}

	public void setMainGameStack(Stack<Card> mainGameStack) {
		this.mainGameStack = mainGameStack;
	}
	
	
	
	

}
