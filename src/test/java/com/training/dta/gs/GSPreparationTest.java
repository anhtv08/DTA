package com.training.dta.gs;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class GSPreparationTest {

    GSPreparation gsPreparation;
    @Before
    public void setup(){
        gsPreparation = new GSPreparation();
    }

    @Test
    public void maxStairCase() {
        assertEquals(0, gsPreparation.maxStairCase(0));
        assertEquals(1, gsPreparation.maxStairCase(1));
        assertEquals(1, gsPreparation.maxStairCase(2));
        assertEquals(2, gsPreparation.maxStairCase(3));
        assertEquals(2, gsPreparation.maxStairCase(4));
    }

    @org.junit.Test
    public void countArr() {
    }

    @org.junit.Test
    public void countSubArray() {
    }

    @org.junit.Test
    public void arryProduct() {
    }

    @org.junit.Test
    public void mergeString() {
    }

    @org.junit.Test
    public void containsChar() {
    }

    @org.junit.Test
    public void repeatedChar() {
    }

    @org.junit.Test
    public void countStep() {
    }

    @org.junit.Test
    public void countStepUtil() {
    }
}
