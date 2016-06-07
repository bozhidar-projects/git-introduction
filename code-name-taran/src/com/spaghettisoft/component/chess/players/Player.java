package com.spaghettisoft.component.chess.players;

import com.spaghettisoft.component.chess.figures.AbstractFigure.Color;
import com.spaghettisoft.component.chess.userinput.UserInputValidatorAndConvertor;
import com.spaghettisoft.globals.StaticObjects;

public class Player {
	
	
	private String name;
	private Color color;
	
	private static final String KEY_WORD_SURRENDER = "surrender";
	private static final String KEY_WORD_REMI = "draw";
	private static final String FLASE = "false";
	private static final String TRUE = "true";
	
	public Player(Color color, String name) {
		this.color = color;
		this.name = name;	
	}
	
	private boolean isPlayerSurrender(String userInput) {
		String trimString = UserInputValidatorAndConvertor.clearAllWhiteSpaces(userInput);
		String toLowerCase = trimString.toLowerCase();
		boolean result = toLowerCase.equals(KEY_WORD_SURRENDER);
		return result;
	}
	
	private boolean isPlayerAskRemi(String userInput) {
		String trimString = UserInputValidatorAndConvertor.clearAllWhiteSpaces(userInput);
		String toLowerCase = trimString.toLowerCase();
		boolean result = toLowerCase.equals(KEY_WORD_REMI);
		return result;
	}
	
	private boolean askOtherPlayerForRemy(Player player, String userInput) {
		boolean draw = false; 
		String îtherPlayerInput;
		Color îtherPlayerColor;
		
		System.out.println(player.getColor() + " player ask draw ");
		
		  if (player.getColor().equals(Color.WHITE)) {
			  îtherPlayerColor = Color.BLACK;
		  } else {
			  îtherPlayerColor = Color.WHITE; 
		  }
		  
			while (true) {
				System.out.println(îtherPlayerColor + " player please answer with true or false");
				
				îtherPlayerInput = StaticObjects.scanner.nextLine();
				String trimString = UserInputValidatorAndConvertor.clearAllWhiteSpaces(îtherPlayerInput);
				String toLowerCase = trimString.toLowerCase();
				
				if (toLowerCase.equals(FLASE)) {
					System.out.println(îtherPlayerColor + " player rejects your request for draw");
					draw = false;
					break;
				} 
				if (toLowerCase.equals(TRUE)) {
					System.out.println(îtherPlayerColor + " player confirms your request for draw");
					draw = true;
					break;
				}
				
				System.out.println("Invalid answer. Try again.\n");
			}
		return draw;
	}
	

	public Move askForMove() {
		
		String userInput;
		Move move = null;
		
		while (true) {
			System.out.println(this.getColor() + " player on turn:");
			System.out.println(this.name + " please make a move:  For example A2 A3");
			userInput = StaticObjects.scanner.nextLine();
			if(isPlayerSurrender(userInput)) {
				return null;	
			}
			if (isPlayerAskRemi(userInput)) {
				if (askOtherPlayerForRemy(this, userInput)) {
					return null;
				} 
				continue;
			}
			if (UserInputValidatorAndConvertor.isValidInput(userInput)) {
				break;
			}
			
			System.out.println("Invalid Move. Try again.\n");
		}

		move = UserInputValidatorAndConvertor.convertToCordinate(userInput);

		return move;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	
}
