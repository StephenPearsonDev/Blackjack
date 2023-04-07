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
import dev.stephenpearson.model.MenuState;
import dev.stephenpearson.model.Renderable;
import dev.stephenpearson.model.State;
import dev.stephenpearson.view.GameWindow;

public class GameController implements Runnable {

	
	private static AnimationController animationController;
	private static TableController tableController;
	private static RenderController renderController;
	private static GUIController guiController;
	private static InputHandler inputHandler;
	private static GameStateController gameStateController;
	private static StateController stateController;
	private static Thread gameThread;
	private static GameWindow gameWindow;
	private static State[] states = new State[] {new MenuState(), new GameState()};
	
	
	
	private static List<Renderable> renderObjects = new ArrayList<>();

	public GameController() {
		
		stateController = new StateController();
		renderController = new RenderController();
		tableController = new TableController(stateController.getCurrentGameState());
		
		
		gameThread = new Thread(this);
		gameThread.start();
		

	}

	



	public void initGameZones() {
		tableController.getGameZones().forEach((S, G) -> renderController.addRenderObjectToList(G));
	}

	
	public void render() {
		gameWindow.repaint();
	}
	
	
	public void initGame() {
		initGameZones();
		tableController.init();
		gameWindow = new GameWindow(states[0],ignored -> {
	        stateController.changeState(states[1]);
	    });
		
		stateController.addObserver(gameWindow);
	}
	

	@Override
	public void run() {

		initGame();

	}

}
