package dev.stephenpearson.controller;

import dev.stephenpearson.model.MenuMessage;

public interface BetPotMessageObserver {
	void update(BetPotMessage betPotMessage);
}
