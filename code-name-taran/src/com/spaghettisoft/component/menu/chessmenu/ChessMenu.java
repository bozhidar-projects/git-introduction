package com.spaghettisoft.component.menu.chessmenu;

import java.util.ArrayList;
import java.util.List;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.Menu;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;
import com.spaghettisoft.component.tictactoe.TicTacToe;

 public class ChessMenu extends Menu{

	private static final String EXIT_OPTION_NAME = "Back";

	public ChessMenu() {
		super(createMenuItems(), EXIT_OPTION_NAME);
	}

	private static List<MenuItem> createMenuItems() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		
		
		MenuItem chess = new StartChessGameOption();
		menuItems.add(chess);
		
		
		
		return menuItems;
	}

}
