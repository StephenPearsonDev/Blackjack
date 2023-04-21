package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;


public class PlayerBankMessage {
	private String message;
    private List<PlayerBankMessageObserver> observers;

    public PlayerBankMessage() {
   
        this.observers = new ArrayList<>();
    }

    public void addObserver(PlayerBankMessageObserver observer) {
    	System.out.println("menu message add observer");
        observers.add(observer);
    }

    public void removeObserver(PlayerBankMessageObserver observer) {
        observers.remove(observer);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPlayerBankMessageObservers();
    }

    public String getMessage() {
        return message;
    }

    private void notifyPlayerBankMessageObservers() {
        for (PlayerBankMessageObserver observer : observers) {
        	
            observer.update(this);
        }
    }
}
