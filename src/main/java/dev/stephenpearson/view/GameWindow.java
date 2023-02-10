package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GameWindow extends JPanel{
	
	private static final int PANEL_WIDTH = 1000;
	private static final int PANEL_HEIGHT = 600;
	
	public GameWindow() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	
	public void render() {
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(78, 115, 96));
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
	}

}
