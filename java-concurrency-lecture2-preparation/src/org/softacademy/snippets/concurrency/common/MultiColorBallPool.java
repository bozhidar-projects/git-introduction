package org.softacademy.snippets.concurrency.common;

public class MultiColorBallPool {
    private int redBallsCount;
    private int blueBallsCount;

    private Object lock1;
    private Object lock2;

    public MultiColorBallPool() {
        this.redBallsCount = 0;
        this.blueBallsCount = 0;
        lock1 = new Object();
        lock2 = new Object();
    }

    public void incrementRedBalls() {
        synchronized (lock1) {
            redBallsCount++;
        }
    }

    public void incrementBoth() {
        synchronized (lock1) {
            redBallsCount++;
            synchronized (lock2) {
                blueBallsCount++;
            }
        }
    }

    public void decrementBoth() {
        synchronized (lock2) {
            blueBallsCount--;
            synchronized (lock1) {
                redBallsCount--;
            }
        }
    }

    synchronized public void incrementBlueBalls() {
        synchronized (lock2) {
            blueBallsCount++;
        }
    }

    public int getRedBalls() {
        return redBallsCount;
    }

    public int getBlueBalls() {
        return blueBallsCount;
    }

    synchronized public void decrementBlueBalls() {
        synchronized (lock2) {
            blueBallsCount--;
        }
    }

    synchronized public void decrementRedBalls() {
        synchronized (lock1) {
            redBallsCount--;
        }
    }
}
