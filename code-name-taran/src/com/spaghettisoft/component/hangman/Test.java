package com.spaghettisoft.component.hangman;

import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Player ani = new Player("Ani");
		Player deni = new Player("Deni");
		Hangman game = new Hangman(ani, deni);
		game.play();
	}

}
