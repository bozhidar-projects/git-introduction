package com.spaghetti.component.game;

import com.spaghettisoft.component.Component;

public abstract class AbstractGame implements Component {

	public void show() {
		while (!isEnded()) {
			drawGame();
			processGame();
		}
		printEndGameMessage();
	}


	protected abstract boolean isEnded();

	protected abstract void drawGame();

	protected abstract void processGame();

	protected abstract void printEndGameMessage();

}
