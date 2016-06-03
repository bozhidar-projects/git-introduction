package com.spaghettisoft.component.menu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.dontgetangry.DontGetAngry;
import com.spaghettisoft.component.dontgetangry.Rules;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

public class DontGetAngryMenu extends Menu{
	
	private static final String START_GAME_OPTION = "Start Game";
	private static final String EXIT_OPTION_NAME = "Back";
	private static final String RULES = "How to play";
	
	public DontGetAngryMenu() {
		super(createMenuItems(),EXIT_OPTION_NAME);
	}

	private static List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<>();
		
		Component dontGetAngryGame = new DontGetAngry();
		MenuItem startGameItem = new SubmenuItem(START_GAME_OPTION, dontGetAngryGame);
		menuItems.add(startGameItem);
		
		Component dontGetAngryRules = new Rules();
		MenuItem rulesGameItem = new SubmenuItem(RULES,dontGetAngryRules);
		menuItems.add(rulesGameItem);
		
		return menuItems;
	}

	/*
	public void display(){
		System.out.println("==================");
		System.out.println("0.Back");
		System.out.println("1.Start game");
		System.out.println("2.How to play");
		System.out.println("==================");
	}*/
	
	
}
