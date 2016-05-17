package com.spaghettisoft.component.hangman;

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class WordToGuess {

	private static final String FILE_NAME = "dict.txt.txt";
	protected static String[] AVAILABLE_WORDS;
	private String theWord;

	public WordToGuess() throws IOException {
		AVAILABLE_WORDS = openFromFile();
		this.theWord = getRandomWord();
	}

	private String[] openFromFile() throws IOException {
		File file = new File(FILE_NAME);
		FileReader fr = new FileReader(file);
		BufferedReader textReader = new BufferedReader(fr);

		int numberOfLines = readLines();
		String[] result = new String[numberOfLines];
		for (int i = 0; i < numberOfLines; i++) {
			result[i] = textReader.readLine();
		}
		textReader.close();
		return result;
	}

	private int readLines() throws IOException {

		FileReader fr = new FileReader(FILE_NAME);
		BufferedReader textReader = new BufferedReader(fr);
		int numberOfLines = 0;
		while (textReader.readLine() != null) {
			numberOfLines++;
		}
		textReader.close();

		return numberOfLines;
	}

	protected String getRandomWord() {

		int a = getRandomNumber();
		this.theWord = AVAILABLE_WORDS[a];

		return this.theWord;
	}

	private static int getRandomNumber() {
		int a = (int) (Math.random() * 100);
		int NUMBER_OF_WORDS = AVAILABLE_WORDS.length - 1;
		if (a <= NUMBER_OF_WORDS) {
			return a;
		} else {
			return (a % NUMBER_OF_WORDS);
		}
	}

	public String hideWord() {
		if (this.theWord == null) {
			return "";
		}

		String hiddenWord = "";
		for (int i = 0; i < this.theWord.length(); i++) {
			hiddenWord = hiddenWord + "*";
		}
		return hiddenWord;
	}

	protected void unhideLetter(Character a) {
		for (int i = 0; i < theWord.length(); i++) {
			if (theWord.charAt(i) == a) {

			}
		}
	}

	public String toString() {
		return theWord.toUpperCase();
	}

	public String[] getAvailableWords() {
		return AVAILABLE_WORDS;
	}

	public String getTheWord() {
		return theWord;
	}

	public void setTheWord(String theWord) {
		this.theWord = theWord;
	}

	public boolean contains(Character c) {
		char[] word = this.theWord.toCharArray();
		for (char letter : word) {
			if (letter == c) {
				return true;
			}
		}
		return false;
	}
}
