package dev.stephenpearson.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.model.GameState;
import dev.stephenpearson.model.Player;
import dev.stephenpearson.model.Renderable;
import dev.stephenpearson.view.GameView;

public class GameController implements Runnable {

	private GameModel gameModel;
	private GameView gameView;
	private static AnimationController animationController;
	private static TableController tableController;
	private static RenderController renderController;
	private static GUIController guiController;
	private static InputHandler inputHandler;
	private static GameStateController gameStateController;
	
	private static List<Renderable> renderObjects = new ArrayList<>();

	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;

		renderController = new RenderController();
		tableController = new TableController();

		guiController = new GUIController(tableController.getPlayerController());
		animationController = new AnimationController(gameView.getGameWindow(), tableController);
		tableController.passAnimationController(animationController);
		inputHandler = new InputHandler(tableController, animationController);
		gameStateController = new GameStateController(GameState.INIT_GAME);
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
		if (guiController.getGUI() == null) {
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
		tableController.getGameZones().forEach((S, G) -> renderController.addRenderObjectToList(G));
	}

	public void passWindowRenderObjects(List<Renderable> r) {

		gameView.getGameWindow().passRenderObjects(r);

	}

	public void passWindowRenderObject(Renderable r) {
		renderObjects.add(r);
		passWindowRenderObjects(renderObjects);

	}

	public void update() {
		guiController.getGUI().getTextAreas().updateTextAreas();
		gameView.getGameWindow().updateRenderList(renderController.getRenderObjects());

		checkGameState();
		tableController.update();
		gameStateController.update((Player)tableController.getPlayerController().getPlayer("Player"));
		//System.out.println(((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().isBetPlaced());
		//System.out.println(gameStateController.getGameState().toString());
	}

	public void checkGameState() {
		// System.out.println(((Player)tableController.getPlayerController().getPlayer("Player")).isBetPlaced());
		// System.out.println(((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().isBetPlaced());
		
		if (gameStateController.getGameState() == GameState.DEALING_ROUND) {
			gameStateController.changeGameState(GameState.WAITING_FOR_PLAYER);
			dealFirstRound();
		}

	}

	public void render() {

		// gameView.getGameWindow().repaint();

	}

	@Override
	public void run() {

		for (;;) {
			update();
			render();
		}

	}

}
