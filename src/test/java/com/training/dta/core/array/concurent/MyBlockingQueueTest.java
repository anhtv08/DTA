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
        PushThread pushThead = new PushThread(myBlockingQueue,"pushThead");
        ReadThread getThead = new ReadThread(myBlockingQueue, "getThead");

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(pushThead);
        //executorService.submit(getThead);

        if(executorService.isTerminated()){
            executorService.shutdown();
        }

    }
    static class PushThread implements Runnable {

        private String name;
        boolean cancelled = false;
        MyBlockingQueue<Integer> myBlockingQueue;
        private  int counter =0;
        public  PushThread(MyBlockingQueue<Integer> myBlockingQueue, String name){
            this.myBlockingQueue = myBlockingQueue;
            this.name = name;
        }

        @Override
        public void run() {
            int el=0;
            while (!cancelled) {
                try {
                    System.out.println("Running thread: " + this.name) ;

                    counter++;
                    System.out.println("Push counter: " + this.counter) ;
                    myBlockingQueue.push(el++);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

            }
        }
        public void setCancell(){
            this.cancelled =true;
        }
    }

    static class ReadThread implements Runnable {

        MyBlockingQueue<Integer> myBlockingQueue;
        private String name;
        private boolean cancelled =false;
        private  int counter =0;

        public  ReadThread(MyBlockingQueue<Integer> myBlockingQueue, String name){
            this.myBlockingQueue = myBlockingQueue;
            this.name = name;

        }



        @Override
        public void run() {
            while (!cancelled) {
                try {

                    System.out.println("Running thread: " + this.name) ;
                    counter++;
                    System.out.println(" Read counter: " + this.counter) ;
                    myBlockingQueue.pop();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }

        public void setCancell(){
            this.cancelled =true;
        }
    }

}
