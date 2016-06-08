package com.spaghettisoft.component.scrabble;

import java.util.Scanner;

public class MainChecks {


	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		GameMenu menu = new GameMenu();
		menu.startGameMenu(scanner);

		scanner.close();

	}
}

