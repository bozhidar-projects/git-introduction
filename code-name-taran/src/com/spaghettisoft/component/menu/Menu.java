/* Menu
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
import com.spaghettisoft.globals.StaticObjects;

import java.io.IOException;
import java.util.List;

//TODO: add menu title
/**
 * @author bobi
 *
 *         Menu
 *         Console components that lists {@link MenuItem}s in the console
 *         and asks the user to select one of them. The first option is
 *         hard-coded and it is used to exit the menu.
 */
public class Menu implements Component {
    private static final String DEFAULT_EXIT_OPTION_NAME = "Exit";

    private List<MenuItem> menuItems;
    private String exitOptionName;

    public Menu(List<MenuItem> menuItems, String exitOptionName) {
        this.menuItems = menuItems;
        this.exitOptionName = exitOptionName;
    }

    public Menu(List<MenuItem> menuItems) {
        this(menuItems, DEFAULT_EXIT_OPTION_NAME);
    }

    /* (non-Javadoc)
     * @see com.spaghettisoft.component.Component#show()
     */
    @Override
    public void show() {
        int option = 0;
        do {
            printMenuOptions();
            option = getOptionFromUser();
            if (option == 0) {
                break;
            }
            MenuItem selectedMenuItem = menuItems.get(option - 1);
            try {
				selectedMenuItem.select();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } while (true);
    }

    private int getOptionFromUser() {
        System.out.print("Please enter valid option:");
        int option = StaticObjects.scanner.nextInt();
        StaticObjects.scanner.nextLine();
        return option;
    }

    private void printMenuOptions() {
        System.out.println("============================");
        System.out.println("0. " + exitOptionName);
        for (int i = 0; i < menuItems.size(); i++) {
            int menuOption = i + 1;
            MenuItem menuItem = menuItems.get(i);
            System.out.println(menuOption + ". " + menuItem.getLabel());
        }
        System.out.println("============================");
    }

}
