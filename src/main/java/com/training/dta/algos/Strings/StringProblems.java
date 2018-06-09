package com.training.dta.algos.Strings;
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
