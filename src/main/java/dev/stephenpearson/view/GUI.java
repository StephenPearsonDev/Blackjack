package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import dev.stephenpearson.model.Constants;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.MenuMessageObserver;
import dev.stephenpearson.view.Button.ButtonAction;



public class GUI implements MenuMessageObserver{
	
	private String topMenuString;
	private Rectangle topMenu;
	private Rectangle bottomMenu;
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
	private List<GuiObserver> guiObservers = new ArrayList<>();

	
	public GUI(Consumer<ButtonAction> onButtonClick) {
		
		topMenuString = "Place a bet to start playing.";
		topMenu = new Rectangle(0,0,Constants.ViewConstants.PANEL_WIDTH, 50);
		bottomMenu = new Rectangle(0,Constants.ViewConstants.PANEL_HEIGHT-50,Constants.ViewConstants.PANEL_WIDTH, 50);
		menuButtons = new ArrayList<>();
		gameButtons = new ArrayList<>();
		this.onButtonClick = onButtonClick;
		initButtons();
		initActionListeners();
	}
	
	public void initButtons() {
		
		exitButton = new Button(Constants.ViewConstants.PANEL_CENTER_X - Constants.ViewConstants.BUTTON_WIDTH/2,Constants.ViewConstants.PANEL_CENTER_Y+Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Exit");
		startButton = new Button(Constants.ViewConstants.PANEL_CENTER_X - Constants.ViewConstants.BUTTON_WIDTH/2,Constants.ViewConstants.PANEL_CENTER_Y-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Start");
		
		//bottom menu
		hitButton = new Button(0,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Hit");
		standButton = new Button(hitButton.getBounds().x+hitButton.getBounds().width,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Stand");
		
		//top menu
		resetButton = new Button(Constants.ViewConstants.PANEL_WIDTH-Constants.ViewConstants.BUTTON_WIDTH,0,buttonWidth,buttonHeight,"Reset");
		returnToMenuButton = new Button(0,0,buttonWidth,buttonHeight,"Menu");
		
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
	
	public void paintMenus(Graphics g) {
		
		g.setColor(new Color(229,229,229));
		g.fillRect(topMenu.x, topMenu.y, topMenu.width, topMenu.height);
		g.fillRect(bottomMenu.x, bottomMenu.y, bottomMenu.width, bottomMenu.height);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("system", Font.BOLD, 20));
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		Rectangle2D topMenuStringBounds = g2d.getFontMetrics().getStringBounds(topMenuString, g2d);
		g2d.drawString(topMenuString, (int) (Constants.ViewConstants.PANEL_CENTER_X - topMenuStringBounds.getWidth()/2), (int) (topMenuStringBounds.getHeight()/2) + topMenu.height/2);

	}
	
	public void paintTableElements(Graphics g) {
		
		
	}
	
	public void paintMenuElements(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		String welcome = "Welcome";
		String to = "to";
		String blackjack = "Blackjack!";
		
		
		g2d.setFont(new Font("system", Font.BOLD, 50));
		g2d.setColor(Color.WHITE);
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		Rectangle2D welcomeBounds = g2d.getFontMetrics().getStringBounds(welcome, g2d);
		Rectangle2D toBounds = g2d.getFontMetrics().getStringBounds(to, g2d);
		Rectangle2D blackjackBounds = g2d.getFontMetrics().getStringBounds(blackjack, g2d);
		
		
		g2d.drawString(welcome, (int) (Constants.ViewConstants.PANEL_CENTER_X - welcomeBounds.getWidth()/2), 100);
		g2d.drawString(to, (int) (Constants.ViewConstants.PANEL_CENTER_X - toBounds.getWidth()/2), 175);
		g2d.drawString(blackjack, (int) (Constants.ViewConstants.PANEL_CENTER_X - blackjackBounds.getWidth()/2), 250);
		
	
		
		
	}

	@Override
	public void update(MenuMessage menuMessage) {
		topMenuString = menuMessage.getMessage();
		System.out.println("test");
		System.out.println(topMenuString);
		notifyGuiObservers();
		
	}
	
	public void addGuiObserver(GuiObserver observer) {
        guiObservers.add(observer);
    }
	
	public void removeGuiObserver(GuiObserver observer) {
        guiObservers.remove(observer);
    }

    private void notifyGuiObservers() {
        for (GuiObserver observer : guiObservers) {
            observer.onGuiUpdate();
        }
    }
	
	}

