package dev.stephenpearson.view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dev.stephenpearson.model.Constants;
import dev.stephenpearson.view.Button.ButtonAction;



public class GUI {
	
	private List<Button> menuButtons;
	private List<Button> gameButtons;
	
	private Button exitButton;
	private Button startButton;
	private Button resetButton;
	private Button hitButton;
	private Button standButton;
	private Button returnToMenuButton;
	private Button splitButton;
	private Button doubleButton;
	private int buttonWidth = Constants.ViewConstants.BUTTON_WIDTH;
	private int buttonHeight = Constants.ViewConstants.BUTTON_HEIGHT;
	
	private Consumer<ButtonAction> onButtonClick;
	
	
	public GUI(Consumer<ButtonAction> onButtonClick) {
		menuButtons = new ArrayList<>();
		gameButtons = new ArrayList<>();
		this.onButtonClick = onButtonClick;
		initButtons();
		initActionListeners();
		
		
	}
	
	public void initButtons() {
		
		exitButton = new Button(100,200,buttonWidth,buttonHeight,"Exit");
		startButton = new Button(100,100,buttonWidth,buttonHeight,"Start");
		hitButton = new Button(100,300,buttonWidth,buttonHeight,"Hit");
		standButton = new Button(100,400,buttonWidth,buttonHeight,"Stand");
		resetButton = new Button(100,500,buttonWidth,buttonHeight,"Reset");
		returnToMenuButton = new Button(100,600,buttonWidth,buttonHeight,"Menu");
		
		menuButtons.add(exitButton);
		menuButtons.add(startButton);
		
		gameButtons.add(hitButton);
		gameButtons.add(standButton);
		gameButtons.add(resetButton);
		gameButtons.add(returnToMenuButton);
		
	}
	
	public void initActionListeners() {
		
		for(Button b : menuButtons) {
			
			b.addActionListener(e -> onButtonClick.accept(b.getButtonAction()));
	
		}
		
		for(Button b : gameButtons) {
			
			b.addActionListener(e -> onButtonClick.accept(b.getButtonAction()));
	
		}
		
	}

	public List<Button> getMenuButtons() {
		return menuButtons;
	}

	public void setMenuButtons(List<Button> menuButtons) {
		this.menuButtons = menuButtons;
	}

	public List<Button> getGameButtons() {
		return gameButtons;
	}

	public void setGameButtons(List<Button> gameButtons) {
		this.gameButtons = gameButtons;
	}
	
	

}
