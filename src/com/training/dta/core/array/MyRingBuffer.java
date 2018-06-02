package com.training.dta.core.array;

import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *  using 2 pointer
 *  1. head for writing pointer
 *  2. tail for reading pointer
 *  3. the ring buffer is empty when head = tail
 *  4. the ring buffer is full when tail = head -1
 *  5. data will be pushed into current head position
 *  6. data will be pulled from current tail position
 *  7. when head or tail read to the cap sized then reset to 0
 */
public class MyRingBuffer {

    int head;  // for writing data
    int tail; //  for reading data
    int [] data;
    int size;
    int cap;

    public  MyRingBuffer(int cap){
        this.cap =cap;
        data = new int [cap];
        size = 0;
        head=0;
        tail=0;
    }

    public boolean push_data(int el){
        boolean isAdded = false;
        // check if ring buffer is full
        if (tail== head-1 && size == cap){
            throw new IllegalArgumentException("ring buffer is full");
        } else {
            size++;
            data[head] = el;
            if(head ==cap){
                head=0;
            }else{
                head++;
            }

            isAdded =true;
        }
        return  isAdded;
    }

    public int pop_data(){

        // check if is empty
        int temp;
        if (head == tail){
            throw new IllegalArgumentException("ring buffer is empty");
        }else{

            if(tail == cap){
                temp = data[tail];
                tail=0;
            } else{
                temp = data[tail];
                tail++;
            }
            size--;
        }
        return temp;
    }
    public int getSize(){
        return size;
    }

    public void display(){
        StringJoiner stringJoiner = new StringJoiner(",");

        for(int item: data){
            stringJoiner.add(item+"");
        }
        System.out.println(stringJoiner.toString());
    }

    public static void main (String... args){
        MyRingBuffer myRingBuffer = new MyRingBuffer(8);

        myRingBuffer.push_data(1);
        myRingBuffer.push_data(7);
        myRingBuffer.push_data(2);
        myRingBuffer.push_data(3);
        myRingBuffer.push_data(4);
        myRingBuffer.push_data(5);
        myRingBuffer.push_data(6);


        System.out.println("myRingBuffer:  " + myRingBuffer.getSize());
//        myRingBuffer.display();


        System.out.println(myRingBuffer.pop_data());
        System.out.println(myRingBuffer.pop_data());
        System.out.println(myRingBuffer.pop_data());



  //      System.out.println("myRingBuffer:  " + myRingBuffer.getSize());
       // myRingBuffer.display();
    }

}
