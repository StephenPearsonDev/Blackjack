package dev.stephenpearson.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class HandValueMessage {
	
	private Rectangle messageRectangle;
	private String message;
	private String boilerPlateMessage;
	private static String defaultStartMessage = "Place a bet to start the game.";
	private List<HandValueMessageObserver> observers;
	
	public HandValueMessage(int x, int y, int width, int height, String boilerPlateMessage) {
		
		this.observers = new ArrayList<>();
		this.boilerPlateMessage = boilerPlateMessage;
		messageRectangle = new Rectangle(x,y,width,height);
		
		
	}
	
	 public void addObserver(HandValueMessageObserver observer) {
	        observers.add(observer);
	    }

	 public void removeObserver(HandValueMessageObserver observer) {
	        observers.remove(observer);
	    }

	public Rectangle getMessageRectangle() {
		return messageRectangle;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
        this.message = message;
        notifyHandValueMessageObserverObservers();
    }
	
	private void notifyHandValueMessageObserverObservers() {
        for (HandValueMessageObserver observer : observers) {
        	
            observer.update(this);
        }
    }

	
	public String getDefaultStartMessage() {
		return defaultStartMessage;
	}

	public String getBoilerPlateMessage() {
		return boilerPlateMessage;
	}
	
	
	

}
