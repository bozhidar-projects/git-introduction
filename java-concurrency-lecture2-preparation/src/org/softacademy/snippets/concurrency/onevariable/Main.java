package org.softacademy.snippets.concurrency.onevariable;

import org.softacademy.snippets.concurrency.common.BallPool;

public class Main {
    private static final int TIMES_TO_MODIFY = 1000000;

    public static void main(String[] args) {
        BallPool ballPool = new BallPool();

        Incrementor incremenentor = new Incrementor(TIMES_TO_MODIFY, ballPool);
        Thread incrementorThread = new Thread(incremenentor);
        incrementorThread.start();

        Decrementer decrementer = new Decrementer(TIMES_TO_MODIFY, ballPool);
        Thread decreementThread = new Thread(decrementer);
        decreementThread.start();

        try {
            incrementorThread.join();
            decreementThread.join();
        } catch (InterruptedException e) {
            System.out.println("Threads interrupted");
        }

        int ballsCount = ballPool.getBallsCount();
        System.out.println(ballsCount);
    }
}
