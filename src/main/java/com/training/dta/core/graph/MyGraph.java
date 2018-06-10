package com.training.dta.core.graph;
import java.util.LinkedList;
import java.util.List;
public class MyGraph<T> {

    T data;
    List<T> relatedNodes;

   public MyGraph (T data){
        this.data = data;
        relatedNodes = new LinkedList<>();
    }
    public void setRelatedNodes(List<T> relatedNodes) {
        this.relatedNodes = relatedNodes;
    }
    public List<T> getRelatedNodes() {
        return relatedNodes;
    }

}
