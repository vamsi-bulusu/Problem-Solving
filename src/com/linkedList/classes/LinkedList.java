package com.linkedList.classes;
import com.linkedList.interfaces.List;
import java.util.*;

public class LinkedList<T> implements List<T> {

    private Node head;

    public LinkedList(T[] numbers){
        for (T number : numbers) {
            add(number);
        }
    }
    public void add(T data){
        if(this.head == null){
            this.head = new Node<>(data);
        }
        else{
            Node temp = this.head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node<>(data);
        }
    }

    public void createLoop(int numNodes){
        Node temp = this.head, prev = null;
        if(temp == null)  return;
        Node[] nodes = new Node[numNodes];
        int start = 0;
        while (temp != null){
            nodes[start] = prev = temp;
            temp = temp.next; start++;
        }
        Random random = new Random();
        int loopToNode = random.nextInt(numNodes);
        prev.next = nodes[loopToNode];
    }

    public Boolean detectLoop(){
        Set<Node> set = new HashSet<>();
        Node node = this.head;
        while(node != null){
            if(set.contains(node))
                return true;
            set.add(node);
            node = node.next;
        }
        return false;
    }
    public void display(){
        Node temp = this.head;
        while (temp != null){
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node middle(){
        Node slow = this.head, fast = this.head;
        while (fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
        }
        return slow;
    }

    public void reverse(){
        Node current = this.head, nextNode, prev = null;
        while(current != null){
            nextNode = current.next; current.next = prev;
            prev = current ; current = nextNode;
        }
        this.head = prev;
    }
    public int size(){
        int length = 0;
        Node curr = this.head;
        while(curr != null){
            curr = curr.next; length++;
        }
        return length;
    }

}
