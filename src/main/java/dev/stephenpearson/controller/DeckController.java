package dev.stephenpearson.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dev.stephenpearson.model.Deck;
import dev.stephenpearson.model.Rank;
import dev.stephenpearson.model.Suit;

public class DeckController {
	
	Map<Integer, Deck> decks;
	
	public DeckController() {
		System.out.println("building deck");
		buildDecks(1);
	}
	
	public void buildDecks(int numOfDecks) {
		decks = new HashMap<>();
		for(int d = 0; d < numOfDecks; d++) {
			decks.put(Integer.valueOf(d), new Deck());
		}
	}
	
	public Map<Integer, Deck> getDecks() {

		return decks;
	}

}
