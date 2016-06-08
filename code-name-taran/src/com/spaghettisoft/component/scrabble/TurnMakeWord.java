package com.spaghettisoft.component.scrabble;

import java.util.ArrayList;
import java.util.Scanner;

public class TurnMakeWord {

	Scanner scanner;
	String word;
	Stave stave;

	TurnMakeWord(Scanner scanner) {
		this.scanner = scanner;
		word = "";
		stave = new Stave('h', 0, 0);
	}

	public void placeWord(Player player, Board board, Bag bag) {

		askPlayerToInsertValidWordAndCoordinates(player,board);
		int wordPoints = drawWord(player, board, bag);
		player.increasePoints(wordPoints);
		setMultipliersTo1(word, stave.getRow(), stave.getCol(), stave.getCh(), board);

		System.out.println(player.getName() + " has " + player.getPoints() + " points.\n");
	}

	private void setMultipliersTo1(String word, int row, int col, char orientation, Board board) {
		for (int i = 0; i < word.length(); i++) {
			if(orientation == 'h') {
				board.getTiles()[row-1][col-1+i].setMultiplierLetter(1);
				board.getTiles()[row-1][col-1+i].setMultiplierWord(1);
			} else {
				board.getTiles()[row-1+i][col-1].setMultiplierLetter(1);
				board.getTiles()[row-1+i][col-1].setMultiplierWord(1);
			}
		}
	}

	private void askPlayerToInsertValidWordAndCoordinates(Player player, Board board) {
		boolean isWordOk = false;
		while(!isWordOk) {
			String playerHas = Checks.takeLettersPlayerHasInHand(player);
			System.out.println("The letters you have are: " + playerHas);
			Stave stave = getValidWordCoordinatesAndOrientation(scanner);
			word = Checks.checkIfWordContainsOnlyLetters(scanner, player);
			word = Constants.makeLowercasesCharsInWordUppercases(word);
			isWordOk = WordValidation.checkIfWordIsValidAndCouldBePlacedOnTheBoard(word, stave.getRow(), stave.getCol(), stave.getCh(), board, player);
		}
	}

	private int drawWord(Player player, Board board, Bag bag) {
		ArrayList<Stave> memoryStick = WordValidation.fillMemoryStick(word, stave.getRow(), stave.getCol(), stave.getCh(), board);
		String playerLetters = Checks.takeLettersPlayerHasInHand(player);

		writeTheWordOnTheBoard(memoryStick, playerLetters, board);
//=============================================================================================================================================
		String lettersThatRemainInPlayer = trimmLettersFromPlayerLetters(memoryStick, playerLetters, board);


		if(lettersThatRemainInPlayer.equals("")) {
			player.increasePoints(50);
			System.out.println("Player used all 7 letters that has: +50 bonus points.");
		}

		String lettersTakyngSomeLettersFormTheBag = playerTakesNewLettersFromTheBag(lettersThatRemainInPlayer, bag);
		ArrayList<Letter> letters = new ArrayList<>();
		for (int i = 0; i < lettersTakyngSomeLettersFormTheBag.length(); i++) {
			char ch = lettersTakyngSomeLettersFormTheBag.charAt(i);
			int points = Constants.checkPointsForLetter(ch);
			Letter letter = new Letter(ch, points, 0);
			letters.add(letter);
		}
		player.setLetters(letters);
		playerLetters = Checks.takeLettersPlayerHasInHand(player);
//=============================================================================================================================================

		int wordPoints = calculatePointsThePlayerMade(word, stave.getRow(), stave.getCol(), stave.getCh(), board);
		System.out.println("Word - " + word + ", points - " + wordPoints);

		return wordPoints;
	}

	private int calculatePointsThePlayerMade(String word, int row, int col, char orientation, Board board) {
		int wordPoints = 0;
		int multiplierWord = 1;
		for (int i = 0; i < word.length(); i++) {
			char ch = ' ';
			int points = Constants.checkPointsForLetter(word.charAt(i));
			int multiplierLetter = 0;
			if(orientation == 'h') {
				multiplierLetter = board.getTiles()[row-1][col-1+i].getMultiplierLetter();
				ch = board.getTiles()[row-1][col-1+i].getContent().charAt(2);
			} else {
				multiplierLetter = board.getTiles()[row-1+i][col-1].getMultiplierLetter();
				ch = board.getTiles()[row-1+i][col-1].getContent().charAt(2);
			}
			if(ch == '!') {
				points = 0;
			}
			wordPoints += points*multiplierLetter;
		}
		for (int i = 0; i < word.length(); i++) {
			int multiplier = 1;
			if(orientation == 'h') {
				multiplier = board.getTiles()[row-1][col-1+i].getMultiplierWord();
			} else {
				multiplier = board.getTiles()[row-1+i][col-1].getMultiplierWord();
			}
			multiplierWord *= multiplier;
		}
		wordPoints *= multiplierWord;
		return wordPoints;
	}

	private String playerTakesNewLettersFromTheBag(String playerLetters, Bag bag) {
		Letter letter = new Letter('!', 0, 0);
		int number = 7 - playerLetters.length();
		for (int i = 0; i < number; i++) {
			if(!bag.isBagEmpty()) {
				letter = bag.takeRandomLetterFromTheBag();
				playerLetters += letter.getCh();
			} else {
				break;
			}
		}
		return playerLetters;
	}

	private String trimmLettersFromPlayerLetters(ArrayList<Stave> memoryStick, String playerLetters, Board board) {
		String lettersForTrim = "";
		for (int i = 0; i < memoryStick.size(); i++) {
			char ch = 'a';
			if(board.getTiles()[memoryStick.get(i).getRow()][memoryStick.get(i).getCol()].getContent().charAt(2) == '!') {
				ch = '?';
			} else {
				ch = memoryStick.get(i).getLetter();
			}
			lettersForTrim += ch;
		}
		for (int i = 0; i < lettersForTrim.length(); i++) {
			char ch = lettersForTrim.charAt(i);
			String newString = WordValidation.trimmCharFromString(playerLetters, ch);
			playerLetters = newString;
		}
		return playerLetters;
	}

	private void writeTheWordOnTheBoard(ArrayList<Stave> memoryStick, String playerLetters, Board board) {
		for (int i = 0; i < memoryStick.size(); i++) {
			int row = memoryStick.get(i).getRow();
			int col = memoryStick.get(i).getCol();
			char ch = memoryStick.get(i).getLetter();
			board.getTiles()[row][col].setContent(" " + ch + " ");

			System.out.println();

			if(Checks.checkIfStringContainsLetter(playerLetters, ch)) {
				WordValidation.trimmCharFromString(playerLetters, ch);
			} else {
				WordValidation.trimmCharFromString(playerLetters, '?');
				char ch1 = board.getTiles()[row][col].getContent().charAt(1);
				String newContent =  " " + ch1 + '!';
				board.getTiles()[row][col].setContent(newContent);
			}
		}
		board.drawBoard();
	}

	private Stave getValidWordCoordinatesAndOrientation(Scanner scanner) {
		System.out.println("Please insert \"h\" for horizontal word or \"v\" for vertical one:");
		char[] chars = {'h', 'v'};
		char orientation = Checks.getValidCharOption(chars, scanner);
		System.out.println("Please insert number of row of the tile where the first letter of the word should be placed:");
		int row = Checks.getValidRowOrColumnOption(scanner);
		System.out.println("Please insert number of column of the first letter:");
		int col = Checks.getValidRowOrColumnOption(scanner);
		stave.setCh(orientation);
		stave.setRow(row);
		stave.setCol(col);

		return new Stave(orientation, row, col);
	}

}
