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


public class GameWindow extends JPanel implements StateObserver{
	
	private static final int PANEL_WIDTH = 800;
	private static final int PANEL_HEIGHT = 800;
	private Consumer<ButtonAction> onButtonClick;

	private static JFrame windowFrame;
	private State currentGameState;
	
	

	
	public GameWindow(State currentGameState, Consumer<ButtonAction> onButtonClick) {
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
	public enum ButtonAction {
	    EXIT,
	    OPTIONS,
	    GAME,
	    BET10,
	    PLACEBET
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
		    g.setColor(new Color(80,107,70));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    JButton gameButton = new JButton("Start game");
		    gameButton.setBounds(getWidth()/2 - 100,200, 200, 100);
		    JButton optionsButton = new JButton("options");
		    optionsButton.setBounds(getWidth()/2 - 100, 320, 200, 100);
		    JButton exitButton = new JButton("Exit game");
		   exitButton.setBounds(getWidth()/2 - 100, 440, 200, 100);
		    
		    exitButton.addActionListener(e -> onButtonClick.accept(ButtonAction.EXIT));
		    gameButton.addActionListener(e -> onButtonClick.accept(ButtonAction.GAME));
		    optionsButton.addActionListener(e -> onButtonClick.accept(ButtonAction.OPTIONS));
		    
		    add(exitButton);
		    add(gameButton);
		    add(optionsButton);
		    
		    break;
		
		case GAME:
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			PlayingTable.draw(g);
			g.setColor(Color.WHITE);
			g.fillRect(200, 200, 400, 100);
			g.setColor(Color.BLACK);
			g.drawString("Place a bet to begin playing!", 325, 250);
			
			JLabel currentBetAmount = new JLabel(String.valueOf(GUIValues.getCurrentBetAmount()));
			currentBetAmount.setOpaque(true);
			JButton bet10 = new JButton("Bet 10");
			bet10.addActionListener(e -> {
				
				if(!GUIValues.isBetJustPlaced()) {
					onButtonClick.accept(ButtonAction.BET10);
					GUIValues.increaseCurrentBetAmount(10);
					currentBetAmount.setText(String.valueOf(GUIValues.getCurrentBetAmount()));
				} else {
					System.out.println("bet already placed");
				}
				
			});
			
			bet10.setBounds(0,700, 100, 100);
			JButton placeBet = new JButton("Place bet");
			
			placeBet.addActionListener(e -> {
				System.out.println("bet placed");
				GUIValues.setPlacedBetAmount(GUIValues.getCurrentBetAmount());
				GUIValues.setCurrentBetAmount(0);
				currentBetAmount.setText(String.valueOf(GUIValues.getCurrentBetAmount()));
				GUIValues.setBetJustPlaced(true);
				onButtonClick.accept(ButtonAction.PLACEBET);
				
				
			});
			placeBet.setBounds(250,700, 100, 100);

			currentBetAmount.setBounds(100, 700, 150, 100);
			add(currentBetAmount);
			add(bet10);
			add(placeBet);
			break;
			
		default:
			System.out.println("error occured");
			break;
		}
		
		
	}

	

}
