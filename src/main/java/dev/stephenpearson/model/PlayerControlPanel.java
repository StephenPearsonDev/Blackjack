package dev.stephenpearson.model;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;

import dev.stephenpearson.controller.InputHandler;

public class PlayerControlPanel {
	
	private JButton bet10Button;
	private JButton bet20Button;
	private JButton	bet50Button;
	private JButton bet100Button;
	private JButton hitButton;
	private JButton standButton;
           
	private Rectangle bet10ButtonRectangle;
	private Rectangle bet20ButtonRectangle;
	private Rectangle bet50ButtonRectangle;
	private Rectangle bet100ButtonRectangle;
	private Rectangle hitButtonRectangle;
	private Rectangle standButtonRectangle;
	
	private Rectangle panelRectangle;
	private Point panelPoint;
	private Dimension panelDimension;
	private Dimension buttonDimension;
	
	private HashMap<String, JButton> playerButtonMap = new HashMap<>();
	
	private InputHandler inputHandler;
	
	public PlayerControlPanel() {
		
		panelPoint = new Point(600,600);
		panelDimension = new Dimension(200,200);
		buttonDimension = new Dimension(80,40);
		initRectangles();
		initButtons();
		
		
	}
	
	public void initRectangles() {
        
		panelRectangle = new Rectangle(panelPoint, panelDimension);
		
		bet10ButtonRectangle = new Rectangle(panelRectangle.x + 10, panelRectangle.y + 20,buttonDimension.width,buttonDimension.height);
		bet20ButtonRectangle = new Rectangle(panelRectangle.x + 10 + 100, panelRectangle.y + 20,buttonDimension.width,buttonDimension.height);
		bet50ButtonRectangle = new Rectangle(panelRectangle.x + 10, panelRectangle.y +20 + buttonDimension.height + 15,buttonDimension.width,buttonDimension.height); 
		bet100ButtonRectangle = new Rectangle(panelRectangle.x + 10 + 100, panelRectangle.y +20 + buttonDimension.height + 15,buttonDimension.width,buttonDimension.height);
		
		hitButtonRectangle = new Rectangle(panelRectangle.x + 10, panelRectangle.y +20 + buttonDimension.height + buttonDimension.height  + 15 + 15,buttonDimension.width,buttonDimension.height);    
		standButtonRectangle = new Rectangle(panelRectangle.x + 10 + 100, panelRectangle.y +20 + buttonDimension.height + buttonDimension.height  + 15 + 15,buttonDimension.width,buttonDimension.height);  
	}
	
	public void passInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}
	
	public void initButtons() {
		
		bet10Button = new JButton("Bet 10");
		bet10Button.setBounds(bet10ButtonRectangle);
		bet10Button.addActionListener(e -> betButtonPressed(e,10));
		playerButtonMap.put("bet10Button", bet10Button);
		
		bet20Button = new JButton("Bet 20");
		bet20Button.setBounds(bet20ButtonRectangle);
		bet20Button.addActionListener(e -> betButtonPressed(e,20));
		playerButtonMap.put("bet20Button", bet20Button);
		
		bet50Button = new JButton("Bet 50");
		bet50Button.setBounds(bet50ButtonRectangle);
		bet50Button.addActionListener(e -> betButtonPressed(e,50));
		playerButtonMap.put("bet50Button", bet50Button);
		
		bet100Button = new JButton("Bet 100");
		bet100Button.setBounds(bet100ButtonRectangle);
		bet100Button.addActionListener(e -> betButtonPressed(e,100));
		playerButtonMap.put("bet100Button", bet100Button);
		
		
		hitButton = new JButton("HIT");
		hitButton.setBounds(hitButtonRectangle);
		hitButton.addActionListener(e -> hitButtonPressed());
		playerButtonMap.put("hitButton", hitButton);
		
		standButton = new JButton("STAND");
		standButton.setBounds(standButtonRectangle);
		standButton.addActionListener(e -> standButtonPressed());
		playerButtonMap.put("standButton", standButton);
		
	}
	public HashMap<String,JButton> getButtonMap() {
		return playerButtonMap;
	}
	
	public void betButtonPressed(ActionEvent e, int betAmount) {
		
		switch(betAmount) {
		
		case 10:
			System.out.println("bet 10");
			inputHandler.handleBetInput(betAmount);
			break;
		case 20:
			System.out.println("bet 20");
			inputHandler.handleBetInput(betAmount);
			break;
		case 50:
			System.out.println("bet 50");
			inputHandler.handleBetInput(betAmount);
			break;
		case 100:
			System.out.println("bet 100");
			inputHandler.handleBetInput(betAmount);
			break;
		}
		
	}
	
	public void standButtonPressed() {
		System.out.println("STAND");
	}
	
	public void hitButtonPressed() {
		System.out.println("HIT");
	}
	
	


	
	
	

}
