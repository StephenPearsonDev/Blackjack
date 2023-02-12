package dev.stephenpearson.controller;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.GameModel;
import dev.stephenpearson.model.RenderObject;
import dev.stephenpearson.view.GameView;



public class GameController implements Runnable, MouseListener {
	
	private GameModel gameModel;
	private GameView gameView;
	private AnimationController animationController;
	
	
	private static TableController tableController;
	private static List<RenderObject> renderObjects = new ArrayList<>();
	
	
	public GameController(GameModel gameModel, GameView gameView) {
		this.gameModel = gameModel;
		this.gameView = gameView;
		tableController = new TableController();
		animationController = new AnimationController(gameView.getGameWindow(), tableController);
		passMouseListener();
		initGameZones();

	}
	
	public void passMouseListener() {
		
		gameView.getGameWindow().addMouseListener(this);
	}
	
	public void initGameZones() {
		tableController.getGameZones().forEach((S,G) -> renderObjects.add(G));

//		for(RenderObject c : tableController.getDeckController().getGameStack()) {
//			renderObjects.add(c);
//		}
		
		renderObjects.add(tableController.getDeckController().lookAtTopCard());
		System.out.println("init top card is: " + tableController.getDeckController().lookAtTopCard().getCardString());
		passWindowRenderObjects(renderObjects);
		
	}
	
	public void passWindowRenderObjects(List<RenderObject> r){
		
		gameView.getGameWindow().passRenderObjects(r);
		
		
	}
	
	public void passWindowRenderObject(RenderObject r) {
		renderObjects.add(r);
		passWindowRenderObjects(renderObjects);
		
	}
	
	public void update() {
		
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
	
	public void handleInput(MouseEvent e) {
		
		
		if(tableController.getDeckController()
				
				.lookAtTopCard()
				.getCardBounds()
				.contains(e.getPoint())) {
			System.out.println("top card is: " + tableController.getDeckController().lookAtTopCard().getCardString());
			
			animationController.animateCard(tableController.getDeckController().lookAtTopCard(), tableController.getDealtCardZone("playerHandZone").getNextZone().getCardHolderLocation());
			
			passWindowRenderObject(tableController.getDeckController().getTopStackCard());
			System.out.println(tableController.getDeckController().lookAtTopCard().getCardString());
		
		} 
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		handleInput(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
