package com.spaghettisoft.component.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Hangman {

	private final Player player1;
	private final Player player2;
	private final WordToGuess word;
	private String maskWord;
	private HashMap<String, ArrayList<Character>> triedLetters = new HashMap<>();
	private static final int MAX_NUMBER_OF_GUESSES = 10;
	private int guessCounter;

	public Hangman(Player player1, Player player2) throws IOException {
		this.player1 = player1;
		this.player2 = player2;
		word = new WordToGuess();
		maskWord = word.hideWord();
		triedLetters.put(player1.getName(), new ArrayList<>());
		triedLetters.put(player2.getName(), new ArrayList<>());
		guessCounter = 0;
	}

	public void setInitialTurn() {
		Random random = new Random();
		int t = random.nextInt(2) + 1;
		System.out.println("Throw a coin to get the turn : ");
		if (t == 1) {
			System.out.println("It's " + player1.getName() + " turn !");
			player1.setMyTurn(true);
			return;
		}
		System.out.println("It's " + player2.getName() + " turn !");
		player2.setMyTurn(true);
	}

	private void changePlayerTurn() {
		if (player1.getMyTurn()) {
			player1.setMyTurn(false);
			player2.setMyTurn(true);
			System.out.println("It's " + player2.getName() + "'s turn");
			return;
		}
		player1.setMyTurn(true);
		player2.setMyTurn(false);
		System.out.println("It's " + player1.getName() + "'s turn");
	}

	public void play() {
		System.out.println("Let's play Hangman : )");
		setInitialTurn();
		System.out.println(word);
		while (!player1.isDead() || !player2.isDead()) {
			char a = player1.guess();
			char b = player2.guess();
		}

	}

	private void checkTriedLetter(Character a, Player p) {
		ArrayList<Character> tried = triedLetters.get(p.getName());
		if (tried.contains(a)) {
			System.out.println("The letter " + a + " is alreday tried ! ");
			return;
		}
		tried.add(a);
		triedLetters.put(p.getName(), tried);
	}

	private void checkWord(Character a, Player p) {
		checkTriedLetter(a, p);
		String w = this.word.getTheWord();
		for (int i = 0; i < w.length(); i++) {
			if (w.charAt(i) == a) {
				unmask(i, a);
			}
		}
	}

	private void unmask(int i, Character a) {
		// TODO Auto-generated method stub

	}

	public void displayGame() {
		char[] life1 = player1.getLifes();
		char[] life2 = player2.getLifes();
		System.out.println("The super - secret word is :");
		System.out.println(word);
		System.out.println("Player1 : " + player1);
		System.out.println(Arrays.toString(life1));
		System.out.println("Player2 : " + player2);
		System.out.println(Arrays.toString(life2));
	}

}