package com.training.dta.core.graph;

import com.training.dta.base.AbstractBaseTest;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class MyGraphTest extends AbstractBaseTest {

    MyGraph<MyGraph.Node> graph;
    @Before
    public void setup(){
        graph = new MyGraph<>();
        initGraphFromData();
    }

    private void initGraphFromData(){

        // data 1->2; 1->3; 1->4
        // 2-5; 2->6
        // 3-> 7, 3->7
        // 4->9, 4->10
        MyGraph.Node<Integer> node1 = new MyGraph.Node<>(1);

        MyGraph.Node<Integer> node2 = new MyGraph.Node<>(2);
        MyGraph.Node<Integer> node3 = new MyGraph.Node<>(3);
        MyGraph.Node<Integer> node4 = new MyGraph.Node<>(4);
        List<MyGraph.Node> childOfNode1 = Stream.of(node2, node3,node4 ).collect(Collectors.toList());

        node1.setRelatedNodes(childOfNode1);

        MyGraph.Node<Integer> node5 = new MyGraph.Node<>(5);
        MyGraph.Node<Integer> node6 = new MyGraph.Node<>(6);

        List<MyGraph.Node> childOfNode2 = Stream.of(node5, node6).collect(Collectors.toList());
        node2.setRelatedNodes(childOfNode2);


        MyGraph.Node<Integer> node7 = new MyGraph.Node<>(7);
        MyGraph.Node<Integer> node8 = new MyGraph.Node<>(8);
        List<MyGraph.Node> childOfNode3 = Stream.of(node7, node8 ).collect(Collectors.toList());

        node3.setRelatedNodes(childOfNode3);


        MyGraph.Node<Integer> node9 = new MyGraph.Node<>(9);
        MyGraph.Node<Integer> node10 = new MyGraph.Node<>(10);

        List<MyGraph.Node> childOfNode4 = Stream.of(node9, node10).collect(Collectors.toList());

        node4.setRelatedNodes(childOfNode4);
        graph.setRoot(node1);

    }

    @Test
    public void traversal(){
        graph.traversal();
    }

    @Test
    public void findNode(){

        MyGraph.Node<Integer> testNode = new MyGraph.Node<>(1);
        assertEquals(true, graph.findNode(testNode).isPresent());

    }

}