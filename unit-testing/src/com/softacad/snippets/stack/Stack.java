package com.softacad.snippets.stack;

public interface Stack<Type> {
    void push(Type item);

    Type pop();

    boolean isEmpty();

    int getSize();
}
