package dev.stephenpearson.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import dev.stephenpearson.model.ComputerDealer;
import dev.stephenpearson.model.HumanPlayer;

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
		
		((HumanPlayer)tableController.getHumanPlayer()).getPlayerBank().decreaseBank(betAmount);
		
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
