package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Dijkstra {

    private List<LinkedList<Edge>> list;
    private Boolean[] visited;
    private int[] distance;
    static class Edge{
        int des, weight;
        Edge(int des, int weight){
            this.des = des;
            this.weight = weight;
        }
    }

    private Dijkstra(int V) {
        this.visited = new Boolean[V];
        this.distance = new int[V];
        Arrays.fill(this.distance, Integer.MAX_VALUE);
        Arrays.fill(this.visited, false);
        this.list = new LinkedList<>();
        for(int i = 0;i < V;i++){
            this.list.add(new LinkedList<>());
        }
    }

    private void printDistanceArray(int source){
        //System.out.println("Distance from "+ source);
        for(int i:this.distance){
            System.out.print(i+" ");
        }
    }

   private void dijkstra(int source,int E){
       Queue<Edge> queue = new PriorityQueue<>(E, Comparator.comparingInt(o -> o.weight));
       queue.add(new Edge(source, 0));
       this.distance[source] = 0;
       while (!queue.isEmpty()){
           Edge temp = queue.poll();
           this.visited[temp.des] = true;
           LinkedList<Edge> edges = this.list.get(temp.des);
           for(Edge edge : edges){
               if(!this.visited[edge.des] && this.distance[temp.des] != Integer.MAX_VALUE && this.distance[temp.des] + edge.weight < this.distance[edge.des]){
                   this.distance[edge.des] = this.distance[temp.des] + edge.weight;
                   queue.add(edge);
               }
           }
       }
       printDistanceArray(source);
   }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int N = Integer.parseInt(buffer[0]);
        int E = Integer.parseInt(buffer[1]);
        Dijkstra dObj = new Dijkstra(N);
        for(int i = 0;i < E;i++){
            String[] edges = bufferedReader.readLine().split("\\s+");
            int src = Integer.parseInt(edges[0]);
            int des = Integer.parseInt(edges[1]);
            int weight = Integer.parseInt(edges[2]);
            dObj.list.get(src-1).add(new Edge(des-1, weight));
            dObj.list.get(des-1).add(new Edge(src-1, weight));
        }
        int source = Integer.parseInt(bufferedReader.readLine().trim());
        dObj.dijkstra(source, E);
    }
}
