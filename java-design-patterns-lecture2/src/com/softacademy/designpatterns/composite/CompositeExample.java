package com.softacademy.designpatterns.composite;

public class CompositeExample {

    public static void main(String[] args) {
        Component recLetter = new RectangularLetter('A');
        Component circularLetter = new CircularLetter('B');

        Composite anotherComposite = new Composite();
        anotherComposite.addChild(new RectangularLetter('C'));
        anotherComposite.addChild(new RectangularLetter('D'));
        anotherComposite.addChild(new CircularLetter('E'));

        Composite composite = new Composite();
        composite.addChild(recLetter);
        composite.addChild(circularLetter);
        composite.addChild(anotherComposite);

        anotherComposite.addChild(composite);

        Component rootComponent = composite;

        rootComponent.draw();
    }

}
