package com.spaghettisoft.component.hangman;

import java.io.IOException;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.globals.StaticObjects;

public class HangmanStartMenuItem implements MenuItem {

	@Override
	public void select() {
		try {
			System.out.println("Enter the name of the 1-st player : ");
			String player1Name = StaticObjects.scanner.nextLine();
			System.out.println("Enter the name of the 2-nd player : ");
			String player2Name = StaticObjects.scanner.nextLine();
			Component hangmanGame = new HangmanGame(new Player(player1Name), new Player(player2Name));
			hangmanGame.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getLabel() {
		return "Start Game";
	}

}
