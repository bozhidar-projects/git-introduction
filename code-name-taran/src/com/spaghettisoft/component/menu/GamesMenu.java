/* Component
 *
 * version 1.0
 *
 * Apr 20, 2016
 *
 * The MIT License (MIT)
 * Copyright (c) <2016> <spaghettisoft LTD>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.spaghettisoft.component.menu;

import com.spaghettisoft.component.Component;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bobi
 *
 *
 *         GamesMenu
 *         Menu that contains all games options in project taran.
 */
public class GamesMenu extends Menu {
    private static final String TIC_TAC_TOE_OPTION_NAME = "Tic Tac Toe";
    private static final String NINE_MEN_MORRIS_OPTION_NAME = "Nine Men Morris";
    private static final String EXIT_OPTION_NAME = "Back";

    public GamesMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> gamesMenuItems = new ArrayList<>();

        Component nineMenMorrisMenu = new NineMenMorrisMenu();
        MenuItem nineMenMorrisItem = new SubmenuItem(NINE_MEN_MORRIS_OPTION_NAME, nineMenMorrisMenu);
        gamesMenuItems.add(nineMenMorrisItem);

        Component ticTacToeMenu = new TicTacToeMenu();
        MenuItem ticTacToeMenuItem = new SubmenuItem(TIC_TAC_TOE_OPTION_NAME, ticTacToeMenu);
        gamesMenuItems.add(ticTacToeMenuItem);

        return gamesMenuItems;
    }

}
