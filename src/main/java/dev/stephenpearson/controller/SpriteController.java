package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.List;

import dev.stephenpearson.model.SpriteSheet;

public class SpriteController {
	
	private List<SpriteSheet> spriteSheetList = new ArrayList<>();
	
	
	public SpriteController() {
		
		
	}
	
	public void addSpriteSheet(SpriteSheet spriteSheet) {
		if(!spriteSheetList.contains(spriteSheet)) {
			spriteSheetList.add(spriteSheet);
		}
		
		
	}
	
	

}
