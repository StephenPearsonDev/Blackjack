package dev.stephenpearson.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.stephenpearson.model.Dealer;
import dev.stephenpearson.model.Player;

public class InputHandler implements MouseListener {
	
	private TableController tableController;
	private AnimationController animationController;
	
	
	public InputHandler(TableController tableController, AnimationController animationController) {
		this.tableController = tableController;
		this.animationController = animationController;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void handleBetInput(int betAmount) {
		
		
		
		switch(betAmount) {
		case 10:
			((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().decreaseBank(betAmount);
			break;
		case 20:
			((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().decreaseBank(betAmount);
			break;
		case 50:
			((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().decreaseBank(betAmount);
			break;
		case 100:
			((Player)tableController.getPlayerController().getPlayer("Player")).getPlayerBank().decreaseBank(betAmount);
			break;
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(tableController.getDeckController()
				.lookAtTopCard()
				.getCardBounds()
				.contains(e.getPoint())) {
			//System.out.println("top card is: " + tableController.getDeckController().lookAtTopCard().getCardString());
			if(!animationController.isCardAnimating()) {
				animationController.animateCard(tableController.getDeckController().lookAtTopCard(), tableController.getDealtCardZone("playerHandZone").getNextZone().getCardHolderLocation());
				
				//passWindowRenderObject(tableController.getDeckController().getTopStackCard());
				//System.out.println(tableController.getDeckController().lookAtTopCard().getCardString());
			}
			
		
		} 
		
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
