package com.training.dta.core.queue;
import com.training.dta.base.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class MyQueueTest extends AbstractBaseTest {

    MyQueue<Integer> myQueue;
    @Before
    public void setUp() throws Exception {
         myQueue = new MyQueue<>();
    }

    @Test
    public void enqueue() {
        myQueue.clear();
        setupData( Stream.of(1,2,3).collect(Collectors.toList()));
        assertEquals(3, myQueue.size());

    }

    @Test
    public void dequeue() {
        myQueue.clear();
        setupData( Stream.of(1,2,3).collect(Collectors.toList()));

        assertEquals(3, myQueue.size());
        assertEquals(java.util.Optional.of(1).get(),  myQueue.dequeue());
        assertEquals(java.util.Optional.of(2).get(), myQueue.dequeue());
        assertEquals(java.util.Optional.of(3).get(), myQueue.dequeue());

    }
    private void setupData( List<Integer> data){
        for(Integer el : data){
            myQueue.enqueue(el);
        }
    }
}