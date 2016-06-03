package com.spaghettisoft.component.menu.items;

public abstract class AbstractMenuItem implements MenuItem {
    private String label;

    public AbstractMenuItem(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
