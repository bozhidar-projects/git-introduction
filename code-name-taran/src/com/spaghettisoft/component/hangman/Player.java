package com.spaghettisoft.component.hangman;

import com.spaghettisoft.globals.StaticObjects;

public class Player {

	private String name;
	private static final char LIFE = '@';
	private static final char DEAD = 'X';
	private static final int NUMBER_OF_LIFES = 6;
	private int lifeCounter;
	private char[] lifes = new char[NUMBER_OF_LIFES];
	private boolean myTurn = false;

	public Player(String name) {
		setName(name);
		lifeCounter = NUMBER_OF_LIFES;
		for (int i = 0; i < NUMBER_OF_LIFES; i++) {
			lifes[i] = LIFE;
		}
	}

	public void munisLife() {
		lifes[--lifeCounter] = DEAD;
	}

	public char guess() {
		char c = StaticObjects.scanner.nextLine().charAt(0);
		while (true) {
			if (!Character.isLetter(c)) {
				System.out.println("Please enter a valid letter from A ~ Z");
				continue;
			} else {
				break;
			}
		}
		return Character.toLowerCase(c);
	}

	public boolean isDead() {
		return lifeCounter == 0;
	}

	public void printLife() {
		System.out.print("[");
		for (char l : lifes) {
			System.out.print(l + " ");
		}
		System.out.println("]");
	}

	public String toString() {
		String result = "Name : " + this.name + "\nlifes : " + this.lifeCounter;
		return result;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public char[] getLifes() {
		return lifes;
	}

	public int getLifeCounter() {
		return lifeCounter;
	}

	public boolean getMyTurn() {
		return myTurn;
	}

	protected void setMyTurn(boolean turn) {
		myTurn = turn;
	}

}
