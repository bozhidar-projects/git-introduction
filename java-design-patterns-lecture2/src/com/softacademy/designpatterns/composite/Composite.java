package com.softacademy.designpatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private List<Component> children;

    public Composite() {
        children = new ArrayList<>();
    }

    public void addChild(Component component) {
        children.add(component);
    }

    public void removeChild(Component component) {
        children.remove(component);
    }

    @Override
    public void draw() {
        System.out.println("{");
        for (Component component : children) {
            component.draw();
        }
        System.out.println("}");
    }

}
