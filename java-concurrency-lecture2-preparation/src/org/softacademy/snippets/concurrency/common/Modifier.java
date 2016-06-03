package org.softacademy.snippets.concurrency.common;

public abstract class Modifier implements Runnable {
    private int timesToModify;

    public Modifier(int timesToIncrement) {
        this.timesToModify = timesToIncrement;
    }

    @Override
    public void run() {
        for (int i = 0; i < timesToModify; i++) {
            modifyPool();
        }
    }

    protected abstract void modifyPool();
}
