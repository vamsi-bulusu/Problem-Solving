package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;


public class PrimsMST {

    private LinkedList<LinkedList<Edge>> list;
    private Boolean[] visited;
    private int[] key;
    static class Edge{
        int V,weight;
        Edge(int v, int weight) {
            V = v;
            this.weight = weight;
        }
    }

    private PrimsMST(int numNodes) {
        this.list = new LinkedList<>();
        this.visited = new Boolean[numNodes];
        this.key = new int[numNodes];
        Arrays.fill(this.key,Integer.MAX_VALUE);
        Arrays.fill(this.visited, false);
        for(int i = 0;i < numNodes;i++){
            this.list.add(new LinkedList<>());
        }
    }

    private int nextMinCost(){
        int min_weight = Integer.MAX_VALUE;
        int index = 0;
        for (int val = 0;val < this.key.length;val++) {
            if (!this.visited[val] && this.key[val] < min_weight) {
                min_weight = this.key[val];
                index = val;
            }
        }
        return index;
    }

    private int prim(){
        this.key[0] = 0;
        int minCost = 0;
        for (int i = 0;i < this.key.length;i++){
            int selectMinVertex = nextMinCost();
            minCost += this.key[selectMinVertex];
            this.visited[selectMinVertex] = true;
            LinkedList<Edge> edges = this.list.get(selectMinVertex);
            for (Edge temp : edges) {
                int vertex = temp.V, weight = temp.weight;
                if (!this.visited[vertex] && weight < this.key[vertex]) {
                    this.key[vertex] = weight;
                }
            }
        }
        return minCost;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int numNodes = Integer.parseInt(buffer[0]);
        int numEdges = Integer.parseInt(buffer[1]);
        PrimsMST mst = new PrimsMST(numNodes);
        for(int i = 0;i < numEdges;i++){
            String[] edge = bufferedReader.readLine().split("\\s+");
            int src = Integer.parseInt(edge[0]);
            int des = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);
            mst.list.get(src-1).add(new Edge(des-1,weight));
            mst.list.get(des-1).add(new Edge(src-1,weight));
        }
        System.out.println(mst.prim());
    }
}
