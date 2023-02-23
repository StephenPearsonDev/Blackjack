package dev.stephenpearson.model;

import java.awt.Rectangle;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TextAreas {
	
	private static HashMap<String, JLabel> textAreas = new HashMap<>();
	
	private Rectangle bankRectangle;
	private PlayerBank playerBank;
	private JTextArea bankArea;
	private JLabel bankLabel;
	
	public TextAreas(PlayerBank playerBank) {
		this.playerBank = playerBank;
		initTextAreas();
		
	}
	
	public void initTextAreas() {
		System.out.println("text area made");
		bankRectangle = new Rectangle(625,550,150,40);
		bankLabel = new JLabel("Bank account: "+playerBank.getBankString(),SwingConstants.CENTER);
		bankLabel.setBounds(bankRectangle);
		bankLabel.setOpaque(true);
		bankArea = new JTextArea(playerBank.getBankString());
		bankArea.setBounds(bankRectangle);
		textAreas.put("BankArea", bankLabel);
	}
	
	public void updateTextAreas() {
		bankLabel.setText(playerBank.getBankString());
	}
	
	public HashMap<String, JLabel> getTextAreasMap() {
		return textAreas;
	}

}
