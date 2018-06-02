import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by anhtrang on 20/9/17.
 */
public class MyHashMap<K,V> {

    // using array to implement map.

    Object [] values;
    Object [] keys;

    int size;
    public  MyHashMap(int size){
        this.size = size;
        keys = new Object [size];
        values = new Object[size];

    }

    public void putNewNode (final K key, final  V value){
         int index = key.hashCode()%size;
        if ( keys[index] ==null) {
            keys[index] = key;
            values[index] =value;

        } else{
            values[index] = value;
        }
    }

    public V getValue( final  K key){
       int index = key.hashCode()%this.size;
        return  (V)values[index];
    }

    public List<K> getKeys (){

        List<K> list = new ArrayList<>();
        for (Object obj : this.keys){
            list.add((K) obj);

        }
        return  list;


    }

    public List<V> getValues (){
        List<V> list = new ArrayList<>();
        for (Object obj : this.values){
            list.add((V) obj);

        }
        return  list;

    }

    static class Node {
        int data;
        public Node( int data){
            this.data = data;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    public  static  void main (String [] arr){
        MyHashMap<String, String> testMap =  new MyHashMap<>(10);
        testMap.putNewNode("key1", "value1");
        testMap.putNewNode("key2", "value2");
        testMap.putNewNode("key3", "value3");
        testMap.putNewNode("key4", "value4");
        testMap.putNewNode("key1", "value5");
        String value = testMap.getValue("key1");

        System.out.println(testMap.getKeys());

        assert value.equals("value2");

        IntStream.range(1,10).forEach((i)->{
            System.out.println(i);
        });


    }


}
