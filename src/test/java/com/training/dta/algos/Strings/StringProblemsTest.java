package com.training.dta.algos.Strings;

import com.training.dta.base.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

public class StringProblemsTest extends AbstractBaseTest {

    StringProblems stringProblems ;
    @Before
    public void setUp() throws Exception {
        stringProblems = new StringProblems();
    }

    @Test
    public void mostCommonWord() {
         String input  = "Bob hit a ball, the hit BALL flew far after it was hit.";

         String [] banned ={"hit"};
        assertEquals("ball",stringProblems.mostCommonWord(input,banned));

    }
    @Test
    public  void cleanup(){
        String input  = "Bob hit a ball, the hit BALL flew far after it was hit.";
        assertEquals("Bob hit a ball the hit BALL flew far after it was hit".toLowerCase() ,stringProblems.cleanUpInput(input));
    }

    @Test
    public void getLargestIndentialSubString(){


        String  inputTest  = "";
        assertEquals(0, stringProblems.getLargestIndentialSubString(inputTest).size());



        // test case 1

          inputTest  = "aaabb";
        assertEquals("aaa", stringProblems.getLargestIndentialSubString(inputTest).keySet().iterator().next());
        assertEquals(Optional.of(3).get(), stringProblems.getLargestIndentialSubString(inputTest).get("aaa"));

        // test case 2
        inputTest  = "aabb";
        assertEquals(2, stringProblems.getLargestIndentialSubString(inputTest).keySet().size());

    }
}