package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;
import com.spaghettisoft.component.ninemenmorris.BoardState;

public class NineMenMorrisMenu extends Menu {
    private static final String START_GAME_OPTION = "Start Game";
    private static final String EXIT_OPTION_NAME = "Back";

    public NineMenMorrisMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        Component nineMenMorrisGame = new BoardState();
        MenuItem nineMenMorris = new SubmenuItem(START_GAME_OPTION, nineMenMorrisGame);
        menuItems.add(nineMenMorris);

        return menuItems;
    }
}
