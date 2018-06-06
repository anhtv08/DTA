package com.training.dta.core.array.concurent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MyBlockingQueueTest {


    MyBlockingQueue<Integer> myBlockingQueue;
    @Before
    public void setup(){
        myBlockingQueue = new MyBlockingQueue<>(10);

    }
    @Test
    public void push() {
        myBlockingQueue.clear();
        myBlockingQueue.push(1);
        myBlockingQueue.push(2);
        myBlockingQueue.push(3);
        myBlockingQueue.push(4);
        myBlockingQueue.push(5);
        assertEquals(5,myBlockingQueue.getSize());


    }

    @Test
    public void pop() {
        myBlockingQueue.clear();
        myBlockingQueue.push(1);
        myBlockingQueue.push(2);
        myBlockingQueue.push(3);
        myBlockingQueue.push(4);
        assertEquals(1,myBlockingQueue.pop().get().intValue());
        assertEquals(2,myBlockingQueue.pop().get().intValue());
        assertEquals(3,myBlockingQueue.pop().get().intValue());
    }
    @Test
    public void stressTest(){

        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(20);
        PushThread pushThead = new PushThread(myBlockingQueue);
        ReadThread getThead = new ReadThread(myBlockingQueue);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(pushThead);
        executorService.submit(getThead);

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

}
