package dev.stephenpearson.model;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class HandHolder {
	
	private List<CardHolder> cardHolders;
	private int holderPointer = 0;
	private Point handHolderCenterPoint;
	
	
	public HandHolder(Point zoneCenterPoint) {
		
			handHolderCenterPoint = zoneCenterPoint;
	}
	
	public void addNewHandLocation(CardHolder cardHolder) {

		cardHolders.add(cardHolder);
		holderPointer++;
	}

}
