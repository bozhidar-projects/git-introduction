package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.hangman.HangmanGame;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

public class HangmanMenu extends Menu {
	
	private static final String EXIT_OPTION_NAME = "Back";
	private static final String START_GAME_OPTION = "Start Game";

	public HangmanMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        
        Component hangmanGame = new HangmanGame();
        MenuItem hangman = new SubmenuItem(START_GAME_OPTION, hangmanGame);
        menuItems.add(hangman);
        return menuItems;
    }

}
