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

        while (queue.size() == cap) {
            try {
//                wait(TIME_OUT);
                lock.wait(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
        queue.add(el);
        result = true;
        System.out.println("pushing value:" + result);

        lock.notifyAll();
        return result;


    }

    public Optional<T> pop() {
        T el;

        while (queue.isEmpty()) { // queue is empty
            try {
                lock.wait(TIME_OUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        el =queue.poll();
        System.out.println("returning value: " + el);
        lock.notifyAll();
        return Optional.ofNullable(el);

    }

    static class PushThread implements Runnable {

        MyBlockingQueue<Integer> myBlockingQueue;
        public  PushThread(MyBlockingQueue<Integer> myBlockingQueue){
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            int counter=0;
            while (true) {
                try {
                    myBlockingQueue.push(counter++);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    static class ReadThread implements Runnable {

        MyBlockingQueue<Integer> myBlockingQueue;
        public  ReadThread(MyBlockingQueue<Integer> myBlockingQueue){
            this.myBlockingQueue = myBlockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    myBlockingQueue.pop();
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    // this is for testing
    public static void main(String[] args) {
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(20);

        PushThread pushThead = new PushThread(myBlockingQueue);
        ReadThread getThead = new ReadThread(myBlockingQueue);


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(pushThead);
        executorService.submit(getThead);

        // executorService.shutdown();

        // System.out.println(myBlockingQueue.getSize());

    }
}
