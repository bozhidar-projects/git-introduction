package com.snippets.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SharedData {
    private int[] data;
    private ReadWriteLock readWriteLock;
    private Lock readLock;
    private Lock writeLock;

    public SharedData() {
        data = new int[15];
        readWriteLock = new ReentrantReadWriteLock(true);

        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();
    }

    public int readData(int index) {
        readLock.lock();
        try {
            int result = data[index];
            System.out.println("Result");
            return result;
        } catch (Exception e) {
            throw e;
        } finally {
            readLock.unlock();
        }
    }

    public void writeData(int index, int data) {
        writeLock.lock();
        try {
            this.data[index] = data + this.data[index];
        } finally {
            writeLock.unlock();
        }
    }
}
