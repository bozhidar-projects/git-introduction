package com.snippets.synchronization;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RestingRooms {
    private Lock massageChamberLock;

    public RestingRooms() {
        massageChamberLock = new ReentrantLock();
    }

    public void goToMassageChamber(String name) {
        try {
            massageChamberLock.lockInterruptibly();
        } catch (InterruptedException e1) {
            System.out.println(name + " has been interrupted on the lock");
            return;
        }
        try {
            System.out.println(name + " enters the massage chamber");
            Thread.sleep(10_000);
            goToKitchenRoom(name);
        } catch (InterruptedException e) {
            System.out.println("");
        } finally {
            massageChamberLock.unlock();
        }
    }

    public void goToKitchenRoom(String name) {
        massageChamberLock.lock();
        try {
            System.out.println("Getting coffee");
        } finally {
            massageChamberLock.unlock();
        }
    }
}
