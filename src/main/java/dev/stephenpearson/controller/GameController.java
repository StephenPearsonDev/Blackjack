package dev.stephenpearson.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.model.GameState;
import dev.stephenpearson.model.HumanPlayer;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.MenuState;
import dev.stephenpearson.model.Renderable;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.GameWindow;

public class GameController implements Runnable {

	private static MenuMessage menuMessage;
	private static TableController tableController;
	private static RenderController renderController;
	private static StateController stateController;
	private static Thread gameThread;
	private static GameWindow gameWindow;
	private static State[] states;
	
	private static State currentGameState;
	
	
	

	public GameController() {
		System.out.println("gameController made");
		states = new State[] {new MenuState(), new GameState()};
		currentGameState = states[0];
		stateController = new StateController();
		renderController = new RenderController();
		tableController = new TableController(stateController.getCurrentGameState());
		menuMessage = new MenuMessage();
		
	
	}

	
	


	public void initGameZones() {
		tableController.getGameZones().forEach((S, G) -> renderController.addRenderObjectToList(G));
	}

	
	public void render() {
		gameWindow.repaint();
	}
	
	
	public void initGame() {
		System.out.println("init called");
		initGameZones();
		tableController.init();
		
		gameWindow = new GameWindow(currentGameState,buttonAction -> {
	        switch(buttonAction) {
	        
	        case EXIT:
	        	System.exit(0);
	        	break;
	        
	        case START:
	        	currentGameState = states[1];
	        	stateController.changeState(currentGameState);
	        	break;
	        
	        case HIT:
	        	System.out.println("hit");
	        	updateMenuMessage("hello");
	        	break;
	        
	        case STAND:
	        	System.out.println("stand");
	        	break;
	        case RESET:
	        	System.out.println("reset");
	        	break;
	        case MENU:
	        	currentGameState = states[0];
	        	stateController.changeState(currentGameState);
	        	break;
	        	
	        
	        default:
	        	
	        	break;
	        } 
	    });

	
		menuMessage.addObserver(gameWindow.getGui());
		gameWindow.getGui().addGuiObserver(gameWindow);
		stateController.addObserver(gameWindow);
		
	}
	


	public void updateMenuMessage(String message) {
		menuMessage.setMessage(message);
	}





	@Override
	public void run() {
		
		initGame();
		
	}
	
	
}
