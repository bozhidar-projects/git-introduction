package com.softacad.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicVariablesExample {

    public static void main(String[] args) {
        AtomicInteger atomicVar = new AtomicInteger(10);

        atomicVar.incrementAndGet();
        atomicVar.decrementAndGet();

        int i = 10;
        int k = i + 10;
        i = k;

        while (true) {
            int value = atomicVar.get();
            int newValue = value + 10;
            if (atomicVar.compareAndSet(value, newValue)) {
                break;
            }
        }

    }
}
