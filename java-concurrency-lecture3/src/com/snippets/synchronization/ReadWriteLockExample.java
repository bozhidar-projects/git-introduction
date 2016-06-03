package com.snippets.synchronization;

import java.util.HashSet;
import java.util.Set;

public class ReadWriteLockExample {
    public static void main(String[] args) {
        SharedData sharedData = new SharedData();

        Set<Thread> readers = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new DataReader(sharedData);
            thread.start();
            readers.add(thread);
        }

        Thread writer = new Writer(sharedData);
        writer.start();
        try {
            for (Thread thread : readers) {
                thread.join();
            }
            writer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
