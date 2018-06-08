package com.training.dta.core.stack;
import com.training.dta.base.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;


public class MyStackTest extends AbstractBaseTest {

    MyStack<Integer> myStack;
    @Before
    public void setup(){
        myStack = new MyStack<>();
    }

    @Test
    public void push() {
        myStack.empty();
        List<Integer> data = Stream.of(1,2,3).collect(Collectors.toList());
        setupData(data);

        assertEquals(3,myStack.size());
    }

    @Test
    public void poll() {
        myStack.empty();
        List<Integer> data = Stream.of(1,2,3).collect(Collectors.toList());
        setupData(data);

        assertEquals(3,myStack.size());
        assertEquals(Optional.of(3).get(), myStack.poll());
        assertEquals(Optional.of(2).get(),myStack.poll());
        assertEquals(Optional.of(1).get(),myStack.poll());

    }

    private void setupData( List<Integer> data){
        for(Integer el : data){
            myStack.push(el);
        }
    }
}
