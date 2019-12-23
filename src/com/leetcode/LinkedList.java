package com.leetcode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
    private static void insert(LinkedList linkedList, int data){
        if(linkedList.head == null){
            linkedList.head = new Node(data);
        }
        else{
            Node temp = linkedList.head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(data);
        }
    }

    private static LinkedList createLoop(LinkedList linkedList, int numNodes){
        Node temp = linkedList.head, prev = null;
        if(temp == null)  return null;
        Node[] nodes = new Node[numNodes];
        int start = 0;
        while (temp != null){
            nodes[start] = prev = temp;
            temp = temp.next; start++;
        }
        Random random = new Random();
        int loopToNode = random.nextInt(numNodes);
        prev.next = nodes[loopToNode];
        return linkedList;
    }

    private static Boolean detectLoop(LinkedList linkedList){
        Map<Node,Integer> map = new HashMap<>();
        Node node = linkedList.head;
        while(node != null){
            if(map.containsKey(node))
                return true;
            map.put(node,0);
            node = node.next;
        }
        return false;
    }
    private static void display(LinkedList linkedList){
        Node temp = linkedList.head;
        while (temp != null){
            System.out.print(temp.data+ " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static Node middle(LinkedList linkedList){
        Node slow = linkedList.head, fast = linkedList.head;
        while (fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
        }
        return slow;
    }

    private static Node reverse(LinkedList linkedList){
        Node current = linkedList.head, nextNode, prev = null;
        while(current != null){
            nextNode = current.next; current.next = prev;
            prev = current ; current = nextNode;
        }
        return prev;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(bufferedReader.readLine().trim());
        LinkedList linkedList = new LinkedList();
        for(int i = 0;i < numNodes;i++) {
            int data = Integer.parseInt(bufferedReader.readLine().trim());
            insert(linkedList, data);
        }
        Node middle = middle(linkedList);
        System.out.println(middle.data);
        linkedList.head = reverse(linkedList);
        display(linkedList);
        linkedList = createLoop(linkedList, numNodes);
        assert linkedList != null;
        System.out.println(detectLoop(linkedList));
    }
}
