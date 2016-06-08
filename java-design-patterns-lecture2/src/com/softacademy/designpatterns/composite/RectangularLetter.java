package com.softacademy.designpatterns.composite;

public class RectangularLetter extends AbstractLetterComponent {

    public RectangularLetter(char letter) {
        super(letter);
    }

    @Override
    public void draw() {
        System.out.println("[" + letter + "]");
    }

}
