package org.softacademy.snippets.concurrency.deadlock.example;

import org.softacademy.snippets.concurrency.common.MultiColorBallPool;

public class DeadlockExample {

    private static final int TIMES_TO_MODIFY = 1_000_000;

    public static void main(String[] args) {
        MultiColorBallPool ballPool = new MultiColorBallPool();

        BothIncrementor bothIncrementor1 = new BothIncrementor(TIMES_TO_MODIFY,
                ballPool);
        Thread bothIncrementorThread1 = new Thread(bothIncrementor1);

        BothDecrementor bothDecrementor = new BothDecrementor(TIMES_TO_MODIFY,
                ballPool);
        Thread bothDecrementorThread = new Thread(bothDecrementor);

        bothIncrementorThread1.start();
        bothDecrementorThread.start();

        try {
            bothDecrementorThread.join();
            bothIncrementorThread1.join();
        } catch (InterruptedException e) {
            System.out.println("A thread has been interrupted");
        }

        System.out.println(
                ballPool.getBlueBalls() + " " + ballPool.getRedBalls());
    }

}