package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class DijkstraAdjMatrix {

    private int[][] adjMatrix;
    private Boolean[] visited;


    private DijkstraAdjMatrix(int vertices) {
        this.visited = new Boolean[vertices];
        this.adjMatrix = new int[vertices][vertices];
        Arrays.fill(this.visited,false);
        for(int i = 0;i < this.adjMatrix.length;i++){
            for(int j = 0;j < this.adjMatrix.length;j++){
               this.adjMatrix[i][j] = 0;
            }
        }

    }

    public void add(int src, int des, int weight){
        this.adjMatrix[src-1][des-1] = weight;
    }

    private int minDis(int[] distance){
        int min_value = Integer.MAX_VALUE, min_index = 0;
        for(int i = 0;i < distance.length;i++){
            if(!this.visited[i] && distance[i] < min_value){
                min_value = distance[i];
                min_index = i;
            }
        }
        return min_index;
    }

    private void printDistance(int[] distance){
        for(int i = 1;i < distance.length;i++){
            System.out.print(distance[i] + " ");
        }
    }
    private void printMatrix(){
        for(int i = 0;i < this.adjMatrix.length;i++){
            for (int j = 0;j < this.adjMatrix.length;j++){
                System.out.print(this.adjMatrix[i][j]+ " ");
            }
            System.out.println();
        }
    }
    private void dijkstra(int vertices){
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[0] = 0;
        for(int i = 0;i < vertices;i++){
            int minDistanceVertex = minDis(distance);
            this.visited[minDistanceVertex] = true;
            for(int v = 0;v < vertices;v++){
                if(!this.visited[v] && this.adjMatrix[minDistanceVertex][v]!= 0 && distance[minDistanceVertex] != Integer.MAX_VALUE){
                    int minVal = distance[minDistanceVertex] + this.adjMatrix[minDistanceVertex][v];
                    if(minVal < distance[v]){
                        distance[v] = minVal;
                    }
                }
            }
        }
        printDistance(distance);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = bufferedReader.readLine().split("\\s+");
        int vertices = Integer.parseInt(buffer[0]);
        int edges = Integer.parseInt(buffer[1]);
        DijkstraAdjMatrix dObj = new DijkstraAdjMatrix(vertices);
        for(int i = 0;i < edges;i++){
            String[] edge = bufferedReader.readLine().split("\\s+");
            int src = Integer.parseInt(edge[0]);
            int des = Integer.parseInt(edge[1]);
            int weight = Integer.parseInt(edge[2]);
            dObj.add(src, des, weight);
        }
        //dObj.printMatrix();
        dObj.dijkstra(vertices);
    }
}
