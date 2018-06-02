package com.training.dta.core;

import java.util.*;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Created by anhtrang on 9/9/17.
 */
public class Graph {


    public static void main (String [] arg){

        Node node1 = new Node(1,1.2f);
        Node node2 = new Node(2,1.3f);
        Node node3 = new Node(3,1.4f);
        Node node4 = new Node(4,1.5f);
        Node node5 = new Node(5,1.6f);

        node1.addRelatedNode(node2);
        node1.addRelatedNode(node3);
//        node2.addRelatedNode(node4);
        node3.addRelatedNode(node4);
        node4.addRelatedNode(node5);

        List<Node> temp = new ArrayList<>();

       int sum = dfs_calculate(node1,temp);

        System.out.println("DFS:" + sum);

        //sum = calcualateGraphPoints(node1);

        //System.out.println("BFS:" + sum);
        //printAllBranch(node1, temp);




       /* System.out.println(visitRateNode(node1, 2));
        System.out.println(visitRateNode(node1, 3));
        System.out.println(visitRateNode(node1,1));

        System.out.println(visitRateNode(node2, 2));

        System.out.println(visitRateNode(node3, 2));

        System.out.println(vistAllNodeBFS(node3, 2));



        System.gc();


        int [] data ={1,4,5,6,6,7,7,3,2,1,0,1,2,3,4,8,5};
       Integer []result = findPeeks(data);

        for (Integer item: result){
            System.out.println(item);
        }

*/


    }

    static Integer  [] findPeeks(int [] data){
         List<Integer> result = new ArrayList<Integer>();
         int max = data[0];
         boolean isIncrease = false; // flag to indicate the graph is upside or downside
         for (int i =1 ; i < data.length ; i++){

             // if current value is greater than max then set max to current value
             // set flag to true
             if (max <= data[i] ){
                   max = data[i];
                  isIncrease =true;
              } else {

                 // if the trend change
                 // and upside is true then current value is a peek
                 // add current peek into the result list
                 // set flag to false
                  if(isIncrease){
                      result.add(max);
                      isIncrease =false;
                  }

              }
         }
         Integer[] arr = new  Integer[result.size()];
          result.toArray(arr);
         return  arr;
     }

        public static List<Node> vistAllNodeBFS (Node node, int selectedNumber){

        List<Node> nodes  = new ArrayList<>();

        // using queue for bread first search algorithm
        Queue<Node> queue  = new LinkedList<>();
        queue.add(node);
        while(queue.size()>0){

            // dequee the visited item.
            Node temp=  queue.poll();

            // check if node is visited.
            if(!nodes.contains(temp)){
                nodes.add(temp);
                List<Node> children =temp.getRelatedNodes();

                for (Node item : children){
                    queue.add(item);
                }

            }

        }

        nodes.remove(node);

        Collections.sort(nodes);

        return nodes.stream().limit(selectedNumber).collect(Collectors.toList());
    }

    public static int dfs_calculate(Node node, List<Node> track){
        int sum = node.data;
        track.add(node);
        if (node.getRelatedNodes().size()==0){
            printNode(track);
            track.remove(node);
        }
        for(Node item: node.getRelatedNodes()){
            if(track.contains(item))continue;
            sum += dfs_calculate(item, track);
        }
        return sum;
    }

     static void printNode(List<Node> nodes){

         StringJoiner stringJoiner = new StringJoiner("->");
         for (Node node : nodes){
            stringJoiner.add(node.data +"");
         }
         System.out.println(stringJoiner.toString());
     }
    public static int calcualateGraphPoints (Node node){

        int sum = 0;
        if (node== null) return  sum;

        else {
            List<Node> nodes  = new ArrayList<>(); //  the list to keep tracking of visited items

            // using queue for bread first search algorithm
            Queue<Node> queue  = new LinkedList<>();
            System.out.println("adding root node:" + node.data);
            queue.add(node);
            while(queue.size()>0){

                // dequeue the visited item.
                Node temp=  queue.poll();
                // add value of visited item into the sum
                sum += temp.data;

                // check if node is visited.
                if(!nodes.contains(temp)){

                    System.out.println("Adding current node into tracking list :"  + temp.data);
                    nodes.add(temp);  //
                    List<Node> children =temp.getRelatedNodes();

                    for (Node item : children){
                        System.out.println("Adding a child into Queue:" + item.data);
                        queue.add(item);
                    }

                }

            }
        }

        return  sum;
    }

     static  void printAllBranch(Node node, List<Node> branch){
         if (node!=null){
             branch.add(node);
             List<Node> related = node.getRelatedNodes();

             if (related.size()==0){
                 printNode(branch);
                 // remove the node leaf
                  branch.remove(node);
                 // remove the l
             }

             if (related!=null && related.size()>0){

                 for(Node item : related){
                     printAllBranch(item, branch);
                 }
             }
         }
     }

    static  void printCurrentBranch(List<Integer> branch){

        StringJoiner stringJoiner = new StringJoiner(",");

        for (Integer item : branch){
            stringJoiner.add(item +"");
        }
        System.out.println(stringJoiner.toString());
    }



    public static List<Node> visitRateNode(Node root, int selectedNumber){

        // using depth first search.
        List<Node> relatedNode  = new ArrayList<>();
        fillnetworkNodes(relatedNode, root);

        relatedNode.remove(root);

        Collections.sort(relatedNode);

       return relatedNode.stream().limit(selectedNumber).collect(Collectors.toList());



    }

    private static  void  fillnetworkNodes( List<Node> nodes, Node node){

        if (!nodes.contains(node)){

            System.out.println("adding node :" + node.getData());
            nodes.add(node);
            // get all related node.
             List<Node> relatedNode =  node.getRelatedNodes();
            for (Node item: relatedNode){
                fillnetworkNodes(nodes,item);
            }
        }
    }

    private  static class Node implements Comparable<Node> {

        private  int data;
        private  float ranking;
        List<Node> relatedNodes;

        public Node(int data, float ranking){
            this.data = data;
            this.ranking = ranking;
             relatedNodes = new ArrayList<>();
        }

        public Node (int data){
            this.data  = data;
            this.relatedNodes = new ArrayList<>();
        }

        public List<Node> getRelatedNodes() {
            return relatedNodes;
        }

        public int getData() {
            return data;
        }

        public void addRelatedNode(Node node)
        {
            this.relatedNodes.add(node);
           // node.addRelatedNode(this);

        }

        public void setRelatedNodes(List<Node> relatedNodes) {
            this.relatedNodes = relatedNodes;
        }

        public void setData(int data) {
            this.data = data;
        }

        public float getRanking() {
            return ranking;
        }

        @Override
        public int compareTo(Node o) {
             if (this.getRanking()> o.getRanking() ) return  -1 ;
            if (this.getRanking() <o.getRanking() ) return  1 ;
            else return  0;
        }

        @Override
        public String toString() {
            return "node :"  + this.getData();
        }

    }
}
