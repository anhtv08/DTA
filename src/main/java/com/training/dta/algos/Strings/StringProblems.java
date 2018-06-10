package com.training.dta.algos.Strings;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StringProblems {

    final  String PUNCTUATION = "[,.;?:]";
    public String mostCommonWord(String paragraph, String[] banned) {

        if (paragraph !=null && paragraph.isEmpty()) return  "";

        String cleanedUpInput = cleanUpInput(paragraph);
        String [] words = cleanedUpInput.split(" ");

        Map<String, Integer> temp = new HashMap<>();


        for (String word: words){
            if(!isBanned(word, banned)){
                if (temp.containsKey(word)){
                    temp.put(word, temp.get(word) + 1);
//                    maxLen = 1;
                }else if (!temp.containsKey(word)){
                    temp.put(word,1);
                }
            }
        }

        // this is my piece of code to find most used word
        String key = "" ;
        int maxLen =0;
        for (Map.Entry<String,Integer> entry:  temp.entrySet()){

            if(entry.getValue() > maxLen){
                key= entry.getKey();
                maxLen = entry.getValue();
            }
        }
        return key;
    }

    public Map<String, Integer> getLargestIndentialSubString(final  String input){
        if (input ==null && input.isEmpty()) return Collections.EMPTY_MAP;
        int currentMax = 0;
        int currentLen = 1;

        Map<String, Integer> result = new HashMap<>();
        int  startIndex = 0;
        int  endIndex = 0;

        char [] temp = input.toCharArray();
        for (int i =0; i < temp.length-1 ; i++){
            if(temp[i] ==temp[i+1]){
                 currentLen ++;
            }else {
                if (currentLen>currentMax){

                    result.clear();
                    result.put(new String (Arrays.copyOfRange(temp,startIndex, i + 1)), currentLen);
                    currentMax = currentLen;
                    startIndex = endIndex;
                    currentLen = 1;

                }else if (currentLen == currentMax){
                    result.put(new String (Arrays.copyOfRange(temp, startIndex ,i+1).toString()), currentLen);
                    startIndex = i;
                    currentLen = 1;
                } else {
                    continue;
                }
            }
        }

        // for last possible result
        if (currentLen== currentMax){
            result.put( new String(Arrays.copyOfRange(temp,startIndex, temp.length)), currentLen);
        }
        return result;
    }

    // remove all punctuation such , .
    public String cleanUpInput(final  String input){
        return input.toLowerCase().replaceAll(PUNCTUATION, "");
    }

    private boolean isBanned(String word, String [] bannedList){
        for(String item: bannedList){
            if(word.equals(item)) return  true;
        }
        return false;
    }

}
