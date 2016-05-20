package com.spaghettisoft.component.menu.items;

public class CreditsMenuItem extends AbstractMenuItem {
    private static final String[] AUTHORS = { "Ana", "Ventsi", "Emil", "Martin", "Kristian" };
    private static final String TITLE = "Credits";

    public CreditsMenuItem() {
        super(TITLE);
    }

    @Override
    public void select() {
        System.out.println(TITLE + ":");
        for (String name : AUTHORS) {
            System.out.println(name);
        }
    }

}
