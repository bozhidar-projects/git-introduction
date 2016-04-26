package com.spaghettisoft.component.menu;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;
import com.spaghettisoft.component.tictactoe.TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeMenu extends Menu {
    private static final String START_GAME_OPTION = "Start Game";
    private static final String EXIT_OPTION_NAME = "Back";

    public TicTacToeMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        Component ticTacToeGame = new TicTacToe();
        MenuItem ticTacToe = new SubmenuItem(START_GAME_OPTION, ticTacToeGame);
        menuItems.add(ticTacToe);

        return menuItems;
    }

}
