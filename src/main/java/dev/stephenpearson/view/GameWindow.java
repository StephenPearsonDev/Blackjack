package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dev.stephenpearson.model.GUIValues;
import dev.stephenpearson.model.State;
import dev.stephenpearson.model.State.StateEnum;
import dev.stephenpearson.view.Button.ButtonAction;


public class GameWindow extends JPanel implements StateObserver{
	
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
		gui = new GUI(onButtonClick);
		
	}
	

	public void render() {
		repaint();
	}
	
	
	@Override
    public void onStateChanged(State newState) {
        this.currentGameState = newState;
        removeAll();
        repaint();
    }
	
	

	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(currentGameState.getState().name());
		
		
		switch(currentGameState.getState()) {
		
		case MENU:
	
			g.setColor(new Color(75,79,76));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			for(Button b : gui.getMenuButtons()) {
				add(b);
			}

		    break;
		
		case GAME:
			g.setColor(new Color(53,101,77));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			
			for(Button b : gui.getGameButtons()) {
				add(b);
			}
			
			System.out.println("state changed");
			
			break;
			
		default:
			System.out.println("error occured");
			break;
		}
		
		
	}

	

}
