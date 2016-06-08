package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;
import com.spaghettisoft.component.sixtysyx.SixtySix;

public class SixtySixMenu extends Menu
{
	public static final String START_GAME_OPTION = "Start game";
	public static final String EXIT_OPTION_NAME = "Back";

	public SixtySixMenu()
	{
		super(createMenuItems(), EXIT_OPTION_NAME);
	}

	private static List<MenuItem> createMenuItems()
	{
		List<MenuItem> menuItems = new ArrayList<>();
		
		Component sixtySixGame = new SixtySix();
		MenuItem sixtySix = new SubmenuItem(START_GAME_OPTION, sixtySixGame);
		menuItems.add(sixtySix);
		
		return menuItems;
	}

}
