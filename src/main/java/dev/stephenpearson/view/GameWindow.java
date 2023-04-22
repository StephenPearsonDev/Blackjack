package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dev.stephenpearson.controller.SpriteController;
import dev.stephenpearson.model.GUIValues;
import dev.stephenpearson.model.State;
import dev.stephenpearson.model.State.StateEnum;
import dev.stephenpearson.view.Button.ButtonAction;


public class GameWindow extends JPanel implements StateObserver, GuiObserver{
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 800;


	private static JFrame windowFrame;
	private State currentGameState;
	
	private GUI gui;
	

	public GameWindow(State currentGameState, Consumer<ButtonAction> onButtonClick) {
		this.currentGameState = currentGameState;
		
		gui = new GUI(onButtonClick);
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(null);
		
		windowFrame = new JFrame();
		windowFrame.setResizable(false);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		windowFrame.add(this);
		
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setVisible(true);
		updateButtons();
		
	}
	

	public void render() {
		repaint();
	}
	
	
	@Override
    public void onStateChanged(State newState) {
		System.out.println("onStateChanged triggered");
        this.currentGameState = newState;
        updateButtons();
    }
	
	@Override
	public void onGuiUpdate() {
		repaint();
	}
	
	private void updateButtons() {
	    removeAll();

	

	    switch (currentGameState.getState()) {
	        case MENU:
	        
	            for (Button b : gui.getMenuButtons()) {
	                System.out.println("Adding menu button: " + b.getText());
	                add(b);
	            }
	            break;

	        case GAME:
	            for (Button b : gui.getGameButtons()) {
	                System.out.println("Adding game button: " + b.getText());
	                add(b);
	            }
	            break;

	        default:
	            System.out.println("error occurred");
	            break;
	    }

	    revalidate();
	    repaint();
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(75,79,76));
		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		switch(currentGameState.getState()) {
		
		case MENU:
			
			g.setColor(new Color(75,79,76));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
			gui.paintMenuElements(g);
			

		    break;
		
		case GAME:
			g.setColor(new Color(53,101,77));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			
			gui.paintMenus(g);
			gui.paintTableElements(g);
			
			
		
			//draw all cards
//			int x = 0;
//			int y = 50;
//			int counter = 0;
//			for(Map.Entry<String, BufferedImage> b : SpriteController.getCardImageMap().entrySet()) {
//				
//				g.drawImage(b.getValue(),x,y,b.getValue().getWidth()*3, b.getValue().getHeight()*3,null);
//				x+=60;
//				counter++;
//				if(counter >= 13) {
//					y+=80;
//					x = 0;
//					counter = 0;
//				}
//			}
			
			
			

			break;
			
		default:
			System.out.println("error occured");
			break;
		}
		
		
	}
	public GUI getGui() {
		return gui;
	}
	
	public void resetWindow() {
		removeAll();
		revalidate();
		repaint();
	}


	

}
