package com.training.dta.core.array.concurent;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    AtomicInteger size;
    int cap;
    T[] data;
    int TIME_OUT = 1000; // MS SECONDS

    Queue<T> queue = new LinkedList<>();
    Lock lock = new ReentrantLock();

    public MyBlockingQueue(int cap) {
        this.cap = cap;
        data = (T[]) new Object[cap];
        queue = new LinkedBlockingQueue<>(10);
        size = new AtomicInteger(0);
    }

    public boolean push(T el) {
        boolean result;

        if(queue.size() == cap){
            while (queue.size() == cap) {
                try {
//                wait(TIME_OUT);
                    wait(TIME_OUT);
                    queue.add(el);
                    size.getAndIncrement();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            notifyAll();
        }else{
            queue.add(el);
            size.getAndIncrement();
        }
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


    public Optional<T> pop() {
        T el =null;

        if(queue.isEmpty()){
            while (queue.isEmpty()) { // queue is empty
                try {
                    wait(TIME_OUT);
                    el =queue.poll();
                    size.getAndDecrement();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                    el = null;
                }
            }
            notifyAll();

        }else{
            el =queue.poll();
            size.getAndDecrement();

        }

        return Optional.ofNullable(el);

    }

}
