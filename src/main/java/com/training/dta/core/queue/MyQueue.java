package com.training.dta.core.queue;

import java.util.LinkedList;

public class MyQueue<T> {

    LinkedList<T> data;
    public  MyQueue(){
        data = new LinkedList<>();
    }

    public boolean enqueue(T el){
         data.addLast(el);
         return  true;
    }
    public T dequeue(){
        if(!data.isEmpty()){
            return data.removeFirst();
        }
        return  null;
    }
    public int size(){
        return  data.size();
    }
    public  void clear(){
        data.clear();
    }
}
