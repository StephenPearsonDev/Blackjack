package dev.stephenpearson.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.RenderObject;
import dev.stephenpearson.view.GameView;



public class GameController implements Runnable {
	
	private GameModel gameModel;
	private GameView gameView;
	private static AnimationController animationController;
	private static TableController tableController;
	private static RenderController renderController;
	private static GUIController guiController;
	private static InputHandler inputHandler;

	private static List<RenderObject> renderObjects = new ArrayList<>();
	
	
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		
		renderController = new RenderController();
		tableController = new TableController();
		
		guiController = new GUIController(tableController.getPlayerController());
		animationController = new AnimationController(gameView.getGameWindow(), tableController);
		tableController.passAnimationController(animationController);
		inputHandler = new InputHandler(tableController, animationController);
		passMouseListener();
		passGUI();
		passInputHandler();
		initGameZones();
		initGame();

	}
	
	public void passMouseListener() {
		
		gameView.getGameWindow().addMouseListener(inputHandler);
	}
	
	public void passInputHandler() {
		guiController.getGUI().getPlayerControlPanel().passInputHandler(inputHandler);
	}
	
	public void passGUI() {
		if(guiController.getGUI() == null) {
			System.out.println("gui is null");
		}
		gameView.getGameWindow().addGUI(guiController.getGUI());
	}
	
	public void initGame() {

		
		renderController.addRenderObjectToList(tableController.getDeckController().lookAtTopCard());
	}
	
	public void dealFirstRound() {
		tableController.getDeckController().initGame(tableController.getPlayerController());
	}
	
	public void initGameZones() {
		//tableController.getGameZones().forEach((S,G) -> renderObjects.add(G));
		tableController.getGameZones().forEach((S,G) -> renderController.addRenderObjectToList(G));
//		for(RenderObject c : tableController.getDeckController().getGameStack()) {
//			renderObjects.add(c);
//		}
		
		//renderObjects.add(tableController.getDeckController().lookAtTopCard());
		//System.out.println("init top card is: " + tableController.getDeckController().lookAtTopCard().getCardString());
		//passWindowRenderObjects(renderObjects);
		
	}
	
	public void passWindowRenderObjects(List<RenderObject> r){
		
		gameView.getGameWindow().passRenderObjects(r);
		
		
	}
	
	public void passWindowRenderObject(RenderObject r) {
		renderObjects.add(r);
		passWindowRenderObjects(renderObjects);
		
	}
	
	public void update() {
		guiController.getGUI().getTextAreas().updateTextAreas();
		gameView.getGameWindow().updateRenderList(renderController.getRenderObjects());
		checkGameState();
	}
	
	public void checkGameState() {
		
		if(((Player)tableController.getPlayerController().getPlayer("Player")).isBetPlaced()) {
			dealFirstRound();
		}
		
	}
	
	public void render() {
		
		//gameView.getGameWindow().repaint();
		
	}

	@Override
	public void run() {
		
		for(;;) {
			update();
			render();
		}
		
	}
	


}
