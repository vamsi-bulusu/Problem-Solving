package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MinimumSpanningTree {

    private Queue<Node> queue;
    private int[] parent;//Denotes the representative of the set
    private int[] rank;//Denotes the height of set
    static class Node{
        int U,V, weight;
        Node(int U,int V,int weight){
            this.U = U; this.V = V;
            this.weight = weight;
        }
    }

    private MinimumSpanningTree(int numNodes, int numEdges) {
        this.parent = new int[numNodes];
        this.rank = new int[numNodes];
        this.queue = new PriorityQueue<>(numEdges, Comparator.comparingInt(o -> o.weight));
        for(int i = 0;i < numNodes;i++){
            this.parent[i] = i;
            this.rank[i] = 0;
        }
    }

    private void display() {
        for (Node item : this.queue) {
            System.out.print(item.weight + " ");
        }
    }

    private void add(Node node){
        this.queue.add(node);
    }

    private int find(int val){
        if(this.parent[val] == val){
            return val;
        }
        int result =  find(this.parent[val]);
        this.parent[val] = result;
        return result;
    }

    private void union(int src, int des){
        if(src == des) return;
        if(this.rank[src] < this.rank[des]){
            this.parent[src] = des;
        }
        else if(this.rank[des] < this.rank[src]){
            this.parent[des] = src;
        }
        else{
            this.parent[des] = src;
            rank[src]++;
        }
    }

    private int kruskalsAlgorithm(int numNodes) {
        int mstEdges = 0, sum = 0;
        while(mstEdges < numNodes - 1){
            Node temp = this.queue.poll();
            if(temp != null) {
                int parent_U = find(temp.U);
                int parent_V = find(temp.V);
                if (parent_U != parent_V) {
                    sum += temp.weight;
                    mstEdges++;
                    union(parent_U, parent_V);
                }
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int numNodes = Integer.parseInt(buffer[0]);
        int numEdges = Integer.parseInt(buffer[1]);
        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree(numNodes, numEdges);
        for(int i = 0;i < numEdges;i++){
            String[] input = bufferedReader.readLine().split("\\s+");
            int startNode =  Integer.parseInt(input[0]);
            int endNode   =  Integer.parseInt(input[1]);
            int weight    =  Integer.parseInt(input[2]);
            minimumSpanningTree.add(new Node(startNode-1,endNode-1,weight));
        }
        System.out.println(minimumSpanningTree.kruskalsAlgorithm(numNodes));
        minimumSpanningTree.display();
    }
}
