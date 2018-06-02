package com.training.dta.core;

import java.util.*;

/**
 * Created by anhtrang on 31/1/18.
 *  Dynamic programing using :
 *  there common sub solution
 */
public class DynamicPrograming {

    // naive solution
    //
    int NIL = -1;
    int max =10;
    int [] memory = new int[max];
    int fin( int n){
        System.out.println(" enter :" + n);
         if (n<=1) return  1;
         else {
              return  fin(n-1) + fin(n-2);
         }
     }
    // using memorizing

    int finViaMemory(int n ){
        // check if value in memorry
        if (memory[n] != NIL){
            System.out.println("getting data from Cache:" + n);
            return  memory[n];
        } else {

            // check base condition
             if (n <=1){
                  memory[n] = 1;
                  return memory[n];
             } else{
                 System.out.println("subbing problem.." + n);
                 memory[n] = finViaMemory(n - 1) + finViaMemory(n-2);
                 return  memory[n];
             }
        }
    }

    int finTab (int n){

        int [] tab = new int [n+1];

        tab[0]=1;
        tab[1] = 1;
        for (int i =2 ; i <= n; i ++){
            tab[i] = tab[i-1] + tab[i-2];
        }
        return  tab[n];

    }

    /**
     * longest increasing subseq problem
     * @return
     */
    int lis (int []arr, int n){

//        int len = arr.length;

        System.out.println("Entering lis function:" + n);

        if (n == 1) return  1;

        int res, max_ref = 1;

        for (int i =1  ; i <n; i ++ ){
            System.out.println("subbing problem:" + i);
            res = lis(arr,i);
            if (arr[i-1] < arr[n-1] && res + 1 > max_ref){
                 max_ref = res + 1;
            }

        }
        return  max_ref;

    }

    int lis_with_tab (int [] arr, int n){

         int [] lis = new int[n];
         Arrays.fill(lis, 1);
        Set<Integer> result = new HashSet<>();
//         lis [0]
        int max =0;
         for (int i =1 ; i < n; i ++){
             for (int j =0 ; j < i ; j ++){
                 if ( arr[j] < arr[i]  && lis[i] < lis[j] + 1){
                     lis[i]  = lis[j] + 1;
                 }
             }
         }

         for (int i =0 ; i< lis.length ; i ++){
             max = Math.max(lis[i], max);
         }
        return  max;

    }


    public static  void main (String [] args){
        DynamicPrograming d = new DynamicPrograming();
//        System.out.println(d.fin(50000));
        Arrays.fill(d.memory,-1);
//        System.out.println(d.finViaMemory(50000));

//        System.out.println(d.finTab(5000));


        int [] temp = {10, 3,5,15,100};
        System.out.println(d.lis(temp, temp.length));
        System.out.println(d.lis_with_tab(temp, temp.length));


    }


}
