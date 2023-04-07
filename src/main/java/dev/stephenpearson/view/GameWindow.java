package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.function.Consumer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dev.stephenpearson.model.State;
import dev.stephenpearson.model.State.StateEnum;


public class GameWindow extends JPanel implements StateObserver{
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 800;
	private Consumer<Void> onButtonClick;

	private static JFrame windowFrame;
	
	private State currentGameState;
	
	

	
	public GameWindow(State currentGameState, Consumer<Void> onButtonClick) {
		this.currentGameState = currentGameState;
		this.onButtonClick = onButtonClick;
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		setLayout(null);
		
		windowFrame = new JFrame();
		windowFrame.setResizable(false);
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		windowFrame.add(this);
		
		windowFrame.pack();
		windowFrame.setLocationRelativeTo(null);
		windowFrame.setVisible(true);
		
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
		    removeAll();
		    g.setColor(new Color(80,107,70));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    JButton button = new JButton("start game");
		    button.setBounds(getWidth()/2 - 100, getHeight()/2 - 50, 200, 100);

		    button.addActionListener(e -> {
		    	onButtonClick.accept(null);
		    	
		    });

		    add(button);
		   
	
		    break;
		
		case GAME:
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			System.out.println("game state");
			break;
			
		default:
			System.out.println("error occured");
			break;
		}
		
		
	}

	

}
