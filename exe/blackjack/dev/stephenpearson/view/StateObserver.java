package dev.stephenpearson.view;

import dev.stephenpearson.model.State;

public interface StateObserver {
	    void onStateChanged(State newState);
	}



