package com.training.dta.gs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by anhtrang on 13/2/18.
 */
public class GSPreparation {

   /*
    find max stair height from given number of block
    */
    public static  int maxStairCase(int blocks){


       if (blocks< 1) return  -1;

       int index=1;
       int height =1;
       int sum = 1;
       while (sum <= blocks){
           index++;
           sum = sum + index;
           if (sum <= blocks){
               height++;
           }
       }
       return  height;

   }

   static int countArr(int []input, int m, int givenNumber){

        int result = 0;
        if (arryProduct(input, givenNumber)) return  countSubArray(input);
        else {
            for (int i =0 ;i <= input.length -m; i ++){
                result +=countArr(Arrays.copyOfRange(input, i, m-i),m-i-1, givenNumber);
            }
        }
        return  result;

    }


    static int countSubArray(int [] input){
        return  input.length * (input.length+1)/2;
    }
    static  boolean arryProduct(int[] input, int inputNo){
        int result = 1;
        for (int item : input){
             result *= item;
        }
        return  result<= inputNo;

    }

    static  String mergeString (String s1, String s2){

        if (s1==null &&  s2==null) return  null;
        else if (s1==null) return s2;
        else if (s2==null)return s1;

        char[] result = new char [s1.length() + s2.length()];

        int index = 0;
        char[] arr1= s1.toCharArray();
        char[] arr2= s2.toCharArray();

        int min = Math.min(arr1.length, arr2.length);

        for (int i =0 ; i < min; i++){
            result[index++] = arr1[i];
            result[index++] = arr2[i];
        }

        for (int i = min; i< arr1.length; i ++){
            result[index++]= arr1[i];
        }

        for (int i = min; i< arr2.length; i ++){
            result[index++]= arr1[i];
        }

        return  new String (result);

    }

    static boolean containsChar(char g, String input){
        return input.indexOf(g) > 0;
    }

    static Character repeatedChar (String input){
        if (input==null) return null;
        else{

            for (int i =0 ; i < input.length(); i++){
                 if(containsChar(input.charAt(i), input.substring(i + 1))) return  input.charAt(i);

            }
        }
        return null;

    }

    static  int countStep (Integer n){
        if (n ==0) return  0;
        else if (n ==1) return  1;
        else if (n ==2) return  2;
        else {
            return  ( countStep(n-1) +  countStep( n-2) + countStep(n-3));
        }
    }

     static int countStepUtil(int steps, int m){
         int res =0;
         if(steps<= 1) return 1;

         else{
             for(int i =1; i < m;i++){
                 res+= countStepUtil(steps-i,m);
             }
         }
         return res;
     }

    public static void main(String[] args){

       //System.out.println(maxStairCase(6));

         int [] testArray = {1,2,3,4};

        System.out.println(countArr(testArray, testArray.length, 10));


        System.out.println(mergeString("abc", "def"));

        System.out.println(repeatedChar("geeksforgeeks")) ;

        System.out.println( countStepUtil(4, 3));

        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append("#",stringBuffer.length(), 10);


    }

}
