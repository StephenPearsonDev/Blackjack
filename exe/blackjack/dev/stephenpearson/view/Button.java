package dev.stephenpearson.view;

import java.util.Arrays;
import java.util.Optional;

import javax.swing.JButton;


public class Button extends JButton {
	

	private ButtonAction buttonAction;
	
	public Button(int x, int y, int width, int height, String buttonText) {
		super(buttonText);
		buttonAction = ButtonAction.getButtonAction(buttonText).orElse(null);
		setBounds(x, y, width, height);
	}
	
	public ButtonAction getButtonAction() {
		return buttonAction;
	}

	public enum ButtonAction {
	    EXIT("Exit"),
	    START("Start"),
	    HIT("Hit"),
	    STAND("Stand"),
	    RESET("Reset"),
	    BET10("Bet 10"),
	    BET50("Bet 50"),
	    PLACEBET("Place bet"),
	    CLEARBET("Clear bet"),
	    MENU("Menu");
		
		private String buttonText;
		
		ButtonAction(String buttonText) {
			this.buttonText = buttonText;
		}
		
		public static Optional<ButtonAction> getButtonAction(String buttonText) {
		
			return Arrays.stream(values())
                    .filter(action -> action.buttonText.equalsIgnoreCase(buttonText))
                    .findFirst();
		}
		
	}
	
	
	

}
