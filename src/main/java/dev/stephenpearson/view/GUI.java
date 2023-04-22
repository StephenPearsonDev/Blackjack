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
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import dev.stephenpearson.controller.BetPotMessage;
import dev.stephenpearson.controller.BetPotMessageObserver;
import dev.stephenpearson.controller.PlayerBankMessage;
import dev.stephenpearson.controller.PlayerBankMessageObserver;
import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.Constants;
import dev.stephenpearson.model.DealerHandValueMessage;
import dev.stephenpearson.model.HandValueMessage;
import dev.stephenpearson.model.HandValueMessageObserver;
import dev.stephenpearson.model.MenuMessage;
import dev.stephenpearson.model.MenuMessageObserver;
import dev.stephenpearson.model.PlayerHandValueMessage;
import dev.stephenpearson.model.Renderable;
import dev.stephenpearson.view.Button.ButtonAction;



public class GUI implements MenuMessageObserver, BetPotMessageObserver, PlayerBankMessageObserver, HandValueMessageObserver{
	
	private String topMenuString;
	private String betPotMessageString;
	private String playerBankMessageString;
	private String playerHandValueMessage;
	private String dealerHandValueMessage;
	
	private Rectangle topMenu;
	private Rectangle bottomMenu;
	private Rectangle betPotMessageZone;
	private Rectangle playerBankMessageZone;
	private Rectangle dealerHandValueMessageZone;
	private Rectangle playerHandValueMessageZone;
	
	private List<Button> menuButtons;
	private List<Button> gameButtons;
	
	private Button exitButton;
	private Button startButton;
	private Button resetButton;
	private Button returnToMenuButton;
	
	//bottom row buttons
	private Button hitButton;
	private Button standButton;
	private Button bet10;
	private Button bet50;
	private Button placeBet;
	private Button clearBet;
	private Button splitButton;
	private Button doubleButton;
	
	
	private int buttonWidth = Constants.ViewConstants.BUTTON_WIDTH;
	private int buttonHeight = Constants.ViewConstants.BUTTON_HEIGHT;
	
	private Consumer<ButtonAction> onButtonClick;
	private List<GuiObserver> guiObservers = new ArrayList<>();
	
	private boolean drawCards = false;
	private List<Renderable> cardsOnScreen = new ArrayList<>();
	
	

	
	public GUI(Consumer<ButtonAction> onButtonClick) {
		betPotMessageString = "0";
		playerBankMessageString = "1000";
		menuButtons = new ArrayList<>();
		gameButtons = new ArrayList<>();
		initButtons();
		topMenu = new Rectangle(0,0,Constants.ViewConstants.PANEL_WIDTH, 50);
		bottomMenu = new Rectangle(0,Constants.ViewConstants.PANEL_HEIGHT-100,Constants.ViewConstants.PANEL_WIDTH, 100);
		betPotMessageZone = new Rectangle(bet10.getX(), bottomMenu.y,bet10.getWidth()*4,100);
		playerBankMessageZone = new Rectangle(0, bottomMenu.y,bet10.getWidth()*4,100);
		dealerHandValueMessageZone = new Rectangle(0, 50,bet10.getWidth()*4,100);
		playerHandValueMessageZone = new Rectangle(0, playerBankMessageZone.y - 50,bet10.getWidth()*4,100);
		
		this.onButtonClick = onButtonClick;
		
		initActionListeners();
	}
	
	public void initButtons() {
		
		exitButton = new Button(Constants.ViewConstants.PANEL_CENTER_X - Constants.ViewConstants.BUTTON_WIDTH/2,Constants.ViewConstants.PANEL_CENTER_Y+Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Exit");
		startButton = new Button(Constants.ViewConstants.PANEL_CENTER_X - Constants.ViewConstants.BUTTON_WIDTH/2,Constants.ViewConstants.PANEL_CENTER_Y-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Start");
		
		//bottom menu
		hitButton = new Button(0,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Hit");
		standButton = new Button(hitButton.getBounds().x+hitButton.getBounds().width,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT,buttonWidth,buttonHeight,"Stand");
		placeBet = new Button(Constants.ViewConstants.PANEL_WIDTH - Constants.ViewConstants.BUTTON_WIDTH,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT, buttonWidth, buttonHeight,"Place bet");
		clearBet = new Button(Constants.ViewConstants.PANEL_WIDTH - Constants.ViewConstants.BUTTON_WIDTH * 2,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT, buttonWidth, buttonHeight,"Clear bet");
		
		bet50 = new Button(Constants.ViewConstants.PANEL_WIDTH - Constants.ViewConstants.BUTTON_WIDTH * 3,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT, buttonWidth, buttonHeight,"Bet 50");
		bet10 = new Button(Constants.ViewConstants.PANEL_WIDTH - Constants.ViewConstants.BUTTON_WIDTH * 4,Constants.ViewConstants.PANEL_HEIGHT-Constants.ViewConstants.BUTTON_HEIGHT, buttonWidth, buttonHeight,"Bet 10");
		
		//top menu
		resetButton = new Button(Constants.ViewConstants.PANEL_WIDTH-Constants.ViewConstants.BUTTON_WIDTH,0,buttonWidth,buttonHeight,"Reset");
		returnToMenuButton = new Button(0,0,buttonWidth,buttonHeight,"Menu");
		
		menuButtons.add(exitButton);
		menuButtons.add(startButton);
		
		gameButtons.add(hitButton);
		gameButtons.add(standButton);
		gameButtons.add(resetButton);
		gameButtons.add(returnToMenuButton);
		gameButtons.add(bet10);
		gameButtons.add(bet50);
		gameButtons.add(clearBet);
		gameButtons.add(placeBet);
		
		
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
		g.setColor(new Color(188,186,217));
		g.fillRect(betPotMessageZone.x, betPotMessageZone.y, betPotMessageZone.width, betPotMessageZone.height/2);
		g.setColor(new Color(156,154,177));
		g.fillRect(playerBankMessageZone.x, playerBankMessageZone.y, playerBankMessageZone.width, playerBankMessageZone.height/2);
		
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("system", Font.BOLD, 15));
		
		
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		Rectangle2D topMenuStringBounds = g2d.getFontMetrics().getStringBounds(topMenuString, g2d);
		g2d.drawString(topMenuString, (int) (Constants.ViewConstants.PANEL_CENTER_X - topMenuStringBounds.getWidth()/2), (int) (topMenuStringBounds.getHeight()/2) + topMenu.height/2);
		g2d.setFont(new Font("system", Font.BOLD, 25));
		Rectangle2D betPotStringBounds = g2d.getFontMetrics().getStringBounds(betPotMessageString,g2d);
		g2d.drawString(betPotMessageString, (int) (betPotMessageZone.getCenterX() - betPotStringBounds.getWidth()/2), (int) (betPotMessageZone.getCenterY() - betPotStringBounds.getHeight()/2));
		g2d.setFont(new Font("system", Font.BOLD, 25));
		Rectangle2D playerBankStringBounds = g2d.getFontMetrics().getStringBounds(playerBankMessageString,g2d);
		g2d.drawString(playerBankMessageString, (int) (playerBankMessageZone.getCenterX() - playerBankStringBounds.getWidth()/2), (int) (playerBankMessageZone.getCenterY() - playerBankStringBounds.getHeight()/2));
		
		
		
		
	}
	
	public void paintTableElements(Graphics g) {
		
		if(drawCards) {
			for(Renderable r : cardsOnScreen) {
				r.draw(g);
				g.setColor(new Color(102,153,153));
				g.fillRect(dealerHandValueMessageZone.x, dealerHandValueMessageZone.y, dealerHandValueMessageZone.width, dealerHandValueMessageZone.height/2);
				g.fillRect(playerHandValueMessageZone.x, playerHandValueMessageZone.y, playerHandValueMessageZone.width, playerHandValueMessageZone.height/2);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setFont(new Font("system", Font.BOLD, 25));
				g2d.setColor(Color.BLACK);
				g2d.setRenderingHint(
				        RenderingHints.KEY_TEXT_ANTIALIASING,
				        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				Rectangle2D playerHandValueMessageBounds = g2d.getFontMetrics().getStringBounds(playerHandValueMessage, g2d);
				g2d.drawString(playerHandValueMessage, (int) (playerHandValueMessageZone.getCenterX() - playerHandValueMessageBounds.getWidth()/2), (int) (playerHandValueMessageZone.getCenterY() - playerHandValueMessageBounds.getHeight()/2));
			}
		}	
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

	@Override
	public void update(BetPotMessage betPotMessage) {
		betPotMessageString = betPotMessage.getMessage();
		notifyGuiObservers();
		
	}
	
	@Override
	public void update(MenuMessage menuMessage) {
		topMenuString = menuMessage.getMessage();
		notifyGuiObservers();
		
	}

	@Override
	public void update(PlayerBankMessage playerBankMessage) {
		playerBankMessageString = playerBankMessage.getMessage();
		notifyGuiObservers();
		
	}
	
	@Override
	public void update(HandValueMessage handValueMessage) {
		if(handValueMessage instanceof PlayerHandValueMessage) {
			this.playerHandValueMessage = handValueMessage.getBoilerPlateMessage() + handValueMessage.getMessage();
		} else if(handValueMessage instanceof DealerHandValueMessage) {
			this.dealerHandValueMessage = handValueMessage.getBoilerPlateMessage() + handValueMessage.getMessage();
		}
		
	}

	public void setDrawCards(boolean drawCards) {
		this.drawCards = drawCards;
	}
	
	@SuppressWarnings("unchecked")
	public <T> void updateRenderableCards(List<T> cards) {
		
		cardsOnScreen.addAll((Collection<? extends Renderable>) cards);
		notifyGuiObservers();
	}
	
	public void init() {
		drawCards = false;
		cardsOnScreen.clear();
		notifyGuiObservers();
	}

	
	
	}

