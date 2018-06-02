package com.training.dta.core.array.concurent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MyBlockingQueue <T>{
    AtomicInteger size;
    int cap;
    T[] data;
    int TIME_OUT = 1000; // MS SECONDS

    public MyBlockingQueue(int cap) {
        this.cap = cap;
        data = (T[]) new Object[cap];
        size = new AtomicInteger(0);
    }
    public int getSize(){
        return  size.intValue();
    }
    public boolean push(T el){
        boolean result = false;
        if(getSize()== cap){
            try {
                System.out.println("waiting for pushing");
                wait(TIME_OUT);
                data[size.getAndIncrement()] = el;
                System.out.println("completing for pushing");
                result = true;
                notifyAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("pushing element:" + size.intValue());
            data[size.getAndIncrement()] = el;
            notifyAll();
            result = true;
        }
        return  result;
    }
    public  T pop(){
        T el  ;
        if (getSize()==0){ // queue is empty
            try {
                System.out.println("waiting for pop");
                wait(TIME_OUT);
                el = data[size.getAndDecrement()] ;
                System.out.println("completing for pop");
                notifyAll();
                return el;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }else{
            System.out.println("getting element:" + size.intValue());
            el =  data[size.getAndDecrement()];
            notifyAll();
        }
        return el;
    }

    public static void main(String [] args){
        MyBlockingQueue<Integer> myBlockingQueue = new MyBlockingQueue<>(10);
//        myBlockingQueue.push(10);
//        myBlockingQueue.push(11);
//        myBlockingQueue.push(12);

        Runnable pushThead = ()-> {
            int counter =0;
            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myBlockingQueue.push(counter++);

            }

        };
        Runnable getThread = ()-> {

            while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myBlockingQueue.pop();

            }

        };
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(getThread);
        executorService.submit(pushThead);


       // executorService.shutdown();

       // System.out.println(myBlockingQueue.getSize());

    }
}
