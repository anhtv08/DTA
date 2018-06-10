package com.training.dta.core.graph;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class MyGraph<Node> {

   Node root;

   public MyGraph (Node root){
        this.root = root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public MyGraph(){}

    static  class  Node<E>{
        E data;
        List<Node> relatedNodes;

       public Node(){}

        public void setData(E data) {
            this.data = data;
        }

        public Node (E data){
            this.data = data;
            relatedNodes = new LinkedList<>();
        }
        public void setRelatedNodes(List<Node> relatedNodes) {
            this.relatedNodes = relatedNodes;
        }
        public List<Node> getRelatedNodes() {
            return relatedNodes;
        }
    }

    public  void traversal(){

    }
    public Optional<MyGraph.Node> findNode(MyGraph.Node node){
       return Optional.empty();
    }

}
