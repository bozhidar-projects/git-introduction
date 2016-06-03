package com.softacademy.command;

public interface Command<T> {
    void execute();

    T getResult();
}
