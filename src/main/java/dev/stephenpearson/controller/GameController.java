package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.model.RenderObject;
import dev.stephenpearson.view.GameView;



public class GameController implements Runnable {
	
	private GameModel gameModel;
	private GameView gameView;
	
	private static TableController tableController;
	private static List<RenderObject> renderObjects = new ArrayList<>();
	
	
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		tableController = new TableController();
		initGameZones();
		
	}
	
	public void initGameZones() {
		tableController.getGameZones().forEach((S,G) -> renderObjects.add(G));

		for(RenderObject c : tableController.getDeckController().getGameStack()) {
			renderObjects.add(c);
		}
		passWindowRenderObjects(renderObjects);
		
	}
	
	public void passWindowRenderObjects(List<RenderObject> r){
		
		gameView.getGameWindow().passRenderObjects(r);
		
		
	}
	
	public void addRenderObject(RenderObject r) {
		gameView.getGameWindow().addRenderObject(r);
	}
	
	public void update() {
		
	}
	
	public void render() {
		
		
	}

	@Override
	public void run() {
		
		for(;;) {
			update();
			render();
		}
		
	}

}
