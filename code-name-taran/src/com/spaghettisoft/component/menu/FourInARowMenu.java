package com.spaghettisoft.component.menu;

import com.spaghettisoft.application.fourinarow.FourInARow;
import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

import java.util.ArrayList;
import java.util.List;

public class FourInARowMenu extends Menu {
    private static final String START_GAME_OPTION = "Start Game";
    private static final String EXIT_OPTION_NAME = "Back";

    public FourInARowMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        Component fourInARowGame = new FourInARow();
        MenuItem fourInARow = new SubmenuItem(START_GAME_OPTION,
                fourInARowGame);
        menuItems.add(fourInARow);

        return menuItems;
    }

}
