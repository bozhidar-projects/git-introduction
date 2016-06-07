package com.spaghettisoft.component.scrabble;

public class Tile {

	String content;
	boolean isEmpty;
	int multiplierLetter;
	int multiplierWord;

	public Tile(String content, boolean isEmpty, int multiplierLetter, int multiplierWord) {
		super();
		this.content = content;
		this.isEmpty = isEmpty;
		this.multiplierLetter = multiplierLetter;
		this.multiplierWord = multiplierWord;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean getEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public int getMultiplierLetter() {
		return multiplierLetter;
	}

	public void setMultiplierLetter(int multiplierLetter) {
		this.multiplierLetter = multiplierLetter;
	}

	public int getMultiplierWord() {
		return multiplierWord;
	}

	public void setMultiplierWord(int multiplierWord) {
		this.multiplierWord = multiplierWord;
	}





}
