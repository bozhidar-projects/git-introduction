package com.softacademy.designpatterns.composite;

public class CircularLetter extends AbstractLetterComponent {

    public CircularLetter(char letter) {
        super(letter);
    }

    @Override
    public void draw() {
        System.out.println("(" + letter + ")");
    }

}
