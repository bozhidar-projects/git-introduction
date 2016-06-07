package com.spaghettisoft.component.scrabble;



import java.util.Scanner;

import com.spaghettisoft.component.game.AbstractGame;
import com.spaghettisoft.globals.StaticObjects;

public class ScrabbleGame extends AbstractGame {

/*
	  while (!isEnded()) {
          drawGame();
          processGame();
      }
      printEndGameMessage();
*/

	Scanner scanner;

	@Override
	protected void printEndGameMessage() {
		System.out.println(Game.endGameMessage);
	}

	@Override
	protected void drawGame() {
		// TODO Auto-generated method stub
	}

	@Override
	protected boolean isEnded() {
		return Game.endGame;
	}

	@Override
	protected void processGame() {
		GameMenu menu = new GameMenu();// TODO Auto-generated method stub
		menu.startNewGame(StaticObjects.scanner);
	}

}
