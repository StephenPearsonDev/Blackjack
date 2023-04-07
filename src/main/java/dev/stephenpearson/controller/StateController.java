package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.MenuState;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.StateObserver;

public class StateController {
	
	private List<StateObserver> observers = new ArrayList<>();
	private State currentState;
	
	public StateController() {
		
		currentState = new MenuState();
	}
	

	 public void addObserver(StateObserver observer) {
	        observers.add(observer);
	    }
	
	 public void changeState(State newState) {
	        this.currentState = newState;
	        notifyObservers();
	    }


	public State getCurrentGameState() {
		return currentState;
	}
	
	private void notifyObservers() {
        for (StateObserver observer : observers) {
            observer.onStateChanged(currentState);
        }
    }


	


}
