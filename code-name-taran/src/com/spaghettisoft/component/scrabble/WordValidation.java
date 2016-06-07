package com.spaghettisoft.component.scrabble;

import java.util.ArrayList;

public class WordValidation {

	public static boolean checkIfWordIsValidAndCouldBePlacedOnTheBoard(String word, int row, int col, char orientation, Board board, Player player) {
		boolean fits = checkIfWordFitsInTheBoard(word, row, col, orientation);
		if(!fits) {
			System.out.println("Your word does not fit on the board.");
			return false;
		}
		boolean wordFitsOnTheTiles = checkIfWordFitsWellOnTheTiles(word, row, col, orientation, board);
		if(!wordFitsOnTheTiles) {
			System.out.println("Your word does not match another word's letter or does not lay on the '@' if first word on the board.");
			return false;
		}
		boolean areEnoughLEtters = checkIfPlayerHasEnoughLetters(word, row, col, orientation, board, player);
		if(!areEnoughLEtters) {
			System.out.println("Your do not have all the necessary letters for the word you wanna play.");
			return false;
		}
		return true;
	}

	private static boolean checkIfWordFitsInTheBoard(String word, int row, int col, char orientation) {
		if(orientation == 'h' && (col + word.length()) > 16) {
			return false;
		} else if (orientation == 'v' && (row + word.length()) > 16) {
			return false;
		}
		return true;
	}

	private static boolean checkIfWordFitsWellOnTheTiles(String word, int row, int col, char orientation, Board board) {
		Tile[] tiles = tilesOnWhichWordShouldBePlaced(word, row, col, orientation, board);
		if(board.getTiles()[7][7].getContent().charAt(1) == '@') {
			int counter = 0;
			for (int i = 0; i < tiles.length; i++) {
				if(tiles[i].getContent().charAt(1) == '@') {
					counter++;
					break;
				}
			}
			if (counter == 0) {
				return false;
			}
		} else {
			int counter = 0;
			for (int i = 0; i < tiles.length; i++) {
				if(Character.isLetter(tiles[i].getContent().charAt(1))) {
					counter++;
					char contentChar = tiles[i].getContent().charAt(1);
					char wordChar = Constants.makeTheLowercaseBecomeUppercase(word.charAt(i));
					if(contentChar != wordChar) {
						return false;
					}
				}
			}
			if(counter == 0) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkIfPlayerHasEnoughLetters(String word, int row, int col, char orientation, Board board, Player player) {
		ArrayList<Stave> memoryStick = fillMemoryStick(word, row, col, orientation, board);
		String playersLetters = Checks.takeLettersPlayerHasInHand(player);
		boolean hasAllLetters = hasThePlayerAllTheNecessaryLetters(memoryStick, playersLetters);
		return hasAllLetters;
	}

	private static boolean hasThePlayerAllTheNecessaryLetters(ArrayList<Stave> memoryStick, String playersLetters) {
		String newStringPlayersLetters = Constants.copyStringToAnotherString(playersLetters);
		for (int i = 0; i < memoryStick.size(); i++) {
			char ch = memoryStick.get(i).getLetter();
			boolean hasLetter = Checks.checkIfStringContainsLetter(newStringPlayersLetters, ch);
			boolean hasWildcard = Checks.checkIfStringContainsLetter(newStringPlayersLetters, '?');

			if(!hasLetter && !hasWildcard) {
				return false;
			}
			if(hasLetter) {
				String newString = trimmCharFromString(newStringPlayersLetters, ch);
				newStringPlayersLetters = newString;
			} else {
				String newString = trimmCharFromString(newStringPlayersLetters, '?');
				newStringPlayersLetters = newString;
			}
		}
		return true;
	}

	private static Tile[] tilesOnWhichWordShouldBePlaced(String word, int row, int col, char orientation, Board board) {
		Tile[] tiles =  new Tile[word.length()];
		if(orientation == 'h') {
			for (int i = 0; i < tiles.length; i++) {
				tiles[i] = new Tile(board.getTiles()[row-1][col-1+i].getContent(), board.getTiles()[row-1][col-1+i].getEmpty(), 1, 1);
			}
		} else {
			for (int i = 0; i < tiles.length; i++) {
				tiles[i] = new Tile(board.getTiles()[row-1+i][col-1].getContent(), board.getTiles()[row-1+i][col-1].getEmpty(), 1, 1);
			}
		}
		return tiles;
	}

	public static ArrayList<Stave> fillMemoryStick(String word, int row, int col, char orientation, Board board) {
		ArrayList<Stave> memoryStick = new ArrayList<>();
		if(orientation == 'h') {
			for (int i = 0; i < word.length(); i++) {
				boolean isEmpty = Checks.checkIfTileIsEmpty(board.getTiles()[row-1][col-1+i]);
				if(isEmpty) {
					Stave stave = new Stave(board.getTiles()[row-1][col-1+i].getContent().charAt(1), row-1, col-1+i);
					stave.setLetter(word.charAt(i));
					memoryStick.add(stave);
				}
			}
		} else {
			for (int i = 0; i <word.length(); i++) {
				boolean isEmpty = Checks.checkIfTileIsEmpty(board.getTiles()[row-1+i][col-1]);
				if(isEmpty) {
					Stave stave = new Stave(board.getTiles()[row-1+i][col-1].getContent().charAt(1), row-1+i, col-1);
					stave.setLetter(word.charAt(i));
					memoryStick.add(stave);
				}
			}
		}
		return memoryStick;
	}

	public static String trimmCharFromString(String word, char ch) {
		String string = "";
		int index = 0;
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == ch) {
				index = i;
				break;
			}
		}
		for (int j = 0; j < word.length(); j++) {
			if(index != j) {
				string += word.charAt(j);
			}
		}
		return string;
	}

}
