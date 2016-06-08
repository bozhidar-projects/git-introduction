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
import com.spaghettisoft.component.menu.chessmenu.ChessMenu;
import com.spaghettisoft.component.menu.items.MenuItem;
import com.spaghettisoft.component.menu.items.SubmenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bobi
 *
 *
 *         GamesMenu Menu that contains all games options in project taran.
 */
public class GamesMenu extends Menu {

    private static final String CONNECT_THE_FOUR_OPTION_NAME = "Connect The Four";
    private static final String SCRABBLE_OPTION_NAME = "Scrabble";
    private static final String BELOTE_OPTION_NAME = "Belote";
    private static final String TIC_TAC_TOE_OPTION_NAME = "Tic Tac Toe";
    private static final String NINE_MEN_MORRIS_OPTION_NAME = "Nine Men Morris";
    private static final String HANGMAN_OPTION_NAME = "Hangman";
    private static final String CHECKERS_OPTION_NAME = "Checkers";
    private static final String DONT_GET_ANGRY_OPTION_NAME = "Don't Get Angry";
    private static final String EXIT_OPTION_NAME = "Back";
    private static final String CHESS_OPTION_NAME = "Chess";
    private static final String SIXTY_SIX_OPTION_NAME = "Sixty-six";

    public GamesMenu() {
        super(createMenuItems(), EXIT_OPTION_NAME);
    }

    private static List<MenuItem> createMenuItems() {
        List<MenuItem> gamesMenuItems = new ArrayList<>();

        Component nineMenMorrisMenu = new NineMenMorrisMenu();
        MenuItem nineMenMorrisItem = new SubmenuItem(
                NINE_MEN_MORRIS_OPTION_NAME, nineMenMorrisMenu);

        gamesMenuItems.add(nineMenMorrisItem);

        Component ticTacToeMenu = new TicTacToeMenu();

        MenuItem ticTacToeMenuItem = new SubmenuItem(TIC_TAC_TOE_OPTION_NAME,
                ticTacToeMenu);

        gamesMenuItems.add(ticTacToeMenuItem);

        Component chessMenu = new ChessMenu();
        MenuItem menuItem = new SubmenuItem(CHESS_OPTION_NAME, chessMenu);
        gamesMenuItems.add(menuItem);

        Component hangmenMenu = new HangmanMenu();
        MenuItem hangMenuItem = new SubmenuItem(HANGMAN_OPTION_NAME,
                hangmenMenu);
        gamesMenuItems.add(hangMenuItem);

        Component beloteMenu = new BeloteMenu();
        MenuItem beloteMenuItem = new SubmenuItem(BELOTE_OPTION_NAME,
                beloteMenu);
        gamesMenuItems.add(beloteMenuItem);

        Component connectTheFourMenu = new ConnectTheFourMenu();
        MenuItem connectTheFourItem = new SubmenuItem(
                CONNECT_THE_FOUR_OPTION_NAME, connectTheFourMenu);
        gamesMenuItems.add(connectTheFourItem);

        Component checkersManu = new CheckersMenu();
        MenuItem checkersMenuItem = new SubmenuItem(CHECKERS_OPTION_NAME,
                checkersManu);
        gamesMenuItems.add(checkersMenuItem);

        Component dontGetAngryMenu = new DontGetAngryMenu();
        MenuItem dontGetAngryMenuItem = new SubmenuItem(
                DONT_GET_ANGRY_OPTION_NAME, dontGetAngryMenu);
        gamesMenuItems.add(dontGetAngryMenuItem);

        Component scrabbleMenu = new ScrabbleMenu();
        MenuItem scrabbleMenuItem = new SubmenuItem(SCRABBLE_OPTION_NAME,
                scrabbleMenu);
        gamesMenuItems.add(scrabbleMenuItem);

        Component sixtySixMenu = new SixtySixMenu();
        MenuItem sixtySixMenuItem = new SubmenuItem(SIXTY_SIX_OPTION_NAME,
                sixtySixMenu);
        gamesMenuItems.add(sixtySixMenuItem);

        return gamesMenuItems;
    }

}
