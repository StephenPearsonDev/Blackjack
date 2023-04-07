package dev.stephenpearson.controller;

import java.util.Collections;
import java.util.Stack;

import dev.stephenpearson.model.Card;
import dev.stephenpearson.model.Deck;

public class DeckBuilder {
    private Stack<Card> cards;

    public DeckBuilder() {
    	
        
        
    }

    public DeckBuilder generateStandardDeck() {
    	cards = new Stack<Card>();
        for (Card.Suit s : Card.Suit.values()) {
            for (Card.Rank r : Card.Rank.values()) {
                if (r != Card.Rank.ACE_ONE) {
                    cards.add(new Card(s, r));
                }
            }
        }
        return this;
    }

    public DeckBuilder shuffle() {
        Collections.shuffle(cards);
        return this;
    }

    public Deck build() {
        return new Deck(cards);
    }
}
