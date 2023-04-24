package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Consumer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import dev.stephenpearson.model.State;
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
	                add(b);
	            }
	            break;

	        case GAME:
	            for (Button b : gui.getGameButtons()) {
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
