package com.spaghettisoft.component.scrabble;

import java.util.Scanner;

public class Game {

	private Board board;
	private Player[] players;
	private Bag bag;

	private TurnLetterChange change;
	private TurnMakeWord word;

	private Scanner scanner;
	public static String endGameMessage;
	public static boolean endGame;

	Game(int numberOfPlayers, Scanner scanner) {
		this.bag = new Bag();
		this.board = new Board();
		this.players = new Player[numberOfPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player(bag, "Player " + (i+1));
		}
		change = new TurnLetterChange();
		word = new TurnMakeWord(scanner);
		this.scanner = scanner;
		endGameMessage = "";
		endGame = false;
	}

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayersNames() {
		for (int i = 0; i < players.length; i++) {
			System.out.println("Please, insert a name for player " + (i+1));
			String name = scanner.nextLine();
			players[i].setName(name);
		}
	}

	public void start() {
// !!!! da se dobaviat opcii da moje da se vyrne6 nazad edna stypka ako si se obyrkal !!!! //
		int numberOfPasses = 0;
		board.drawBoard();
		int index = 0;

		while(!endGame) {
			int option = printTurnMenu(players[index]);
			switch(option) {
				case 0:
					endGame = true;
					break;
				case 1:
					numberOfPasses = playerWantsToPlaceAWordOnTheBoard(players[index]);
					if(bag.isBagEmpty() && players[index].hasNoMoreLetters()) { endGame = true;}
					break;
				case 2:
					numberOfPasses = playerWantsToPassHisTurn(numberOfPasses);
					if(numberOfPasses == players.length*2) {endGame = true;}
					break;
				case 3:
					numberOfPasses = playerWantsToChangeSomeLetters(index);
			}
			index = changePlayer(index);
		}
	}

	private int changePlayer(int index) {
		index++;
		if(index >= players.length) {
			index = 0;
		}
		return index;
	}

	private int playerWantsToPlaceAWordOnTheBoard(Player player) {
		word.placeWord(player, board, bag);
		return 0;
	}

	private int playerWantsToPassHisTurn(int numberOfPasses) {
		numberOfPasses++;
		board.drawBoard();
		return numberOfPasses;
	}

	private int playerWantsToChangeSomeLetters(int index) {
		int numberOfPasses = 0;
		if(bag.isBagEmpty()) {
			System.out.println("The bag is empty. You cannot change letters anymore. You pass your turn.");
			numberOfPasses++;
		} else {
			String lettersThatShalldBeChanged = change.changeLetters(scanner, players[index]);
			while(lettersThatShalldBeChanged.length() > bag.numberOfLettersInTheBag()) {
				System.out.println("Please, select less letters for change. In the bag there are only " + bag.numberOfLettersInTheBag() + " letters.");
				lettersThatShalldBeChanged = change.changeLetters(scanner, players[index]);
			}
			putLettersInBagAndTakeNewOnes(players[index], lettersThatShalldBeChanged);
		}
		return numberOfPasses;
	}

	private int printTurnMenu(Player player)  {
		System.out.println("Now on turn is " + player.getName() + ":");
		String lettersPlayerHasInHand = Checks.takeLettersPlayerHasInHand(player);
		System.out.println("Currently " + player.getName() + " has the letters:" + lettersPlayerHasInHand);
		System.out.println();
		System.out.println("1 - to place a word");
		System.out.println("2 - to pass your turn");
		System.out.println("3 - to change some of your letters");
		System.out.println("0 - to exit the game");
		char[] chars = {'0', '1', '2', '3'};
		int option = Checks.getValidOption(chars, scanner);
		return option;
	}

	private void putLettersInBagAndTakeNewOnes(Player player, String lettersThatShalldBeChanged) {
		for (int i = 0; i < lettersThatShalldBeChanged.length(); i++) {
			player.getLetters().add(bag.takeRandomLetterFromTheBag());
		}
		for (int i = 0; i < lettersThatShalldBeChanged.length(); i++) {
			char ch = lettersThatShalldBeChanged.charAt(i);
			int points = findPointsForALetter(ch);
			Letter letter = new Letter(ch, points, 0);
			bag.putLetterInTheBag(letter);
		}
		System.out.println("The letters " + player.getName() + " has now are:");
		for (int i = 0; i < player.getLetters().size(); i++) {
			System.out.print(player.getLetters().get(i).getCh());
		}
		System.out.println();
		System.out.println();
	}

	private int findPointsForALetter(char ch) {
		int points = 0;
		Letter[] alphabet = Constants.alphabet;
		for (int i = 0; i < alphabet.length; i++) {
			if(ch == alphabet[i].getCh()){
				points = alphabet[i].getPoints();
			}
		}
		return points;
	}




}
