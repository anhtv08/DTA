package com.training.dta.core;

import java.util.*;
import java.util.LinkedList;

/**
 * Created by anhtrang on 9/9/17.
 */
public class BinaryTree {


    NodeItem root;

    public void insertNode(NodeItem nodeItem){

        System.out.println("inserting item:" + nodeItem.key);
        NodeItem current = root;
        NodeItem prev =null;
        if (current ==null){
             root = nodeItem;
        } else{

            // finding position to insert
             while (current!=null){
                 prev = current;
                 if (  nodeItem.key < current.key ) current = current.left;
                 else{
                     current = current.right;
                 }
             }

        }

        if (prev!=null){
            if (prev.key > nodeItem.key) prev.left = nodeItem;
            else prev.right = nodeItem;
        }
    }

     public NodeItem search (NodeItem nodeItem){
         // check if empty tree
         if (root==null) return  null;

          NodeItem current = root;
         while(current!=null){

             // move left
             if (current.key > nodeItem .key) current = current.left;
             // move right
             else if (current.key < nodeItem.key) current =current.right;
             else break;
         }

        return  current;
     }

    public boolean deleteNode (NodeItem nodeItem){

        System.out.println("deleting node item :" + nodeItem.key);
        boolean result  =false;

        // find node to be deleted.
        // following scenario will be considered.
        // 1. if node is a leaf, then just remove this node and set the child of its parent to null.
        // 2. if node has one child, then just remove it, and snip it's child as child of its parent
        // 3. if node has two child.
        // 3.1 find successor, then move its successor to replace it.
        // the successor will left most element of its right sub-tree.

       NodeItem parent = root;
       NodeItem current = root;
       boolean hasLeftChild = false;
        boolean hasRightChild = false;

        if (current ==null) return  false;

        while(current !=null){
           // parent = current;

            // move right
            if (current.key < nodeItem.key) {

                if (current.right !=null) {
                    parent = current;
                    hasRightChild = true;
                }

                current = current.right;
            } else if (current.key >  nodeItem.key){

                if (current.left != null) {
                    parent = current;
                    hasRightChild = true;
                }
                current = current.left;
            } else {
                hasRightChild = current.right!=null? true: false;
                hasLeftChild = current.left!=null? true: false;

                break;
            }
        }


        // found if current is not null.
        if (current ==null) return false;

        else {

            // if node is root then empty tree
            if (current.key == root.key) root = null;

            // if found node is leaf.
            if (!hasLeftChild && !hasRightChild ){
                if (parent.right !=null && parent.right.key == current.key)
                    parent.right = null;
                if (parent.left!=null && parent.left.key == current.key)
                    parent.left = null;

            } else if ( hasLeftChild ==true && hasRightChild ==false){

                if (parent.right !=null && parent.right.key == current.key)
                    parent.right = current.left;
                if (parent.left!=null && parent.left.key == current.key)
                    parent.left = current.left;
            } else if ( hasLeftChild ==false && hasRightChild ==true){

                if (parent.right !=null && parent.right.key == current.key)
                    parent.right = current.right;
                if (parent.left!=null && parent.left.key == current.key)
                    parent.left = current.right;
            } else {

                // node has both left and right child
                // TODO: to be completed tomorrow
                // find the successor in the subtree with the right child node as a root node of sub tree

                NodeItem successorParentNode = current.right;
                NodeItem currentSuccessorNode = current.right;

                while (currentSuccessorNode.left!=null){
                    successorParentNode = currentSuccessorNode;
                    currentSuccessorNode = currentSuccessorNode.left;
                }
                // assign the left child of successor parent to null
                successorParentNode.left = null;

                // successor node will become a right child of the parent of the deleting node.
                parent.right = currentSuccessorNode;
                currentSuccessorNode.left = current.left;


            }

            // if found node has one child.


        }

        return  result;
    }
    public void printTree (NodeItem current){
        if (current ==null) {
            return;
        }
        System.out.print(current.key + " ");
        printTree(current.left);
        printTree(current.right);

    }



    public static void main(String []args ){
        //Node  =

        BinaryTree testTree = new BinaryTree();

        NodeItem item = new NodeItem(1);
        NodeItem item1 = new NodeItem(3);
        NodeItem item2= new NodeItem(2);
        NodeItem item3 = new NodeItem(4);
        NodeItem item4 = new NodeItem(5);
        NodeItem item5 = new NodeItem(10);
        NodeItem item6 = new NodeItem(7);
        NodeItem item7 = new NodeItem(9);


        testTree.insertNode(item);
        testTree.insertNode(item1);
        testTree.insertNode(item2);
        testTree.insertNode(item3);
        testTree.insertNode(item4);
        testTree.insertNode(item5);
        testTree.insertNode(item6);
        testTree.insertNode(item7);


        testTree.printTree(testTree.root);

        testTree.deleteNode(item5);
        testTree.printTree(testTree.root);

       // System.out.println(testTree.search(item4).key);
    }



    public void topView(Node root) {


        Set<Integer> set = new HashSet<>();


        Queue<QItem> queue = new LinkedList<>();

        QItem qItem = new QItem( 0, root);
        queue.add(qItem);

        while(queue.size()>0){

            QItem temp= queue.poll();
            if (!set.contains(temp.hd)) {  // the set is to track whether node is seen or not
                System.out.print(temp.node.data + " ");
            }
            if (temp.node.left !=null){
                QItem qItem1 = new QItem(temp.hd -1,temp.node.left);
                queue.add(qItem1);
            }
            if (temp.node.right !=null){
                QItem qItem2 = new QItem(temp.hd -1,temp.node.right);
                queue.add(qItem2);
            }


        }


    }

    static class  NodeItem {
        int key;
        NodeItem right;
        NodeItem  left;

        public  NodeItem(int key){
            this.key = key;
        }

    }

    static class QItem {
        int hd; //horizontal distance from root
        Node node;

        QItem(int hd, Node node){
            this.hd = hd;
            this.node = node;
        }


    }

    static class Node{
        int data;
        Node left;
        Node right;

    }



}
