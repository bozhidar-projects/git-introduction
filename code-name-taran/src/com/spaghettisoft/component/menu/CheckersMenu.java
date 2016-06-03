package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.checkers.Checkers;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

public class CheckersMenu extends Menu {

	private static final String START_GAME_OPTION = "Start Game";
	private static final String EXIT_OPTION_NAME = "Back";

	public CheckersMenu() {
		super(createMenuItems(), EXIT_OPTION_NAME);
	}

	private static List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<>();

		Component checkersGame = new Checkers();
		MenuItem checkers = new SubmenuItem(START_GAME_OPTION, checkersGame);
		menuItems.add(checkers);

		return menuItems;
	}

}
