package org.softacademy.snippets.concurrency.common;

import java.util.concurrent.atomic.AtomicInteger;

public class BallPool {
    private AtomicInteger ballsCount;

    public BallPool() {
        ballsCount = new AtomicInteger(0);
    }

    public void incrementBalls() {
        ballsCount.incrementAndGet();
    }

    public void decrementBalls() {
        ballsCount.decrementAndGet();
    }

    public int getBallsCount() {
        return ballsCount.get();
    }
}
