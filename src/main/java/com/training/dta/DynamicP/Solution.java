package com.training.dta.DynamicP;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by anhtrang on 3/4/18.
 */
public class Solution {

    public  static void main(String [] args){
//        List<Integer> result = new ArrayList<>();
//        int [] arr = {1,2,3};
//        System.out.println(takeWays(5,arr,0));

//        int [] testArr = {1,2,3};
//        int totalTestVal = 3;

        //System.out.println(count_ways(testArr,totalTestVal));

        //rec_count_way
//
//        Map<String, Integer> map = new HashMap<>();
//        System.out.println(change_coin(testArr, totalTestVal, testArr.length - 1, map));


//        if (!Objects.equals(finbo_modified_seq(1) ,0)) { System.out.println("test case failed");}
//
//        if(!Objects.equals(finbo_modified_seq(2),1)){ System.out.println("test case failed");}
//        if(!Objects.equals(finbo_modified_seq(3) ,1)){ System.out.println("test case failed");}
//        if(!Objects.equals(finbo_modified_seq(4) ,2)){ System.out.println("test case failed");}
//        if(!Objects.equals(finbo_modified_seq(5) ,5)){ System.out.println("test case failed");}

//        System.out.print(finbo_modified_seq(9));
//        System.out.println(nominalNumber(3,3));
//        System.out.println(nominalNumber(0,3));
//        System.out.println(nominalNumber(1,0));
//        System.out.println(nominalNumber(1,3));
//
//        System.out.println(nominalNumber(2,3));
        //System.out.println(nominalNumber(5,100));
       // System.out.println(longestEvenWord("It is a pleasant day today"));

       // System.out.println(breakPalindrome("bab"));

        System.out.println(breakPalindrome("acaca"));





    }

    static int takeWays (int m, int [] arr, int sum){

        //int i = arr[0];
        //int sum =0;
        if (m >0){
            for (int i =0 ; i < arr.length; i++){
                if (m>=arr[i]){
                  //  result.add(arr[i]);
                   takeWays(m-arr[i], arr, ++sum);
                }
            }
        }
        return sum;


        // print result.

    }

     static int count_ways(int [] arr,  int totalValue){
         Map<String,Integer> map = new HashMap<>();
         return  rec_count_way(arr,totalValue, arr.length-1,map);

     }
    static  int rec_count_way(int [] arr, int total ,int index, Map<String, Integer> mem){

        String key= total + ":" + index;
        if (mem.containsKey(key)) return  mem.get(key);  // trying from cache first
        else {

            // base case:
            if (total==0) return 1;// empty set
            else if (total < 0) return  0;
            else if (index< 0) return  0;
            else {
                // check if current testing value > total:
                int result ;
                if(arr[index] > total) {
                    result = rec_count_way(arr, total, index-1 , mem);
                }
                else {
                    result = rec_count_way(arr, total, index-1, mem) + rec_count_way(arr, total-arr[index], index-1, mem);

                }
                mem.put(key, result);
                return result;
            }

        }
    }

    // coin change problem
    static  int change_coin(int [] arr, int total ,int index,Map<String, Integer> cache) {

        // base case:
        String key= index+ ":" + total;
        if (cache.get(key)!=null) return  cache.get(key);

        if (total == 0) return 1;// empty set

        if (total < 0) return 0;
        if (index < 0 && total > 0) return 0;

        int result = change_coin(arr, total, index - 1, cache) + change_coin(arr, total - arr[index], index ,cache);
        cache.put(key, result);
        return  result;

    }

    // finbo modified.
    // source: https://www.hackerrank.com/challenges/fibonacci-modified/problem
         static BigInteger finbo_modified_seq(int  n){
             if (n==1) return  new BigInteger("0", 32 );
             if (n ==2) return new BigInteger("1",32);
             BigInteger []arr = new BigInteger [n+1];
             arr[0]=new BigInteger("0", 32 );
             arr[1]= new BigInteger("0",32);
             arr[2]=new BigInteger("1", 32 );

             for (int i=3; i <= n ; i++){
                 arr[i] = arr[i-1].multiply(arr[i-1]).add(arr[i-2]);
             }
             return  arr[n];
         }

     static int  nominalNumber (int  n , int m ){

         // base case
         if (m ==1) return  1;
         if (m ==n) return  1;
         if (n==0) return  1;
         else if(n <1 || m < 1) return  0;

         int [][] arr = new int[m+2][n+2] ;
         for (int i =0 ; i <= m;i++) {
             arr[m][0] = 1;
             //System.out.println(arr[m][0]);
         }
        // Arrays.fill(arr,1);
//         Arrays.fi
         // set all

         for (int i =1 ; i <=m+1 ; i++){
             for (int j =1; j <= n+1;j++){
                // System.out.println(arr[i][j]);
//                 System.out.println(String.format("index i :%d, j:  %d",i , j));
                 if (i ==j) {
                     arr[i][j]=1;
//                     System.out.println(arr[i][j]);
                     continue;
                 }
                 if (j>i) continue;
                // if (j==1 || j==n) arr[i][j] =1;
                 arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
             }
         }
         return arr[m+1][n+1];

     }

    static String longestEvenWord(String sentence) {
        /*
         * Write your code here.
         */
        int currentIndex =-1;
        int currentLen=-1;
        String []words;
        if (sentence==null || sentence.length() ==0){
            return "";
        }
        else {
            words = sentence.split(" ");

            for (int i =0;i< words.length;i++){
                if (isEven(words[i])){
                    if (words.length > currentLen){
                        currentIndex =i;
                        currentLen = words[i].length();
                    }

                }
            }

        }
        if (currentIndex >=0) return words[currentIndex];
        else return "";

    }
    static boolean isEven(String s){
        if (s==null || s.length()==0) return false;
        return (s.length()%2==0);
    }

        static String breakPalindrome(String s) {
            if (s ==null || s.length() ==0) return  "IMPOSSIBLE";

            char [] arr =s.toCharArray();
            char [] lexArr = "abcdefghijklmnopqrstuvwxyz".toCharArray();
            char [] allLexArr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
//            arr.length
            boolean found= false;
            for (int i =0; i < arr.length ; i++){
                for (int j=0; j < lexArr.length/2; j++){
                    if(arr[i] > lexArr[j]){
                        arr[i] = lexArr[j];
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found){
                return new String(arr);
            }
            return  "IMPOSSIBLE";

        }


}


    // nominal


