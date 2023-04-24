package dev.stephenpearson.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;


public class SpriteController {

	private static Map<String, BufferedImage> cardImageMap = new LinkedHashMap<>();

	private String[] suitNames = new String[] {"HEARTS","DIAMONDS","SPADES","CLUBS"};
	private String[] cardValues = new String[] {"ACE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", 
												"NINE","TEN","JACK","QUEEN","KING"};

	public SpriteController() {
		processCardSheet();
	}
	
	public void processCardSheet() {
	
			//BufferedImage cardSheet = ImageIO.read(new File("src/main/java/res/cards.png"));
			//BufferedImage cardSheet = ImageIO.read(getClass().getResource("/cards.png"));
			
		  try {
			  InputStream is = getClass().getResourceAsStream("/cards.png");
			  BufferedImage cardSheet = ImageIO.read(is);  
			 
	            int cardCounter = 0;
	            int counter = 0;
	            int width = 17;
	            int height = 26;
	            int offset = 1;
	            int y = 1;
	            int x = 19;
	                    
	            for(String s : suitNames) {
	                for(String v: cardValues) {
	                    
	                    BufferedImage c = cardSheet.getSubimage(x, y, width, height);
	                    cardImageMap.put(v+"OF"+s, c);
	                    counter++;
	                    x += 18;
	                    if(counter >= 13) {
	                        x = 19;
	                        counter = 0;
	                    }
	                    
	                    cardCounter++;
	                }
	                counter = 0;
	                y+=height+1;    
	            }
	            
	            BufferedImage cardBack = cardSheet.getSubimage(19, 109, width, height);
	            cardImageMap.put("CARDBACK", cardBack);
	            
	            
	        } catch(IOException e) {
	            System.out.println("file not found");
	            e.printStackTrace();
	        }
		
			

	
			
	}
	
	
		
	
	
	public static Map<String, BufferedImage> getCardImageMap() {
		return cardImageMap;
	}
	
	

}
