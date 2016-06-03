package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.menu.items.CreditsMenuItem;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

public class MainMenu extends Menu {
    public MainMenu() {
        super(createMenuItems());
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();

        GamesMenu gamesMenu = new GamesMenu();
        SubmenuItem submenuItem = new SubmenuItem("Select Game", gamesMenu);
        menuItems.add(submenuItem);

        MenuItem credits = new CreditsMenuItem();
        menuItems.add(credits);

        return menuItems;
    }
}
