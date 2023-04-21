package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;



public class BetPotMessage {
	
	
	 private String message;
	    private List<BetPotMessageObserver> observers;

	    public BetPotMessage() {
	   
	        this.observers = new ArrayList<>();
	    }

	    public void addObserver(BetPotMessageObserver observer) {
	    	System.out.println("menu message add observer");
	        observers.add(observer);
	    }

	    public void removeObserver(BetPotMessageObserver observer) {
	        observers.remove(observer);
	    }

	    public void setMessage(String message) {
	        this.message = message;
	        notifyBetPotObservers();
	    }

	    public String getMessage() {
	        return message;
	    }

	    private void notifyBetPotObservers() {
	        for (BetPotMessageObserver observer : observers) {
	        	
	            observer.update(this);
	        }
	    }

}
