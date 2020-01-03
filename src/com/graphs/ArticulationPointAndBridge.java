package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class ArticulationPointAndBridge {


    static class Edge{
        int src, des;

        Edge(int src, int des) {
            this.src = src;
            this.des = des;
        }
    }
    private List<LinkedList<Integer>> lists;
    private List<Edge> bridges;
    private Boolean[] visited;
    private Boolean[] articulationPoints;
    private int[] lowLinks;
    private int[] id;
    private int time;
    private int numAP;


    private ArticulationPointAndBridge(int vertices) {
        this.numAP = 0;
        this.lists = new LinkedList<>();
        this.bridges = new LinkedList<>();
        this.visited = new Boolean[vertices];
        this.id = new int[vertices];
        this.lowLinks = new int[vertices];
        this.articulationPoints = new Boolean[vertices];
        this.bridges = new LinkedList<>();
        this.time = 0;
        for(int i = 0;i < vertices;i++){
            this.lists.add(new LinkedList<>());
            this.visited[i] = false;
            this.articulationPoints[i] = false;
        }
    }
    private void dfs(int next,int parent){
        int children = 0;
        this.visited[next] = true;
        this.id[next] = this.lowLinks[next] = this.time++;
        LinkedList<Integer> adjList = this.lists.get(next);
        for(int item : adjList){
            if(item == parent) continue;
            if(!this.visited[item]){
                children++;
                dfs(item,next);
                this.lowLinks[next] = Math.min(this.lowLinks[next], this.lowLinks[item]);
                if(parent == -1 && children > 1){
                    this.articulationPoints[next] = true;
                    this.numAP++;
                }
                else if (parent != -1 && this.id[next] <= this.lowLinks[next]) {// condition #1
                    this.articulationPoints[next] = true;
                    this.numAP++;
                }
                if (this.id[next] < this.lowLinks[item]) // condition to find a bridge
                    this.bridges.add(new Edge(next, item));
            }
            else{
               this.lowLinks[next] = Math.min(this.lowLinks[next],this.id[item]);
            }
        }
    }
    private void findArticulationPointAndBridges(){
        dfs(0,-1);
        System.out.println(this.numAP);
        for(int i = 0;i < this.articulationPoints.length;i++){
            if(this.articulationPoints[i]){
                System.out.print(i+" ");
            }
        }
        System.out.println();
        System.out.println(this.bridges.size());
        this.bridges.sort((o1, o2) -> {
            if (o1.src == o2.src) {
                return o1.des - o2.des;
            }
            return o1.src - o2.src;
        });
        for (Edge edge:this.bridges) {
            System.out.println(edge.src +" "+edge.des);
        }

    }
    private void add(int src, int des){
        this.lists.get(src).add(des);
        this.lists.get(des).add(src);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] pair = reader.readLine().split("\\s+");
        int vertices = Integer.parseInt(pair[0]);
        int edges = Integer.parseInt(pair[1]);
        ArticulationPointAndBridge articulationPointAndBridge = new ArticulationPointAndBridge(vertices);
        for(int i = 0;i < edges;i++){
            String[] edge = reader.readLine().split("\\s+");
            int src = Integer.parseInt(edge[0]);
            int des = Integer.parseInt(edge[1]);
            articulationPointAndBridge.add(src, des);
        }
        articulationPointAndBridge.findArticulationPointAndBridges();
    }
}
