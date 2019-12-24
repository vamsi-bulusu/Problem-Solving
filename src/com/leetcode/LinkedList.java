package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class LinkedList {
    private Node head;
    static class Node{
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    private LinkedList(String[] numbers){
        for (String number : numbers) {
            int value = Integer.parseInt(number);
            insert(value);
        }
    }
    private void insert(int data){
        if(this.head == null){
            this.head = new Node(data);
        }
        else{
            Node temp = this.head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    private void createLoop(Node head, int numNodes){
        Node temp = head, prev = null;
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

    private  Boolean detectLoop(Node head){
        Set<Node> set = new HashSet<>();
        Node node = head;
        while(node != null){
            if(set.contains(node))
                return true;
            set.add(node);
            node = node.next;
        }
        return false;
    }
    private void display(Node head){
        Node temp = head;
        while (temp != null){
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private Node middle(Node head){
        Node slow = head, fast = head;
        while (fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
        }
        return slow;
    }

    private Node reverse(Node head){
        Node current = head, nextNode, prev = null;
        while(current != null){
            nextNode = current.next; current.next = prev;
            prev = current ; current = nextNode;
        }
        return prev;
    }
    private int size(Node head){
        int length = 0;
        Node curr = head;
        while(curr != null){
            curr = curr.next;
            length++;
        }
        return length;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(bufferedReader.readLine().trim());
        String[] numbers = Arrays.copyOf(bufferedReader.readLine().split("\\s+"),numNodes);
        LinkedList linkedList = new LinkedList(numbers);
        linkedList.head = linkedList.reverse(linkedList.head);
        linkedList.display(linkedList.head);
        System.out.println(linkedList.middle(linkedList.head));
        linkedList.createLoop(linkedList.head, linkedList.size(linkedList.head));
        System.out.println(linkedList.head.data);
        System.out.println(linkedList.detectLoop(linkedList.head));
    }
}
