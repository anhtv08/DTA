import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by anhtrang on 7/9/17.
 */
public class Solution {

    public static  void main (String [] args){
        System.out.println();

        List<Movie> movies =null;
        int temp = 0;

        movies.
                stream()
                .sorted(Comparator.comparingDouble(item->item.getRate()))
                .filter(item-> item.getRate() == temp).collect(Collectors.toList());



        Collections.sort(movies, new Comparator<Movie>() {
                    @Override
                    public int compare(Movie o1, Movie o2) {
                        if (o1.getRate() > o2.getRate()) return  1;
                        else if (o1.getRate() < o2.getRate()) return  -1;
                        else  return  0;

                    };
                }
        );

    }



}
class Movie {
    public  int getRate(){
        return 1;
    }

}
