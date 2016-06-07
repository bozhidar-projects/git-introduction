package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.scrabble.ScrabbleGame;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

public class ScrabbleMenu extends Menu {

	private static final String START_GAME_OPTION = "Start Game";
	private static final String EXIT_OPTION_NAME = "Back";

	public ScrabbleMenu() {
		super(createMenuItems(), EXIT_OPTION_NAME);
	}

	private static List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<>();

		Component belote = new ScrabbleGame();
		MenuItem startBeloteOption = new SubmenuItem(START_GAME_OPTION, belote);
		menuItems.add(startBeloteOption);

		return menuItems;
	}

}
