package com.snippets.synchronization;

public class SynchronizationBlockExample {
    private int someVar;
    private int anotherVar;
    private int otherVar;

    private Object lock1;
    private Object lock2;
    private Object lock3;

    public SynchronizationBlockExample() {
        lock1 = new Object();
        lock2 = new Object();
        lock3 = new Object();
    }

    public void foo() {
        synchronized (lock1) {
            someVar++;
            System.out.println("Ehoo");
        }
    }

    public void bar() {
        synchronized (lock1) {
            System.out.println(someVar);
            System.out.println("Another");
        }
    }

    public void some() {
        synchronized (lock1) {
            someVar--;
        }
    }

    public void other() {
        synchronized (lock2) {
            otherVar += 2;
        }
    }

    public void another() {
        synchronized (lock3) {
            System.out.println(anotherVar);
        }
    }
}
