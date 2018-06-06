package com.training.dta.amz;
import java.util.*;

/**
 * Created by anhtrang on 7/3/18.
 */
public class Solution {

    public  static  void main(String []args){

//        String testString = "sjffoisjroeijosurweursrs;oifsirlfjs;fdjljflsjfosj";
//        List<String> result = getKdistint(testString, 4);
//        result.forEach(item-> System.out.println(item));

        int n = Integer.parseInt(scan.nextLine().trim());


        staircase(n);

        Integer []arr = {1,2,-3,4,2};
        Arrays.sort(arr);

        Arrays.copyOfRange(arr, 0,arr.length-1);

        for (int item : arr){
            System.out.println(item);
        }


    }
    static  List<String> getKdistint(String input, int n){
        List<String> result = new ArrayList<>();
        if (n< 3 ||n>= input.toCharArray().length) return  result;
        else{
            char [] temp = input.toCharArray();
            for (int i =0 ; i < input.toCharArray().length - n; i++){
                 char [] temp1=  Arrays.copyOfRange(temp, i ,i +n);
                if(isKdistinct(temp1)){
                    result.add( new String(temp1));
                }
            }
        }
        return  result;
    }

    static  boolean isKdistinct(char [] testStr){

        Set<Character> set  = new HashSet<>();
        for(Character ch : testStr) {
            set.add(ch);
        }
        return testStr.length-1== (set.size());
    }

    /*
     * Complete the staircase function below.
     */
    static void staircase(int n) {
        /*
         * Write your code here.
         */
        StringBuffer result = new StringBuffer();
        for (int index =0 ; index< n;index ++){
            // appending leading space
            //appendStr(result)
            appendStr(result, ' ', n - index - 1);
            //appending '#'
            appendStr(result, '#', index + 1);
            //appending new line
            appendStr(result, '\n', 1);
        }
        System.out.println(result.toString());

    }

    static void appendStr (StringBuffer bs, char ch, int len){
        for (int i =0 ; i < len; i++){
            bs.append(ch);
        }
    }

    private static final Scanner scan = new Scanner(System.in);


}
