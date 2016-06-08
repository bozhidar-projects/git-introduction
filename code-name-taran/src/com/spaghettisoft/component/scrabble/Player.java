package com.spaghettisoft.component.scrabble;

import java.util.ArrayList;

public class Player {

	String name;
	ArrayList<Letter> letters;
	int points;

	Player(Bag bag, String name) {
		this.name = name;
		letters = new ArrayList<>();
		takeFirst7LettersFromTheBag(bag);
		points = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Letter> letters) {
		this.letters = letters;
	}

	public int getPoints() {
		return points;
	}

	public void increasePoints(int points) {
		this.points += points;
	}

	public void decreasePoints(int points) {
		this.points -= points;
	}

	private void takeFirst7LettersFromTheBag(Bag bag) {
		for (int i = 0; i < 7; i++) {
			letters.add(bag.takeRandomLetterFromTheBag());
		}
	}

	public boolean hasNoMoreLetters() {
		if(letters.isEmpty()) {
			return true;
		}
		return false;
	}

}
