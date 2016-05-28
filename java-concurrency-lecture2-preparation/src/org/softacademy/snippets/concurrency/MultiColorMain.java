package org.softacademy.snippets.concurrency;

import org.softacademy.snippets.concurrency.common.MultiColorBallPool;

public class MultiColorMain {

    private static final int TIMES_TO_MODIFY = 1000000;

    public static void main(String[] args) {
        MultiColorBallPool ballPool = new MultiColorBallPool();

        BlueBallIncrementor blueBallIncrementor = new BlueBallIncrementor(
                TIMES_TO_MODIFY, ballPool);
        Thread blueBallIncrementorThread = new Thread(blueBallIncrementor);

        BlueBallDecrementor blueBallDecrementor = new BlueBallDecrementor(
                TIMES_TO_MODIFY, ballPool);
        Thread blueBallDecrementorThread = new Thread(blueBallDecrementor);

        RedBallIncrementor redBallIncrementor = new RedBallIncrementor(
                TIMES_TO_MODIFY, ballPool);
        Thread redBallIncrementorThread = new Thread(redBallIncrementor);

        RedBallDecrementor redBallDecrementor = new RedBallDecrementor(
                TIMES_TO_MODIFY, ballPool);
        Thread redBallDecrementorThread = new Thread(redBallDecrementor);

        blueBallIncrementorThread.start();
        blueBallDecrementorThread.start();
        redBallIncrementorThread.start();
        redBallDecrementorThread.start();

        try {
            blueBallIncrementorThread.join();
            blueBallDecrementorThread.join();
            redBallDecrementorThread.join();
            redBallIncrementorThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println(
                ballPool.getBlueBalls() + " " + ballPool.getRedBalls());
    }

}
