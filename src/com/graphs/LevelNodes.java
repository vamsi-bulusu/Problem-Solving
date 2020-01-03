package com.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelNodes {
    private Boolean[] visited;
    private int[] level;
    private List<LinkedList<Integer>> list;


    private LevelNodes(int numNodes){
        this.visited = new Boolean[numNodes];
        this.level = new int[numNodes];
        Arrays.fill(this.visited, false);
        Arrays.fill(this.level, 0);
        this.list = new LinkedList<>();
        for(int i = 0;i < numNodes;i++){
            this.list.add(new LinkedList<>());
        }
    }

    private void add(int u, int v){
        this.list.get(u-1).add(v-1);
        this.list.get(v-1).add(u-1);
    }

    private int getNodes1(int level){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(-1);
        this.visited[0] = true;
        int currLevel = 0;
        while (!queue.isEmpty()){
            int currNode = queue.poll();
            if(currNode == -1){
                if(++currLevel == level){
                    break;
                }
                queue.add(-1);
                continue;
            }
            LinkedList<Integer> tempList = this.list.get(currNode);
            for (int integer : tempList) {
                if (!this.visited[integer]) {
                    this.visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
        return queue.size();
    }

    private int getNodes(int level){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        this.visited[0] = true;
        this.level[0] = 0;
        while (!queue.isEmpty()){
            int currNode = queue.poll();
            LinkedList<Integer> tempList = this.list.get(currNode);
            for (int integer : tempList) {
                if (!this.visited[integer]) {
                    this.level[integer] = this.level[currNode] + 1;
                    this.visited[integer] = true;
                    queue.add(integer);
                }
            }
        }
        int count = 0;
        for(int item : this.level){
            if(item == level){
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numNodes = Integer.parseInt(bufferedReader.readLine().trim());
        LevelNodes nodes = new LevelNodes(numNodes);
        for(int i = 0 ;i < numNodes-1;i++){
            String[] buffer = bufferedReader.readLine().split("\\s+");
            int U = Integer.parseInt(buffer[0]);
            int V = Integer.parseInt(buffer[1]);
            nodes.add(U, V);
        }
        int level = Integer.parseInt(bufferedReader.readLine().trim());
        System.out.println(nodes.getNodes(level-1));
        System.out.println(nodes.getNodes1(level-1));
    }
}

