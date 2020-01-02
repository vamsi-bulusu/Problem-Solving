package com.graphs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ArticulationPoint {
    private List<LinkedList<Integer>> edges;
    private Boolean[] visited;
    private Boolean[] articulationPoint;
    private int[] id;
    private int[] lowLink;
    private int start;


    private ArticulationPoint(int vertices) {
        this.start = 0;
        this.visited = new Boolean[vertices];
        this.articulationPoint = new Boolean[vertices];
        this.id = new int[vertices];
        this.lowLink = new int[vertices];
        this.edges = new LinkedList<>();
        for(int i = 0;i < vertices;i++){
            this.edges.add(new LinkedList<>());
            this.visited[i] = this.articulationPoint[i] = false;
        }
    }

    private void add(int src, int des){
        this.edges.get(src-1).add(des-1);
        this.edges.get(des-1).add(src-1);
    }

    private void dfs(int src, int parent){
        this.visited[src] = true;
        this.id[src] = this.lowLink[src] = start++;
        LinkedList<Integer> list = this.edges.get(src);
        for(int curr = 0;curr < list.size();curr++){
            if(curr == parent) continue;
            if(!this.visited[curr]){
                dfs(curr, src);
                this.lowLink[src] = Math.min(this.lowLink[curr], this.lowLink[src]);

                if(this.lowLink[curr] >= this.id[src]){
                    this.articulationPoint[src] = true;
                }
            }
            else{
                this.lowLink[src] = Math.min(this.lowLink[src], this.id[curr]);
            }
        }

    }

    private void getArticulationPointsAndBridges(int source){
        dfs(source,0);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer = reader.readLine().split("\\s+");
        int vertices = Integer.parseInt(buffer[0]);
        int edges = Integer.parseInt(buffer[1]);
        ArticulationPoint articulationPoint = new ArticulationPoint(vertices);
        for(int i = 0;i < edges;i++){
            String[] edge = reader.readLine().split("\\s+");
            int src = Integer.parseInt(edge[0]);
            int des = Integer.parseInt(edge[1]);
            articulationPoint.add(src,des);
        }

    }
}
