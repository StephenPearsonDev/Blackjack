package dev.stephenpearson.model;

import java.util.ArrayList;
import java.util.List;

public class MenuMessage {
    private String message;
    private List<MenuMessageObserver> observers;

    public MenuMessage() {
   
        this.observers = new ArrayList<>();
    }

    public void addObserver(MenuMessageObserver observer) {
    	System.out.println("menu message add observer");
        observers.add(observer);
    }

    public void removeObserver(MenuMessageObserver observer) {
        observers.remove(observer);
    }

    public void setMessage(String message) {
        this.message = message;
        notifyMenuMessageObservers();
    }

    public String getMessage() {
        return message;
    }

    private void notifyMenuMessageObservers() {
        for (MenuMessageObserver observer : observers) {
        	
            observer.update(this);
        }
    }
}
