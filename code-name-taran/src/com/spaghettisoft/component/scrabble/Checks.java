package com.spaghettisoft.component.scrabble;

import java.util.Scanner;

public class Checks {

	public static String takeLettersPlayerHasInHand(Player player) {
		String lettersPlayerHasInHand = "";
		for (int i = 0; i < player.getLetters().size(); i++) {
			lettersPlayerHasInHand += player.getLetters().get(i).getCh();
		}
		return lettersPlayerHasInHand;
	}

	public static int getValidOption (char[] chars, Scanner scanner) {
		int number = 0;
		String option = scanner.nextLine();
		while(option.equals("")) {
			System.out.println("Please, insert a valid option:");
			option = scanner.nextLine();
		}
		char ch = option.charAt(0);
		boolean validOption = Checks.checkChar(chars, ch);
		while(!validOption) {
			System.out.println("Please, insert a valid option:");
			option = scanner.nextLine();
			ch = option.charAt(0);
			validOption = Checks.checkChar(chars, ch);
		}

		for (int i = 0; i < 5; i++) {
			String some = "" + i;
			char character = some.charAt(0);
			if(ch == character) {
				number = i;
			}
		}
		return number;
	}

	public static char getValidCharOption (char[] chars, Scanner scanner) {
		String option = scanner.nextLine();
		while(option.equals("")) {
			System.out.println("Please, insert a valid option:");
			option = scanner.nextLine();
		}
		char ch = option.charAt(0);
		boolean validOption = checkChar(chars, ch);
		while(!validOption) {
			System.out.println("Please, insert a valid option:");
			option = scanner.nextLine();
			ch = option.charAt(0);
			validOption = Checks.checkChar(chars, ch);
		}

		return ch;
	}

	public static int getValidRowOrColumnOption(Scanner scanner) {
		while(true) {
			String option = scanner.nextLine();
			while(option.equals("")) {
				System.out.println("Please insert a valid integer between 1 and 15:");
				option = scanner.nextLine();
			}
			char[] chars = {'2', '3', '4', '5', '6', '7', '8', '9'};
			boolean isChar2to9 = checkChar(chars, option.charAt(0));
			if(isChar2to9) {
				String nums = "23456789";
				for (int i = 2; i < nums.length()+2; i++) {
					if(nums.charAt(i-2) == option.charAt(0)) {
						return i;
					}
				}
			} else if (option.charAt(0) == '1') {
				if(option.length() > 1) {
					char[] chars2 = {'0', '1', '2', '3', '4', '5'};
					boolean isChar0to5 =  checkChar(chars2, option.charAt(1));
					if(isChar0to5) {
						String numbers = "012345";
						for (int i = 0; i < numbers.length(); i++) {
							if(numbers.charAt(i) == option.charAt(1)) {
								return i+10;
							}
						}

					}
				} else {
					return 1;
				}
			}
		}
	}

	private static boolean checkChar(char[] chars, char ch) {
		for (int i = 0; i < chars.length; i++) {
			if(ch == chars[i]) {
				return true;
			}
		}
		return false;
	}

	public static String checkIfWordContainsOnlyLetters(Scanner scanner, Player player) {
		System.out.println("Please, insert the word you would like to place on the board:");
		String word = scanner.nextLine();
		boolean isWordValid = Checks.checkIfCharsInTheWordAreValid(word, scanner);
		System.out.println();
		while(!isWordValid) {
			System.out.println("Please insert a valid word containing only letters:");
			word = scanner.nextLine();
			isWordValid = Checks.checkIfCharsInTheWordAreValid(word, scanner);
			System.out.println();

		}
		return word;
	}

	public static boolean checkIfCharsInTheWordAreValid(String word, Scanner scanner) {
		while(word.equals("")) {
			System.out.println("Please, insert a valid word:");
			word = scanner.nextLine();
		}
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if(!Character.isLetter(ch)) {
		     	return false;
		    }
		}
		return true;
	}

	public static boolean checkIfStringContainsLetter(String word, char ch) {
		for (int i = 0; i < word.length(); i++) {
			if(word.charAt(i) == ch) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkIfTileIsEmpty(Tile tile){
		return checkIfStringContainsLetter(" .*^~@",tile.getContent().charAt(1));
	}


	public static void writeSomeWordsOnTheBoardForTesting(Board board) {
		board.getTiles()[7][7].setContent(" A ");
		board.getTiles()[6][7].setContent(" E ");
		board.getTiles()[8][7].setContent(" R ");
		board.getTiles()[9][7].setContent(" N ");
		board.getTiles()[5][7].setContent(" L ");
		board.getTiles()[5][8].setContent(" O ");
		board.getTiles()[5][9].setContent(" S ");
		board.getTiles()[5][10].setContent(" S ");
		board.getTiles()[6][10].setContent(" A ");
		board.getTiles()[7][10].setContent(" M ");
		board.getTiles()[8][10].setContent(" E ");
		board.getTiles()[6][6].setContent(" M ");
		board.getTiles()[6][5].setContent(" A ");
		board.getTiles()[6][4].setContent(" N ");
		board.getTiles()[5][4].setContent(" E ");
		board.getTiles()[4][4].setContent(" E ");
		board.getTiles()[3][4].setContent(" U ");
		board.getTiles()[2][4].setContent(" Q ");
	}














}
