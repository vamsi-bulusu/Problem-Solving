package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class UnReachableNodes {
    private Boolean[] visited;
    private List<LinkedList<Integer>> list;
    private int count;


    private UnReachableNodes(int numNodes){
        this.visited = new Boolean[numNodes];
        this.count = 0;
        Arrays.fill(this.visited, false);
        this.list = new LinkedList<>();
        for(int i = 0;i < numNodes;i++){
            this.list.add(new LinkedList<>());
        }
    }

    private void add(int u, int v){
        this.list.get(u-1).add(v-1);
        this.list.get(v-1).add(u-1);
    }

    private void dfs(int source){
        this.visited[source] = true;
        this.count++;
        for(int item : this.list.get(source)){
            if(!this.visited[item]){
                dfs(item);
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] buffer1 = bufferedReader.readLine().split("\\s+");
        int numNodes = Integer.parseInt(buffer1[0]);
        int numEdges = Integer.parseInt(buffer1[1]);
        UnReachableNodes nodes = new UnReachableNodes(numNodes);
        for(int i = 0;i < numEdges;i++){
            String[] buffer = bufferedReader.readLine().split("\\s+");
            int U = Integer.parseInt(buffer[0]);
            int V = Integer.parseInt(buffer[1]);
            nodes.add(U, V);
        }
        int source = Integer.parseInt(bufferedReader.readLine().trim());
        nodes.dfs(source-1);
        System.out.println(numNodes - nodes.count);
    }
}