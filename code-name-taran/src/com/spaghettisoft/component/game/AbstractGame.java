package com.spaghettisoft.component.game;

import com.spaghettisoft.component.Component;

public abstract class AbstractGame implements Component {
    
	@Override
    public void show() {
		
        while (!isEnded()) {
            drawGame();
            processGame();
        }
        printEndGameMessage();
    }

    protected abstract void printEndGameMessage();

    protected abstract void drawGame();

    protected abstract boolean isEnded();

    protected abstract void processGame();
}