package com.spaghettisoft.component.scrabble;

import java.util.Scanner;

public class GameMenu {

//=========================================================================================
	public void startGameMenu(Scanner scanner) {
//##############################################
		int option = printStartGameMenu(scanner);

		while(option != 0) {
			if(option == 1) {
				startNewGame(scanner);
				option = printStartGameMenu(scanner);
			} else if(option == 2) {
				Constants.printHelp();
				option = printStartGameMenu(scanner);
			} else {
				char[] validOptions = {'0', '1', '2'};
				option = Checks.getValidOption(validOptions, scanner);
			}
		}
	}
//=========================================================================================

	private int printStartGameMenu(Scanner scanner) {
		System.out.println("\nPlease select an option:");
		printStartMenu();
		char[] validOptions = {'0', '1', '2'};
		int option = Checks.getValidOption(validOptions, scanner);

		return option;
	}

	private void printStartMenu() {
		System.out.println("0 - Go back to main menu");
		System.out.println("1 - Start new game");
		System.out.println("2 - Read help");
	}

	public void startNewGame(Scanner scanner) {
		int numberOfPlayers = getNumberOfPlayers(scanner);
		Game game = new Game(numberOfPlayers, scanner);
		game.setPlayersNames();
		game.start();
	}

	private int getNumberOfPlayers(Scanner scanner) {
		System.out.println("Please, insert number of players:");
		char[] chars = {'2', '3', '4'};
		int numberOfPlayers = Checks.getValidOption(chars, scanner);
		return numberOfPlayers;
	}






}
