package com.training.dta.core.array.concurent;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBlockingQueue<T> {
    AtomicInteger size;
    int cap;
    T[] data;
    int TIME_OUT = 1000; // MS SECONDS
    Queue<T> queue;

    public MyBlockingQueue(int cap) {
        this.cap = cap;
        data = (T[]) new Object[cap];
        queue = new LinkedBlockingQueue<>(10);
        size = new AtomicInteger(0);
    }

    public synchronized boolean push(T el) {
        boolean result;

        while (queue.size() == cap) {
            try {

                System.out.println("Waiting push");
                wait(TIME_OUT);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        queue.add(el);
        size.getAndIncrement();

        notify();
        result = true;
        return result;


    }

    public void clear(){
        queue.clear();
        size = new AtomicInteger(0);
    }
    public int getSize(){
        return size.intValue();
    }


    public synchronized Optional<T> pop() {
        T el;

        while (queue.isEmpty()) { // queue is empty
            try {
                System.out.println("Waiting Pop");
                wait(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        el =queue.poll();
        size.getAndDecrement();
        notify();

        return Optional.ofNullable(el);

    }

}
