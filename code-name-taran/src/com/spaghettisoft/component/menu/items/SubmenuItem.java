package com.spaghettisoft.component.menu.items;

import com.spaghettisoft.component.Component;

public class SubmenuItem extends AbstractMenuItem {

    private Component component;

    public SubmenuItem(String label, Component component) {
        super(label);
        this.component = component;
    }

    @Override
    public void select() {
        component.show();
    }

}
