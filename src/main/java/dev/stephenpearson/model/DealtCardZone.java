package dev.stephenpearson.model;

import java.awt.Point;
import java.awt.Rectangle;

public class DealtCardZone extends GameZone {
	
	private Rectangle[] dealZones = new Rectangle[7];
	private CardHolder[] cardHolders = new CardHolder[12];
	private Point zonePoint;
	private Point zoneCenterPoint;
	private int holderPointer = 0;
	
	

	public DealtCardZone(String zoneName) {
		super(zoneName);
		zonePoint = super.getZoneRectangle().getLocation();
		zoneCenterPoint = super.getZoneCenterPoint();
		
		initCardZones();
		
	}
	
	public void initCardZones() {
		
		int pointer1 = 0;
		int pointer2 = 0;
		for(int i = 0; i < cardHolders.length; i++) {
			
			if(i < 6) {
				cardHolders[i] = new CardHolder(zonePoint.x + 30 + (80 * i), zonePoint.y + 10 + (0 * i));
			} else {
				cardHolders[i] = new CardHolder(zonePoint.x + 30 + (80 * pointer2++), zonePoint.y + 50 + (0 * pointer2));
			
			}
			
			//dealZones[i] = new Rectangle(zonePoint.x + 20 + (150 * i), zonePoint.y + 50);
			
		}
		
	}
	
	public Rectangle[] getDealZones() { 
		return dealZones;
	}
	
	public CardHolder getNextZone() {
		
		if(holderPointer < cardHolders.length) {
			return cardHolders[holderPointer++];
		} else {
			System.out.println("no empty zones left");
			
			return null;
		}
		
	}
	
	public int getHolderPointer() {
		return holderPointer;
	}

}
