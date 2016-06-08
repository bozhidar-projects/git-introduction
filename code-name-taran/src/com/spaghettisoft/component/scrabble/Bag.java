package com.spaghettisoft.component.scrabble;

import java.util.ArrayList;
import java.util.Random;

public class Bag {

	private ArrayList<Letter> letters;

	Bag() {
		letters = putAllTheLettersInTheBag();
	}

	public Letter takeRandomLetterFromTheBag() {
		Random random = new Random();
		int index = random.nextInt(letters.size());
		Letter letter = letters.get(index);
		letters.remove(index);
		return letter;
	}

	public void putLetterInTheBag(Letter letter) {
		letters.add(letter);
	}

	public boolean isBagEmpty() {
		if(letters.isEmpty()) {
			return true;
		}
		return false;
	}

	public int numberOfLettersInTheBag() {
		return letters.size();
	}

	private ArrayList<Letter> putAllTheLettersInTheBag() {
		Letter[] alphabet = Constants.alphabet;
		ArrayList<Letter> letters = new ArrayList<Letter>();
		for (int i = 0; i < alphabet.length; i++) {
			Letter letter = alphabet[i];
			for (int j = 0; j < alphabet[i].getFrequency(); j++) {
				letters.add(letter);
			}
		}
		return letters;
	}

}
