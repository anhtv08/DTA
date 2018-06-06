package com.training.dta.misc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *   remove the list an element from the list
 *   be cautious when removing the item from collection. It might throw the Concurrent modification exception
 *   There are 2 way to remove items from collection.
 *   1. using iterator
 *   2. using stream API to filter out the unwanted element
 */
public class CollectionsUtils {

    List<Aninmal> aninmalList = new LinkedList<>();
    interface  Aninmal{};
    static class   Dog implements com.training.dta.misc.CollectionsUtils.Aninmal {

    };

    boolean remove (Aninmal aninmal){

        boolean removedFlag = false;
        Iterator<Aninmal> aninmalIterator  = aninmalList.iterator();
        while(aninmalIterator.hasNext()){
            if (aninmalIterator.next().equals(aninmal)){
                aninmalIterator.remove();
                removedFlag = true;
            }
        }
        return  removedFlag;
    }

    static class   Cat implements Aninmal {} ;
    public static void main(String... args){
        List<Aninmal> aninmalList = new LinkedList<>();
        Dog dog = new Dog();
        Cat cat = new Cat();
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        aninmalList.add(dog);
        aninmalList.add(cat);
        aninmalList.add(cat1);
        aninmalList.add(cat2);

//        List<Aninmal> toRemovedList = new LinkedList<>();

//        Iterator<Aninmal> aninmalIterator = aninmalList.iterator();
//        while (aninmalIterator.hasNext()){
//            if(aninmalIterator.next() instanceof Cat){
//                aninmalIterator.remove();
//            }
//        }

//        System.out.println(aninmalList.size());

        List<Aninmal> resultList = aninmalList.stream()
                .filter(item -> !(item instanceof Cat))
                .collect(Collectors.toList());

        System.out.println(resultList.size());
//        for(Aninmal aninmal: aninmalList){
//
//            if (aninmal instanceof  Cat){
//                aninmalList.iterator().remove();
////                aninmalList.remove(aninmal);
////              toRemovedList.add(aninmal);
//            }
//        }


    }
}
