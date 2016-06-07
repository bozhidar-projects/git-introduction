package com.softacademy.designpatterns.composite;

public abstract class AbstractLetterComponent implements Component {
    protected char letter;

    public AbstractLetterComponent(char letter) {
        this.letter = letter;
    }

}
