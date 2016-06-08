package com.spaghettisoft.component.scrabble;

public class Stave {

	private char ch;
	private char letter;
	private int row;
	private int col;

	public Stave(char ch, int row, int col) {
		this.ch = ch;
		this.row = row;
		this.col = col;
		letter = 'x';
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}




}
