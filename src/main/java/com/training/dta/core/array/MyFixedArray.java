package com.training.dta.core.array;

public class MyFixedArray<T> {

    int size;
    int cap;
    Object [] data;

    public MyFixedArray(int cap){
        size=0;
        this.cap = cap;
        data = new Object[cap];
    }
    public T get(int index){
        if(index< size){
            return  (T) data[index];
        }
        else{
            throw  new IndexOutOfBoundsException("Index out of bound exception");
        }
    }
    public boolean add( T el){
        boolean result = false;
        if(size< cap){
            data[size] = el;
            size++;
            result = true;
        }
        return  result;
    }
    public boolean remove (T el){

        boolean found = false;
        int index =-1;
        // find the index of element;

        for (int i=0; i < size; i++){
            if (data[i].equals(el)){
                found = true;
                index = i;
            }
        }
        if(found){

            //shift the right part from found index.
            for (int i = index; i< size-1; i++){
                data[i] = data[i+1];
            }
            size--;

        }
        return found;

    }
    public int getSize(){
        return  size;
    }
    public  static void main(String ... args){
        MyFixedArray myFixedArray = new MyFixedArray(10);

        myFixedArray.add(1);
        myFixedArray.add(2);
        myFixedArray.add(3);

        System.out.println( "array size:   " + myFixedArray.getSize());
        myFixedArray.remove(1);
        System.out.println( "array size:   " + myFixedArray.getSize());

        myFixedArray.get(3);


    }
}
