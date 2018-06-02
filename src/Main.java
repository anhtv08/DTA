import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        LinkedList myLink  = new LinkedList();
        Node node = new Node(1);
        Node node1 = new Node(2);
        Node node3 = new Node(4);
        Node node2 = new Node(3);
        Node node5 = new Node(6);
        Node node4 = new Node(5);
        myLink.add(node);
        myLink.add(node1);
        myLink.add(node2);
        myLink.add(node3);
        myLink.add(node4);
        myLink.add(node5);

        myLink.display();
        myLink.deleteFirst();
        myLink.display();
        System.out.println(myLink.find(node1));


    }
}

class MyArray<E>{

    void add (E element){}
    void delete (E element){}
    E find (E element){ return null;}
    E update (E element){ return  null;}

}

class LinkedList {
    Node head;
    void add ( Node element)
    {
        // if list is empty
         if(head ==null) head = element;
         else {
            element.next =head;
            head = element;
         }
    };

     Node find( Node element){

       Node current = head;
       while(current!=null&& current.idata != element.idata ){
           current = current.next;
       }

       return current;
     };


    void deleteFirst ()
    {
        head = head.next;
    }

    void delete(Node node){
        Node current;
        //
    }
    public void display(){
        Node current =  head;
        while(current!=null ){
            System.out.println(current);
            current = current.next;

        }
    }

}
class Node{
    public int idata;
    public  Node next;

    public  Node (int idata){
         this.idata = idata;
        next = null;
    }

    @Override
    public String toString() {
        return "[" + idata + " ]";
    }
}
