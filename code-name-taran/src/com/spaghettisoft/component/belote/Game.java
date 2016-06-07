package com.spaghettisoft.component.belote;

import java.util.Scanner;

public class Game implements BelotConstants {

	private Cards belotCards;
	private Player[] players;
	private int dealer;
	private int trump;
	private int whoseTurn;
	private int caller;

	Game() {
		belotCards = new Cards();
		players = new Player[NUMBER_OF_PLAYERS];
		players[0] = new Player("Player One");
		players[1] = new Player("Player Two");
		dealer = 0;
	}

	Scanner sc = new Scanner(System.in);

	public void dealFiveCards() {
		whoseTurn = dealer;
		dealer = otherPlayer(dealer);
		trump = UNKNOWN;
		belotCards.assignValues(trump);
		belotCards.shuffle();
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i].dealCards(belotCards, 5);
			players[i].sortCards(trump, USE_TRUMP);
		}

	}

	public void listTrumpOptions() {
		for (int i = 0; i < TRUMP_NAMES.length; i++) {
			System.out.println(i + ": " + TRUMP_NAMES[i]);
		}
	}

	public int TrumpOptionsLength() {
		return TRUMP_NAMES.length;
	}

	public void callTrump() {
		int choice1 = 10;
		int choice2 = 10;
		listTrumpOptions();
		while (choice1 < 0 || choice1 > 6) {
			System.out.println("PLAYER 1: Select trumb");
			choice1 = sc.nextInt();
			sc.nextLine();
			if (choice1 < 0 || choice1 > 6) {
				System.out.println("Select valid number!");
			}
		}
		while (choice2 > 6) {
			System.out.println("PLAYER 2: Select trumb");
			choice2 = sc.nextInt();
			sc.nextLine();
			if (choice2 > 6) {
				System.out.println("Select valid number!");
			}
		}

		belotCards.assignValues(trump);

		if (choice1 == 0 && choice2 == 0) {
			trump = UNKNOWN;
			caller = 0;
		} else if (choice1 >= choice2) {
			trump = choice1;
			caller = 0;
		} else {
			trump = choice2;
			caller = 1;
		}

		belotCards.assignValues(trump);
	}

	public void finishDealing() {
		players[caller].dealCards(belotCards, 3);
		players[otherPlayer(caller)].dealCards(belotCards, 3);
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i].sortCards(trump, USE_TRUMP);
		}
		// flippedCard = belotCards.showNextCard();
	}

	public void scoreBonuses() {
		boolean bonusesDone = false;
		int numberBelots;
		int card = 0;
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			numberBelots = players[i].findNumberBelots(trump);
			if (numberBelots > 0) {
				System.out.println(players[i].getName() + " has "
						+ numberBelots + " belot(s)");
				players[i].addToRawGamePoints(numberBelots * BELOT_BONUS);
			}
		}
		while ((!bonusesDone) && (card < ALL_FOUR.length)) {
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				if (players[i].hasALL_FOUR(ALL_FOUR[card])) {
					System.out.println(players[i].getName() + " has all four "
							+ RANK_NAMES[card] + "s");
					players[i].addToRawGamePoints(ALL_FOUR_BONUSES[card]);
					bonusesDone = true;
				}
			}
			card++;
		}
		Bonuses bestRun = new Bonuses();
		Bonuses currentRunInfo;
		int winner = -1;
		if (!bonusesDone) {
			for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
				currentRunInfo = players[i].findRunInfo(trump);
				if (currentRunInfo.betterThan(bestRun, trump)) {
					winner = i;
					bestRun = currentRunInfo;
				}
			}
			if (winner >= 0) {
				System.out.print(players[winner].getName() + " has a ");
				bestRun.print();
				players[winner].addToRawGamePoints(bestRun.calculateValue());
			}
		}
		System.out.println();
	}

	public int playerChoice(int whoseTurn) {
		int cardByPlayer = 10;

		while (players[whoseTurn].numberOfCardsInDeck() < cardByPlayer) {
			System.out.println("Player " + (whoseTurn + 1)
					+ "- Select card from deck");
			cardByPlayer = sc.nextInt();
			sc.nextLine();
			if (cardByPlayer > players[whoseTurn].numberOfCardsInDeck()) {
				System.out
						.println("Number "
								+ cardByPlayer
								+ " is not valid card position! Please select from the list of cards above!");
			}
		}
		return cardByPlayer - 1;
	}

	public void playCards() {
		Card[] cards = new Card[NUMBER_OF_PLAYERS];
		int otherPlayer;
		int[] wins = new int[NUMBER_OF_PLAYERS];
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++)
			wins[i] = 0;
		for (int i = 0; i < CARDS_IN_HAND; i++) {
			otherPlayer = otherPlayer(whoseTurn);

			cards[whoseTurn] = players[whoseTurn]
					.getCard(playerChoice(whoseTurn));

			do {
				cards[otherPlayer] = players[otherPlayer]
						.getCard(playerChoice(otherPlayer));
			} while (((cards[whoseTurn].getSuit() != cards[otherPlayer]
					.getSuit() && players[otherPlayer]
					.findPlay(cards[whoseTurn]))));

			printResult(cards[whoseTurn], cards[otherPlayer], whoseTurn,
					otherPlayer, trump);
			if (!cards[whoseTurn].isHigher(cards[otherPlayer], trump))
				whoseTurn = otherPlayer;
			for (int j = 0; j < NUMBER_OF_PLAYERS; j++) {
				players[j].discard(cards[j]);
				players[whoseTurn].addToRawGamePoints(cards[j]);
				players[j].print();
				System.out.println();
			}

			wins[whoseTurn]++;
			if (wins[whoseTurn] == CARDS_IN_HAND) {
				otherPlayer = otherPlayer(whoseTurn);
				players[whoseTurn].addToRawGamePoints(ALL_TRICKS_BONUS);
				players[whoseTurn].addToRawGamePoints(players[otherPlayer]
						.getRawGamePoints());
				players[otherPlayer].setRawGamePoints(0);
			}
			printStatus(false);
		}
	}

	public void calculateScores() {
		int defender = otherPlayer(caller);
		int pointsForCaller = players[caller].getRawGamePoints();
		int pointsForDefender = players[defender].getRawGamePoints();
		if (pointsForDefender >= pointsForCaller) {
			if (pointsForDefender > pointsForCaller) {
				players[defender].setRawGamePoints(pointsForCaller
						+ pointsForDefender);
			}
			players[caller].setRawGamePoints(0);
		}
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i]
					.addToConvertedGamePoints(((players[i].getRawGamePoints() + 5) / 10)
							* trump);
		}
	}

	public void printStatus(boolean printPlayers) {
		if (printPlayers) {
			System.out.println();
			System.out.println("Trump = " + TRUMP_NAMES[trump]);
			if (trump != UNKNOWN)
				System.out.println("Caller = " + players[caller].getName());
		}
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			System.out.println();
			System.out.print(players[i].getName());
			System.out.println(", current game points: "
					+ players[i].getRawGamePoints());

			if (printPlayers)
				players[i].print();
		}
		System.out.println("**************************");

		pause();
	}

	private int otherPlayer(int player) {
		return NUMBER_OF_PLAYERS - player - 1;
	}

	private void printResult(Card cardLed, Card cardFollowed, int leader,
			int follower, int trump) {
		if (cardLed.isHigher(cardFollowed, trump))
			System.out.print(" * ");
		else
			System.out.print("   ");
		System.out.print(players[leader].getName() + " plays ");
		cardLed.print();
		if (!cardLed.isHigher(cardFollowed, trump))
			System.out.print(" * ");
		else
			System.out.print("   ");
		System.out.print(players[follower].getName() + " plays ");
		cardFollowed.print();
	}

	private void pause() {
		try {
			System.in.read();
		} catch (java.io.IOException e) {
		}
	}

}
