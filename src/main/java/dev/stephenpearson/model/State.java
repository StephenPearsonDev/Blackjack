package dev.stephenpearson.model;

public abstract class State {
	
	protected StateEnum state;
	
	
	public enum StateEnum {
	MENU,
	GAME,
	EXIT
	}


	public StateEnum getState() {
		return state;
	}


	public void setState(StateEnum state) {
		this.state = state;
	}
	
	
}
