package com.snippets.synchronization;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private int someVar;
    private int anotherVar;
    private int otherVar;

    private Lock lock1;
    private Lock lock2;
    private Lock lock3;

    public LockExample() {
        lock1 = new ReentrantLock();
        lock2 = new ReentrantLock();
        lock3 = new ReentrantLock();
    }

    public void foo() {
        boolean lockAcquired = false;
        try {
            lockAcquired = lock1.tryLock(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (lockAcquired) {
            try {
                someVar++;
                System.out.println("Ehoo");
                checkSomething();
            } finally {
                lock1.unlock();
            }
        }
    }

    private void checkSomething() {
        System.out.println("Check something");
        //Complex task
        //Complex task
        //Complex task
        throw new IllegalStateException();
    }

    public void bar() {
        try {
            lock1.lockInterruptibly();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(someVar);
            System.out.println("Another");
        } finally {
            lock1.unlock();
        }
    }

    public void some() {
        lock1.lock();
        try {
            someVar--;
        } finally {
            lock1.unlock();
        }
    }

    public void other() {
        lock2.lock();
        try {
            otherVar += 2;
        } finally {
            lock2.unlock();
        }
    }

    public void another() {
        lock3.lock();
        try {
            System.out.println(anotherVar);
        } finally {
            lock3.unlock();
        }
    }
}
