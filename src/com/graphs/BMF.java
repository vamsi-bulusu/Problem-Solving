package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BMF {

    static class Edge{

        int src, des, weight;

        Edge(int src, int des, int weight) {
            this.src = src;
            this.des = des;
            this.weight = weight;
        }
    }
    private List<Edge> edges;
    private int V, E;
    private BMF(int numNodes, int numEdges){
        this.V = numNodes;
        this.E = numEdges;
        this.edges = new LinkedList<>();
    }

    private void add(int src, int des, int weight){
        this.edges.add(new Edge(src, des, weight));
    }

    private void printArray(int[] dist){
        for(int i = 1;i < dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }


    private void bmf(int source){
        int[] dist = new int[this.V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for(int i = 0;i < this.V-1;i++){
            for(int j = 0;j < this.E;j++){
                Edge temp = this.edges.get(j);
                if(dist[source] != Integer.MAX_VALUE && dist[temp.src] + temp.weight < dist[temp.des]){
                    dist[temp.des] = dist[temp.src] + temp.weight;
                }
            }
        }
        printArray(dist);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int numNodes = Integer.parseInt(buffer[0]);
        int numEdges = Integer.parseInt(buffer[1]);
        BMF bmf = new BMF(numNodes, numEdges);
        for(int i = 0;i < numEdges;i++){
            String[] buffer1 = bufferedReader.readLine().split("\\s+");
            int source = Integer.parseInt(buffer1[0]);
            int destination = Integer.parseInt(buffer1[1]);
            int weight = Integer.parseInt(buffer1[2]);
            bmf.add(source, destination, weight);
        }
        int source = Integer.parseInt(bufferedReader.readLine().trim());
        bmf.bmf(source);
    }
}
