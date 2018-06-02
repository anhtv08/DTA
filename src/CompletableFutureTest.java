import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by anhtrang on 20/9/17.
 */
public class CompletableFutureTest {


    public static void main (String [] arrs) throws ExecutionException, InterruptedException {


        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync((() ->"test1"));
        CompletableFuture<String> completableFuture1= completableFuture.thenCombine(CompletableFuture.supplyAsync( ()-> "test"), ( v1,v2)-> v1 + v2);

        String value = (String)CompletableFuture.anyOf(completableFuture, completableFuture1).get();

        System.out.println(value);
    }
}
