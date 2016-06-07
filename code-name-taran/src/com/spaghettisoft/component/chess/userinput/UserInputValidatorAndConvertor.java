package com.spaghettisoft.component.chess.userinput;

import com.spaghettisoft.component.chess.board.ChessBoard;
import com.spaghettisoft.component.chess.players.Move;
import com.spaghettisoft.component.chess.players.Player;

public abstract class UserInputValidatorAndConvertor {

	
	
	private static final int MAX_INPUT_LENGHT = 4;
	private static final String REGEX_ALLOWED_ONLY_WORDS_AND_SPACES = "^\\p{L}+(?: \\p{L}+)*$";
	private static final String WHITE_SPACE_REGEX = "\\s+"; 
	private static final String EMPTY_STRING = "";
	
	public static Move convertToCordinate(String userInput) {
		String[] splitInput = processingInput(userInput);
		Move move = fillWithCordinated(splitInput);
		return move;
	}

	private static Move fillWithCordinated(String[] splitInput) {

		int fromColumn = convertedLetterToInt(splitInput[0]);
		int fromRow = ChessBoard.CHESS_BOARD_SIZE - Integer.parseInt(splitInput[1]);
		int toColumn = convertedLetterToInt(splitInput[2]);
		int toRow = ChessBoard.CHESS_BOARD_SIZE - Integer.parseInt(splitInput[3]);

		Move move = new Move(fromRow, fromColumn, toRow, toColumn);

		return move;
	}
	
	private static int convertedLetterToInt(String string) {
		
		switch (string.charAt(0)) {
		case 'A':
			return 0;
		case 'B':
			return 1;
		case 'C':
			return 2;
		case 'D':
			return 3;
		case 'E':
			return 4;
		case 'F':
			return 5;
		case 'G':
			return 6;
		default:
			return 7;
		}
		
	}

	public static boolean isValidInput(String userInput) {

		 String[] splitInput = processingInput(userInput);
		
		if (splitInput.length != MAX_INPUT_LENGHT) {
			return false;
		}
		
		if ( !(IsLetters(splitInput) && IsNumbers(splitInput)) ) {
			return false;
		}
		
		if (!IsInputInRange(splitInput)) {
			return false;
		}
	
		return true;
	}

	private static boolean IsInputInRange(String[] splitInput) {
		
		if (0 < splitInput[1].charAt(0)
				&& splitInput[1].charAt(0) <= ChessBoard.columsLabel.length) {
			System.out.println("parvata cifra");
			return false;
		}

		if (0 < splitInput[3].charAt(0)
				&& splitInput[3].charAt(0) <= ChessBoard.columsLabel.length) {
			System.out.println("vtorata  cifra");
			return false;
		}
		
		
		if ( splitInput[0].charAt(0) < ChessBoard.columsLabel[0].charAt(0)  
				|| splitInput[0].charAt(0) > ChessBoard.columsLabel[ChessBoard.CHESS_BOARD_SIZE-1].charAt(0)) {
			System.out.println("purvata bukva");
			return false;
		}
		
		if ( splitInput[2].charAt(0) < ChessBoard.columsLabel[0].charAt(0) 
				|| splitInput[2].charAt(0) > ChessBoard.columsLabel[ChessBoard.CHESS_BOARD_SIZE-1].charAt(0)) {
			System.out.println("vtorata bukva");
			return false;
		}
		
		return true;
	}

	private static String[] processingInput(String input) {
		String InputWithoutWhiteSpases = clearAllWhiteSpaces(input);
		String inpuToUpperCase = InputWithoutWhiteSpases.toUpperCase();

		String[] splitInput = new String[inpuToUpperCase.length()];
		for (int i = 0; i < splitInput.length; i++) {
			splitInput[i] = inpuToUpperCase.substring(i, i+1);
		}
		
		return splitInput;
	}

	public static String clearAllWhiteSpaces(String input) {
		String inputWithoutWhiteSpases = input.replaceAll(WHITE_SPACE_REGEX, EMPTY_STRING);
		return inputWithoutWhiteSpases;
	}
	
	private static boolean IsLetters(String[] splitInput) {
		boolean isFirsLetter = Character.isLetter(splitInput[0].charAt(0));
		boolean isSecondLetter = Character.isLetter(splitInput[2].charAt(0));
		boolean result = isFirsLetter && isSecondLetter;
		
		return result;
	}
	
	private static boolean IsNumbers(String[] splitInput) {
		boolean isFirsNumeric = Character.isDigit(splitInput[1].charAt(0));
		boolean isSecondNumeric = Character.isDigit(splitInput[3].charAt(0));

		boolean result = isFirsNumeric && isSecondNumeric;
		
		return result;	
	}
	

		public static boolean validatePlayerName(String name) {
			boolean result = false;
				if (name.matches(REGEX_ALLOWED_ONLY_WORDS_AND_SPACES) ) {
					result = true;
				}
				return result;
			}
	

}
