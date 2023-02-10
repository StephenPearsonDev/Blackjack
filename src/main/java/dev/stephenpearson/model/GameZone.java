package dev.stephenpearson.model;

import java.awt.Color;
import java.awt.Rectangle;

public class GameZone {
	
	private Zone zone;
	private Rectangle zoneRect;
	private Color zoneColor;
	
	
	
	public GameZone(String zoneName) {
		
		zone = Zone.valueOf(zoneName);
		zoneRect = new Rectangle(zone.x, zone.y, zone.w, zone.h);
		zoneColor = zone.color;
	}
	
	public Rectangle getZoneRectangle() {
		return zoneRect;
	}
	
	public Color getZoneColor() {
		return zoneColor;
	}
	
	enum Zone {
		
		DEALER_HAND("dealerHand",100,100,100,100, new Color(153, 153, 255)),
		PLAYER_HAND("playerHand",100,100,100,100, new Color(153, 153, 255)),
		DECK("deck",500,0,100,100, new Color(153, 153, 255)),
		BURNT_CARDS("burntCards",100,100,100,100, new Color(153, 153, 255)),
		BET_CHIPS("betChips",100,100,100,100, new Color(153, 153, 255)),
		CHIP_STACK("chipStack",100,100,100,100, new Color(153, 153, 255));
		
		private String zoneName;
		private int x, y, w, h;
		private Color color;
		
		private Zone(String zoneName, int x, int y, int w, int h, Color color) {
			this.zoneName = zoneName;
			
		}
		
		public String getZoneName() {
			return zoneName;
		}
		
		
		
	}

}
