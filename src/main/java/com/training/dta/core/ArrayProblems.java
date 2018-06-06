package com.training.dta.core;

import java.util.*;

/**
 * Created by anhtrang on 17/12/17.
 */
public class ArrayProblems {

    public  static  Integer[] findsharedElementFrom2Arrays(int [] first, int [] second){

        Set<Integer>  set1 = new HashSet<>();
        Set<Integer>  set2 = new HashSet<>();
        Set<Integer>  combinedSet = new HashSet<>();
        List<Integer> result  = new ArrayList<>();


        for (int i =0 ; i <first.length; i++){
            set1.add(first[i]);
        }

        for (int i =0 ; i <second.length; i++){
            set2.add(second[i]);
        }


       // combinedSet.addAll(set1);
        combinedSet.addAll(set1);

        set2.forEach(item -> {
            if(combinedSet.contains(item)){
                result.add(item);
            }
        });

        Integer [] temp = new Integer[result.size()];

        return  result.toArray(temp);
    }

   static class SubSequence implements Comparable<SubSequence> {
        int startIndex;
        int endIndex;
        int sum ;
        public  SubSequence(int startIndex, int endIndex, int sum){
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.sum = sum;

        }


       @Override
       public int compareTo(SubSequence o) {
           if (this.sum > o.sum)
               return  -1;
           else if (this.sum < o.sum)
               return 1;
           else {
              return 0;
           }

       }
   }

    public static int[] findLargestSequece (int [] input){

        List<SubSequence> temp = new ArrayList<>();
        int startIndex =0;
        int endIndex = 0;
        int startValue ;
        int endValue;
        int sum;

        if (input==null || input.length ==0) return  null;
        startValue = input[0];
        endValue = input[0];
        sum =startValue;
        //boolean hasBreak = false;

        for (int i=0 ; i < input.length ; i++){
            if ( input[i] >  endValue ){
                sum+= input[i];
                endIndex = i;
                endValue = input[i];
            } else {
                 if (endIndex > startIndex){ // add new sub Sequence to the temp result
                    SubSequence subSequence = new SubSequence(startIndex, endIndex, sum);
                    temp.add(subSequence);
                    // hasBreak = true;
                 }
                 // moving start/end index  and initial value.
                 startIndex = i;
                 endIndex= i;
                 startValue = input[i];
                 endValue   = input[i];
                 sum=startValue;

            }

        }

        // adding the last one.
        temp.add(new SubSequence(startIndex,endIndex, sum));

       /* if (!hasBreak){
            return  Arrays.copyOfRange(input, startIndex,++endIndex);
        }*/
        Collections.sort(temp);
        SubSequence subSequence=  temp.get(0);
       return  Arrays.copyOfRange(input, subSequence.startIndex,++subSequence.endIndex);

       // return null;
    }
    private  static void printArray(int [] input){
        if (input==null || input.length==0) return;
        for (int i=0; i < input.length ;i++){
            System.out.println(input[i]);
        }

    }

    public static void main (String[] args){
        int[] input1 = {1,100,4,5,6,7,8,9,10000, 100};

        printArray(findLargestSequece(input1));
        /*int[] input2 = {1,4,6};
        Integer []result =  findsharedElementFrom2Arrays(input1, input2);

        for (int i =0; i < result.length ; i++){
            System.out.println(result[i]);
        }*/



    }

}
