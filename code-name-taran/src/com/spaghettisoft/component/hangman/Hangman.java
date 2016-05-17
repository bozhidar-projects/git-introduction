package com.spaghettisoft.component.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Hangman {

	private final Player player1;
	private final Player player2;
	private final WordToGuess word;
	private char[] maskWord;
	private HashMap<String, ArrayList<Character>> triedLetters = new HashMap<>();

	public Hangman(Player player1, Player player2) throws IOException {
		this.player1 = player1;
		this.player2 = player2;
		word = new WordToGuess();
		maskWord = word.hideWord().toCharArray();
		triedLetters.put(player1.getName(), new ArrayList<>());
		triedLetters.put(player2.getName(), new ArrayList<>());
	}

	public void setInitialTurn() {
		Random random = new Random();
		int t = random.nextInt(2) + 1;
		System.out.println("Throw a coin to get the turn : ");
		if (t == 1) {
			player1.setMyTurn(true);
			return;
		}
		player2.setMyTurn(true);
	}

	private void changePlayerTurn() {
		if (player1.getMyTurn()) {
			player1.setMyTurn(false);
			player2.setMyTurn(true);
			return;
		}
		player1.setMyTurn(true);
		player2.setMyTurn(false);
	}

	private Player checkTurn() {
		Player playerTurn = (player1.getMyTurn() ? player1 : player2);
		return playerTurn;
	}

	public void play() {
		System.out.println("Let's play Hangman : )");
		System.out.println("The super-secret word is : ");
		printMaskWord();
		setInitialTurn();
		while (true) {
			Player player = checkTurn();
			System.out.println("It's "+player.getName()+" turn. Please enter a letter : ");
			Character c = player.guess();
			System.out.println(player.getName()+ " your try is " + c);
			checkWord(c, player);
			if (hasWin()){
				System.out.println("The word is " + word.getTheWord());
				System.out.println("Player "+player.getName()+" has won the game !");
				break;
			}
			if (player.isDead()){
				System.out.println(player.getName()+" is dead hahaha muhahaha !");
				break;
			}
			changePlayerTurn();			
		}

	}

	private void printMaskWord() {
		for (char c : maskWord) {
			System.out.print(c + " ");
		}
		System.out.println();
		
	}

	private boolean hasWin() {
		// TODO Auto-generated method stub
		String theWord = word.getTheWord();
		String result = "";
		for(int i =0; i<maskWord.length; i++){
			result += maskWord[i];
		}
		return theWord.equals(result);
	}

	protected boolean isEnded() {
		// TODO Auto-generated method stub
		return (player1.isDead() || player2.isDead());
	}

	private void checkTriedLetter(Character c, Player p) {
		ArrayList<Character> tried = triedLetters.get(p.getName());
		if (tried.contains(c)) {
			System.out.println("The letter " + c + " is alreday tried ! ");
			return;
		}
		tried.add(c);
		triedLetters.put(p.getName(), tried);
	}

	private void checkWord(Character c, Player p) {
		checkTriedLetter(c, p);
		String w = this.word.getTheWord();
		if (this.word.contains(c)) {
		for (int i = 0; i < w.length(); i++) {
			if (w.charAt(i) == c) {
				unmask(i, c);
				System.out.println(c + " is in the word !");
				printMaskWord();
			}
		}
		} else {
			System.out.println(c + " is not in the word !");
			p.munisLife();
			System.out.println(p.getName() + "'s lifes left : ");
			p.printLife();
		}
	}

	private void unmask(int i, Character a) {
		// TODO Auto-generated method stub
		maskWord[i] = a;	
	}
}