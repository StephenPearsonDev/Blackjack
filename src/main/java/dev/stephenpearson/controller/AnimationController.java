package dev.stephenpearson.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.view.GameWindow;

public class AnimationController implements ActionListener {
	
	private Card cardToAnimate;
	private Point cardOrigin;
	private Point cardDestination;
	private GameWindow gameWindow;
	private TableController tableController;
	
	
	private int targetX;
	private Timer timer;
	private float start = 0f;
	private float end = 400f;
	private float duration = 1000f;
	private float currentTime = 0f;
	private boolean moved = false;
	
	Point startP = new Point(700, 100);
	Point endP = new Point(100, 500);
	private int distance;
	
	public AnimationController(GameWindow gameWindow, TableController tableController) {
		this.gameWindow = gameWindow;
		this.tableController = tableController;
		timer = new Timer(1, this);
	}
	
	
	public void startTimer() {
			System.out.println("timer started");
			
			timer.start();
			
	}
	
	public void animateCard(Card cardToAnimate, Point destination) {
		
		System.out.println("animating");
		System.out.println(cardToAnimate.getCardString());
		this.cardToAnimate = cardToAnimate;

		cardOrigin = cardToAnimate.getCornerPoint();
		cardDestination = destination;
		startTimer();
		
		
		
	}
	
	public static Point easeInOutCubicPoint(float t, Point b, Point c, float d) {
		  t /= d/2;
		  if (t < 1) return new Point((int)(c.x/2*t*t*t + b.x), (int)(c.y/2*t*t*t + b.y));
		  t -= 2;
		  return new Point((int)(c.x/2*(t*t*t + 2) + b.x), (int)(c.y/2*(t*t*t + 2) + b.y));
		}

	public void anCard() {
		
		int durationDelta;
		int distanceDelta;
		distance = 100;

		if(currentTime < duration ) {

			//float easedValue = easeInOutCubic(currentTime, start, end - start, duration);
			Point easedValue = easeInOutCubicPoint(currentTime, cardOrigin, new Point(cardDestination.x - cardOrigin.x, cardDestination.y - cardOrigin.y), duration);
			
			//cardList.get(0).slide(easedValue);
			
			cardToAnimate.slidePoint(easedValue);
			currentTime += 50;
			gameWindow.repaint();
	} else {
		System.out.println("timer stopped");
		timer.stop();
		currentTime = 0;
	}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		anCard();
		
	}

}
