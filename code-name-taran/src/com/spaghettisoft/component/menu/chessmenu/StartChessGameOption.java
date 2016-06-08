package com.spaghettisoft.component.menu.chessmenu;

import com.spaghettisoft.component.chess.chessgame.ChessGame;
import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.userinput.UserInputValidatorAndConvertor;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.globals.StaticObjects;

public class StartChessGameOption implements MenuItem {
	private static final String START_GAME_OPTION = "Start Game";
	
	public StartChessGameOption() {
	}

	@Override
	public void select() {
		System.out.println("=====> CHESS GAME <=====");
		
		String whitePlayerName = askPlayerForName(Color.WHITE);
		String blackPlayerName = askPlayerForName(Color.BLACK);

		ChessGame chessGame = new ChessGame(whitePlayerName, blackPlayerName);
		chessGame.show();
	}
	
	private String askPlayerForName(Color color) {
		String userInput;

		while (true) {
			System.out.print(color + " Player please enter your name:\n ");
			userInput = StaticObjects.scanner.nextLine();
			if (UserInputValidatorAndConvertor.validatePlayerName(userInput)) {
				break;
			}
			System.out.println("Invalid name. Try again.\n");
		}

		return userInput;
	}

	@Override
	public String getLabel() {
		return START_GAME_OPTION;
	}

}
