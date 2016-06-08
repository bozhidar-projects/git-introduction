package com.spaghettisoft.component.scrabble;

public class Letter {

	private char ch;
	private int points;
	private int frequency;

	Letter(char ch, int points, int frequency) {
		this.ch = ch;
		this.points = points;
		this.frequency = frequency;
	}

	public char getCh() {
		return ch;
	}

	public int getPoints() {
		return points;
	}

	public int getFrequency() {
		return frequency;
	}




}
