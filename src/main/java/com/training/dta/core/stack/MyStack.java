package com.training.dta.core.stack;
import java.util.LinkedList;
public class MyStack<T> {

    private LinkedList<T> data ;

    public MyStack(){
        data  = new LinkedList<>();
    }

    public  boolean push(T el){

        data.add(el);
        return false;
    }
    public T poll(){

        T el = null;
        if(!data.isEmpty()){
           el =data.removeLast();
        }
        return el;

    }
    public int size(){
        return  data.size();
    }
    public  void empty(){
        data.clear();
    }

}
