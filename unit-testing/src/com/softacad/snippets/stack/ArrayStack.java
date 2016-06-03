package com.softacad.snippets.stack;

public class ArrayStack<Type> implements Stack<Type> {
    private Object[] items;
    private int size;

    public ArrayStack(int capacity) {
        items = new Object[capacity];
        size = 0;
    }

    @Override
    public void push(Type item) {
        items[size] = item;
        size++;
    }

    @Override
    public Type pop() {
        size--;
        Type result = (Type) items[size];
        items[size] = null;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }
}
