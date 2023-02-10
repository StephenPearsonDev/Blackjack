package dev.stephenpearson.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.List;

import javax.swing.JPanel;

import dev.stephenpearson.model.RenderObject;

public class GameWindow extends JPanel{
	
	private static final int PANEL_WIDTH = 600;
	private static final int PANEL_HEIGHT = 800;

	
	public GameWindow() {
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
	}
	
	public void render() {
	
		repaint();
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(78, 115, 96));
		//Graphics2D g2d = (Graphics2D) g;
		
		
		//Color[] colors = {new Color(78, 115, 96), new Color(36, 41, 39)};
		//Point2D point = new Point2D.Float(300,400);
		//float[] dist = {0.0f, 0.2f, 1.0f};
		//RadialGradientPaint rgp1 = new RadialGradientPaint(point,0.5f * 300,dist,colors);
		//GradientPaint gp1 = new GradientPaint(0, 0, new Color(78, 115, 96), 600, 800, new Color(36, 41, 39));  
		 //g2d.setPaint(gp1);  
		//g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
		
	}

}
